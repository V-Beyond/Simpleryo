package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
 * @Describe 修改昵称选择框
 * @author huanglei
 * @time 2018/3/22 15:47
 */

public class UserDesDialogFragment extends BaseDialogFragment {
    EditText et_nickname;
    TextView tv_edittext_count;
    String des;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.dialog_user_des, container, false);
            //对话框底部对齐, 这句话不能写onCreate里(包括父类的)，在onCreate里执行getDialog()获取的值为null
            getDialog().getWindow().setGravity(Gravity.CENTER);
            x.view().inject(this, mMainView);
            et_nickname=mMainView.findViewById(R.id.et_nickname);
            tv_edittext_count=mMainView.findViewById(R.id.tv_edittext_count);
            des=getArguments().getString("des");
            if (des!=null){
                et_nickname.setText(des);
                et_nickname.setSelection(des.length());
                tv_edittext_count.setText(+des.length()+"/60");
            }
            et_nickname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    tv_edittext_count.setText(+charSequence.length()+"/60");
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
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

    @Event(value = {R.id.tv_cancel,R.id.tv_sure}, type = View.OnClickListener.class)
    private void Click(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_sure:
                String des=et_nickname.getText().toString().trim();
                EventBus.getDefault().post(new BusEntity(89,des));
                dismiss();
                break;
        }
    }
}
