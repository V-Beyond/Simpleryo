package com.simpleryo.leyotang.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.FragMentAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.StoreDetailBean;
import com.simpleryo.leyotang.fragment.BusinessCoachFragment;
import com.simpleryo.leyotang.fragment.BusinessCourseFragment;
import com.simpleryo.leyotang.fragment.CouponFragment;
import com.simpleryo.leyotang.fragment.ShareDialogFragment;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.view.ArcViewCover;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：BusinessHomeActivty.java
 * @Describe 商家主页
 * @author huanglei
 * @time 2018/3/27 14:24
 */
@ContentView(R.layout.activity_business_home)
public class BusinessHomeActivty extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_share)
    TextView tv_share;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @ViewInject(R.id.view_pager_main)
    ViewPager view_pager_main;
    FragMentAdapter<XLibraryLazyFragment> mAdapter;
    List<XLibraryLazyFragment> fragments = new ArrayList<XLibraryLazyFragment>();
    String storeId;
    @ViewInject(R.id.tv_store_name)
    TextView tv_store_name;
    @ViewInject(R.id.tv_store_status)
    TextView tv_store_status;
    @ViewInject(R.id.iv_licence)
    ImageView iv_licence;
    @ViewInject(R.id.tv_followCount)
    TextView tv_followCount;
    @ViewInject(R.id.arc_headerview)
    ArcViewCover arc_headerview;
    UMShareAPI umShareAPI;//友盟分享
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText(getResources().getString(R.string.business_home));
        iv_msg.setVisibility(View.GONE);
        tv_share.setVisibility(View.VISIBLE);
        storeId=getIntent().getStringExtra("storeId");
        umShareAPI = UMShareAPI.get(this);
        Bundle bundle=new Bundle();
        bundle.putString("storeId",storeId);
        BusinessCourseFragment businessCourseFragment=new BusinessCourseFragment();
        businessCourseFragment.setArguments(bundle);
        BusinessCoachFragment businessCoachFragment=new BusinessCoachFragment();
        businessCoachFragment.setArguments(bundle);
        CouponFragment couponFragment=new CouponFragment();
        couponFragment.setArguments(bundle);
        fragments.add(businessCourseFragment);
        fragments.add(businessCoachFragment);
        fragments.add(couponFragment);
        mAdapter = new FragMentAdapter<XLibraryLazyFragment>(
                getSupportFragmentManager(), fragments);
        view_pager_main.setAdapter(mAdapter);
        view_pager_main.setCurrentItem(0);
        getStoreDetail();
    }

    /**
     * 获取商家详情
     */
    public void getStoreDetail(){
        SimpleryoNetwork.getStoreDetail(BusinessHomeActivty.this,new MyBaseProgressCallbackImpl(BusinessHomeActivty.this){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                StoreDetailBean storeDetailBean=info.getRetDetail(StoreDetailBean.class);
                if (storeDetailBean.getCode().equalsIgnoreCase("0")){
                    tv_store_name.setText(storeDetailBean.getData().getStoreInfo().getName());
                    if (storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDIT_OK")){
                        tv_store_status.setText(getResources().getString(R.string.certified));
                    }else if(storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDITING")){
                        tv_store_status.setText(getResources().getString(R.string.to_be_audited));
                    }
                    else if(storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDIT_FAIL")){
                        tv_store_status.setText(getResources().getString(R.string.not_through));
                    }
                    if (storeDetailBean.getData().getStoreInfo().getLicenceUrl()!=null){
                        Picasso.with(BusinessHomeActivty.this).load(storeDetailBean.getData().getStoreInfo().getLicenceUrl()).into(iv_licence);
                    }
                    if (storeDetailBean.getData().getStoreInfo().getCoverUrl()!=null){
                        coverUrl=storeDetailBean.getData().getStoreInfo().getCoverUrl();
                        Picasso.with(BusinessHomeActivty.this).load(storeDetailBean.getData().getStoreInfo().getCoverUrl()).into( arc_headerview.imageView);
                    }

                    tv_followCount.setText(storeDetailBean.getData().getFollowCount()+"");
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                loadingDialog.dismiss();
                Toast.makeText(BusinessHomeActivty.this,"数据一不小心走丢了，请稍后回来",Toast.LENGTH_SHORT).show();
            }
        },storeId);
    }
    @Event(value = {R.id.radio_group_main}, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_course:
                view_pager_main.setCurrentItem(0);
                break;
            case R.id.radio_btn_coach:
                view_pager_main.setCurrentItem(1);
                break;
            case R.id.radio_btn_coupon:
                view_pager_main.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Event(value = {R.id.iv_back,R.id.iv_msg,R.id.tv_share}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(BusinessHomeActivty.this,MyMsgActivity.class));
                break;
            case R.id.tv_share://分享
                ShareDialogFragment shareDialogFragment = new ShareDialogFragment();
                shareDialogFragment.show(getSupportFragmentManager(), "shareDialogFragment");
                break;
        }
    }

    private ProgressDialog dialog;
    /**
     * 分享回调
     */
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            dialog = ProgressDialog.show(BusinessHomeActivty.this, null, "Loading", false, true);
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            dialog.dismiss();
            Toast.makeText(BusinessHomeActivty.this, "分享成功", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dialog.dismiss();
            Log.w("cc", "onError:" + t.getMessage());
            Toast.makeText(BusinessHomeActivty.this, "分享失败", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            dialog.dismiss();
            Toast.makeText(BusinessHomeActivty.this, "分享取消", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 国内平台分享
     * @param shareMedia
     */
    public void share(SHARE_MEDIA shareMedia) {
        UMWeb web = new UMWeb(SimpleryoNetwork.h5Url+"Main/CourseDetail?id="+storeId);
        web.setTitle(courseName);//标题
        web.setThumb(new UMImage(this, coverUrl));  //缩略图
        web.setDescription(getResources().getString(R.string.share_title));//描述
        new ShareAction(BusinessHomeActivty.this)
                .withMedia(web)
                .setPlatform(shareMedia)
                .setCallback(shareListener)
                .share();
    }

    /**
     * Facebook分享
     * @param shareMedia
     */
    public void shareFaceBook(SHARE_MEDIA shareMedia) {
        UMImage imagelocal = new UMImage(this, coverUrl);
        new ShareAction(BusinessHomeActivty.this).withText(courseName)
                .withMedia(imagelocal)
                .setPlatform(shareMedia)
                .setCallback(shareListener).share();
    }

    String courseName="这个机构的课程和教练都很棒，快来围观~";
    String coverUrl;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 701) {//微信
            if (umShareAPI.isInstall(BusinessHomeActivty.this,SHARE_MEDIA.WEIXIN)) {
                share(SHARE_MEDIA.WEIXIN);
            } else {
                Toast.makeText(BusinessHomeActivty.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
            }
        }
        if (bus.getType() == 702) {//朋友圈
            if (umShareAPI.isInstall(BusinessHomeActivty.this,SHARE_MEDIA.WEIXIN)) {
                share(SHARE_MEDIA.WEIXIN_CIRCLE);
            } else {
                Toast.makeText(BusinessHomeActivty.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
            }
        }
        if (bus.getType() == 703) {//支付宝
            share(SHARE_MEDIA.ALIPAY);
        }
        if (bus.getType() == 704) {//Facebook
            shareFaceBook(SHARE_MEDIA.FACEBOOK);
        }
    }
}
