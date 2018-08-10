package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
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

import static com.simpleryo.leyotang.utils.EventBusType.CAMERA;
import static com.simpleryo.leyotang.utils.EventBusType.PHOTO;

/**
 * @author huanglei
 * @ClassNname：CustomBottomSheetDialog.java
 * @Describe 自定义从底部弹出框
 * @time 2018/8/8 11:13
 */

public class AvatarBottomSheetDialog extends BaseDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.dialog_avatar_select, container, false);
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
    public void updateCollect(BusEntity bus) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置宽度满屏
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        //设置显示和关闭时的动画
        getDialog().getWindow().setWindowAnimations(R.style.style_item);
    }

    @Event(value = {R.id.tv_cancel, R.id.tv_photo,R.id.tv_camera}, type = View.OnClickListener.class)
    private void Click(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_photo:
                EventBus.getDefault().post(new BusEntity(PHOTO));
                dismiss();
                break;
            case R.id.tv_camera:
                EventBus.getDefault().post(new BusEntity(CAMERA));
                dismiss();
                break;
            default:
                break;
        }
    }
}
