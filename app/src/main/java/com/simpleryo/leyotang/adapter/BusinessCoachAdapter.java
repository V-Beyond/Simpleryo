package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.CourseListBean;
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

public class BusinessCoachAdapter extends BaseAdapter<CourseListBean.DataBeanX> {

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
        final CourseListBean.DataBeanX bean = listData.get(position);
        if (bean.getCoach()!=null){
//            ((CoachItemViewHolder) holder).tv_introduce.setBackgroundColor(0); // 设置背景色
//            ((CoachItemViewHolder) holder).tv_introduce.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
            if (bean.getCoach().getIntro()!=null){
                ((CoachItemViewHolder) holder).tv_introduce.setText(bean.getCoach().getIntro());
            }else{
                ((CoachItemViewHolder) holder).tv_introduce.setText("暂无介绍");
            }
            if (bean.getCoverUrl()!=null){
                Picasso.with(mContext).load(bean.getCoverUrl()).into(((CoachItemViewHolder) holder).iv_coach_img);
            }
            if (bean.getCoach().getNickName()!=null){
                ((CoachItemViewHolder) holder).tv_name.setText(bean.getCoach().getNickName());
            }else{
                ((CoachItemViewHolder) holder).tv_name.setText("暂无昵称");
            }
        }
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
