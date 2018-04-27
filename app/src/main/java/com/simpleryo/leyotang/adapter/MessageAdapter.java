package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.MessageListBean;
import com.simpleryo.leyotang.viewholder.MessageViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class MessageAdapter extends BaseAdapter<MessageListBean.DataBean> {

    public MessageAdapter(Context context) {
        super(context);
    }
    public final static String CSS_STYLE = "<style>* {font-size:14px;line-height:20px;}p {color:#989898;font-size:12px}</style>";
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
             ((MessageViewHolder) holder).tv_title.setText(dataBean.getTitle());
             if (dataBean.getBody() != null) {
                 ((MessageViewHolder) holder).tv_content.loadDataWithBaseURL("about:blank", CSS_STYLE+dataBean.getBody() , "text/html", "utf-8", null);
             } else {
                 ((MessageViewHolder) holder).tv_content.loadDataWithBaseURL("about:blank", CSS_STYLE+"<p>暂无详情</p>", "text/html", "utf-8", null);
             }

         }
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
