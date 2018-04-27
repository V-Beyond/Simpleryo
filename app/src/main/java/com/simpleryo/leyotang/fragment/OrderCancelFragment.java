package com.simpleryo.leyotang.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.MyOrderAdapter;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.bean.OrderListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyFragment.java
 * @Describe 个人中心fragment
 * @time 2018/3/19 11:10
 */

public class OrderCancelFragment extends XLibraryLazyFragment {
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    MyOrderAdapter myOrderAdapter;
    private List<MultipleItem> mItemModels = new ArrayList<>();
    ArrayList<OrderListBean.DataBean> orderListBeans=new ArrayList<>();
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    private ProgressDialog dialog;
    String status="CANCALLED";//订单状态
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_order_list, container, false);
            x.view().inject(this, mMainView);
            isPrepared = true;
            EventBus.getDefault().register(this);
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mMainView.getParent();
        if (parent != null) {
            parent.removeView(mMainView);
        }
        return mMainView;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(50f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myOrderAdapter = new MyOrderAdapter(getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(myOrderAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(true);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
        lrecyclerview.forceToRefresh();
    }

    int offset=0;
    int limit=9;
    public void initData() {
        SimpleryoNetwork.getOrders(getActivity(),new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                mHasLoadedOnce=true;
                MultipleItem  item;
                OrderListBean orderListBean=info.getRetDetail(OrderListBean.class);
                if (orderListBean.getCode().equalsIgnoreCase("0")){
                    if(orderListBean.getData()!=null&&orderListBean.getData().size()>0){
                        if (orderListBeans!=null&&orderListBeans.size()>0){
                            orderListBeans.clear();
                        }
                        if (mItemModels!=null&&mItemModels.size()>0){
                            mItemModels.clear();
                        }
                        orderListBeans.addAll(orderListBean.getData());
                        for (OrderListBean.DataBean dataBean:orderListBeans){
                            item = new MultipleItem(MultipleItem.ORDER);
                            item.setOrderListBean(dataBean);
                            mItemModels.add(item);
                        }
                        myOrderAdapter.setDataList(mItemModels);
                        lRecyclerViewAdapter.notifyDataSetChanged();
                    }else{
                        if (orderListBeans.size()>0){
                            lrecyclerview.setNoMore(true);
                        }else{
                            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                        }
                    }
                }
                lrecyclerview.refreshComplete(orderListBeans.size());
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
            }
        },status,offset,limit);
    }
    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (orderListBeans!=null&&orderListBeans.size()>0){
                orderListBeans.clear();
            }
            if (mItemModels!=null&&mItemModels.size()>0){
                mItemModels.clear();
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType()==111){
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
