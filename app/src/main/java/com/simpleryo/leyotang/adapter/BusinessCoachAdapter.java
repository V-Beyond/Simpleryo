package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.CoachListBean;
import com.simpleryo.leyotang.viewholder.CoachItemViewHolder;
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

public class BusinessCoachAdapter extends BaseAdapter<CoachListBean.DataBean> {

    public BusinessCoachAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new CoachItemViewHolder(layoutInflater.inflate(R.layout.layout_business_coach_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final CoachListBean.DataBean bean = listData.get(position);
            if (bean.getIntro()!=null){
                ((CoachItemViewHolder) holder).tv_introduce.setText(bean.getIntro());
            }else{
                ((CoachItemViewHolder) holder).tv_introduce.setText("暂无介绍");
            }
            if (bean.getAvatarUrl()!=null){
                Picasso.with(mContext).load(bean.getAvatarUrl()).into(((CoachItemViewHolder) holder).iv_coach_img);
            }
            if (bean.getNickName()!=null){
                ((CoachItemViewHolder) holder).tv_name.setText(bean.getNickName());
            }else{
                ((CoachItemViewHolder) holder).tv_name.setText("暂无昵称");
            }
            ((CoachItemViewHolder) holder).rating_bar.setRating(Float.parseFloat(bean.getPoint()));
            ((CoachItemViewHolder) holder).tv_coach_count.setText(bean.getStudentCount());
            ((CoachItemViewHolder) holder).tv_point.setText(bean.getPoint());
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
