package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.adapter.SearchCourseAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.MultipleItem;
import com.simpleryo.leyotang.bean.RecommendStoresBean;
import com.simpleryo.leyotang.fragment.SearchCourseFragment;
import com.simpleryo.leyotang.fragment.SearchStoreFragment;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.umeng.analytics.MobclickAgent;

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
 * @Describe 课程搜索页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_search_course)
public class CourseSearchActivity extends BaseActivity {
    private final String mPageName = "CourseSearchActivity";
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.course_lrecyclerview)
    LRecyclerView course_lrecyclerview;
    SearchCourseAdapter searchCourseAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    public static   EditText edittext_search;
    @ViewInject(R.id.ll_search_main)
    LinearLayout ll_search_main;
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    ArrayList<RecommendStoresBean.DataBean> recommendList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置 U-Dplus场景
        MobclickAgent.setScenarioType(CourseSearchActivity.this, MobclickAgent.EScenarioType.E_DUM_NORMAL);
        tv_name.setText("课程搜索");
        edittext_search=findViewById(R.id.edittext_search);
        EventBus.getDefault().register(this);
        //是否允许嵌套滑动
        course_lrecyclerview.setNestedScrollingEnabled(false);
        searchCourseAdapter = new SearchCourseAdapter(CourseSearchActivity.this);
        DividerDecoration divider = new DividerDecoration.Builder(CourseSearchActivity.this)
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CourseSearchActivity.this, 1);
        course_lrecyclerview.setLayoutManager(gridLayoutManager);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(searchCourseAdapter);
        course_lrecyclerview.setAdapter(lRecyclerViewAdapter);
        course_lrecyclerview.removeItemDecoration(divider);
        course_lrecyclerview.addItemDecoration(divider);
        course_lrecyclerview.setHasFixedSize(true);
        course_lrecyclerview.setLoadMoreEnabled(false);
        course_lrecyclerview.setPullRefreshEnabled(false);
        edittext_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(!v.getText().toString().trim().isEmpty()){
                        course_lrecyclerview.setVisibility(View.GONE);
                        ll_search_main.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(CourseSearchActivity.this,"请输入要搜索的内容",Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        edittext_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>0){
                    course_lrecyclerview.setVisibility(View.GONE);
                    ll_search_main.setVisibility(View.VISIBLE);
                }
//                else{
//                    course_lrecyclerview.setVisibility(View.VISIBLE);
//                    ll_search_main.setVisibility(View.GONE);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //获取推荐门店
        SimpleryoNetwork.getRecommendStores(CourseSearchActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                RecommendStoresBean recommendStoresBean = info.getRetDetail(RecommendStoresBean.class);
                if (recommendStoresBean.getCode().equalsIgnoreCase("0")) {
                    recommendList.addAll(recommendStoresBean.getData());
                    MultipleItem item;
                    item = new MultipleItem(MultipleItem.InstitutionalRecommen);
                    mItemModels.add(item);
                    searchCourseAdapter.setRecommendList(recommendList);
                    searchCourseAdapter.setDataList(mItemModels);
                }
            }
        });
        fragments.add(new SearchCourseFragment());
        fragments.add(new SearchStoreFragment());
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
    }

    @Event(value = {R.id.radio_group_main}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_course:
                view_pager_main.setCurrentItem(0);
                break;
            case R.id.radio_btn_store:
                view_pager_main.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 44) {
            String courseId = bus.getContent();
            SimpleryoNetwork.collectCourse(CourseSearchActivity.this, new MyBaseProgressCallbackImpl(CourseSearchActivity.this) {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    loadingDialog.dismiss();
                    CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                    if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                        Toast.makeText(CourseSearchActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        searchCourse("课程");
                    }
                }
            }, courseId);
        }
        if (bus.getType() == 45) {
            String courseId = bus.getContent();
            SimpleryoNetwork.disCollectCourse(CourseSearchActivity.this, new MyBaseProgressCallbackImpl(CourseSearchActivity.this) {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    loadingDialog.dismiss();
                    CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                    if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                        Toast.makeText(CourseSearchActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        searchCourse("课程");
                    } else {
                        Toast.makeText(CourseSearchActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, courseId);
        }
    }

    private List<MultipleItem> mItemModels = new ArrayList<>();

    public void searchCourse(String name) {
        SimpleryoNetwork.getCourse(CourseSearchActivity.this, new MyBaseProgressCallbackImpl(CourseSearchActivity.this) {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                MultipleItem item;
                item = new MultipleItem(MultipleItem.InstitutionalRecommen);
                if (mItemModels != null && mItemModels.size() > 0) {
                    mItemModels.clear();
                }
                mItemModels.add(item);
                searchCourseAdapter.setDataList(mItemModels);
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                TextView textView=mEmptyView.findViewById(R.id.tv_tips);
                textView.setText("数据一不小心走丢了，请稍后回来");
                course_lrecyclerview.setEmptyView(mEmptyView);
            }
        }, "", name, "", "","","",0.00,0.00,"",0,0,0);
    }

    @Event(value = {R.id.iv_back, R.id.tv_cancel}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CourseSearchActivity.this);
                break;
            case R.id.tv_cancel:
                XActivityUtils.getInstance().popActivity(CourseSearchActivity.this);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
    }
}
