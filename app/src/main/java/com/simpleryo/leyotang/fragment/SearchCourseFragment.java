package com.simpleryo.leyotang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.simpleryo.leyotang.activity.CourseDetailActivity;
import com.simpleryo.leyotang.activity.CourseSearchActivity;
import com.simpleryo.leyotang.activity.LoginActivity;
import com.simpleryo.leyotang.adapter.SearchHotCourseAdapter;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.CourseListBean;
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
 * @ClassNname：CourseFragment.java
 * @Describe 搜索课程fragment
 * @time 2018/3/19 11:10
 */

public class SearchCourseFragment extends XLibraryLazyFragment {

    List<CourseListBean.DataBeanX> hotCourseList = new ArrayList<>();
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    SearchHotCourseAdapter searchHotCourseAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    int offset = 0;
    int limit = 9;
    DividerDecoration divider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_order_list, container, false);
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
        divider = new DividerDecoration.Builder(getActivity())
                .setHeight(10f)
                .setColorResource(R.color.color_transparent)
                .build();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lrecyclerview.setLayoutManager(layoutManager);
        searchHotCourseAdapter = new SearchHotCourseAdapter(getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(searchHotCourseAdapter);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), CourseDetailActivity.class).putExtra("courseId", hotCourseList.get(position).getId()));
            }
        });
        //监听输入框搜索事件
        CourseSearchActivity.edittext_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    courseName=v.getText().toString().trim();
                    if(!courseName.isEmpty()){
                        searchCourse(courseName);
                    }else{
                        Toast.makeText(getActivity(),"请输入要搜索的内容",Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        String edittextContent = CourseSearchActivity.edittext_search.getText().toString();
        if (!edittextContent.isEmpty()){
            searchCourse(edittextContent);
        }

        CourseSearchActivity.edittext_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                courseName=charSequence.toString().trim();
//                if(!courseName.isEmpty()){
                    searchCourse(courseName);
//                }else{
//                    if (hotCourseList != null && hotCourseList.size() > 0) {
//                        hotCourseList.clear();
//                    }
//                    lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
//                    lrecyclerview.setAdapter(lRecyclerViewAdapter);
//                    lrecyclerview.refreshComplete(hotCourseList.size());
//                    searchHotCourseAdapter.notifyDataSetChanged();
//                    lRecyclerViewAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    String courseName;
    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (hotCourseList != null && hotCourseList.size() > 0) {
                hotCourseList.clear();
            }
            offset = 0;
            limit = 9;
            searchCourse(courseName);
        }
    };
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset = limit + 1;
            limit += 10;
            searchCourse(courseName);
        }
    };

    /**
     * 搜索课程
     * @param name
     */
    public void searchCourse(String name) {
        if (!name.isEmpty()) {
            SimpleryoNetwork.getCourse(getActivity(), new MyBaseProgressCallbackImpl() {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    CourseListBean courseListBean = info.getRetDetail(CourseListBean.class);
                    if (courseListBean.getCode().equalsIgnoreCase("0")) {
                        if (courseListBean.getData() != null && courseListBean.getData().size() > 0) {
                            if (hotCourseList != null && hotCourseList.size() > 0) {
                                hotCourseList.clear();
                            }
                            hotCourseList.addAll(courseListBean.getData());
                            searchHotCourseAdapter.setDataList(hotCourseList);
                            lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            lrecyclerview.setPullRefreshEnabled(true);
                            lrecyclerview.setOnRefreshListener(onRefreshListener);
                            lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
                            lrecyclerview.removeItemDecoration(divider);
                            lrecyclerview.addItemDecoration(divider);
                        } else {
                            if (hotCourseList.size()>0){
                                lrecyclerview.setNoMore(true);
                            }else{
                                lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                                lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            }
                        }
                        lrecyclerview.refreshComplete(hotCourseList.size());
                        searchHotCourseAdapter.notifyDataSetChanged();
                        lRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }, "", name, "", "");
        }else{
            searchHotCourseAdapter.clear();
            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
            lrecyclerview.setAdapter(lRecyclerViewAdapter);
            searchHotCourseAdapter.notifyDataSetChanged();
            lRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 收藏与取消收藏课程
     * @param bus
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 33) {
            if (isLogin) {
                String courseId = bus.getContent();
                boolean isCollect = bus.isCollect();
                if (isCollect) {//取消收藏
                    SimpleryoNetwork.disCollectCourse(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
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
                    }, courseId);
                } else {//收藏
                    SimpleryoNetwork.collectCourse(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, courseId);
                }

            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
