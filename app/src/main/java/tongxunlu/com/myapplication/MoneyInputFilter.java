package tongxunlu.com.myapplication;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.util.Log;

import tongxunlu.com.myapplication.adapter.StringHelp;

/**
 * /**
 * <p>
 * 描述   ：金额输入字体监听类，限制小数点后输入位数
 * <p>
 * 默认限制小数点2位
 * 默认第一位输入小数点时，转换为0.
 * 如果起始位置为0,且第二位跟的不是".",则无法后续输入
 * Created by wangwei on 2018/3/30.
 */

public class MoneyInputFilter extends DigitsKeyListener {
    private static final String TAG = "MoneyInputFilter";

    public MoneyInputFilter() {
        super(false, true);
    }

    private int digits = 2;

    public MoneyInputFilter setDigits(int d) {
        digits = d;
        return this;
    }

    /**
     * source 当前输入的值
     * dstart  输入之前的总长度
     * dest  输入之前的内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        CharSequence out = super.filter(source, start, end, dest, dstart, dend);


        Log.d(TAG, "-------source:" + source);
        Log.d(TAG, "-------start:" + start);
        Log.d(TAG, "-------end:" + end);
        Log.d(TAG, "-------dest:" + dest);
        Log.d(TAG, "-------dstart:" + dstart);
        Log.d(TAG, "-------dend:" + dend);


        // if changed, replace the source
        if (out != null) {
            source = out;
            start = 0;
            end = out.length();
        }

        int len = end - start;
        // if deleting, source is empty
        // and deleting can't break anything
        if (len == 0) {
            return source;
        }

        //最大只能输入  9999999
        if (StringHelp.parseDouble(dest.toString()) >= 9999999) {
            return "";
        }

        //当输入了7位数字后 还不包含小数点， 后面只能输入点 不能输入数字
        if (dest.toString().length() == 7 && !dest.toString().contains(".") && !source.toString().equals(".")) {
            return "";
        }

        //以点开始的时候，自动在前面添加0
        if (source.toString().equals(".") && dstart == 0) {
            return "0.";
        }

        //如果起始位置为0,且第二位跟的不是".",则无法后续输入
        if (!source.toString().equals(".") && dest.toString().equals("0")) {
            return "";
        }


        int dlen = dest.length();
        // 小数点的位置 .
        for (int i = 0; i < dstart; i++) {
            if (dest.charAt(i) == '.') {
                // being here means, that a number has
                // been inserted after the dot
                // check if the amount of digits is right
                return (dlen - (i + 1) + len > digits) ?
                        "" :
                        new SpannableStringBuilder(source, start, end);
            }
        }

        for (int i = start; i < end; ++i) {
            if (source.charAt(i) == '.') {
                // being here means, dot has been inserted
                // check if the amount of digits is right
                if ((dlen - dend) + (end - (i + 1)) > digits)
                    return "";
                else
                    break;  // return new SpannableStringBuilder(source, start, end);
            }
        }


        Log.d(TAG, "-------return:" + new SpannableStringBuilder(source, start, end).toString());
        Log.d(TAG, "-----------------------");

        // if the dot is after the inserted part,
        // nothing can break
        return new SpannableStringBuilder(source, start, end);
    }
}