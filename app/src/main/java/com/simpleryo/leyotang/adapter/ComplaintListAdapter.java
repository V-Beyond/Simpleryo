package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.ComplainListBean;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.ComplaintItemViewHolder;
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

public class ComplaintListAdapter extends BaseAdapter<ComplainListBean.DataBean> {

    public ComplaintListAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new ComplaintItemViewHolder(layoutInflater.inflate(R.layout.layout_history_record_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final ComplainListBean.DataBean bean = listData.get(position);
        if (holder instanceof  ComplaintItemViewHolder){
            if (bean.getCreator().getAvatarUrl()!=null){
                Picasso.with(mContext).load(bean.getCreator().getAvatarUrl()).transform(transformation).into(((ComplaintItemViewHolder) holder).iv_coach_img);
            }
            ((ComplaintItemViewHolder) holder).tv_time.setText(XStringPars.getCouponTime(bean.getCreationTime()+""));
            ((ComplaintItemViewHolder) holder).tv_content.setText(bean.getTitle());
        }
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
