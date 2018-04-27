package com.simpleryo.leyotang.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.AboutUsActivity;
import com.simpleryo.leyotang.activity.BindAccontActivity;
import com.simpleryo.leyotang.activity.ComplaintProposalActivity;
import com.simpleryo.leyotang.activity.LoginActivity;
import com.simpleryo.leyotang.activity.MyAttentionActivity;
import com.simpleryo.leyotang.activity.MyCollectionActivity;
import com.simpleryo.leyotang.activity.MyInfoActivity;
import com.simpleryo.leyotang.activity.MyMsgActivity;
import com.simpleryo.leyotang.activity.MyOrderActivity;
import com.simpleryo.leyotang.activity.UseHelpActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.UserInfoBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.PhotoUtils;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.umeng.analytics.MobclickAgent;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * @author huanglei
 * @ClassNname：MyFragment.java
 * @Describe 个人中心fragment
 * @time 2018/3/19 11:10
 */

public class MyFragment extends XLibraryLazyFragment {
    @ViewInject(R.id.iv_avatar)
    ImageView iv_avatar;
    Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadius(30)
            .borderWidth(10)
            .borderColor(Color.WHITE)
            .oval(true)
            .build();
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_collection)
    TextView  tv_collection;
    @ViewInject(R.id.tv_attention)
    TextView  tv_attention;
    @ViewInject(R.id.tv_nickname)
    TextView tv_nickname;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/avatar.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_avatar.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private static final int OUTPUT_X = 200;
    private static final int OUTPUT_Y = 200;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            mMainView = inflater
                    .inflate(R.layout.fragment_my, container, false);
            x.view().inject(this, mMainView);
            isPrepared = true;
            iv_back.setVisibility(View.GONE);
            tv_name.setText("个人中心");
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mMainView.getParent();
        if (parent != null) {
            parent.removeView(mMainView);
        }
        return mMainView;
    }

    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
    }
    String mPageName="MyFragment";
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
    }

    public  String userId;
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
        userId= SharedPreferencesUtils.getKeyString("userId");
        if (isLogin) {
            SimpleryoNetwork.getUserInfo(getActivity(),new MyBaseProgressCallbackImpl(){
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    mHasLoadedOnce=true;
                    UserInfoBean userInfoBean=info.getRetDetail(UserInfoBean.class);
                    if (userInfoBean.getData().getAvatarUrl()!=null){
                        Picasso.with(getContext().getApplicationContext()).load(userInfoBean.getData().getAvatarUrl()).transform(transformation).into(iv_avatar);
                    }else{
                        Picasso.with(getContext().getApplicationContext()).load(R.mipmap.iv_my_info).into(iv_avatar);
                    }
                    if (userInfoBean.getData().getCollectCount()!=null){
                        tv_collection.setText(userInfoBean.getData().getCollectCount()+"");
                    }else{
                        tv_collection.setText("0");
                    }
                    if (userInfoBean.getData().getFollowCount()!=null){
                        tv_attention.setText(userInfoBean.getData().getFollowCount()+"");
                    }else{
                        tv_attention.setText("0");
                    }
                    if (userInfoBean.getData().getNickName()!=null){
                        tv_nickname.setText(userInfoBean.getData().getNickName());
                    }else{
                        tv_nickname.setText("暂无昵称");
                    }

                }
            },userId);
        }else{
            tv_nickname.setText("未登录");
            Picasso.with(getContext().getApplicationContext()).load("http://p2.so.qhmsg.com/bdr/_240_/t0118ff1cab46ddba27.jpg").transform(transformation).into(iv_avatar);
        }
    }

    @Event(value = {R.id.iv_msg,R.id.ll_login, R.id.ll_use_help,R.id.ll_wait_pay,R.id.ll_comprehensive_evaluation, R.id.ll_contact_us, R.id.ll_my_course, R.id.iv_avatar, R.id.ll_my_info, R.id.ll_bind_account, R.id.ll_complaint, R.id.ll_my_attention, R.id.ll_collection}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                SharedPreferencesUtils.saveKeyBoolean("isLogin", false);
                SharedPreferencesUtils.saveKeyString("token","simpleryo");
                startActivity(new Intent(getActivity(), MyMsgActivity.class));
                break;
            case R.id.ll_use_help:
                startActivity(new Intent(getActivity(), UseHelpActivity.class));
                break;
            case R.id.ll_contact_us:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.ll_wait_pay:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyOrderActivity.class).putExtra("status","NEW"));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_comprehensive_evaluation:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyOrderActivity.class).putExtra("status","RECEIVED"));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_my_course:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyOrderActivity.class).putExtra("status","COMPLETED"));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_my_info:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyInfoActivity.class).putExtra("userId",userId));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_bind_account:
                startActivity(new Intent(getActivity(), BindAccontActivity.class));
                break;
            case R.id.ll_my_attention:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyAttentionActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_collection:
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_complaint:
                startActivity(new Intent(getActivity(), ComplaintProposalActivity.class));
                break;
            case R.id.ll_login:
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.iv_avatar:
                if (isLogin) {
                    PhotoUtils.openPic(getActivity(), CODE_GALLERY_REQUEST);
//                    imageUri = Uri.fromFile(fileUri);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                        imageUri = FileProvider.getUriForFile(getActivity(), "com.simpleryo.nz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
//                        PhotoUtils.takePicture(getActivity(), imageUri, CODE_CAMERA_REQUEST);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
//                SystemProgramUtils.zhaopian(getActivity());
                break;
        }
    }

    String imgPath = getInnerSDCardPath() + "/simpleryo/icon/avatar.jpg";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode) {
                //相机返回
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
//                Picasso.with(getContext().getApplicationContext()).load("http://p0.so.qhmsg.com/bdr/_240_/t01eb2a6c6319b04655.jpg").transform(transformation).into(iv_avatar);
//                 PhotoUtils.cropImageUri(getActivity(), imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
                    Picasso.with(getContext().getApplicationContext()).load(cropImageUri).transform(transformation).into(iv_avatar);
                    break;
                //相册返回
                case CODE_GALLERY_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    Uri newUri = Uri.parse(PhotoUtils.getPath(getActivity(), data.getData()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        newUri = FileProvider.getUriForFile(getActivity(), "com.simpleryo.nz.fileprovider", new File(newUri.getPath()));
                    }
                    Picasso.with(getContext().getApplicationContext()).load(newUri).transform(transformation).into(iv_avatar);
                    break;
                //裁剪返回
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, getActivity());
                    if (bitmap != null) {
                        Picasso.with(getContext().getApplicationContext()).load("http://p0.so.qhmsg.com/bdr/_240_/t01eb2a6c6319b04655.jpg").transform(transformation).into(iv_avatar);
                    }
                    break;
            }
        }

//        if (resultCode != RESULT_OK) {
//            return;
//        }
//        Uri filtUri;
//        File outputFile = new File(getInnerSDCardPath()+"/simpleryo/icon/avatar_out.jpg");//裁切后输出的图片
//        switch (requestCode) {
//            case SystemProgramUtils.REQUEST_CODE_PAIZHAO:
//                //拍照完成，进行图片裁切
//                File file = new File(imgPath);
//                filtUri = FileProviderUtils.uriFromFile(getActivity(), file);
//                SystemProgramUtils.Caiqie(getActivity(), filtUri, outputFile);
//                break;
//            case SystemProgramUtils.REQUEST_CODE_ZHAOPIAN:
//                //相册选择图片完毕，进行图片裁切
//                if (data == null ||  data.getData()==null) {
//                    return;
//                }
//                filtUri = data.getData();
//                SystemProgramUtils.Caiqie(getActivity(), filtUri, outputFile);
//                break;
//            case SystemProgramUtils.REQUEST_CODE_CAIQIE:
//                //图片裁切完成，显示裁切后的图片
//                try {
//                    Uri uri = Uri.fromFile(outputFile);
//                    Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
//                    iv_avatar.setImageBitmap(bitmap);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//                break;
//        }
    }
}
