package com.simpleryo.leyotang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.CourseDetailActivity;
import com.simpleryo.leyotang.activity.LoginActivity;
import com.simpleryo.leyotang.adapter.BusinessCourseAdapter;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.CourseListBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：BusinessCourseFragment.java
 * @Describe 商家课程fragment
 * @time 2018/3/19 11:10
 */

public class BusinessCourseFragment extends XLibraryLazyFragment {
    List<CourseListBean.DataBeanX> excellentListBeans = new ArrayList<>();
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    @ViewInject(R.id.empty_view)
    View empty_view;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    BusinessCourseAdapter businessCourseAdapter;
    private List<MultipleItem> mItemModels = new ArrayList<>();
    String storeId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_business_course, container, false);
            x.view().inject(this, mMainView);
            EventBus.getDefault().register(this);
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
        businessCourseAdapter = new BusinessCourseAdapter(getActivity());
        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(businessCourseAdapter);
       lrecyclerview.setAdapter(lRecyclerViewAdapter);
       lrecyclerview.setPullRefreshEnabled(true);
       lrecyclerview.setLoadMoreEnabled(false);
       lrecyclerview.removeItemDecoration(divider);
       lrecyclerview.addItemDecoration(divider);
       lrecyclerview.setHasFixedSize(false);
       lrecyclerview.setOnRefreshListener(onRefreshListener);
       if (storeId!=null){
           lrecyclerview.forceToRefresh();
       }
       lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(View view, int position) {
               startActivity(new Intent(getActivity(), CourseDetailActivity.class).putExtra("courseId",excellentListBeans.get(position).getId()));
           }
       });
    }
    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            initExcellentCourse();
        }
    };
    /**
     * 根据商家id获取课程
     */
    public void initExcellentCourse() {
        SimpleryoNetwork.getCourse(getActivity(), new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info)  {
                mHasLoadedOnce=true;
                CourseListBean courseListBean=info.getRetDetail(CourseListBean.class);
                if (courseListBean.getCode().equalsIgnoreCase("0")){
                    if (courseListBean.getData()!=null&&courseListBean.getData().size()>0){
                        if (excellentListBeans != null && excellentListBeans.size() > 0) {
                            excellentListBeans.clear();
                        }
                        excellentListBeans.addAll(courseListBean.getData());
                        businessCourseAdapter.setDataList(excellentListBeans);
                    }else{
                        lrecyclerview.setEmptyView(empty_view);
                    }
                }
                lrecyclerview.refreshComplete(excellentListBeans.size());
            }
            @Override
            public void onFailure(HttpInfo info)   {

            }
        },storeId,"","","","","");
        lrecyclerview.setFocusable(false);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BusMain(BusEntity bus) {
        if (bus.getType()==1003){
            if (isLogin){
                String id=bus.getContent();
                boolean isCollect=bus.isCollect();
                if (isCollect){
                    SimpleryoNetwork.disCollectCourse(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                            loadingDialog.dismiss();
                        }
                    }, id);
                }else{
                    SimpleryoNetwork.collectCourse(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                        }
                    },id);
                }
            }else{
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
