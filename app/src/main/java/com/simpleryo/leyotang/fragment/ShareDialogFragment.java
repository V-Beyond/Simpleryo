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
 * @author huanglei
 * @ClassNname：SexDialogFragment.java
 * @Describe 性别选择框
 * @time 2018/3/22 15:47
 */

public class ShareDialogFragment extends BaseDialogFragment {
    String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.dialog_share, container, false);
            //对话框底部对齐, 这句话不能写onCreate里(包括父类的)，在onCreate里执行getDialog()获取的值为null
            getDialog().getWindow().setGravity(Gravity.BOTTOM);
            type = getArguments().getString("type");
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

    @Event(value = {R.id.tv_cancel, R.id.iv_wechat, R.id.iv_wechat_circle, R.id.iv_alipay, R.id.iv_facebook}, type = View.OnClickListener.class)
    private void Click(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.iv_wechat:
                if (type.equalsIgnoreCase("course")) {
                    EventBus.getDefault().post(new BusEntity(701));
                }else if (type.equalsIgnoreCase("store")) {
                    EventBus.getDefault().post(new BusEntity(705));
                }
                dismiss();
                break;
            case R.id.iv_wechat_circle:
                if (type.equalsIgnoreCase("course")) {
                    EventBus.getDefault().post(new BusEntity(702));
                }else  if (type.equalsIgnoreCase("store")) {
                    EventBus.getDefault().post(new BusEntity(706));
                }
                dismiss();
                break;
            case R.id.iv_alipay:
                if (type.equalsIgnoreCase("course")) {
                    EventBus.getDefault().post(new BusEntity(703));
                }else  if (type.equalsIgnoreCase("store")) {
                    EventBus.getDefault().post(new BusEntity(707));
                }
                dismiss();
                break;
            case R.id.iv_facebook:
                if (type.equalsIgnoreCase("course")) {
                    EventBus.getDefault().post(new BusEntity(704));
                }else  if (type.equalsIgnoreCase("store")) {
                    EventBus.getDefault().post(new BusEntity(708));
                }
                dismiss();
                break;
            default:
                break;
        }
    }
}
