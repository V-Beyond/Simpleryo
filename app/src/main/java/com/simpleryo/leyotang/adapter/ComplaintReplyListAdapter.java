package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.ComplaintReplyList;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.ComplaintReplyItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class ComplaintReplyListAdapter extends BaseAdapter<ComplaintReplyList.DataBean> {

    public ComplaintReplyListAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new ComplaintReplyItemViewHolder(layoutInflater.inflate(R.layout.layout_complaint_reply_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final ComplaintReplyList.DataBean bean = listData.get(position);
        if (holder instanceof  ComplaintReplyItemViewHolder){
            if (bean.getCreator()!=null){
                if (bean.getCreator().getAvatarUrl()!=null){
                    Picasso.with(mContext).load(bean.getCreator().getAvatarUrl()).transform(transformation).into(((ComplaintReplyItemViewHolder) holder).iv_coach_img);
                }else{
                    Picasso.with(mContext).load("http://p2.so.qhmsg.com/bdr/_240_/t0118ff1cab46ddba27.jpg").transform(transformation).into(((ComplaintReplyItemViewHolder) holder).iv_coach_img);
                }
                ((ComplaintReplyItemViewHolder) holder).tv_title.setText(bean.getCreator().getNickName());
            }else{
                ((ComplaintReplyItemViewHolder) holder).tv_title.setText("LeYoTown");
                Picasso.with(mContext).load("http://p2.so.qhmsg.com/bdr/_240_/t0118ff1cab46ddba27.jpg").transform(transformation).into(((ComplaintReplyItemViewHolder) holder).iv_coach_img);
            }
            if (bean.getCreationTime()!=null){
                ((ComplaintReplyItemViewHolder) holder).tv_time.setText(XStringPars.getCouponTime(bean.getCreationTime()+""));
            }else{
                ((ComplaintReplyItemViewHolder) holder).tv_time.setText("暂无回复日期");
            }
            if (bean.getBody()!=null){
                ((ComplaintReplyItemViewHolder) holder).tv_content.setText(bean.getBody());
            }else{
                ((ComplaintReplyItemViewHolder) holder).tv_content.setText("暂无回复内容");
            }
        }
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
