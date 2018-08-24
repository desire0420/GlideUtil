package tongxunlu.com.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import tongxunlu.com.myapplication.adapter.NumericWheelAdapter;

/**
 * Created by wangwei on 2018/2/9.
 */

public class ChooseDialog extends Dialog {
    protected View root;
    protected Context mContext;
    WheelView yearWheel;
    WheelView monthWheel;
    WheelView dayWheel;
    TextView cancel, ok;


    public ChooseDialog(Context context) {
        this(context, R.style.customDialog);
        this.mContext = context;

    }

    public ChooseDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.choose_dialog, null);
        setContentView(root);
        initView();
        initYear();
        initMonth();
        initDay();

    }

    private void initView() {
        cancel = root.findViewById(R.id.cancel);
        ok = root.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthWheel.setAdapter(new NumericWheelAdapter(1, 6));
            }
        });
    }


    private void initYear() {
        yearWheel = root.findViewById(R.id.year);
        yearWheel.setLineSpacingMultiplier(2.0f);
        yearWheel.setCyclic(false);
        yearWheel.setAdapter(new NumericWheelAdapter(1970, 2100));
        //wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        yearWheel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
            }
        });
    }

    private void initMonth() {
        monthWheel = root.findViewById(R.id.month);
        monthWheel.setCyclic(false);
        monthWheel.setLineSpacingMultiplier(2.0f);
        monthWheel.setAdapter(new NumericWheelAdapter(1, 12));
        monthWheel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });

    }

    private void initDay() {
        dayWheel = root.findViewById(R.id.day);
        dayWheel.setLineSpacingMultiplier(2.0f);
        dayWheel.setCyclic(true);
        dayWheel.setCurrentItem(2);
        dayWheel.setAdapter(new NumericWheelAdapter(1, 30));
        dayWheel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
            }
        });
    }


}
