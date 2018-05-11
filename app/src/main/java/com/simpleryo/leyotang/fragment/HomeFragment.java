package com.simpleryo.leyotang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.CourseSearchActivity;
import com.simpleryo.leyotang.activity.LoginActivity;
import com.simpleryo.leyotang.activity.MyMsgActivity;
import com.simpleryo.leyotang.adapter.HomeAdapter;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.push.NotificationBroadcast;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：HomeFragment.java
 * @Describe 首页fragment
 * @time 2018/3/19 11:10
 */

public class HomeFragment extends XLibraryLazyFragment {
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    HomeAdapter homeAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<MultipleItem> mItemModels = new ArrayList<>();
    ArrayList<HomeDataBean.DataBeanX.BannersBean> bannerListBeans = new ArrayList<>();
    List<HomeDataBean.DataBeanX.CoursesBeanX> hotCourseList = new ArrayList<>();
    List<HomeDataBean.DataBeanX.CourseTypesBean> courseTypetBeans = new ArrayList<>();
    List<HomeDataBean.DataBeanX.CoursesBeanX> excellentListBeans = new ArrayList<>();
    List<HomeDataBean.DataBeanX.CoursesBeanX> introductoryListBeans = new ArrayList<>();
    @ViewInject(R.id.empty_view)
    View empty_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_home, container, false);
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        simpleryoNetwork = new SimpleryoNetwork();
        //是否允许嵌套滑动
        lrecyclerview.setNestedScrollingEnabled(false);
        homeAdapter = new HomeAdapter(getActivity());
        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        lrecyclerview.setLayoutManager(layoutManager);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(homeAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.removeItemDecoration(divider);
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setHasFixedSize(true);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.forceToRefresh();
//        getCourseType();
    }
    SimpleryoNetwork simpleryoNetwork;

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            getCourseType();
        }
    };


    public void getCourseType() {
        simpleryoNetwork.getHomeCourse(getActivity(), new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                mHasLoadedOnce=true;
                HomeDataBean homeDataBean = info.getRetDetail(HomeDataBean.class);
                if (homeDataBean.getCode().equalsIgnoreCase("0")) {
                    MultipleItem item;
                    if (mItemModels != null && mItemModels.size() > 0) {
                        mItemModels.clear();
                    }
                    if (homeDataBean.getData().getBanners() != null&&homeDataBean.getData().getBanners().size()>0) {
                        if (bannerListBeans != null && bannerListBeans.size() > 0) {
                            bannerListBeans.clear();
                        }
                        item = new MultipleItem(MultipleItem.HOMEBANNER);
                        mItemModels.add(item);
                        bannerListBeans.addAll(homeDataBean.getData().getBanners());
                        homeAdapter.setImages(bannerListBeans);//设置banner

                    }
                    if (homeDataBean.getData().getCourseTypes() != null&&homeDataBean.getData().getCourseTypes().size()>0) {
                        if (courseTypetBeans != null && courseTypetBeans.size() > 0) {
                            courseTypetBeans.clear();
                        }
                        item = new MultipleItem(MultipleItem.HOMECOURSETYPE);
                        mItemModels.add(item);
                        for (HomeDataBean.DataBeanX.CourseTypesBean courseTypesBean : homeDataBean.getData().getCourseTypes()) {
                            if (courseTypetBeans.size() <= 7) {
                                courseTypetBeans.add(courseTypesBean);
                            }
                        }
                        homeAdapter.setCourseTypetBeans(courseTypetBeans);
                    }
                    if (homeDataBean.getData().getCourses() != null && homeDataBean.getData().getCourses().size() > 0) {
                        if (introductoryListBeans != null && introductoryListBeans.size() > 0) {
                            introductoryListBeans.clear();
                        }
                        if (hotCourseList != null && hotCourseList.size() > 0) {
                            hotCourseList.clear();
                        }
                        if (excellentListBeans != null && excellentListBeans.size() > 0) {
                            excellentListBeans.clear();
                        }
                        for (HomeDataBean.DataBeanX.CoursesBeanX coursesBeanList : homeDataBean.getData().getCourses()) {
                            if (coursesBeanList.getTag().getId().equalsIgnoreCase("HOT")) {
                                item = new MultipleItem(MultipleItem.HOMEHOTCOURSE);
                                mItemModels.add(item);
                                hotCourseList.add(coursesBeanList);
                                homeAdapter.setOrderListBeans(coursesBeanList);
                            }
                            if (coursesBeanList.getTag().getId().equalsIgnoreCase("EXCELLENT")) {
                                item = new MultipleItem(MultipleItem.HOMEEXCELLENT);
                                mItemModels.add(item);
                                excellentListBeans.add(coursesBeanList);
                                homeAdapter.setExcellentListBeans(coursesBeanList);
                            }
                            if (coursesBeanList.getTag().getId().equalsIgnoreCase("OFFCIAL")) {
                                item = new MultipleItem(MultipleItem.HOMEINTRODUCTORYCOURSE);
                                mItemModels.add(item);
                                introductoryListBeans.add(coursesBeanList);
                                homeAdapter.setIntroductoryListBeans(coursesBeanList);
                            }
                        }
                    }
                    homeAdapter.setDataList(mItemModels);
                } else if (homeDataBean.getCode().equalsIgnoreCase("401")) {
                    Intent intent=new Intent();
                    intent.setClass(getActivity(), NotificationBroadcast.class);
                    intent.setAction(NotificationBroadcast.ACTION_REFRESHTOKEN);
                    getActivity().sendBroadcast(intent);
//                    SharedPreferencesUtils.saveKeyString("token", "simpleryo");
//                    lrecyclerview.forceToRefresh();
                }
                lrecyclerview.refreshComplete(mItemModels.size());
                homeAdapter.notifyDataSetChanged();
                lRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                if (mItemModels!=null&&mItemModels.size()>0){
                    lrecyclerview.refreshComplete(mItemModels.size());
                    lrecyclerview.setEmptyView(empty_view);
                }else {
                    lrecyclerview.setEmptyView(empty_view);
                }
                Toast.makeText(getActivity(), info.getRetDetail(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void BusMain(BusEntity bus) {
        if (bus.getType() == 999) {
            lrecyclerview.refreshComplete(mItemModels.size());
            lRecyclerViewAdapter.notifyDataSetChanged();
        }
        if (bus.getType() == 021) {
            getCourseType();
        }
        if(bus.getType()==022){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        if (bus.getType() == 122) {
            if (isLogin) {
                HomeDataBean.DataBeanX.CoursesBeanX.CoursesBean coursesBean = bus.getCoursesBean();
                if (coursesBean.isHasCollect()) {
                    SimpleryoNetwork.disCollectCourse(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
//                                getCourseType();
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                            loadingDialog.dismiss();
                        }
                    }, coursesBean.getId());
                } else {
                    SimpleryoNetwork.collectCourse(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
//                                getCourseType();
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                            loadingDialog.dismiss();
                        }
                    }, coursesBean.getId());
                }
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }

        }
    }

    String mPageName = "HomeFragment";

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        EventBus.getDefault().post(new BusEntity(444));//通知banner暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
        MobclickAgent.onPageStart(mPageName);
        EventBus.getDefault().post(new BusEntity(555));//通知banner开始轮播
    }

    public List<String> mockData() {
        List<String> datas = new ArrayList<>();
        datas.add("http://p1.so.qhimgs1.com/bdr/_240_/t01ded39fec3bb5b85d.jpg");
        datas.add("http://p0.so.qhmsg.com/bdr/_240_/t01e33175042a193d75.jpg");
        datas.add("http://p3.so.qhimgs1.com/bdr/_240_/t01d4060a32f79916b3.jpg");
        datas.add("http://p5.so.qhimgs1.com/bdr/_240_/t0198b4fbdeae2d484b.jpg");
        return datas;
    }

    @Event(value = {R.id.iv_msg, R.id.rl_search}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                startActivity(new Intent(getActivity(), MyMsgActivity.class));
                break;
            case R.id.rl_search:
                startActivity(new Intent(getActivity(), CourseSearchActivity.class));
                break;
        }
    }


}
