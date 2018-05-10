package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.ImagePickerAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BaseResult;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.ImageItemBean;
import com.simpleryo.leyotang.bean.OrderDetailBean;
import com.simpleryo.leyotang.imageloader.GlideImageLoader;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.view.SelectDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：MyCourse.java
 * @Describe 订单评论页面
 * @author huanglei
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_remark_course)
public class RemarkCourseActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener{
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_order_img)
    ImageView iv_order_img;
    @ViewInject(R.id.tv_course_name)
    TextView tv_course_name;
    @ViewInject(R.id.edittext_comment)
    EditText edittext_comment;
    @ViewInject(R.id.rating_bar)
    RatingBar rating_bar;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    String orderId;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数
    public Transformation transformation = new RoundedTransformationBuilder()
            .cornerRadiusDp(30)
            .borderColor(Color.WHITE)
            .borderWidth(10)
            .oval(true)
            .build();
    String token;
    String corseId;
    OSS oss;
    JsonArray json=new JsonArray();
    ArrayList<ImageItemBean> imageItemBeans = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("晒单评价");
        EventBus.getDefault().register(this);
                String endpoint = "oss-cn-hangzhou.aliyuncs.com";
//        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        // 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
        OSSCredentialProvider credentialProvider = new OSSCustomSignerCredentialProvider() {
            @Override
            public String signContent(String content) {
                // 您需要在这里依照OSS规定的签名算法，实现加签一串字符内容，并把得到的签名传拼接上AccessKeyId后返回
                // 一般实现是，将字符内容post到您的业务服务器，然后返回签名
                // 如果因为某种原因加签失败，描述error信息后，返回nil
                // 以下是用本地算法进行的演示
//                String token= OSSUtils.sign("LTAIbjVuAOZS2Wq7","tL17dGzj2l2VAqTOLxpRKEPmh4s4Mq",content);
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
        oss = new OSSClient(RemarkCourseActivity.this, endpoint, credentialProvider, conf);
        //最好放到 Application oncreate执行
        initImagePicker();
        initWidget();
        orderId=getIntent().getStringExtra("orderId");
        SimpleryoNetwork.getOrderDetail(RemarkCourseActivity.this,new MyBaseProgressCallbackImpl(RemarkCourseActivity.this){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
                OrderDetailBean orderDetailBean=info.getRetDetail(OrderDetailBean.class);
                if (orderDetailBean.getCode().equalsIgnoreCase("0")){
                    corseId=orderDetailBean.getData().getCourseId();
                    Picasso.with(RemarkCourseActivity.this).load(orderDetailBean.getData().getImageUrl()).transform(transformation).into(iv_order_img);
                    tv_course_name.setText("课程名称："+orderDetailBean.getData().getCourseName());
                }

            }
        },orderId);
    }
    String comment;
    @Event(value = {R.id.iv_back,R.id.tv_commit,R.id.iv_msg}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(RemarkCourseActivity.this);
                break;
            case R.id.iv_msg:
                startActivity(new Intent(RemarkCourseActivity.this,MyMsgActivity.class));
                break;
            case R.id.tv_commit://评论订单
                int count = imageItemBeans.size();
                for (int i = 0; i < count; i++) {
                    JsonObject jsonObject=new JsonObject();
                    jsonObject.addProperty("value", imageItemBeans.get(i).getValue());
                    json.add(jsonObject);
                }
                comment=edittext_comment.getText().toString().trim();
                if (TextUtils.isEmpty(comment)){
                    Toast.makeText(RemarkCourseActivity.this,"请留下你的课程感想",Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleryoNetwork.reviewCourse(RemarkCourseActivity.this,new MyBaseProgressCallbackImpl(RemarkCourseActivity.this){
                    @Override
                    public void onSuccess(HttpInfo info) {
                        super.onSuccess(info);
                        loadingDialog.dismiss();
                        BaseResult baseResult=info.getRetDetail(BaseResult.class);
                        if (baseResult.getCode().equalsIgnoreCase("0")){
                            Toast.makeText(RemarkCourseActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                            XActivityUtils.getInstance().popActivity(RemarkCourseActivity.this);
                        }else{
                            Toast.makeText(RemarkCourseActivity.this,baseResult.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(HttpInfo info) {
                        super.onFailure(info);
                        loadingDialog.dismiss();
                        Toast.makeText(RemarkCourseActivity.this,"数据一不小心走丢了，请稍后回来",Toast.LENGTH_SHORT).show();
                    }
                },orderId,comment,System.currentTimeMillis()+"",rating_bar.getRating()+"",json);
                break;
        }
    }
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(RemarkCourseActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(RemarkCourseActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                    EventBus.getDefault().post(new BusEntity(405));
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                    EventBus.getDefault().post(new BusEntity(405));
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSex(BusEntity bus) {
        if (bus.getType()==405) {
            uploadImages(selImageList);
        }
    }
    public void uploadImages(ArrayList<ImageItem> imageItems){
        if (null == imageItems || imageItems.size() == 0) {
            return;
        } // 上传文件
        uploadImg(imageItems);
    }
    String uploadAvataPath;

    /**
     * 上传图片
     * @param imageItems
     */
    public void uploadImg(final ArrayList<ImageItem> imageItems) {
        final String fileName = "file/" + XStringPars.md5("simpleryo_android_" + System.currentTimeMillis());
        if (imageItems.size() <= 0) {
            // 文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
            return;// 这个return必须有，否则下面报越界异常，原因自己思考下哈
        }
        final ImageItem imageItem = imageItems.get(0);
        if (TextUtils.isEmpty(imageItem.path)) {
            imageItems.remove(0);
            // url为空就没必要上传了，这里做的是跳过它继续上传的逻辑。
            uploadImg(imageItems);
            return;
        }

        File file = new File(imageItem.path);
        if (null == file || !file.exists()) {
            imageItems.remove(0);
            // 文件为空或不存在就没必要上传了，这里做的是跳过它继续上传的逻辑。
            uploadImg(imageItems);
            return;
        }
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest("simpleryo-china", fileName, imageItem.path);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });
        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                imageItems.remove(0);
                uploadImg(imageItems);// 递归同步效果
                uploadAvataPath = SimpleryoNetwork.imgUrl + fileName;
                ImageItemBean imageItemBean = new ImageItemBean();
                imageItemBean.setValue(uploadAvataPath);
                imageItemBeans.add(imageItemBean);
                Log.w("cc", "UploadSuccess");
                Log.w("cc", result.getETag());
                Log.d("cc", result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
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
    }

}
