package com.simpleryo.leyotang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.BusinessHomeActivty;
import com.simpleryo.leyotang.activity.CourseSearchActivity;
import com.simpleryo.leyotang.adapter.StoreListAdapter;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.StoreListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * @ClassNname：CourseFragment.java
 * @Describe  课程fragment
 * @author huanglei
 * @time 2018/3/19 11:10
 */

public class SearchStoreFragment extends XLibraryLazyFragment {

    ArrayList<StoreListBean.DataBean> orderListBeans=new ArrayList<>();
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    StoreListAdapter storeListAdapter;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    int offset=0;
    int limit=9;
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
    GridItemDecoration divider;
    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
         divider = new GridItemDecoration.Builder(getActivity())
                .setHorizontal(30f)
                .setVertical(30f)
                .setColorResource(R.color.color_transparent)
                .build();

        lrecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        storeListAdapter = new StoreListAdapter(getActivity());
         lRecyclerViewAdapter = new LRecyclerViewAdapter(storeListAdapter);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), BusinessHomeActivty.class).putExtra("storeId",orderListBeans.get(position).getStoreInfo().getId()));
            }
        });
        String edittextContent=CourseSearchActivity.edittext_search.getText().toString();
        initExcellentCourse(edittextContent);
        CourseSearchActivity.edittext_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                storeName=charSequence.toString().trim();
                initExcellentCourse(storeName);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    String storeName;
    private OnRefreshListener onRefreshListener=new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (orderListBeans!=null&&orderListBeans.size()>0){
                orderListBeans.clear();
            }
            offset=0;
            limit=9;
            initExcellentCourse(storeName);
        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset=limit+1;
            limit+=10;
            initExcellentCourse(storeName);
        }
    };

    public void initExcellentCourse(String name) {
        if (!name.equalsIgnoreCase("")){
            SimpleryoNetwork.getStoreListByName(getActivity(),new MyBaseProgressCallbackImpl(){
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    StoreListBean orderListBean=info.getRetDetail(StoreListBean.class);
                    if (orderListBean.getCode().equalsIgnoreCase("0")){
                        if (orderListBeans!=null&&orderListBeans.size()>0){
                            orderListBeans.clear();
                        }
                        storeListAdapter.clear();
                        if(orderListBean.getData()!=null&&orderListBean.getData().size()>0){
                            orderListBeans.addAll(orderListBean.getData());
                            storeListAdapter.setDataList(orderListBeans);
                            lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            lrecyclerview.setPullRefreshEnabled(true);
                            lrecyclerview.setLoadMoreEnabled(true);
                            lrecyclerview.removeItemDecoration(divider);
                            lrecyclerview.addItemDecoration(divider);
                            lrecyclerview.setOnRefreshListener(onRefreshListener);
                            lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
                        }else{
                            if (orderListBeans.size()>0){
                                lrecyclerview.setNoMore(true);
                            }else{
                                lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                                lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            }
                        }
                    }
                    lrecyclerview.refreshComplete(orderListBeans.size());
                    storeListAdapter.notifyDataSetChanged();
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(HttpInfo info) {
                    super.onFailure(info);
                    loadingDialog.dismiss();
                    lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                }
            },name);
        }else{
            storeListAdapter.clear();
            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
            lrecyclerview.setAdapter(lRecyclerViewAdapter);
            storeListAdapter.notifyDataSetChanged();
            storeListAdapter.notifyDataSetChanged();
        }
        lrecyclerview.setFocusable(false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType()==301){
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
