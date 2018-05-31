package tongxunlu.com.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

public class SecondFragment extends Fragment {
    View view = null;
    MainActivity mainActivity;
    String url = "http://img.zcool.cn/community/010f87596f13e6a8012193a363df45.jpg@1280w_1l_2o_100sh.jpg";
    ImageView demo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        view = inflater.inflate(R.layout.one, container, false);
        demo = view.findViewById(R.id.demo);

        Glide.with(getActivity()).load(url).asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Log.w("TAG", "secd------->" + resource.hashCode());
                demo.setImageBitmap(resource);
            }
        }); //方法中设置asBitmap可以设置回调类型


        Glide.with(getActivity()).load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        // 可替换成进度条
                        Toast.makeText(getActivity(), "图片加载失败", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        // 图片加载完成，取消进度条
                        Toast.makeText(getActivity(), "图片加载成功", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }).error(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(demo);


        return view;
    }


}
