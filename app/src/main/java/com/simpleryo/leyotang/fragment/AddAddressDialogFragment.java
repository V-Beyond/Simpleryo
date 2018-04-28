package com.simpleryo.leyotang.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseDialogFragment;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @author huanglei
 * @ClassNname：SexDialogFragment.java
 * @Describe 新增地址选择框
 * @time 2018/3/22 15:47
 */

public class AddAddressDialogFragment extends BaseDialogFragment {
    @ViewInject(R.id.tv_contacts)
    TextView tv_contacts;
    @ViewInject(R.id.tv_phone)
    TextView tv_phone;
    @ViewInject(R.id.tv_address)
    TextView tv_address;
    @ViewInject(R.id.tv_door_number)
    TextView tv_door_number;
    @ViewInject(R.id.radio_button_mr)
    RadioButton radio_button_mr;
    @ViewInject(R.id.radio_button_mrs)
    RadioButton radio_button_mrs;
    @ViewInject(R.id.et_nickname)
    EditText et_nickname;
    @ViewInject(R.id.ed_phone)
    EditText ed_phone;
    @ViewInject(R.id.edittext_address)
    EditText edittext_address;
    @ViewInject(R.id.edittext_house_number)
    EditText edittext_house_number;
    String gender;
    String linkman;
    String phone;
    String myAddress;
    String housenNmber;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.dialog_add_address, container, false);
            //对话框底部对齐, 这句话不能写onCreate里(包括父类的)，在onCreate里执行getDialog()获取的值为null
            getDialog().getWindow().setGravity(Gravity.CENTER);
            x.view().inject(this, mMainView);
            EventBus.getDefault().register(this);
//            tv_contacts.setText(AlignedTextUtils.formatText("联　系　人"));
//            tv_phone.setText(AlignedTextUtils.formatText("电　　　话"));
//            tv_address.setText(AlignedTextUtils.formatText("地　　　址"));
//            tv_door_number.setText(AlignedTextUtils.formatText("门　牌　号"));
            userId = SharedPreferencesUtils.getKeyString("userId");
            Bundle bundle = getArguments();
            gender = bundle.getString("gender");
            linkman = bundle.getString("linkman");
            phone = bundle.getString("phone");
            myAddress = bundle.getString("myAddress");
            housenNmber = bundle.getString("housenNmber");
            if (gender != null) {
                if (gender.equalsIgnoreCase("1")) {
                    radio_button_mr.setChecked(true);
                } else if (gender.equalsIgnoreCase("2")) {
                    radio_button_mrs.setChecked(true);
                }
            }
            if (linkman != null) {
                et_nickname.setText(linkman);
                et_nickname.setSelection(linkman.length());
            }
            if (phone != null) {
                ed_phone.setText(phone);
            }
            if (myAddress != null) {
                edittext_address.setText(myAddress);
            }
            if (housenNmber != null) {
                edittext_house_number.setText(housenNmber);
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
            case R.id.radio_button_mr:
                break;
            case R.id.radio_button_mrs:
                break;
            default:
                break;
        }
    }

    @Event(value = {R.id.tv_cancel, R.id.tv_sure}, type = View.OnClickListener.class)
    private void Click(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_sure:
                linkman = et_nickname.getText().toString().trim();
                phone = ed_phone.getText().toString().trim();
                housenNmber = edittext_house_number.getText().toString().trim();
                SimpleryoNetwork.addUserAddress(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                    @Override
                    public void onSuccess(HttpInfo info) {
                        super.onSuccess(info);
                        loadingDialog.dismiss();
                        CodeBean codeBean = info.getRetDetail(CodeBean.class);
                        if (codeBean.getCode().equalsIgnoreCase("0")) {
                            EventBus.getDefault().post(new BusEntity(90));
                            Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getActivity(), codeBean.getData().getValue(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpInfo info) {
                        super.onFailure(info);
                        loadingDialog.dismiss();
                        Toast.makeText(getActivity(), "数据一不小心走丢了，请稍后回来", Toast.LENGTH_SHORT).show();
                    }
                }, userId, linkman, phone, "上海", "上海市", "静安区", housenNmber);
                break;
            default:
                break;
        }
    }
}
