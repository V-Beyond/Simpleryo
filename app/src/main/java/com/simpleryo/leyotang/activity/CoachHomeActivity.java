package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.CourseListTypeAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.CoachDetailBean;
import com.simpleryo.leyotang.bean.CourseListBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.squareup.picasso.Picasso;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 课程分类页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_coach_home)
public class CoachHomeActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_coach_img)
    ImageView iv_coach_img;
    @ViewInject(R.id.tv_coach_name)
    TextView tv_coach_name;
    @ViewInject(R.id.tv_coach_years)
    TextView tv_coach_years;
    @ViewInject(R.id.tv_des)
    TextView tv_des;
    @ViewInject(R.id.tv_store_status)
    TextView tv_store_status;
    String coachId;
    @ViewInject(R.id.ll_skills)
    LinearLayout ll_skills;
    @ViewInject(R.id.tv_goodreviewrate)
    TextView tv_goodreviewrate;
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    @ViewInject(R.id.tv_course_count)
            TextView tv_course_count;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    CourseListTypeAdapter courseListTypeAdapter;
    List<CourseListBean.DataBeanX> hotCourseList = new ArrayList<>();
    private List<MultipleItem> mItemModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("个人主页");
        coachId = getIntent().getStringExtra("coachId");
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(50f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        lrecyclerview.setLayoutManager(gridLayoutManager);
        courseListTypeAdapter = new CourseListTypeAdapter(CoachHomeActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(courseListTypeAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(false);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(CoachHomeActivity.this, CourseDetailActivity.class).putExtra("courseId", hotCourseList.get(position).getId()));
            }
        });
        getCoacheInfoById();
        initData();
    }

    String storeId;

    public void getCoacheInfoById() {
        SimpleryoNetwork.getCoacheInfoById(this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                CoachDetailBean coachDetailBean = info.getRetDetail(CoachDetailBean.class);
                if (coachDetailBean.getCode().equalsIgnoreCase("0")) {
                    storeId = coachDetailBean.getData().getStoreId();
                    Picasso.with(CoachHomeActivity.this).load(coachDetailBean.getData().getAvatarUrl()).transform(raduisTransformation).into(iv_coach_img);
                    tv_coach_name.setText(coachDetailBean.getData().getNickName());
                    tv_coach_years.setText("教龄：" + coachDetailBean.getData().getWorkLife());
                    tv_des.setText(coachDetailBean.getData().getIntro());
                    tv_goodreviewrate.setText("好评率："+coachDetailBean.getData().getPoint()+"%");
                    tv_store_status.setText(coachDetailBean.getData().getStoreName());
                    if (coachDetailBean.getData().getSkills()!=null&&coachDetailBean.getData().getSkills().size()>0){
                        for (CoachDetailBean.DataBean.SkillsBean skillsBean:coachDetailBean.getData().getSkills()){
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            lp.setMargins(0, 0, 20, 0);
                            TextView textView=new TextView(CoachHomeActivity.this);
                            textView.setText(skillsBean.getValue());
                            textView.setBackgroundResource(R.drawable.coach_skill_shape);
                            textView.setPadding(16,8,16,8);
                            textView.setTextSize(11);
                            textView.setAlpha((float) 0.50);
                            textView.setTextColor(Color.parseColor("#ffffff"));
                            textView.setLayoutParams(lp);
                            ll_skills.addView(textView);
                        }
                    }
                }
            }
        }, coachId);
    }


    public void initData() {
        SimpleryoNetwork.getCourse(CoachHomeActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
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
                        for (CourseListBean.DataBeanX dataBean : hotCourseList) {
                            multipleItem = new MultipleItem(MultipleItem.HOMEHOTCOURSE);
                            multipleItem.setCourseListBean(dataBean);
                            mItemModels.add(multipleItem);
                        }
                        courseListTypeAdapter.setDataList(mItemModels);
                    } else {
                        lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                    }
                    tv_course_count.setText("Ta的课程 / "+hotCourseList.size()+"节");
                    lrecyclerview.refreshComplete(hotCourseList.size());
                    courseListTypeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                TextView textView = mEmptyView.findViewById(R.id.tv_tips);
                textView.setText("数据一不小心走丢了，请稍后回来");
                lrecyclerview.setEmptyView(mEmptyView);
            }
        }, "", "", "", "","", coachId);
    }

    @Event(value = {R.id.iv_back, R.id.iv_msg, R.id.tv_business_home}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CoachHomeActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(CoachHomeActivity.this, MyMsgActivity.class));
                break;
            case R.id.tv_business_home:
                Intent businessIntent = new Intent(CoachHomeActivity.this, BusinessHomeActivty.class);
                businessIntent.putExtra("storeId", storeId);
                startActivity(businessIntent);
                break;

        }
    }
}
