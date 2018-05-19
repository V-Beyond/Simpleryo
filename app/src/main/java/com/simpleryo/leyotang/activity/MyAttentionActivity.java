package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.MyOrderAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.bean.StoreFollowBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：MyCourse.java
 * @Describe 我的关注页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_course_list)
public class MyAttentionActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    @ViewInject(R.id.empty_view)
            View empty_view;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    MyOrderAdapter myOrderAdapter;
    private List<MultipleItem> mItemModels = new ArrayList<>();
    ArrayList<StoreFollowBean.DataBean> storeList = new ArrayList<>();
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    int offset=0;
    int limit=9;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("我的关注");
        GridItemDecoration divider = new GridItemDecoration.Builder(this)
               .setHorizontal(30f)
                .setVertical(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        myOrderAdapter = new MyOrderAdapter(MyAttentionActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(myOrderAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(true);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.forceToRefresh();
    }
    private OnRefreshListener onRefreshListener=new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (mItemModels != null && mItemModels.size() > 0) {
                mItemModels.clear();
            }
            if (storeList != null && storeList.size() > 0) {
                storeList.clear();
            }
            offset=0;
            limit=9;
            initData();
        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset=limit+1;
            limit+=10;
            initData();
        }
    };
    /**
     * 获取我的关注列表
     */
    public void initData() {
        SimpleryoNetwork.getStoresList(MyAttentionActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                StoreFollowBean storeFollowBean=info.getRetDetail(StoreFollowBean.class);
                if (storeFollowBean.getCode().equalsIgnoreCase("0")){
                    if (storeFollowBean.getData()!=null&&storeFollowBean.getData().size()>0){
                        MultipleItem item;
                        if (mItemModels != null && mItemModels.size() > 0) {
                            mItemModels.clear();
                        }
                        if (storeList != null && storeList.size() > 0) {
                            storeList.clear();
                        }
                        storeList.addAll(storeFollowBean.getData());
                        for (StoreFollowBean.DataBean dataBean : storeList) {
                            if (dataBean!=null){
                                item = new MultipleItem(MultipleItem.MYATTENTION);
                                item.setStoreDataBean(dataBean);
                                mItemModels.add(item);
                            }
                        }
                        myOrderAdapter.setDataList(mItemModels);
                    }else{
                        if (storeList.size()>0){
                            lrecyclerview.setNoMore(true);
                        }else{
                            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                        }
                    }
                }
                lrecyclerview.refreshComplete(storeList.size());
                lRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                TextView textView = mEmptyView.findViewById(R.id.tv_tips);
                textView.setText("数据一不小心走丢了，请稍后回来");
                lrecyclerview.setEmptyView(mEmptyView);
            }
        },offset,limit);

    }
    @Event(value = {R.id.iv_back,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyAttentionActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(MyAttentionActivity.this,MyMsgActivity.class));
                break;
        }
    }
}
