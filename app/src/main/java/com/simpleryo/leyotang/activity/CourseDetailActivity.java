package com.simpleryo.leyotang.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.CodeBean;
import com.simpleryo.leyotang.bean.CourdeDetailBean;
import com.simpleryo.leyotang.bean.StoreDetailBean;
import com.simpleryo.leyotang.fragment.ShareDialogFragment;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.wxapi.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 课程详情页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_course_detail)
public class CourseDetailActivity extends BaseActivity  implements IWXAPIEventHandler {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.tv_share)
    TextView tv_share;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @ViewInject(R.id.iv_course_detail_img)
    ImageView iv_course_detail_img;
    @ViewInject(R.id.iv_business_course_img)
    ImageView iv_business_course_img;
    @ViewInject(R.id.iv_qualification_certificate_img)
    ImageView iv_qualification_certificate_img;
    @ViewInject(R.id.tv_course_category)
    TextView tv_course_category;
    @ViewInject(R.id.tv_coach_name)
    TextView tv_coach_name;
    @ViewInject(R.id.tv_course_time)
    TextView tv_course_time;
    @ViewInject(R.id.tv_coach_intro)
    TextView tv_coach_intro;
    @ViewInject(R.id.tv_price)
    TextView tv_price;
    @ViewInject(R.id.tv_popular)
    TextView tv_popular;
    @ViewInject(R.id.tv_course_detail)
    WebView tv_course_detail;
    String courseId;
    @ViewInject(R.id.iv_coach_img)
    public ImageView iv_coach_img;
    @ViewInject(R.id.tv_address)
    TextView tv_address;
    @ViewInject(R.id.tv_course_address)
    TextView tv_course_address;
    @ViewInject(R.id.tv_join_count)
    TextView tv_join_count;
    @ViewInject(R.id.tv_goodreviewrate)
    TextView tv_goodreviewrate;
    @ViewInject(R.id.tv_store_status)
    TextView tv_store_status;
    @ViewInject(R.id.rating_bar)
    RatingBar rating_bar;
    @ViewInject(R.id.tv_studentCount)
    TextView tv_studentCount;
    @ViewInject(R.id.tv_point)
    TextView tv_point;
    @ViewInject(R.id.tv_collection)
    TextView tv_collection;
    boolean hasCollect;//是否收藏
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadius(10)
            .oval(false)
            .build();
    public final static String CSS_STYLE = "<style>* {font-size:14px;line-height:20px;}p {color:#373737;font-size:12px}</style>";
    UMShareAPI umShareAPI;//友盟分享
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("课程详情");
        iv_msg.setVisibility(View.GONE);
        tv_share.setVisibility(View.VISIBLE);
        umShareAPI = UMShareAPI.get(this);
        EventBus.getDefault().register(this);//注册EventBus
        courseId = getIntent().getStringExtra("courseId");
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
        EventBus.getDefault().post(new BusEntity(113));
    }

    /**
     * 国内平台分享
     * @param shareMedia
     */
    public void share(SHARE_MEDIA shareMedia) {
        UMWeb web = new UMWeb(SimpleryoNetwork.h5Url+"Main/CourseDetail?id="+courseId);
        web.setTitle(courseName);//标题
        web.setThumb(new UMImage(this, coverUrl));  //缩略图
        web.setDescription("我在乐友堂推荐你一个课程");//描述
        new ShareAction(CourseDetailActivity.this)
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
        new ShareAction(CourseDetailActivity.this).withText(courseName)
                .withMedia(imagelocal)
                .setPlatform(shareMedia)
                .setCallback(shareListener).share();
    }

    String courseName;
    String coverUrl;

    /**
     * 微信原生SDK分享
     * @param type
     */
    public void shareToWeiXin(int type){
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = SimpleryoNetwork.h5Url+"Main/CourseDetail?id="+courseId;//设置跳转的url
        final WXMediaMessage mediaMessage = new WXMediaMessage(wxWebpageObject);
        mediaMessage.title = "乐友堂";//设置标题
        mediaMessage.description = "我在乐友堂推荐你一个课程";//设置内容
        SendMessageToWX.Req  req = new SendMessageToWX.Req();
        req.transaction = "webpage";//分享类型为网页
        req.message = mediaMessage;//分享的内容为上面的信息
        req.scene = type;//设置分享到微信好友OR朋友圈
        api.sendReq(req);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 701) {//微信
            if (api.isWXAppInstalled()) {
//                share(SHARE_MEDIA.WEIXIN);
                shareToWeiXin(SendMessageToWX.Req.WXSceneSession);
            } else {
                Toast.makeText(CourseDetailActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
            }
        }
        if (bus.getType() == 702) {//朋友圈
            if (api.isWXAppInstalled()) {
//                share(SHARE_MEDIA.WEIXIN_CIRCLE);
                shareToWeiXin(SendMessageToWX.Req.WXSceneTimeline);
            } else {
                Toast.makeText(CourseDetailActivity.this, "请安装微信客户端", Toast.LENGTH_SHORT).show();
            }
        }
        if (bus.getType() == 703) {//支付宝
            share(SHARE_MEDIA.ALIPAY);
        }
        if (bus.getType() == 704) {//Facebook
            shareFaceBook(SHARE_MEDIA.FACEBOOK);
        }
        if (bus.getType() == 222) {
            getStoreDetail(storeId);
        }
        if (bus.getType() == 113) {//获取课程详情
            SimpleryoNetwork.getCourseDetail(CourseDetailActivity.this, new MyBaseProgressCallbackImpl(CourseDetailActivity.this) {
                @Override
                public void onSuccess(HttpInfo info) {
                    super.onSuccess(info);
                    loadingDialog.dismiss();
                    CourdeDetailBean courdeDetailBean = info.getRetDetail(CourdeDetailBean.class);
                    if (courdeDetailBean.getCode().equalsIgnoreCase("0")) {
                        storeId = courdeDetailBean.getData().getStoreId();
                        if (courdeDetailBean.getData().getTagId1() != null) {
                            courseName = courdeDetailBean.getData().getName();
                            tv_course_category.setText(courseName);
                        }
                        if (courdeDetailBean.getData().getCoverUrl() == null) {
                            Picasso.with(CourseDetailActivity.this).load("http://p0.so.qhmsg.com/bdr/_240_/t01eb2a6c6319b04655.jpg").into(iv_course_detail_img);
                        } else {
                            coverUrl = courdeDetailBean.getData().getCoverUrl();
                            Picasso.with(CourseDetailActivity.this).load(coverUrl).into(iv_course_detail_img);
                        }
                        hasCollect = courdeDetailBean.getData().isHasCollect();
                        if (hasCollect) {
                            tv_collection.setText("已收藏");
                        } else {
                            tv_collection.setText("收藏");
                        }
                        tv_coach_name.setText(courdeDetailBean.getData().getCoach().getNickName());
                        tv_price.setText(XStringPars.foramtPrice(Integer.valueOf(courdeDetailBean.getData().getPrice())) + "$");
                        tv_join_count.setText("已有" + courdeDetailBean.getData().getClassCount() + "人参加");
                        tv_popular.setText(courdeDetailBean.getData().getClassCount() + "个人正在学习");
                        tv_goodreviewrate.setText("好评率：" + courdeDetailBean.getData().getGoodReviewRate() + "%");
                        //上课时间
                        if (courdeDetailBean.getData().getDurations().getData() != null && courdeDetailBean.getData().getDurations().getData().size() > 0) {
                            StringBuilder durations = new StringBuilder();
                            durations.append(courdeDetailBean.getData().getDurations().getStartDate() + "至" + courdeDetailBean.getData().getDurations().getEndDate());
                            durations.append("\n");
                            for (int i = 0; i < courdeDetailBean.getData().getDurations().getData().size(); i++) {
                                CourdeDetailBean.DataBeanX.DurationsBean.DataBean dataBean = courdeDetailBean.getData().getDurations().getData().get(i);
                                durations.append(dataBean.getWeek() + "   " + dataBean.getStartTime() + "-" + dataBean.getEndTime());
                                if (i < courdeDetailBean.getData().getDurations().getData().size() - 1) {
                                    durations.append("\n");
                                }
                            }
                            tv_course_time.setText(durations);
                        }
                        if (courdeDetailBean.getData().getIntro() != null) {
                            tv_course_detail.loadDataWithBaseURL("about:blank", CSS_STYLE + courdeDetailBean.getData().getIntro(), "text/html", "utf-8", null);
                        } else {
                            tv_course_detail.loadDataWithBaseURL("about:blank", CSS_STYLE + "<p>暂无详情</p>", "text/html", "utf-8", null);
                        }
                        tv_coach_intro.setBackgroundColor(0);
                        CourdeDetailBean.DataBeanX.CoachBean coachBean = courdeDetailBean.getData().getCoach();
                        if (coachBean != null) {
                            tv_studentCount.setText(coachBean.getStudentCount() + "");
                            rating_bar.setRating(coachBean.getPoint());
                            tv_point.setText(coachBean.getPoint() + "");
                            Picasso.with(CourseDetailActivity.this).load(coachBean.getAvatarUrl()).into(iv_coach_img);
                            tv_coach_intro.setText(coachBean.getIntro());
                        } else {
                            tv_coach_intro.setText("暂无介绍");
                        }

                        CourdeDetailBean.DataBeanX.AddressBean addressBean = courdeDetailBean.getData().getAddress();
                        if (addressBean != null) {
//                    String address=addressBean.getProvice()+addressBean.getCity()+addressBean.getDistrict()+addressBean.getDetail();
                            String address = addressBean.getDetail();
                            tv_course_address.setText("线下授课，授课地点：" + address);
                        }
                        EventBus.getDefault().post(new BusEntity(222, storeId));
                    }
                }

                @Override
                public void onFailure(HttpInfo info) {
                    super.onFailure(info);
                    loadingDialog.dismiss();
                    Toast.makeText(CourseDetailActivity.this, "数据一不小心走丢了，请稍后回来", Toast.LENGTH_SHORT).show();
                }
            }, courseId);
        }
    }

    boolean isFollow;//商家是否关注

    /**
     * 获取商家详情
     * @param storeId
     */
    public void getStoreDetail(String storeId) {
        SimpleryoNetwork.getStoreDetail(CourseDetailActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                StoreDetailBean storeDetailBean = info.getRetDetail(StoreDetailBean.class);
                if (storeDetailBean.getCode().equalsIgnoreCase("0")) {
                    isFollow = storeDetailBean.getData().isHasFollow();
                    if (storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDIT_OK")) {
                        tv_store_status.setText("已认证");
                    } else if (storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDITING")) {
                        tv_store_status.setText("待审核");
                    } else if (storeDetailBean.getData().getStoreInfo().getStatus().equalsIgnoreCase("AUDIT_FAIL")) {
                        tv_store_status.setText("未通过");
                    }
                    if (storeDetailBean.getData().getStoreInfo().getAddress() != null) {
                        tv_address.setText(storeDetailBean.getData().getStoreInfo().getAddress().getDetail());
                    } else {
                        tv_address.setText("暂无地址");
                    }
                    if (storeDetailBean.getData().getStoreInfo().getCoverUrl() != null) {
                        Picasso.with(CourseDetailActivity.this).load(storeDetailBean.getData().getStoreInfo().getCoverUrl()).into(iv_business_course_img);
                    }
                    if (storeDetailBean.getData().getStoreInfo().getLicenceUrl() != null) {
                        Picasso.with(CourseDetailActivity.this).load(storeDetailBean.getData().getStoreInfo().getLicenceUrl()).into(iv_qualification_certificate_img);
                    }
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
            }
        }, storeId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    boolean isLogin;
    String storeId;

    @Event(value = {R.id.iv_back, R.id.tv_reservations, R.id.tv_share, R.id.tv_business_home, R.id.ll_collection, R.id.tv_to_home, R.id.tv_to_use_help}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(CourseDetailActivity.this);
                break;
            case R.id.tv_to_home:
                Intent intent2 = new Intent(CourseDetailActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_to_use_help://关注与取消关注商家
                if (isLogin) {
                    if (isFollow) {//取消关注
                        SimpleryoNetwork.disfollowStore(CourseDetailActivity.this, new MyBaseProgressCallbackImpl(CourseDetailActivity.this) {
                            @Override
                            public void onSuccess(HttpInfo info) {
                                super.onSuccess(info);
                                loadingDialog.dismiss();
                                CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                                if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                    EventBus.getDefault().post(new BusEntity(113));
                                    Toast.makeText(CourseDetailActivity.this, "取消关注成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CourseDetailActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(HttpInfo info) {
                                super.onFailure(info);
                                loadingDialog.dismiss();
                            }
                        }, storeId);
                    } else {//关注
                        SimpleryoNetwork.followStore(CourseDetailActivity.this, new MyBaseProgressCallbackImpl(CourseDetailActivity.this) {
                            @Override
                            public void onSuccess(HttpInfo info) {
                                super.onSuccess(info);
                                loadingDialog.dismiss();
                                CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                                if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                    EventBus.getDefault().post(new BusEntity(113));
                                    Toast.makeText(CourseDetailActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CourseDetailActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(HttpInfo info) {
                                super.onFailure(info);
                                loadingDialog.dismiss();
                            }
                        }, storeId);
                    }
                } else {
                    Intent intent = new Intent(CourseDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_collection://收藏与取消收藏课程
                if (isLogin) {
                    if (hasCollect) {//收藏
                        SimpleryoNetwork.collectCourse(CourseDetailActivity.this, new MyBaseProgressCallbackImpl(CourseDetailActivity.this) {
                            @Override
                            public void onSuccess(HttpInfo info) {
                                super.onSuccess(info);
                                loadingDialog.dismiss();
                                CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                                if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                    EventBus.getDefault().post(new BusEntity(113));
                                    Toast.makeText(CourseDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CourseDetailActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, courseId);
                    } else {//取消收藏
                        SimpleryoNetwork.disCollectCourse(CourseDetailActivity.this, new MyBaseProgressCallbackImpl(CourseDetailActivity.this) {
                            @Override
                            public void onSuccess(HttpInfo info) {
                                super.onSuccess(info);
                                loadingDialog.dismiss();
                                CodeBean createOrderBean = info.getRetDetail(CodeBean.class);
                                if (createOrderBean.getCode().equalsIgnoreCase("0")) {
                                    EventBus.getDefault().post(new BusEntity(113));
                                    Toast.makeText(CourseDetailActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CourseDetailActivity.this, createOrderBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(HttpInfo info) {
                                super.onFailure(info);
                                loadingDialog.dismiss();
                            }
                        }, courseId);
                    }
                } else {
                    Intent intent = new Intent(CourseDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_reservations://确定订单
                if (isLogin) {
                    Intent intent = new Intent(CourseDetailActivity.this, ComfirmOrderActivity.class);
                    intent.putExtra("courseId", courseId);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CourseDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_business_home:
                Intent businessIntent = new Intent(CourseDetailActivity.this, BusinessHomeActivty.class);
                businessIntent.putExtra("storeId", storeId);
                startActivity(businessIntent);
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
            dialog = ProgressDialog.show(CourseDetailActivity.this, null, "Loading", false, true);
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            dialog.dismiss();
            Toast.makeText(CourseDetailActivity.this, "分享成功", Toast.LENGTH_LONG).show();
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
            Toast.makeText(CourseDetailActivity.this, "分享失败", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            dialog.dismiss();
            Toast.makeText(CourseDetailActivity.this, "分享取消", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }
}
