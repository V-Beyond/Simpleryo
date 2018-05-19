package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.MessageAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.MessageListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.XActivityUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglei
 * @ClassNname：MyCourse.java
 * @Describe 我的消息页面
 * @time 2018/3/19 13:28
 */
@ContentView(R.layout.activity_my_msg)
public class MyMsgActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;
    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    MessageAdapter messageAdapter;
    List<MessageListBean.DataBean> messageList = new ArrayList<>();
    @ViewInject(R.id.empty_view)
    private View mEmptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_name.setText("我的消息");
        iv_msg.setVisibility(View.GONE);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(MyMsgActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(messageAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(true);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lrecyclerview.setOnLoadMoreListener(onLoadMoreListener);
        lrecyclerview.forceToRefresh();
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MessageListBean.DataBean dataBean = messageList.get(position);
                if (dataBean.getSecondTypeCode().equalsIgnoreCase("SYSTEM_NOTICE")) {//公告
                    startActivity(new Intent(MyMsgActivity.this, MyNoticeActivity.class).putExtra("msg_id", dataBean.getId()));
                }
                if (dataBean.getSecondTypeCode().equalsIgnoreCase("PAY_SUCCESS")) {//支付消息
                    startActivity(new Intent(MyMsgActivity.this, OrderDetailActivity.class).putExtra("orderId", dataBean.getLinkId()));
                }
                if (dataBean.getSecondTypeCode().equalsIgnoreCase("COURSE_ALERT")) {//课程提醒
                    startActivity(new Intent(MyMsgActivity.this, MyCourseDetailActivity.class).putExtra("id", dataBean.getLinkId()));
                }
            }
        });
    }
    int offset=0;
    int limit=9;
    /**
     * 获取消息列表
     */
    public void getMessageList() {
        SimpleryoNetwork.getMessageList(MyMsgActivity.this, new MyBaseProgressCallbackImpl() {
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                MessageListBean messageListBean = info.getRetDetail(MessageListBean.class);
                if (messageListBean.getCode().equalsIgnoreCase("0")) {
                    if (messageListBean.getData() != null && messageListBean.getData().size() > 0) {
                        if (messageList!=null&&messageList.size()>0){
                            messageList.clear();
                        }
                        messageList.addAll(messageListBean.getData());
                        messageAdapter.setDataList(messageList);
                    } else {
                        if (messageList.size()>0){
                            lrecyclerview.setNoMore(true);
                        }else{
                            lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                        }
                    }
                }
                lrecyclerview.refreshComplete(messageList.size());
                lRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(HttpInfo info) {
                super.onFailure(info);
                TextView textView=mEmptyView.findViewById(R.id.tv_tips);
                textView.setText("数据一不小心走丢了，请稍后回来");
                lrecyclerview.setEmptyView(mEmptyView);
            }
        },offset,limit);
    }
    private OnRefreshListener onRefreshListener=new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (messageList!=null&&messageList.size()>0){
                messageList.clear();
            }
            offset=0;
            limit=9;
            getMessageList();
        }
    };
    private OnLoadMoreListener onLoadMoreListener=new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            offset=limit+1;
            limit+=10;
            getMessageList();
        }
    };

    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(MyMsgActivity.this);
                break;
        }
    }
}
