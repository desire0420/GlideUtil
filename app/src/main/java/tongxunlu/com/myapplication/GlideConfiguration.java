package tongxunlu.com.myapplication;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * GlideConfiguration
 */

public class GlideConfiguration implements GlideModule {

    // 需要在AndroidManifest.xml中声明
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
        //手机app路径
        builder.setDiskCache(new DiskLruCacheFactory(GlideCatchUtil.getInstance().getStorageDirectory() + "/GlideDisk", diskCacheSizeBytes));

    }


    @Override
    public void registerComponents(Context context, Glide glide) {
        //nil
    }
}
