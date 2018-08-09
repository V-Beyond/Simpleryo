package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.CouponsListAdapter;
import com.simpleryo.leyotang.base.BaseDialogFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CouponsListBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @author huanglei
 * @ClassNname：MyFragment.java
 * @Describe 优惠券
 * @time 2018/3/19 11:10
 */

public class CourseCouponFragment extends BaseDialogFragment {
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    CouponsListAdapter couponsListAdapter;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    CouponsListBean couponsListBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_course_coupons_list, container, false);
            //对话框底部对齐, 这句话不能写onCreate里(包括父类的)，在onCreate里执行getDialog()获取的值为null
            getDialog().getWindow().setGravity(Gravity.BOTTOM);
            x.view().inject(this, mMainView);
            EventBus.getDefault().register(this);
            couponsListBean= (CouponsListBean) getArguments().getSerializable("couponlist");
            DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                    .setHeight(30f)
                    .setColorResource(R.color.color_transparent)
                    .build();
            lrecyclerview.addItemDecoration(divider);
            lrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            couponsListAdapter = new CouponsListAdapter(getActivity());
            lRecyclerViewAdapter = new LRecyclerViewAdapter(couponsListAdapter);
            lrecyclerview.setAdapter(lRecyclerViewAdapter);
            lrecyclerview.setLoadMoreEnabled(false);
            lrecyclerview.setPullRefreshEnabled(false);
            if (couponsListBean!=null&&couponsListBean.getData()!=null&&couponsListBean.getData().size()>0){
                couponsListAdapter.setDataList(couponsListBean.getData());
                lRecyclerViewAdapter.notifyDataSetChanged();
            }else{
                lrecyclerview.setEmptyView(mEmptyView);
            }
        }
        ViewGroup parent = (ViewGroup) mMainView.getParent();
        if (parent != null) {
            parent.removeView(mMainView);
        }
        return mMainView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType()==111){
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
