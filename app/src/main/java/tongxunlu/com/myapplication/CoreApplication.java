package tongxunlu.com.myapplication;

import android.app.Application;

/**
 * Created by wangwei on 2017/3/17.
 */

public class CoreApplication extends Application {
    private String TAG = this.getClass().getSimpleName() + "-ww";

    private static CoreApplication sInstance;

    public CoreApplication() {
    }

    public void onCreate() {
        super.onCreate();
        if (sInstance == null) {
            sInstance = this;
        }

    }

    public static CoreApplication getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Application has not been created");
        }
        return sInstance;
    }


}
