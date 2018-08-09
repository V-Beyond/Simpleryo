package com.simpleryo.leyotang.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.CouponsListAdapter;
import com.simpleryo.leyotang.base.BaseBottomSheetDialogFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CouponsListBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import static com.simpleryo.leyotang.utils.EventBusType.SELECTCOUPON;

/**
 * @author huanglei
 * @ClassNname：CustomBottomSheetDialog.java
 * @Describe 自定义从底部弹出框
 * @time 2018/8/8 11:13
 */

public class CustomBottomSheetDialog extends BaseBottomSheetDialogFragment {
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    CouponsListAdapter couponsListAdapter;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    CouponsListBean couponsListBean;
    private BottomSheetBehavior mBehavior;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        mMainView = View.inflate(getContext(), R.layout.fragment_course_coupons_list, null);
        dialog.setContentView(mMainView);
        x.view().inject(this, mMainView);
        EventBus.getDefault().register(this);
        mBehavior = BottomSheetBehavior.from((View) mMainView.getParent());
        couponsListBean = (CouponsListBean) getArguments().getSerializable("couponlist");
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
        if (couponsListBean != null && couponsListBean.getData() != null && couponsListBean.getData().size() > 0) {
            couponsListAdapter.setDataList(couponsListBean.getData());
            lRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            lrecyclerview.setEmptyView(mEmptyView);
        }
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (couponsListBean.getData().get(position).getCategory().equalsIgnoreCase("CASH")) {
                    EventBus.getDefault().post(new BusEntity(SELECTCOUPON, "CASH", couponsListBean.getData().get(position).getSubtractAmount() + ""));
                } else if (couponsListBean.getData().get(position).getCategory().equalsIgnoreCase("DISCOUNT")) {
                    EventBus.getDefault().post(new BusEntity(SELECTCOUPON, "DISCOUNT", couponsListBean.getData().get(position).getDiscount()));
                }
                dismiss();
            }
        });
        return dialog;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 111) {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        //默认全屏展开
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void doclick(View v) {
        //点击任意布局关闭
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

}
