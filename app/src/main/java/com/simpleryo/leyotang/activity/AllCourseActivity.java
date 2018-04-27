package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.AllCoureseAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.HomeDataBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：MyCourse.java
 * @Describe 所有课程页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_all_course)
public class AllCourseActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    AllCoureseAdapter allCoureseAdapter;
    List<HomeDataBean.DataBeanX.CourseTypesBean> courseTypetBeans = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("全部课程");
        GridItemDecoration divider = new GridItemDecoration.Builder(this)
                .setHorizontal(30f)
                .setVertical(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        GridLayoutManager layoutManager = new GridLayoutManager(AllCourseActivity.this,2);
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(layoutManager);
        allCoureseAdapter = new AllCoureseAdapter(AllCourseActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(allCoureseAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(false);
        getCourseType();
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(AllCourseActivity.this,CourseListActivity.class);
                intent.putExtra("type",courseTypetBeans.get(position).getName());
                intent.putExtra("tagId1",courseTypetBeans.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public void getCourseType(){
        SimpleryoNetwork.getHomeCourse(AllCourseActivity.this,new MyBaseProgressCallbackImpl(AllCourseActivity.this){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                HomeDataBean homeDataBean=info.getRetDetail(HomeDataBean.class);
                if (homeDataBean.getCode().equalsIgnoreCase("0")){
                    if (courseTypetBeans != null && courseTypetBeans.size() > 0) {
                        courseTypetBeans.clear();
                    }
                    courseTypetBeans.addAll(homeDataBean.getData().getCourseTypes());
                    allCoureseAdapter.setDataList(courseTypetBeans);
                }
            }
        });
    }


    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(AllCourseActivity.this);
                break;
        }
    }
}
