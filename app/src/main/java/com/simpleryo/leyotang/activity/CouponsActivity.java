package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.CouponsListAdapter;
import com.simpleryo.leyotang.adapter.GirdDropDownAdapter;
import com.simpleryo.leyotang.adapter.ListDropDownAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BaseResult;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CouponsListBean;
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

import static com.simpleryo.leyotang.utils.EventBusType.GETCOUPON;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的优惠券
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_coupons_list_layout)
public class CouponsActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @ViewInject(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    LinearLayout ll_content;
    private String headers[] = {"课程分类", "优惠类型", "使用门槛", "发放类型"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter cityAdapter;
    private GirdDropDownAdapter girdDropDownAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private String preferential_type[] = {"全部", "满减券", "折扣券"};
    private String distribution_type[] = {"全部", "平台发放", "商家发放"};
    LRecyclerViewAdapter lRecyclerViewAdapter;
    CouponsListAdapter couponsListAdapter;
    ArrayList<CouponsListBean.DataBean> dataBeanArrayList = new ArrayList<>();
    LRecyclerView lrecyclerview;
    private View mEmptyView;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        view = LayoutInflater.from(this).inflate(R.layout.layout_coupons_list_content, null);
        lrecyclerview = view.findViewById(R.id.lrecyclerview);
        mEmptyView = view.findViewById(R.id.empty_view);
        iv_msg.setVisibility(View.GONE);
        tv_name.setText(getResources().getString(R.string.coupon));
        ll_content = view.findViewById(R.id.ll_content);
        getTags();
        DividerDecoration divider = new DividerDecoration.Builder(CouponsActivity.this)
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(CouponsActivity.this));
        couponsListAdapter = new CouponsListAdapter(CouponsActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(couponsListAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(true);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.forceToRefresh();
    }

    int offset = 0;
    int limit = 9;
    //刷新
    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                dataBeanArrayList.clear();
            }
            offset = 0;
            limit = 9;
            tickets();
        }
    };
    //加载更多
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset = limit + 1;
            limit += 10;
            tickets();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType()==GETCOUPON){//领取券
            if (isLogin){//是否登录
                getCouponById(bus.getContent());
            }else{
                startActivity(new Intent(CouponsActivity.this,LoginActivity.class));
            }

        }
    }


    String upAmount = "0";
    String lowAmount = "0";
    String category="";
    //通过id领取优惠券
    public void getCouponById(String id ){
        SimpleryoNetwork.getCardcouponById(CouponsActivity.this,new MyBaseProgressCallbackImpl(CouponsActivity.this){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                BaseResult baseResult=info.getRetDetail(BaseResult.class);
                if (baseResult.getCode().equalsIgnoreCase("0")) {
                    Toast.makeText(CouponsActivity.this,"领取成功",Toast.LENGTH_SHORT).show();
                }else   if (baseResult.getCode().equalsIgnoreCase("1")) {
                    Toast.makeText(CouponsActivity.this,baseResult.getMsg(),Toast.LENGTH_SHORT).show();
                }else   if (baseResult.getCode().equalsIgnoreCase("2")) {
                    Toast.makeText(CouponsActivity.this,baseResult.getMsg(),Toast.LENGTH_SHORT).show();
                }
                lrecyclerview.forceToRefresh();
            }
            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                loadingDialog.dismiss();
            }
        },id);
    }


    //获取优惠券列表
    public void tickets() {
        SimpleryoNetwork.cardcoupontypes(CouponsActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                CouponsListBean myCouponListBean = info.getRetDetail(CouponsListBean.class);
                if (myCouponListBean.getCode().equalsIgnoreCase("0")) {
                    if (myCouponListBean.getData() != null && myCouponListBean.getData().size() > 0) {
                        dataBeanArrayList.addAll(myCouponListBean.getData());
                        couponsListAdapter.setDataList(dataBeanArrayList);
                        lRecyclerViewAdapter.notifyDataSetChanged();
                    } else {
                        if (dataBeanArrayList.size()>0){
                            lrecyclerview.setNoMore(true);
                        }else{
                            if (dataBeanArrayList != null&& dataBeanArrayList.size() > 0) {
                                dataBeanArrayList.clear();
                            }
                            couponsListAdapter = new CouponsListAdapter(CouponsActivity.this);
                            lRecyclerViewAdapter = new LRecyclerViewAdapter(couponsListAdapter);
                            lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                        }
                    }
                }
                lrecyclerview.refreshComplete(dataBeanArrayList.size());
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                TextView textView = mEmptyView.findViewById(R.id.tv_tips);
                textView.setText("数据一不小心走丢了，请稍后回来");
                lrecyclerview.setEmptyView(mEmptyView);
            }
        }, "","",category, channel, Integer.valueOf(lowAmount)*100+"", Integer.valueOf(upAmount)*100+"", offset, limit,tagId1,tagId2);
    }

    ListView cityView;
    ListView pop_listview_center;
    String channel = "";
    //初始化筛选菜单弹窗
    private void initView() {
        View menView = LayoutInflater.from(this).inflate(R.layout.layout_two_list, null);
        //init city menu
        cityView = (ListView) menView.findViewById(R.id.pop_listview_left);
        pop_listview_center = (ListView) menView.findViewById(R.id.pop_listview_center);

        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(preferential_type));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(distribution_type));
        sexView.setAdapter(sexAdapter);

        final View thresholdView = LayoutInflater.from(this).inflate(R.layout.layout_use_the_threshold, null);
        TextView tv_commit=thresholdView.findViewById(R.id.tv_commit);
        final EditText edittext_low=thresholdView.findViewById(R.id.edittext_low);
        final   EditText edittext_up=thresholdView.findViewById(R.id.edittext_up);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowAmount=edittext_low.getText().toString().trim();
                upAmount=edittext_up.getText().toString().trim();
                mDropDownMenu.closeMenu();
//                channel="";
//                category="";
//                tagId2="";
//                tagId1="";
                if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                    dataBeanArrayList.clear();
                }
                offset=0;
                limit=9;
                tickets();
            }
        });
        //init popupViews
        popupViews.add(menView);
        popupViews.add(ageView);
        popupViews.add(thresholdView);
        popupViews.add(sexView);
        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                if (position==0){
                    pop_listview_center.setVisibility(View.VISIBLE);
                    if (childDataList != null && childDataList.size() > 0) {
                        childDataList.clear();
                    }
                    TagsListBean.DataBean mDataBean=new TagsListBean.DataBean();
                    mDataBean.setName("全部课程");
                    childDataList.add(mDataBean);
                    girdDropDownAdapter = new GirdDropDownAdapter(CouponsActivity.this, childDataList, 1);
                    pop_listview_center.setDividerHeight(0);
                    pop_listview_center.setAdapter(girdDropDownAdapter);
                }else{
                    getChildTags(dataBeans.get(position).getId(), position);
                }

            }
        });
        pop_listview_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                girdDropDownAdapter.setCheckItem(position);
//                tagId1="";
//                tagId3="";
//                tagId2=childDataList.get(position).getId();
//                channel="";
//                category="";
                if (position==0){
                    tagId2 = "";
                }else{
                    tagId2=childDataList.get(position).getId();
                }
                mDropDownMenu.setTabText(childDataList.get(position).getName());
                mDropDownMenu.closeMenu();
                if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                    dataBeanArrayList.clear();
                }
                offset=0;
                limit=9;
                tickets();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
//                channel="";
//                tagId2="";
//                tagId1="";
                String categoryName = preferential_type[position];
                mDropDownMenu.setTabText(categoryName);
                mDropDownMenu.closeMenu();
                if (categoryName.equalsIgnoreCase("全部")) {
                    category = "";
                }
                if (categoryName.equalsIgnoreCase("满减券")) {
                    category = "CASH";
                }
                if (categoryName.equalsIgnoreCase("折扣券")) {
                    category = "DISCOUNT";
                }
                if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                    dataBeanArrayList.clear();
                }
                offset=0;
                limit=9;
                tickets();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                String channelName = distribution_type[position];
                mDropDownMenu.setTabText(channelName);
                mDropDownMenu.closeMenu();
//                category="";
//                tagId2="";
//                tagId1="";
                if (channelName.equalsIgnoreCase("全部")) {
                    channel = "";
                }
                if (channelName.equalsIgnoreCase("平台发放")) {
                    channel = "PLATFORM";
                }
                if (channelName.equalsIgnoreCase("商家发放")) {
                    channel = "STORE";
                }
                if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                    dataBeanArrayList.clear();
                }
                offset=0;
                limit=9;
                tickets();
            }
        });
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, view);
    }
    String tagId2="";
    String tagId1="";
    String parentId = "";//二级分类的父id
    ArrayList<TagsListBean.DataBean> dataBeans = new ArrayList<>();
    ArrayList<TagsListBean.DataBean> childDataList = new ArrayList<>();
    //获取一级分类
    public void getTags() {
        SimpleryoNetwork.tags(CouponsActivity.this, new MyBaseProgressCallbackImpl() {
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
                        TagsListBean.DataBean mDataBean=new TagsListBean.DataBean();
                        mDataBean.setName("全部行业");
                        dataBeans.add(mDataBean);
                        for (TagsListBean.DataBean dataBean:tagsListBean.getData()){
                            dataBeans.add(dataBean);
                        }
                        cityAdapter = new GirdDropDownAdapter(CouponsActivity.this, dataBeans, 0);
                        cityView.setDividerHeight(0);
                        cityView.setAdapter(cityAdapter);
                    }
                }
            }
        }, "COURSE_TYPE", parentId);
    }
    //获取二级分类
    public void getChildTags(String parentId, final int position) {
        SimpleryoNetwork.tags(CouponsActivity.this, new MyBaseProgressCallbackImpl() {
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
                        girdDropDownAdapter = new GirdDropDownAdapter(CouponsActivity.this, childDataList, 1);
                        pop_listview_center.setDividerHeight(0);
                        pop_listview_center.setAdapter(girdDropDownAdapter);
                    } else {
                        //没有二级分类时则用一级分类请求数据,，并且重置除课程分类外的其他筛选条件
                        pop_listview_center.setVisibility(View.INVISIBLE);
                        mDropDownMenu.setTabText(dataBeans.get(position).getName());
                        mDropDownMenu.closeMenu();
//                        lowAmount="";
//                        upAmount="";
//                        channel="";
//                        category="";
//                        tagId2="";
                        tagId1=dataBeans.get(position).getId();
                        if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                            dataBeanArrayList.clear();
                        }
                        offset=0;
                        limit=9;
                        tickets();
                    }
                }
            }
        }, "COURSE_TYPE", parentId);
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

    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CouponsActivity.this);
                break;
        }
    }
}
