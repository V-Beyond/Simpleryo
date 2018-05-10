package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.SearchHotCourseAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.CourseListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
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
 * @Describe 课程列表页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_course_list)
public class CourseListActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    SearchHotCourseAdapter searchHotCourseAdapter;
    List<CourseListBean.DataBeanX> hotCourseList = new ArrayList<>();
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    String type;
    String tagId1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        type = getIntent().getStringExtra("type");
        tagId1 = getIntent().getStringExtra("tagId1");
        tv_name.setText(type);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(50f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        searchHotCourseAdapter = new SearchHotCourseAdapter(CourseListActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(searchHotCourseAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(CourseListActivity.this, CourseDetailActivity.class).putExtra("courseId", hotCourseList.get(position).getId()));
            }
        });
        lrecyclerview.forceToRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 33) {
            if (isLogin) {
                String courseId = bus.getContent();
                boolean isCollect = bus.isCollect();
                if (isCollect) {//取消收藏
                    SimpleryoNetwork.disCollectCourse(CourseListActivity.this, new MyBaseProgressCallbackImpl(CourseListActivity.this) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(CourseListActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CourseListActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                            loadingDialog.dismiss();
                        }
                    }, courseId);
                } else {//收藏
                    SimpleryoNetwork.collectCourse(CourseListActivity.this, new MyBaseProgressCallbackImpl(CourseListActivity.this) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(CourseListActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CourseListActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, courseId);
                }
            } else {
                startActivity(new Intent(CourseListActivity.this, LoginActivity.class));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            initData();
        }
    };

    public void initData() {
        SimpleryoNetwork.getCourse(CourseListActivity.this, new MyBaseProgressCallbackImpl() {
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
                    } else {
                        lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                    }
                    lrecyclerview.refreshComplete(hotCourseList.size());
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                TextView textView = mEmptyView.findViewById(R.id.tv_tips);
                textView.setText("数据一不小心走丢了，请稍后回来");
                lrecyclerview.setEmptyView(mEmptyView);
            }
        }, "", "", tagId1, "");
    }

    @Event(value = {R.id.iv_back,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CourseListActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(CourseListActivity.this,MyMsgActivity.class));
                break;
        }
    }
}
