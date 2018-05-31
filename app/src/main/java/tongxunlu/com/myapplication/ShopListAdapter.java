package tongxunlu.com.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * 门店选择列表Adapter
 */
public class ShopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Activity mContext;
    private final LayoutInflater mLayoutInflater;

    public ArrayList<String> mDatas = new ArrayList<>();

    public void addItem(String item) {
        mDatas.add(item);
    }

    public void addAll(List<String> data) {
        mDatas.addAll(data);
    }


    public void clear() {
        mDatas.clear();
    }

    public interface OnItemClickLitener {
        void onItemClick(String item);
    }

    private ShopListAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(ShopListAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public ShopListAdapter(Activity context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.layout_item, parent, false);
        MyViewHolder masterViewHolder = new MyViewHolder(inflate);
        return masterViewHolder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final String oj = mDatas.get(position);
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(oj);
                }
            });
        }
        Log.w("TAG", "oj------->" + oj);
        Glide.with(mContext).load(oj).asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Log.w("TAG", "secd------->" + resource.hashCode());
                ((MyViewHolder) holder).image.setImageBitmap(resource);
            }
        }); //方法中设置asBitmap可以设置回调类型


    }


    @Override
    public int getItemCount() {
        return mDatas.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View itemView;

        private ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image = itemView.findViewById(R.id.image);

        }

    }


}



