package com.simpleryo.leyotang.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.simpleryo.leyotang.R;
import com.simpleryo.leyotang.bean.CourseRemarkListBean;
import com.simpleryo.leyotang.bean.PhotoInfo;
import com.simpleryo.leyotang.utils.XStringPars;
import com.simpleryo.leyotang.viewholder.RemarkItemViewHolder;
import com.simpleryo.leyotang.viewholder.SuperViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * @author huanglei
 * @version V1.0
 * @Title: JingXuanAdapter
 * @Package com.hpkj.kexue.adapter
 * @Description: 精选推荐item适配器
 * @date 2017/11/10 18:55
 */

public class RemarkListAdapter extends BaseAdapter<CourseRemarkListBean.DataBean> {

    public RemarkListAdapter(Context context) {
        super(context);
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new RemarkItemViewHolder(layoutInflater.inflate(R.layout.layout_course_remark_item, parent, false));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        final CourseRemarkListBean.DataBean bean = listData.get(position);
        if (holder instanceof  RemarkItemViewHolder){
            if (bean.getCreator().getAvatarUrl()!=null){
                Picasso.with(mContext).load(bean.getCreator().getAvatarUrl()).transform(transformation).into(((RemarkItemViewHolder) holder).iv_coach_img);
            }
            ((RemarkItemViewHolder) holder).tv_name.setText(bean.getCreator().getNickName());
            ((RemarkItemViewHolder) holder).tv_time.setText(XStringPars.getCouponTime(bean.getCreationTime()+""));
            ((RemarkItemViewHolder) holder).tv_content.setText(bean.getComment());
            ((RemarkItemViewHolder) holder).rating_bar.setRating(bean.getPoint());
            if(bean.getImageUrls()!=null){
                final List<PhotoInfo> photos =new ArrayList<>();
                for (CourseRemarkListBean.DataBean.ImageUrlsBean imageUrlsBean:bean.getImageUrls()){
                    PhotoInfo p1 = new PhotoInfo();
                    p1.url = imageUrlsBean.getValue();
                    p1.w =449;
                    p1.h = 449;
                    photos.add(p1);
                }
                ((RemarkItemViewHolder) holder).multi_image.setList(photos);
//                multiImagView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        urls.clear();
//                        for (int i = 0; i < photos.size(); i++) {
//                            urls.add(photos.get(i).url);
//                        }
//                        commentList = new CommentGalleryContainer(urls, dataBean.getContent());
//                        Intent it = new Intent(context, PictureActivity.class);
//                        it.putExtra("position", position);
//                        it.putExtra("list", commentList);
//                        context.startActivity(it);
//                    }
//                });

            }
        }
    }
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }
}
