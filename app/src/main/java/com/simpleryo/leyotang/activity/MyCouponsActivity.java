package com.simpleryo.leyotang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.adapter.MyCouponsListAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.MyCouponListBean;
import com.simpleryo.leyotang.fragment.CouponFragment;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.simpleryo.leyotang.utils.EventBusType.MYCOUPONSELECT;
import static com.simpleryo.leyotang.utils.EventBusType.SELECTCOUPON;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的优惠券
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_my_coupons_list_layout)
public class MyCouponsActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    MyCouponsListAdapter myCouponsListAdapter;
    ArrayList<MyCouponListBean.DataBean> dataBeanArrayList = new ArrayList<>();
    String type = "UNUSED";
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    String category;
    String storeId;
    String courseId;
    int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        iv_msg.setVisibility(View.GONE);
        tv_name.setText(getResources().getString(R.string.coupon));
        category = getIntent().getStringExtra("type");
        storeId = getIntent().getStringExtra("storeId");
        courseId = getIntent().getStringExtra("courseId");
        count=getIntent().getIntExtra("count",0);
        Bundle bundle = new Bundle();
        bundle.putString("type", "UNUSED");
        CouponFragment couponFragment = new CouponFragment();
        couponFragment.setArguments(bundle);
        Bundle bundle1 = new Bundle();
        bundle1.putString("type", "USED");
        CouponFragment couponFragment1 = new CouponFragment();
        couponFragment1.setArguments(bundle1);
        Bundle bundle2 = new Bundle();
        bundle2.putString("type", "EXPIRED");
        CouponFragment couponFragment2 = new CouponFragment();
        couponFragment2.setArguments(bundle2);
        fragments.add(couponFragment);
        fragments.add(couponFragment1);
        fragments.add(couponFragment2);
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(MyCouponsActivity.this)
                .setHeight(30f)
                .setColorResource(R.color.color_transparent)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(MyCouponsActivity.this));
        myCouponsListAdapter = new MyCouponsListAdapter(MyCouponsActivity.this,category);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(myCouponsListAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.forceToRefresh();
    }

    int offset = 0;
    int limit = 9;
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
    private OnLoadMoreListener onLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset = limit + 1;
            limit += 10;
            tickets();
        }
    };

    /**
     * 获取我的优惠券列表
     */
    public void tickets() {
        if (category.equalsIgnoreCase("my")) {//个人中心进入我的优惠券
            SimpleryoNetwork.tickets(MyCouponsActivity.this, new MyBaseProgressCallbackImpl() {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    MyCouponListBean myCouponListBean = info.getRetDetail(MyCouponListBean.class);
                    if (myCouponListBean.getCode().equalsIgnoreCase("0")) {
                        if (myCouponListBean.getData() != null && myCouponListBean.getData().size() > 0) {
                            if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                                dataBeanArrayList.clear();
                            }
                            dataBeanArrayList.addAll(myCouponListBean.getData());
                            myCouponsListAdapter.setDataList(dataBeanArrayList);
                        } else {
                            if (dataBeanArrayList.size() > 0) {
                                lrecyclerview.setNoMore(true);
                            } else {
                                lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                                myCouponsListAdapter = new MyCouponsListAdapter(MyCouponsActivity.this,category);
                                lRecyclerViewAdapter = new LRecyclerViewAdapter(myCouponsListAdapter);
                                lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            }
                        }
                    }
                    lrecyclerview.refreshComplete(dataBeanArrayList.size());
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(HttpInfo info) {
                    super.onFailure(info);
                    TextView textView = mEmptyView.findViewById(R.id.tv_tips);
                    textView.setText("数据一不小心走丢了，请稍后回来");
                    lrecyclerview.setEmptyView(mEmptyView);
                }
            }, type, offset, limit);
        } else if (category.equalsIgnoreCase("order")) {//下单页面进入我的优惠券
            SimpleryoNetwork.availableTickets(MyCouponsActivity.this, new MyBaseProgressCallbackImpl() {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    MyCouponListBean myCouponListBean = info.getRetDetail(MyCouponListBean.class);
                    if (myCouponListBean.getCode().equalsIgnoreCase("0")) {
                        if (myCouponListBean.getData() != null && myCouponListBean.getData().size() > 0) {
                            if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                                dataBeanArrayList.clear();
                            }
                            dataBeanArrayList.addAll(myCouponListBean.getData());
                            myCouponsListAdapter.setDataList(dataBeanArrayList);
                        } else {
                            if (dataBeanArrayList.size() > 0) {
                                lrecyclerview.setNoMore(true);
                            } else {
                                lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                                myCouponsListAdapter = new MyCouponsListAdapter(MyCouponsActivity.this,category);
                                lRecyclerViewAdapter = new LRecyclerViewAdapter(myCouponsListAdapter);
                                lrecyclerview.setAdapter(lRecyclerViewAdapter);
                            }
                        }
                    }
                    lrecyclerview.refreshComplete(dataBeanArrayList.size());
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(HttpInfo info) {
                    super.onFailure(info);
                }
            }, storeId, courseId,count);
        }

    }

    @Event(value = {R.id.radio_group_main}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_my_wait_pay:
                type = "UNUSED";
                view_pager_main.setCurrentItem(0);
                lrecyclerview.forceToRefresh();
                break;
            case R.id.radio_btn_payed:
                type = "USED";
                view_pager_main.setCurrentItem(1);
                lrecyclerview.forceToRefresh();
                break;
            case R.id.radio_btn_wait_remark:
                type = "EXPIRED";
                view_pager_main.setCurrentItem(2);
                lrecyclerview.forceToRefresh();
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
        if (bus.getType() == MYCOUPONSELECT) {
            String coupon=bus.getCoupon();
            if (bus.getContent().equalsIgnoreCase("CASH")){//现金券
                EventBus.getDefault().post(new BusEntity(SELECTCOUPON, "CASH", coupon));
            }  else if (bus.getContent().equalsIgnoreCase("DISCOUNT")){//折扣券
                EventBus.getDefault().post(new BusEntity(SELECTCOUPON, "DISCOUNT", coupon));
            }
            XActivityUtils.getInstance().popActivity(MyCouponsActivity.this);
        }
    }
    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyCouponsActivity.this);
                break;
        }
    }
}
