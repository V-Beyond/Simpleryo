package com.simpleryo.leyotang.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.activity.AboutUsActivity;
import com.simpleryo.leyotang.activity.BindAccontActivity;
import com.simpleryo.leyotang.activity.ComplaintProposalActivity;
import com.simpleryo.leyotang.activity.LoginActivity;
import com.simpleryo.leyotang.activity.MyAttentionActivity;
import com.simpleryo.leyotang.activity.MyCollectionActivity;
import com.simpleryo.leyotang.activity.MyCouponsActivity;
import com.simpleryo.leyotang.activity.MyInfoActivity;
import com.simpleryo.leyotang.activity.MyMsgActivity;
import com.simpleryo.leyotang.activity.MyOrderActivity;
import com.simpleryo.leyotang.activity.UseHelpActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.base.XLibraryLazyFragment;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.UserInfoBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.PhotoUtils;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.view.LoadingDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
    @ViewInject(R.id.tv_exit)
    TextView tv_exit;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.tv_collection)
    TextView tv_collection;
    @ViewInject(R.id.tv_attention)
    TextView tv_attention;
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
            EventBus.getDefault().register(this);
            isPrepared = true;
            iv_back.setVisibility(View.GONE);
            tv_name.setText(getResources().getString(R.string.personal_center));
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

    OSS oss;

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        // 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
        OSSCredentialProvider credentialProvider = new OSSCustomSignerCredentialProvider() {
            @Override
            public String signContent(String content) {
                // 您需要在这里依照OSS规定的签名算法，实现加签一串字符内容，并把得到的签名传拼接上AccessKeyId后返回
                // 一般实现是，将字符内容post到您的业务服务器，然后返回签名
                // 如果因为某种原因加签失败，描述error信息后，返回nil
                // 以下是用本地算法进行的演示
//                String token=OSSUtils.sign("LTAIbjVuAOZS2Wq7","tL17dGzj2l2VAqTOLxpRKEPmh4s4Mq",content);
                String token = OSSUtils.sign("LTAIdS9LK729tPQy", "n1BLiLsioXbPcigywAADDgEOdv5XO4", content);
                Log.w("cc", "OSS签名token:" + token);
                return token;
            }
        };
        //该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        oss = new OSSClient(getActivity().getApplicationContext(), endpoint, credentialProvider, conf);
//       EventBus.getDefault().post(new BusEntity(2222));
    }

    ProgressDialog dialog;
    String emmail;
    String loginName;
    String gender;
    String starSign;
    String des;
    boolean isBindWechat;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
        if (bus.getType()==1001){
            SharedPreferencesUtils.saveKeyBoolean("isLogin", false);
            SharedPreferencesUtils.saveKeyString("token","simpleryo");
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }
        if (bus.getType()==402){
            Log.w("cc","uploadAvataPath:"+uploadAvataPath);
            SimpleryoNetwork.updateInfo(getActivity(), new MyBaseProgressCallbackImpl(getActivity()) {
                @Override
                public void onSuccess(HttpInfo info)  {
                    loadingDialog.dismiss();
                    Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new BusEntity(401));
                }
                @Override
                public void onFailure(HttpInfo info)  {
                    loadingDialog.dismiss();
                }
            },userId,emmail,bus.getContent(),loginName,gender,starSign,des,uploadAvataPath);
        }
        if (bus.getType()==401){
            isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
            userId = SharedPreferencesUtils.getKeyString("userId");
            if (isLogin) {
                tv_exit.setVisibility(View.VISIBLE);
                SimpleryoNetwork.getUserInfo(getActivity(), new MyBaseProgressCallbackImpl() {
                    @Override
                    public void onSuccess(HttpInfo info) {
                        super.onSuccess(info);
                        mHasLoadedOnce = true;
                        UserInfoBean userInfoBean = info.getRetDetail(UserInfoBean.class);
                        if (userInfoBean.getCode().equalsIgnoreCase("0")){
                            emmail=userInfoBean.getData().getEmail();
                            gender=userInfoBean.getData().getGender();
                            loginName=userInfoBean.getData().getPhone();
                            starSign=userInfoBean.getData().getStarSign();
                            des=userInfoBean.getData().getIntro();
                            if (userInfoBean.getData().getAvatarUrl() != null) {
                                Picasso.with(getActivity().getApplicationContext()).load(userInfoBean.getData().getAvatarUrl()).transform(transformation).into(iv_avatar);
                            } else {
                                Picasso.with(getActivity().getApplicationContext()).load(R.mipmap.iv_my_info).into(iv_avatar);
                            }
                            if (userInfoBean.getData().getCollectCount() != null) {
                                tv_collection.setText(userInfoBean.getData().getCollectCount() + "");
                            } else {
                                tv_collection.setText("0");
                            }
                            if (userInfoBean.getData().getFollowCount() != null) {
                                tv_attention.setText(userInfoBean.getData().getFollowCount() + "");
                            } else {
                                tv_attention.setText("0");
                            }
                            if (userInfoBean.getData().getNickName() != null) {
                                tv_nickname.setText(userInfoBean.getData().getNickName());
                            } else {
                                tv_nickname.setText("暂无昵称");
                            }
                            if (userInfoBean.getData().getThirdNos()!=null&&userInfoBean.getData().getThirdNos().size()>0){
                                SharedPreferencesUtils.saveKeyBoolean("isBindWechat",true);
                            }else{
                                SharedPreferencesUtils.saveKeyBoolean("isBindWechat",false);
                            }
                        }

                    }
                }, userId);
            } else {
                tv_exit.setVisibility(View.GONE);
                tv_nickname.setText(getResources().getString(R.string.no_login));
                tv_collection.setText("0");
                tv_attention.setText("0");
                Picasso.with(getActivity().getApplicationContext()).load("http://p2.so.qhmsg.com/bdr/_240_/t0118ff1cab46ddba27.jpg").transform(transformation).into(iv_avatar);
            }
        }
        if (bus.getType()==403){
//            dialog = ProgressDialog.show(getActivity(), null, "上传中，请稍后", false, true);
            if (loadingDialog == null) {
                loadingDialog = new LoadingDialog(getActivity());
                //点击空白处Dialog不消失
                loadingDialog.setCanceledOnTouchOutside(false);
                loadingDialog.showDialog();
            }
        }
        if (bus.getType()==404){

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    String uploadAvataPath;
    public LoadingDialog loadingDialog;//加载提示框
    public void uploadImg(String filePath) {
        final String fileName = "file/" + XStringPars.md5("simpleryo_android_" + System.currentTimeMillis());
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest("simpleryo-china", fileName, filePath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                EventBus.getDefault().post(new BusEntity(403));
                Log.w("cc","上传进度:"+currentSize+"/"+totalSize);
            }
        });
    OSSAsyncTask ossAsyncTask= oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                uploadAvataPath=SimpleryoNetwork.imgUrl+fileName;
                EventBus.getDefault().post(new BusEntity(402));
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                loadingDialog.dismiss();
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.w("cc", serviceException.getErrorCode());
                    Log.w("cc", serviceException.getRequestId());
                    Log.w("cc", serviceException.getHostId());
                    Log.w("cc", serviceException.getRawMessage());
                }
            }
        });
        ossAsyncTask.waitUntilFinished();
    }

    String mPageName = "MyFragment";

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
    }

    public String userId;

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        EventBus.getDefault().post(new BusEntity(401));
    }

    @Event(value = {R.id.iv_msg,R.id.tv_exit, R.id.iv_coupons_more,R.id.ll_login, R.id.ll_use_help, R.id.ll_wait_pay, R.id.ll_comprehensive_evaluation, R.id.ll_contact_us, R.id.ll_my_course, R.id.iv_avatar, R.id.ll_my_info, R.id.ll_bind_account, R.id.ll_complaint, R.id.ll_my_attention, R.id.ll_collection}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exit://退出
                ExitDialogFragment exitDialogFragment=new ExitDialogFragment();
                exitDialogFragment.show(getFragmentManager(),"exitDialogFragment");
                break;
            case R.id.iv_msg://消息
                startActivity(new Intent(getActivity(), MyMsgActivity.class));
                break;
            case R.id.ll_use_help://使用磅数
                startActivity(new Intent(getActivity(), UseHelpActivity.class));
                break;
            case R.id.ll_contact_us://联系我们
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.ll_wait_pay://待付款
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyOrderActivity.class).putExtra("status", "NEW"));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.iv_coupons_more://优惠券
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyCouponsActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_comprehensive_evaluation://待评价
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyOrderActivity.class).putExtra("status", "RECEIVED"));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_my_course://已完成
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyOrderActivity.class).putExtra("status", "COMPLETED"));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_my_info://基本信息
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyInfoActivity.class).putExtra("userId", userId));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_bind_account://绑定账号
                if (isLogin) {
                    startActivity(new Intent(getActivity(), BindAccontActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_my_attention://我的关注
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyAttentionActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_collection://我的收藏
                if (isLogin) {
                    startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_complaint://投诉建议
                startActivity(new Intent(getActivity(), ComplaintProposalActivity.class));
                break;
            case R.id.ll_login://登录
                if (!isLogin) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.iv_avatar:
                if (isLogin) {
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, CODE_GALLERY_REQUEST);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //相机返回
                case CODE_CAMERA_REQUEST:
                    cropImageUri = Uri.fromFile(fileCropUri);
                    Picasso.with(getActivity().getApplicationContext()).load(cropImageUri).transform(transformation).into(iv_avatar);
                    break;
                //相册返回
                case CODE_GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    Log.w("cc", "filePath:" + filePath);
                    uploadImg(filePath);
//                    Picasso.with(getContext().getApplicationContext()).load(newUri).transform(transformation).into(iv_avatar);
                    break;
                //裁剪返回
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, getActivity());
                    if (bitmap != null) {
                        Picasso.with(getActivity().getApplicationContext()).load("http://p0.so.qhmsg.com/bdr/_240_/t01eb2a6c6319b04655.jpg").transform(transformation).into(iv_avatar);
                    }
                    break;
            }
        }
    }
}
