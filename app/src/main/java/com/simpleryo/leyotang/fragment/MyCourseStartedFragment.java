package com.simpleryo.leyotang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.MyCourseDetailActivity;
import com.simpleryo.leyotang.adapter.MyStartedCourseAdapter;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BuyedCouseListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * @ClassNname：CourseFragment.java
 * @Describe  课程fragment
 * @author huanglei
 * @time 2018/3/19 11:10
 */

public class MyCourseStartedFragment extends XLibraryLazyFragment {

    ArrayList<BuyedCouseListBean.DataBeanX> orderListBeans=new ArrayList<>();
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    MyStartedCourseAdapter myStartedCourseAdapter;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    String status;
    int offset=0;
    int limit=9;
    String userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_started_course, container, false);
            x.view().inject(this, mMainView);
            isPrepared = true;
            status=getArguments().getString("status");
            userId= SharedPreferencesUtils.getKeyString("userId");
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
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lrecyclerview.setLayoutManager(layoutManager);
        myStartedCourseAdapter = new MyStartedCourseAdapter(getActivity());
         lRecyclerViewAdapter = new LRecyclerViewAdapter(myStartedCourseAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setLoadMoreEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
        lrecyclerview.forceToRefresh();
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), MyCourseDetailActivity.class).putExtra("id",orderListBeans.get(position).getOrderId()));
            }
        });
    }
    private OnRefreshListener onRefreshListener=new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (orderListBeans!=null&&orderListBeans.size()>0){
                orderListBeans.clear();
            }
            offset=0;
            limit=9;
            initExcellentCourse();
        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset=limit+1;
            limit+=10;
            initExcellentCourse();
        }
    };

    public void initExcellentCourse() {
        SimpleryoNetwork.getBuyAllCourse(getActivity(),new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                mHasLoadedOnce=true;
                BuyedCouseListBean buyedCouseListBean=info.getRetDetail(BuyedCouseListBean.class);
                if (buyedCouseListBean.getCode().equalsIgnoreCase("0")){
                    if(buyedCouseListBean.getData()!=null&&buyedCouseListBean.getData().size()>0){
                        orderListBeans.addAll(buyedCouseListBean.getData());
                        myStartedCourseAdapter.setDataList(orderListBeans);
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
                loadingDialog.dismiss();
                lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
            }
        },userId,status,offset,limit);
        lrecyclerview.setFocusable(false);
    }
}
