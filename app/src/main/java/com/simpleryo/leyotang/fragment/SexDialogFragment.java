package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseDialogFragment;
import com.simpleryo.leyotang.bean.BusEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @ClassNname：SexDialogFragment.java
 * @Describe 性别选择框
 * @author huanglei
 * @time 2018/3/22 15:47
 */

public class SexDialogFragment extends BaseDialogFragment {
    String gender;
    @ViewInject(R.id.iv_boy)
    RadioButton iv_boy;
    @ViewInject(R.id.iv_girl)
    RadioButton iv_girl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.dialog_sex, container, false);
            //对话框底部对齐, 这句话不能写onCreate里(包括父类的)，在onCreate里执行getDialog()获取的值为null
            getDialog().getWindow().setGravity(Gravity.CENTER);
            x.view().inject(this, mMainView);
            EventBus.getDefault().register(this);
            gender=getArguments().getString("gender");
            if (gender!=null){
                if (gender.equalsIgnoreCase("1")){
                    iv_boy.setChecked(true);
                }else if(gender.equalsIgnoreCase("2")){
                    iv_girl.setChecked(true);
                }
            }
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
    @Event(value = {R.id.radio_group_sex}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.iv_boy:
                gender="1";
                break;
            case R.id.iv_girl:
                gender="2";
                break;
            default:
                break;
        }
    }

    @Event(value = {R.id.tv_cancel}, type = View.OnClickListener.class)
    private void Click(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                EventBus.getDefault().post(new BusEntity(84,gender));
                dismiss();
                break;
//            case R.id.iv_boy:
//                EventBus.getDefault().post(new BusEntity(84,gender));
//                dismiss();
//                break;
//            case R.id.iv_girl:
//                EventBus.getDefault().post(new BusEntity(84,"女"));
//                dismiss();
//                break;
            default:
                break;
        }
    }
}
