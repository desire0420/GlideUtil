package tongxunlu.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    TextView edit;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient.retrofit().create(ApiStores.class).loadDataByRetrofitRxJava("101220602")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        });

    }

    //创建一个下游  观察者Observer
    Observer<MainModel> observer = new Observer<MainModel>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(MainModel value) {
            Log.w(TAG, "" + value.getWeatherinfo().getCity());
            Log.d(TAG, "observer thread is : " + Thread.currentThread().getName());

        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onComplete() {
            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();


        }
    };


}
