package tongxunlu.com.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class ThreadFragment extends Fragment {
    View view = null;
    private Button btnCatchSize, btnCatchCleanDisk, btnCatchCleanMemory, btnCatchCleanDiskSelf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.three, container, false);
        btnCatchSize = view.findViewById(R.id.btn_catch_size);
        btnCatchCleanDisk = view.findViewById(R.id.btn_catch_clean_disk);
        btnCatchCleanMemory = view.findViewById(R.id.btn_catch_clean_memory);
        btnCatchCleanDiskSelf = view.findViewById(R.id.btn_catch_clean_disk_self);
        setListener();
        return view;
    }

    private void setListener() {
        // Glide磁盘缓存大小
        btnCatchSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Glide磁盘缓存大小:" + GlideCatchUtil.getInstance().getCacheSize());
            }
        });

        // 删除文件夹方法在主线程执行
        btnCatchCleanDisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlideCatchUtil.getInstance().cleanCatchDisk()) {
                    showToast("清除Glide磁盘缓存成功，删除文件夹方法");
                } else {
                    showToast("清除Glide磁盘缓存失败，删除文件夹方法");
                }
            }
        });


        // 清除内存缓存
        btnCatchCleanMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlideCatchUtil.getInstance().clearCacheMemory()) {
                    showToast("清除Glide内存缓存成功");
                } else {
                    showToast("清除Glide内存缓存失败");
                }
            }
        });

        // Glide自带删除磁盘缓存方法在线程中执行
        btnCatchCleanDiskSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlideCatchUtil.getInstance().clearCacheDiskSelf()) {
                    showToast("清除Glide磁盘缓存成功，Glide自带方法");
                } else {
                    showToast("清除Glide磁盘缓存失败，Glide自带方法");
                }
            }
        });


    }


    private void showToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }
}