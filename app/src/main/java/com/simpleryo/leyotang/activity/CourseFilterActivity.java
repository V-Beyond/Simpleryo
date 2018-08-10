package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.CourseListTypeAdapter;
import com.simpleryo.leyotang.adapter.GirdDropDownAdapter;
import com.simpleryo.leyotang.adapter.ListDropDownAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.CourseListBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.bean.TagsListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.view.DropDownMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 课程筛选列表页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_course_filter_list)
public class CourseFilterActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @ViewInject(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;//筛选菜单
    LinearLayout ll_content;
    private String headers[] = {"课程分类", "附近", "组合排序"};
    private List<View> popupViews = new ArrayList<>();
    //一级分类列表适配器
    private GirdDropDownAdapter cityAdapter;
    //二级分类列表适配器
    private GirdDropDownAdapter girdDropDownAdapter;
    //距离列表适配器
    private ListDropDownAdapter distanceListViewAdapter;
    //组合排序适配器
    private ListDropDownAdapter combinationAdapter;
    private String distanceArrays[] = {"500米", "1km", "3km", "5km", "10km"};
    private String combinationArrays[] = {"最新发布", "好评优先", "价格实惠"};
    View view;
    LRecyclerView lrecyclerview;
    private View mEmptyView;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    CourseListTypeAdapter courseListTypeAdapter;
    List<CourseListBean.DataBeanX> hotCourseList = new ArrayList<>();
    private List<MultipleItem> mItemModels = new ArrayList<>();
    String tagId3 = "";//官方推荐，热销，精品等tagId
    String tagId2 = "";//二级分类id
    String tagId1 = "";//一级分类id

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tagId1 = getIntent().getStringExtra("tagId1");
        tagId3 = getIntent().getStringExtra("tagId3");
        if (tagId1 == null) {
            tagId1 = "";
        }
        if (tagId3 == null) {
            tagId3 = "";
        }
//        if (type.equalsIgnoreCase("hot")) {//热门
        tv_name.setText("服务列表");
//        } else if (type.equalsIgnoreCase("excellent")) {//精选
//            tv_name.setText("精品课程");
//        } else if (type.equalsIgnoreCase("introductory")) {//推荐
//            tv_name.setText("官方课程");
//        }
        view = LayoutInflater.from(this).inflate(R.layout.layout_course_list_content, null);
        lrecyclerview = view.findViewById(R.id.lrecyclerview);
        mEmptyView = view.findViewById(R.id.empty_view);
        ll_content = view.findViewById(R.id.ll_content);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(50f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        courseListTypeAdapter = new CourseListTypeAdapter(CourseFilterActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(courseListTypeAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.forceToRefresh();
        getTags();
    }

    //init city menu
    ListView cityView;//一级分类列表
    ListView pop_listview_center;//二级分类列表

    private void initView() {
        View menView = LayoutInflater.from(this).inflate(R.layout.layout_two_list, null);
        //init city menu
        cityView = (ListView) menView.findViewById(R.id.pop_listview_left);
        pop_listview_center = (ListView) menView.findViewById(R.id.pop_listview_center);
        //init age menu
        final ListView distanceListView = new ListView(this);
        distanceListView.setDividerHeight(0);
        distanceListViewAdapter = new ListDropDownAdapter(this, Arrays.asList(distanceArrays));
        distanceListView.setAdapter(distanceListViewAdapter);

        //init sex menu
        final ListView combinationListView = new ListView(this);
        combinationListView.setDividerHeight(0);
        combinationAdapter = new ListDropDownAdapter(this, Arrays.asList(combinationArrays));
        combinationListView.setAdapter(combinationAdapter);
        //init popupViews
        popupViews.add(menView);
        popupViews.add(distanceListView);
        popupViews.add(combinationListView);
        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                getChildTags(dataBeans.get(position).getId(), position);
            }
        });
        pop_listview_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                girdDropDownAdapter.setCheckItem(position);
                tagId1 = "";
                tagId3 = "";
                lat = 0.00;//维度
                lng = 0.00;//经度
                distance = "";
                tagId2 = childDataList.get(position).getId();
                mDropDownMenu.setTabText(childDataList.get(position).getName());
                mDropDownMenu.closeMenu();
                initData();
            }
        });
        //距离选择事件
        distanceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tagId1 = "";
                tagId3 = "";
                tagId2 = "";
                lat = 31.23;//维度
                lng = 121.47;//经度
                distance = "100";
                distanceListViewAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(distanceArrays[position]);
                mDropDownMenu.closeMenu();
                initData();
            }
        });
        //组合排序
        combinationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                combinationAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(combinationArrays[position]);
                mDropDownMenu.closeMenu();
            }
        });
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, view);
    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
    }

    String parentId = "";
    ArrayList<TagsListBean.DataBean> dataBeans = new ArrayList<>();
    ArrayList<TagsListBean.DataBean> childDataList = new ArrayList<>();
    //获取一级分类列表
    public void getTags() {
        SimpleryoNetwork.tags(CourseFilterActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                initView();
                TagsListBean tagsListBean = info.getRetDetail(TagsListBean.class);
                if (tagsListBean.getCode().equalsIgnoreCase("0")) {
                    if (tagsListBean.getData() != null && tagsListBean.getData().size() > 0) {
                        if (dataBeans != null && dataBeans.size() > 0) {
                            dataBeans.clear();
                        }
                        dataBeans.addAll(tagsListBean.getData());
                        cityAdapter = new GirdDropDownAdapter(CourseFilterActivity.this, dataBeans, 0);
                        cityView.setDividerHeight(0);
                        cityView.setAdapter(cityAdapter);
                    }
                }
            }
        }, "COURSE_TYPE", parentId);
    }
    //根据一级分类获取二级分类列表
    public void getChildTags(String parentId, final int position) {
        SimpleryoNetwork.tags(CourseFilterActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                TagsListBean tagsListBean = info.getRetDetail(TagsListBean.class);
                if (tagsListBean.getCode().equalsIgnoreCase("0")) {
                    if (tagsListBean.getData() != null && tagsListBean.getData().size() > 0) {
                        pop_listview_center.setVisibility(View.VISIBLE);
                        if (childDataList != null && childDataList.size() > 0) {
                            childDataList.clear();
                        }
                        childDataList.addAll(tagsListBean.getData());
                        girdDropDownAdapter = new GirdDropDownAdapter(CourseFilterActivity.this, childDataList, 1);
                        pop_listview_center.setDividerHeight(0);
                        pop_listview_center.setAdapter(girdDropDownAdapter);
                    } else {
                        pop_listview_center.setVisibility(View.INVISIBLE);
                        mDropDownMenu.setTabText(dataBeans.get(position).getName());
                        mDropDownMenu.closeMenu();
                        tagId3 = "";
                        tagId2 = "";
                        lat = 0.00;//维度
                        lng = 0.00;//经度
                        distance = "";
                        tagId1 = dataBeans.get(position).getId();
                        initData();
                    }
                }
            }
        }, "COURSE_TYPE", parentId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 33) {
            if (isLogin) {
                String courseId = bus.getContent();
                boolean isCollect = bus.isCollect();
                if (isCollect) {//取消收藏
                    SimpleryoNetwork.disCollectCourse(CourseFilterActivity.this, new MyBaseProgressCallbackImpl(CourseFilterActivity.this) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(CourseFilterActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CourseFilterActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpInfo info) {
                            super.onFailure(info);
                            loadingDialog.dismiss();
                        }
                    }, courseId);
                } else {//收藏
                    SimpleryoNetwork.collectCourse(CourseFilterActivity.this, new MyBaseProgressCallbackImpl(CourseFilterActivity.this) {
                        @Override
                        public void onSuccess(HttpInfo info) {
                            super.onSuccess(info);
                            loadingDialog.dismiss();
                            CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                            if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                lrecyclerview.forceToRefresh();
                                Toast.makeText(CourseFilterActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CourseFilterActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, courseId);
                }
            } else {
                startActivity(new Intent(CourseFilterActivity.this, LoginActivity.class));
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

    double lat = 0.00;//维度
    double lng = 0.00;//经度
    String distance = "";//距离
    //获取课程列表
    public void initData() {
        if (hotCourseList != null && hotCourseList.size() > 0) {
            hotCourseList.clear();
        }
        SimpleryoNetwork.getCourse(CourseFilterActivity.this, new MyBaseProgressCallbackImpl() {
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
                        lRecyclerViewAdapter.notifyDataSetChanged();
                        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                startActivity(new Intent(CourseFilterActivity.this, CourseDetailActivity.class).putExtra("courseId", hotCourseList.get(position).getId()));
                            }
                        });
                    } else {
                        if (hotCourseList.size() > 0) {
                            lrecyclerview.setNoMore(true);
                        } else {
                            if (hotCourseList != null && hotCourseList.size() > 0) {
                                hotCourseList.clear();
                            }
                            if (mItemModels != null && mItemModels.size() > 0) {
                                mItemModels.clear();
                            }
                            courseListTypeAdapter = new CourseListTypeAdapter(CourseFilterActivity.this);
                            lRecyclerViewAdapter = new LRecyclerViewAdapter(courseListTypeAdapter);
                            lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                        }
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
        }, "", "", tagId1, tagId2, tagId3, "", lat, lng, distance);
    }

    @Event(value = {R.id.iv_back, R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CourseFilterActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(CourseFilterActivity.this, MyMsgActivity.class));
                break;
        }
    }
}
