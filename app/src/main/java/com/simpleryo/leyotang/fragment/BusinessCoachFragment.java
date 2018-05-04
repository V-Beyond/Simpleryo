package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.BusinessCoachAdapter;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.CoachListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;

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

public class BusinessCoachFragment extends XLibraryLazyFragment {

    List<CoachListBean.DataBean> excellentListBeans = new ArrayList<>();
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    @ViewInject(R.id.empty_view)
    View empty_view;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    BusinessCoachAdapter businessCoachAdapter;
    String storeId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_business_coach, container, false);
            x.view().inject(this, mMainView);
            isPrepared = true;
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
        storeId=getArguments().getString("storeId");
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        //是否允许嵌套滑动
        lrecyclerview.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lrecyclerview.setLayoutManager(layoutManager);
        businessCoachAdapter = new BusinessCoachAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(businessCoachAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setPullRefreshEnabled(false);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.removeItemDecoration(divider);
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setHasFixedSize(false);
        initExcellentCourse();
        mHasLoadedOnce=true;
    }
    public void initExcellentCourse() {
        SimpleryoNetwork.getCoachesByStoreId(getActivity(), new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info)  {
                CoachListBean coachListBean=info.getRetDetail(CoachListBean.class);
                if (coachListBean.getCode().equalsIgnoreCase("0")){
                    if (coachListBean.getData()!=null&&coachListBean.getData().size()>0){
                        excellentListBeans.addAll(coachListBean.getData());
                        businessCoachAdapter.setDataList(excellentListBeans);
                    }else{
                        lrecyclerview.setEmptyView(empty_view);
                    }
                }
            }
            @Override
            public void onFailure(HttpInfo info)   {

            }
        },storeId);
        lrecyclerview.setFocusable(false);
    }
}
