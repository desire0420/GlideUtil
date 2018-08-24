package tongxunlu.com.myapplication.adapter;

import android.text.TextUtils;

/**
 * Created by wangwei on 2018/8/24.
 */

public class StringHelp {

    /*字符串转Double类型*/
    public static double parseDouble(String str) {
        double j;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            j = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            j = 0;
        }
        return j;
    }
}
