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
import com.simpleryo.leyotang.adapter.CourseListTypeAdapter;
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

import static com.simpleryo.leyotang.bean.CourseListBean.*;

/**
 * @author huanglei
 * @ClassNname：MyFragment.java
 * @Describe 个人中心fragment
 * @time 2018/3/19 11:10
 */

public class HotCourseFragment extends XLibraryLazyFragment {
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    CourseListTypeAdapter courseListTypeAdapter;
    List<CourseListBean.DataBeanX> hotCourseList = new ArrayList<>();
    private List<MultipleItem> mItemModels = new ArrayList<>();
    @ViewInject(R.id.empty_view)
    private View mEmptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_course_list, container, false);
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
                .setHeight(20f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        courseListTypeAdapter = new CourseListTypeAdapter(getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(courseListTypeAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), CourseDetailActivity.class).putExtra("courseId", hotCourseList.get(position).getId()));
            }
        });
        lrecyclerview.forceToRefresh();
//        initData();
    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            initData();
        }
    };

    public void initData() {
        SimpleryoNetwork.getCourse(getActivity(), new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                mHasLoadedOnce = true;
                MultipleItem multipleItem;

                CourseListBean courseListBean = info.getRetDetail(CourseListBean.class);
                if (courseListBean.getCode().equalsIgnoreCase("0")) {
                    if (courseListBean.getData() != null && courseListBean.getData().size() > 0) {
                        if (hotCourseList != null && hotCourseList.size() > 0) {
                            hotCourseList.clear();
                        }
                        if (mItemModels != null && mItemModels.size() > 0) {
                            mItemModels.clear();
                        }
                        hotCourseList.addAll(courseListBean.getData());
                        for (DataBeanX dataBean : hotCourseList) {
                            multipleItem = new MultipleItem(MultipleItem.HOMEHOTCOURSE);
                            multipleItem.setCourseListBean(dataBean);
                            mItemModels.add(multipleItem);
                        }
                        courseListTypeAdapter.setDataList(mItemModels);
                    } else {
                        lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                    }
                    lrecyclerview.refreshComplete(hotCourseList.size());
                }
            }
        }, "", "", "", "HOT","","");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BusMain(BusEntity bus) {
        if (bus.getType()==131){
            if (isLogin){
                CourseListBean.DataBeanX courseListBean=bus.getCourseListBean();
                if (courseListBean.isHasCollect()){
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
                    }, courseListBean.getId());
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
                    }, courseListBean.getId());
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
