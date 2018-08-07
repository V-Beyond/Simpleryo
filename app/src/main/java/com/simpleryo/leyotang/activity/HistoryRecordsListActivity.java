package com.simpleryo.leyotang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.okhttplib.HttpInfo;
import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.adapter.ComplaintkListAdapter;
import com.simpleryo.leyotang.base.BaseActivity;
import com.simpleryo.leyotang.base.MyBaseProgressCallbackImpl;
import com.simpleryo.leyotang.bean.BusEntity;
import com.simpleryo.leyotang.bean.ComplainListBean;
import com.simpleryo.leyotang.network.SimpleryoNetwork;
import com.simpleryo.leyotang.utils.SharedPreferencesUtils;
import com.simpleryo.leyotang.utils.XActivityUtils;

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
 * @Describe 历史记录
 * @author huanglei
 * @time 2018/7/23 10:28
 */
@ContentView(R.layout.activity_history_records_list)
public class HistoryRecordsListActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.iv_msg)
    ImageView iv_msg;

    @ViewInject(R.id.lrecyclerview)
    LRecyclerView lrecyclerview;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    ComplaintkListAdapter complaintkListAdapter;
    @ViewInject(R.id.empty_view)
    private View mEmptyView;
    List<ComplainListBean.DataBean> dataBeanArrayList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tv_name.setText(getResources().getString(R.string.historical_records));
        iv_msg.setVisibility(View.GONE);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(1f)
                .setColorResource(R.color.color_a7a7a7)
                .build();
        lrecyclerview.addItemDecoration(divider);
        lrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        complaintkListAdapter = new ComplaintkListAdapter(HistoryRecordsListActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(complaintkListAdapter);
        lrecyclerview.setAdapter(lRecyclerViewAdapter);
        lrecyclerview.setLoadMoreEnabled(false);
        lrecyclerview.setPullRefreshEnabled(true);
        lrecyclerview.setOnRefreshListener(onRefreshListener);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(HistoryRecordsListActivity.this, HistoryRecordsDetailActivity.class).putExtra("id", dataBeanArrayList.get(position).getId()));
            }
        });
        lrecyclerview.forceToRefresh();
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

    public void getComplaintList(){
        SimpleryoNetwork.getComplaintList(HistoryRecordsListActivity.this,new MyBaseProgressCallbackImpl(){
            @Override
            public void onSuccess(HttpInfo info) {
                super.onSuccess(info);
                ComplainListBean complainListBean = info.getRetDetail(ComplainListBean.class);
                if (complainListBean.getCode().equalsIgnoreCase("0")) {
                    if (complainListBean.getData()!=null&&complainListBean.getData().size()>0){
                        dataBeanArrayList.addAll(complainListBean.getData());
                        complaintkListAdapter.setDataList(dataBeanArrayList);
                    }else {
                        lrecyclerview.setEmptyView(mEmptyView);//设置在setAdapter之前才能生效
                    }
                    lrecyclerview.refreshComplete(dataBeanArrayList.size());
                    lRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (dataBeanArrayList!=null&&dataBeanArrayList.size()>0){
                dataBeanArrayList.clear();
            }
            getComplaintList();
        }
    };


    @Event(value = {R.id.iv_back}, type = View.OnClickListener.class)
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                XActivityUtils.getInstance().popActivity(HistoryRecordsListActivity.this);
                break;
        }
    }
}
