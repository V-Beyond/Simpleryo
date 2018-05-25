package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.MyOrderAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.CollectionListBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的收藏页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_course_list)
public class MyCollectionActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    MyOrderAdapter myOrderAdapter;
    private List<MultipleItem> mItemModels = new ArrayList<>();
    ArrayList<CollectionListBean.DataBean> collectList = new ArrayList<>();
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    int offset=0;
    int limit=9;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText(getResources().getString(R.string.my_collections));
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(50f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myOrderAdapter = new MyOrderAdapter(MyCollectionActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(myOrderAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(true);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MyCollectionActivity.this, CourseDetailActivity.class).putExtra("courseId",collectList.get(position).getId()));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new BusEntity(002));
    }

    private OnRefreshListener onRefreshListener=new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (collectList!=null&&collectList.size()>0){
                collectList.clear();
            }
            if (mItemModels!=null&&mItemModels.size()>0){
                mItemModels.clear();
            }
            offset=0;
            limit=9;
            initCollectList();
        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset=limit+1;
            limit+=10;
            initCollectList();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 获取收藏列表
     */
    public void initCollectList() {
        SimpleryoNetwork.geCollectList(MyCollectionActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                CollectionListBean collectionListBean = info.getRetDetail(CollectionListBean.class);
                if (collectionListBean.getCode().equalsIgnoreCase("0")) {
                    if (mItemModels != null && mItemModels.size() > 0) {
                        mItemModels.clear();
                    }
                    if (collectList != null && collectList.size() > 0) {
                        collectList.clear();
                    }
                    if (collectionListBean.getData() != null && collectionListBean.getData().size() > 0) {
                        MultipleItem item;
                        collectList.addAll(collectionListBean.getData());
                        for (CollectionListBean.DataBean dataBean : collectList) {
                            item = new MultipleItem(MultipleItem.MYCOLLECTION);
                            item.setCollectDataBean(dataBean);
                            mItemModels.add(item);
                        }
                        myOrderAdapter.setDataList(mItemModels);
                    } else {
                        if (collectList.size()>0){
                            lrecyclerview.setNoMore(true);
                        }else{
                            myOrderAdapter = new MyOrderAdapter(MyCollectionActivity.this);
                            lRecyclerViewAdapter = new LRecyclerViewAdapter(myOrderAdapter);
                            lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                        }
                    }
                    lrecyclerview.refreshComplete(collectList.size());
                    myOrderAdapter.notifyDataSetChanged();
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                TextView tv_tips=mEmptyView.findViewById(R.id.tv_tips);
                tv_tips.setText("数据一不小心走丢了，请稍后回来");
                lrecyclerview.setEmptyView(mEmptyView);
            }
        },offset,limit);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 001) {//取消收率
            final CollectionListBean.DataBean dataBean = bus.getDataBean();
            final int position = Integer.parseInt(bus.getContent());
            SimpleryoNetwork.disCollectCourse(MyCollectionActivity.this, new MyBaseProgressCallbackImpl() {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                    if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                        Toast.makeText(MyCollectionActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        myOrderAdapter.getDataList().remove(position);
                        myOrderAdapter.notifyItemRemoved(position);
                        EventBus.getDefault().post(new BusEntity(002));
                    }
                }

                @Override
                public void onFailure(HttpInfo info) {
                    super.onFailure(info);
                }
            }, dataBean.getId());
        }
        if (bus.getType() == 002) {
            lrecyclerview.forceToRefresh();
        }
    }

    @Event(value = {R.id.iv_back,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyCollectionActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(MyCollectionActivity.this,MyMsgActivity.class));
                break;
        }
    }
}
