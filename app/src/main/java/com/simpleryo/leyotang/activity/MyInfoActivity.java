package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.UserInfoBean;
import com.simpleryo.leyotang.fragment.AddAddressDialogFragment;
import com.simpleryo.leyotang.fragment.ConstellationDialogFragment;
import com.simpleryo.leyotang.fragment.SexDialogFragment;
import com.simpleryo.leyotang.fragment.UpdateNickNameDialogFragment;
import com.simpleryo.leyotang.fragment.UserDesDialogFragment;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @author huanglei
 * @ClassNname：MyInfoActivity.java
 * @Describe 基本信息
 * @time 2018/3/22 15:30
 */
@ContentView(R.layout.activity_my_info)
public class MyInfoActivity extends BaseActivity {
    @ViewInject(R.id.tv_sex)
    TextView tv_sex;
    @ViewInject(R.id.tv_nickname)
    TextView tv_nickname;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_email)
    TextView tv_email;
    @ViewInject(R.id.tv_address)
    TextView tv_address;
    @ViewInject(R.id.tv_constellation)
    TextView tv_constellation;
    @ViewInject(R.id.tv_intro)
    TextView tv_intro;
    String userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText("基本信息");
        userId= SharedPreferencesUtils.getKeyString("userId");
        EventBus.getDefault().post(new BusEntity(87));
    }
    String gender;

    @Event(value = {R.id.iv_back,R.id.iv_msg,R.id.rl_sex,R.id.rl_name,R.id.rl_constellation,R.id.rl_address,R.id.rl_info}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(MyInfoActivity.this,MyMsgActivity.class));
                break;
            case R.id.rl_sex://性别
                SexDialogFragment sexDialogFragment=new SexDialogFragment();
                Bundle sexBundle=new Bundle();
                sexBundle.putString("gender",gender);
                sexDialogFragment.setArguments(sexBundle);
                sexDialogFragment.show(getSupportFragmentManager(),"sexDialogFragment");
                break;
            case R.id.rl_name://修改昵称
                UpdateNickNameDialogFragment updateNickNameDialogFragment=new UpdateNickNameDialogFragment();
                updateNickNameDialogFragment.show(getSupportFragmentManager(),"updateNickNameDialogFragment");
                break;
            case R.id.rl_info://个人简介
                UserDesDialogFragment userDesDialogFragment=new UserDesDialogFragment();
                Bundle desBundle=new Bundle();
                desBundle.putString("des",des);
                userDesDialogFragment.setArguments(desBundle);
                userDesDialogFragment.show(getSupportFragmentManager(),"userDesDialogFragment");
                break;
            case R.id.rl_constellation://星座
                ConstellationDialogFragment constellationDialogFragment=new ConstellationDialogFragment();
                Bundle mBundle=new Bundle();
                mBundle.putString("starSign",starSign);
                constellationDialogFragment.setArguments(mBundle);
                constellationDialogFragment.show(getSupportFragmentManager(),"constellationDialogFragment");
                break;
            case R.id.rl_address://地址
                AddAddressDialogFragment addAddressDialogFragment=new AddAddressDialogFragment();
                Bundle bundle=new Bundle();
                bundle.putString("gender",gender);
                bundle.putString("linkman",linkman);
                bundle.putString("phone",phone);
                bundle.putString("myAddress",myAddress);
                bundle.putString("housenNmber",housenNmber);
                addAddressDialogFragment.setArguments(bundle);
                addAddressDialogFragment.show(getSupportFragmentManager(),"addAddressDialogFragment");
                break;
        }
    }
    String linkman;
    String phone;
    String starSign;
    String myAddress;
    String housenNmber;
    String loginName;
    String emmail;
    String des;
    String avatarUrl;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
        if (bus.getType() == 84) {
            gender=bus.getContent();
            EventBus.getDefault().post(new BusEntity(86));
        }
        if (bus.getType() == 88) {
            starSign=bus.getContent();
            EventBus.getDefault().post(new BusEntity(86));
        }
        if (bus.getType() == 89) {
            des=bus.getContent();
            EventBus.getDefault().post(new BusEntity(86));
        }
        if (bus.getType() == 90) {
            EventBus.getDefault().post(new BusEntity(87));
        }
        if (bus.getType() == 86) {//修改个人信息
            SimpleryoNetwork.updateInfo(MyInfoActivity.this, new MyBaseProgressCallbackImpl(MyInfoActivity.this) {
                @Override
                public void onSuccess(HttpInfo info)  {
                    loadingDialog.dismiss();
                    Toast.makeText(MyInfoActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new BusEntity(87));
                }
                @Override
                public void onFailure(HttpInfo info)  {
                    loadingDialog.dismiss();
                }
            },userId,emmail,bus.getContent(),loginName,gender,starSign,des,avatarUrl);
        }
        if (bus.getType()==87){//获取用户信息
            SimpleryoNetwork.getUserInfo(MyInfoActivity.this,new MyBaseProgressCallbackImpl(){
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    UserInfoBean userInfoBean=info.getRetDetail(UserInfoBean.class);
                    emmail=userInfoBean.getData().getEmail();
                    avatarUrl=userInfoBean.getData().getAvatarUrl();
                    gender=userInfoBean.getData().getGender();
                    loginName=userInfoBean.getData().getPhone();
                    des=userInfoBean.getData().getIntro();
                    if (userInfoBean.getData().getName()!=null){
                        tv_nickname.setText(userInfoBean.getData().getName());
                    }else{
                        tv_nickname.setText("暂无昵称");
                    }
                    if (gender!=null){
                        if (userInfoBean.getData().getGender().equalsIgnoreCase("1")){
                            tv_sex.setText("男");
                        }else if (userInfoBean.getData().getGender().equalsIgnoreCase("2")){
                            tv_sex.setText("女");
                        }else {
                            tv_sex.setText("未知");
                        }
                    }else{
                        tv_sex.setText("未知");
                    }
                    if (userInfoBean.getData().getEmail()!=null){
                        tv_email.setText(userInfoBean.getData().getEmail());
                    }
                    if (userInfoBean.getData().getStarSign()!=null){
                        starSign=userInfoBean.getData().getStarSign();
                        tv_constellation.setText(userInfoBean.getData().getStarSign());
                    }else{
                        tv_constellation.setText("暂无星座");
                    }
                    if (userInfoBean.getData().getIntro()!=null){
                        tv_intro.setText(userInfoBean.getData().getIntro());
                    }else{
                        tv_intro.setText("暂无个人简介");
                    }
                    if (userInfoBean.getData().getShipToAddr()!=null){
                        linkman=userInfoBean.getData().getShipToAddr().get(0).getLinkman();
                        phone=userInfoBean.getData().getShipToAddr().get(0).getPhone();
                        myAddress=userInfoBean.getData().getShipToAddr().get(0).getProvice()+userInfoBean.getData().getShipToAddr().get(0).getCity()+userInfoBean.getData().getShipToAddr().get(0).getDistrict();
                        housenNmber=userInfoBean.getData().getShipToAddr().get(0).getDetail();
                        tv_address.setText(myAddress+housenNmber);
                    }else{
                        phone=null;
                        myAddress=null;
                        housenNmber=null;
                        tv_address.setText("暂无地址");
                    }
                }
            },userId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
