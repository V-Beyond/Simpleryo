package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.simpleryo.leyotang.bean.OrderDetailBean;
import com.simpleryo.leyotang.bean.QiNiuTokenDataBean;
import com.simpleryo.leyotang.imageloader.GlideImageLoader;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.view.SelectDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：MyCourse.java
 * @Describe 我的订单页面
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("晒单评价");
        //最好放到 Application oncreate执行
        initImagePicker();
        initWidget();
        SimpleryoNetwork.getQiNiuToken(RemarkCourseActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                QiNiuTokenDataBean qiNiuTokenDataBean=info.getRetDetail(QiNiuTokenDataBean.class);
                token=qiNiuTokenDataBean.getToken();
            }
        });
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
    @Event(value = {R.id.iv_back,R.id.tv_commit}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(RemarkCourseActivity.this);
                break;
            case R.id.tv_commit:
                comment=edittext_comment.getText().toString().trim();
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
                },orderId,comment,System.currentTimeMillis()+"",rating_bar.getRating()+"");
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
                    for (ImageItem imageItem:images){
                        uploadPic(token,bitmapToString(imageItem.path));
                    }
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
                }
            }
        }
    }



    public void uploadPic(String key,String localFile){
        SimpleryoNetwork.uploadPicToQiNiu(RemarkCourseActivity.this,new MyBaseProgressCallbackImpl(RemarkCourseActivity.this){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                loadingDialog.dismiss();
            }
        },key,localFile);
    }
    //把bitmap转换成String
    public static String bitmapToString(String filePath) {
        Bitmap bm = getSmallBitmap(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //1.5M的压缩后在100Kb以内，测试得值,压缩后的大小=94486字节,压缩后的大小=74473字节
        //这里的JPEG 如果换成PNG，那么压缩的就有600kB这样
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        Log.w("cc", "压缩后的大小=" + b.length);
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
