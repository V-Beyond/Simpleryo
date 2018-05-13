package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.MessageListBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.MessageViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;


/**
 * @ClassNname：MessageAdapter.java
 * @Describe 消息列表item适配器
 * @author huanglei
 * @time 2018/5/8 11:29
 */

public class MessageAdapter extends BaseAdapter<MessageListBean.DataBean> {

    public MessageAdapter(Context context) {
        super(context);
    }
    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new MessageViewHolder(layoutInflater.inflate(R.layout.layout_message_item, parent, false));
    }
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
         MessageListBean.DataBean  dataBean= listData.get(position);
         if (holder instanceof  MessageViewHolder){
             String secondTypeCode=dataBean.getSecondTypeCode();
             if (secondTypeCode.equalsIgnoreCase("SYSTEM_NOTICE")){
                ((MessageViewHolder) holder).iv_msg_category.setImageResource(R.mipmap.iv_msg_content);
             }else if(secondTypeCode.equalsIgnoreCase("COURSE_ALERT")){
                 ((MessageViewHolder) holder).iv_msg_category.setImageResource(R.mipmap.iv_msg_email);
             }else if(secondTypeCode.equalsIgnoreCase("PAY_SUCCESS")){
                 ((MessageViewHolder) holder).iv_msg_category.setImageResource(R.mipmap.iv_msg_buy);
             }
             if (dataBean.getTitle()!=null){
                 ((MessageViewHolder) holder).tv_title.setText(dataBean.getTitle());
             }else{
                 ((MessageViewHolder) holder).tv_title.setText("暂无标题");
             }

            ((MessageViewHolder) holder).tv_time.setText(XStringPars.getStrTime(dataBean.getCreationTime()));
         }
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
