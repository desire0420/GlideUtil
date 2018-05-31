package tongxunlu.com.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    View view = null;
    private RecyclerView mRecyclerView;

    List<String> list = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_list, container, false);
        mRecyclerView = view.findViewById(R.id.rv_list);
        initData();
        return view;
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());    // 设置item动画
        ShopListAdapter mAdapter = new ShopListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        list.add("http://img1.imgtn.bdimg.com/it/u=3203939691,850046686&fm=21&gp=0.jpg");
        list.add("http://img.zcool.cn/community/0166e959ac1386a801211d25c63563.jpg@1280w_1l_2o_100sh.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=4139437186,74667515&fm=21&gp=0.jpg");
        list.add("http://img.zcool.cn/community/0125fd5770dfa50000018c1b486f15.jpg@1280w_1l_2o_100sh.jpg");
        list.add("http://img2.niutuku.com/1312/0831/0831-niutuku.com-28071.jpg");
        list.add("http://img.zcool.cn/community/01626057ff29a1a84a0d304f3b5991.jpg@1280w_1l_2o_100sh.jpg");
        list.add("http://img.zcool.cn/community/01690955496f930000019ae92f3a4e.jpg@2o.jpg");
        list.add("http://img07.tooopen.com/images/20170301/tooopen_sy_200052155387.jpg");
        list.add("http://pic20.nipic.com/20120504/9174242_113752197000_2.jpg");
        list.add("http://img.zcool.cn/community/01080755c1edaf32f87528a18e9840.jpg@900w_1l_2o_100sh.jpg");
        list.add("http://img.taopic.com/uploads/allimg/140714/234975-140G4155Z571.jpg");
        list.add("http://news.cnhubei.com/ctjb/ctjbsgk/ctjb40/200808/W020080822221006461534.jpg");
        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
    }


}
