package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseDialogFragment;
import com.simpleryo.leyotang.bean.BusEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * @ClassNname：SexDialogFragment.java
 * @Describe 性别选择框
 * @author huanglei
 * @time 2018/3/22 15:47
 */

public class ShareDialogFragment extends BaseDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.dialog_share, container, false);
            //对话框底部对齐, 这句话不能写onCreate里(包括父类的)，在onCreate里执行getDialog()获取的值为null
            getDialog().getWindow().setGravity(Gravity.BOTTOM);
            x.view().inject(this, mMainView);
            EventBus.getDefault().register(this);
        }
        ViewGroup parent = (ViewGroup) mMainView.getParent();
        if (parent != null) {
            parent.removeView(mMainView);
        }
        return mMainView;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Event(value = {R.id.tv_cancel}, type = View.OnClickListener.class)
    private void Click(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }
}
