package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.RecommendStoresBean;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by 77429 on 2018/3/28.
 */

public class InstitutionalRecommendAdapter extends ListBaseAdapter<RecommendStoresBean.DataBean> {
    public InstitutionalRecommendAdapter(Context context) {
        super(context);
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_institutional_recommend_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        CardView cardView =  holder.getView(R.id.card_view);
        ImageView image_view = holder.getView(R.id.image_view);
        RecommendStoresBean.DataBean itemModel = mDataList.get(position);
        if (itemModel.getStoreInfo().getCoverUrl()!=null){
            Picasso.with(mContext).load(itemModel.getStoreInfo().getCoverUrl()).into(image_view);
        }else{
            Picasso.with(mContext).load("http://p3.so.qhimgs1.com/bdr/_240_/t01144f848052b04663.jpg").into(image_view);
        }

//        //修改高度，模拟交错效果
//        cardView.getLayoutParams().height = itemModel.height;
        if (position==3){
            cardView.setVisibility(View.GONE);
        }
    }

}
