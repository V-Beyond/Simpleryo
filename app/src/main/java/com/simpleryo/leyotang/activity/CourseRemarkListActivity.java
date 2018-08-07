package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.RemarkListAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.CourseRemarkListBean;
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
@ContentView(R.layout.activity_course_remark_list)
public class CourseRemarkListActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;

    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;

    LRecyclerViewAdapter lRecyclerViewAdapter;
    RemarkListAdapter remarkListAdapter;
    List<CourseRemarkListBean.DataBean> dataBeanArrayList = new ArrayList<>();

    String courseId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        courseId = getIntent().getStringExtra("courseId");
        tv_name.setText("全部评价");
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(1f)
                .setColorResource(R.color.color_a7a7a7)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(CourseRemarkListActivity.this));
        remarkListAdapter = new RemarkListAdapter(CourseRemarkListActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(remarkListAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.forceToRefresh();
    }
    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (dataBeanArrayList!=null&&dataBeanArrayList.size()>0){
                dataBeanArrayList.clear();
            }
            getRemarkList();
        }
    };
    public void getRemarkList(){
        SimpleryoNetwork.comments(CourseRemarkListActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                CourseRemarkListBean courseRemarkListBean = info.getRetDetail(CourseRemarkListBean.class);
                if (courseRemarkListBean.getCode().equalsIgnoreCase("0")) {
                    if (courseRemarkListBean.getData()!=null&&courseRemarkListBean.getData().size()>0){
                        dataBeanArrayList.addAll(courseRemarkListBean.getData());
                        remarkListAdapter.setDataList(dataBeanArrayList);
                    }else {
                        lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                    }
                    lrecyclerview.refreshComplete(dataBeanArrayList.size());
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        },courseId);
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
                    SimpleryoNetwork.disCollectCourse(CourseRemarkListActivity.this, new MyBaseProgressCallbackImpl(CourseRemarkListActivity.this) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(CourseRemarkListActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CourseRemarkListActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                            loadingDialog.dismiss();
                        }
                    }, courseId);
                } else {//收藏
                    SimpleryoNetwork.collectCourse(CourseRemarkListActivity.this, new MyBaseProgressCallbackImpl(CourseRemarkListActivity.this) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(CourseRemarkListActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CourseRemarkListActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, courseId);
                }
            } else {
                startActivity(new Intent(CourseRemarkListActivity.this, LoginActivity.class));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Event(value = {R.id.iv_back,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CourseRemarkListActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(CourseRemarkListActivity.this,MyMsgActivity.class));
                break;
        }
    }
}
