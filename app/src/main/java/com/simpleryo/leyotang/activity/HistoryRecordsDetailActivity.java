package com.simpleryo.leyotang.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.ComplaintReplyListAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BaseResult;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.ComplaintDetailBean;
import com.simpleryo.leyotang.bean.ComplaintReplyList;
import com.simpleryo.leyotang.bean.PhotoInfo;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.view.MultiImageView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNname：HistoryRecordsListActivity.java
 * @Describe 投诉建议详情
 * @author huanglei
 * @time 2018/7/23 10:28
 */
@ContentView(R.layout.activity_history_records_detail)
public class HistoryRecordsDetailActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @ViewInject(R.id.iv_coach_img)
    ImageView iv_coach_img;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.tv_time)
    TextView tv_time;
    @ViewInject(R.id.tv_content)
    TextView tv_content;
    @ViewInject(R.id.multi_image)
    MultiImageView multi_image;
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    @ViewInject(R.id.edittext_reply)
    EditText edittext_reply;
    String reply;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    ComplaintReplyListAdapter complaintReplyListAdapter;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        iv_msg.setVisibility(View.GONE);
        id=getIntent().getStringExtra("id");
        tv_name.setText("投诉详情");
        lrecyclerview.setNestedScrollingEnabled(false);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(1f)
                .setColorResource(R.color.color_a7a7a7)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        complaintReplyListAdapter = new ComplaintReplyListAdapter(HistoryRecordsDetailActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(complaintReplyListAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(false);
        getComplaintDetail();
        getReplyComplaint();
    }
    //回复评论
    public void replyComplaint(){
        SimpleryoNetwork.replyComplaint(HistoryRecordsDetailActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                edittext_reply.setText("");
                BaseResult baseResult = info.getRetDetail(BaseResult.class);
                if (baseResult.getCode().equalsIgnoreCase("0")) {
                    Toast.makeText(HistoryRecordsDetailActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HistoryRecordsDetailActivity.this,baseResult.getMsg(),Toast.LENGTH_SHORT).show();
                }
                getReplyComplaint();
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
            }
        },id,reply);
    }
    //获取评论详情
    public void getComplaintDetail(){
        SimpleryoNetwork.getComplaintDetail(HistoryRecordsDetailActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                ComplaintDetailBean complaintDetailBean = info.getRetDetail(ComplaintDetailBean.class);
                if (complaintDetailBean.getCode().equalsIgnoreCase("0")) {
                    Picasso.with(HistoryRecordsDetailActivity.this).load(complaintDetailBean.getData().getCreator().getAvatarUrl()).transform(transformation).into(iv_coach_img);
                    tv_title.setText(complaintDetailBean.getData().getCreator().getNickName());
                    tv_content.setText(complaintDetailBean.getData().getBody());
                    tv_time.setText(XStringPars.getCouponTime(complaintDetailBean.getData().getCreationTime()+""));
                    if (complaintDetailBean.getData().getImageUrls() != null && complaintDetailBean.getData().getImageUrls().size() > 0) {
                        final List<PhotoInfo> photos = new ArrayList<>();
                        for (ComplaintDetailBean.DataBean.ImageUrlsBean imageUrlsBean : complaintDetailBean.getData().getImageUrls()) {
                            PhotoInfo p1 = new PhotoInfo();
                            p1.url = imageUrlsBean.getValue();
                            p1.w = 200;
                            p1.h = 200;
                            photos.add(p1);
                        }
                        multi_image.setList(photos);
                    }
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);

            }
        },id);
    }
    List<ComplaintReplyList.DataBean> dataBeanArrayList = new ArrayList<>();
    //获取评论回复列表
    public void getReplyComplaint(){
        SimpleryoNetwork.getReplyComplaint(HistoryRecordsDetailActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                ComplaintReplyList complaintReplyList = info.getRetDetail(ComplaintReplyList.class);
                if (complaintReplyList.getCode().equalsIgnoreCase("0")) {
                    if (complaintReplyList.getData()!=null&&complaintReplyList.getData().size()>0){
                        dataBeanArrayList.addAll(complaintReplyList.getData());
                        complaintReplyListAdapter.setDataList(dataBeanArrayList);
                    }else {
                        lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                    }
                    lrecyclerview.refreshComplete(dataBeanArrayList.size());
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
            }
        },id);
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SharedPreferencesUtils.getKeyBoolean("isLogin");//获取用户登录状态
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCollect(BusEntity bus) {
        if (bus.getType() == 33) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Event(value = {R.id.iv_back,R.id.tv_commit}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(HistoryRecordsDetailActivity.this);
                break;
            case R.id.tv_commit:
                reply=edittext_reply.getText().toString().trim();
                if (reply.equalsIgnoreCase("")){
                    Toast.makeText(HistoryRecordsDetailActivity.this,"请输入回复内容",Toast.LENGTH_SHORT).show();
                    return;
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edittext_reply.getWindowToken(), 0);
                replyComplaint();
                break;
        }
    }
}
