package com.atguigu.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.bean.DuanziBean;
import com.atguigu.myapp.bean.TuijianBean;
import com.atguigu.myapp.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017/7/9.
 */

public class DuanziAdapter extends BaseAdapter {
    private final Context context;
    private final List<DuanziBean.ListBean> datas;
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_AD = 1;

    public DuanziAdapter(Context context, List<DuanziBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        //根据位置，从列表中得到一个数据对象
        DuanziBean.ListBean listBean = datas.get(position);
        String type = listBean.getType();//得到类型
        if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        } else {
            itemViewType = TYPE_AD;//广播
        }
        return itemViewType;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = initView(convertView, getItemViewType(position), datas.get(position));

        return convertView;
    }
    private View initView(View convertView, int itemViewType, DuanziBean.ListBean mediaItem) {
        switch (itemViewType) {


            case TYPE_TEXT://文字

                TextHolder textHolder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.all_text_item, null);
                    textHolder = new TextHolder(convertView);

                    convertView.setTag(textHolder);
                } else {
                    textHolder = (TextHolder) convertView.getTag();
                }

                textHolder.setData(mediaItem);

                break;
            case TYPE_AD://软件广告

                ADHolder adHolder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.all_ad_item, null);
                    adHolder = new ADHolder(convertView);
                    convertView.setTag(adHolder);
                } else {
                    adHolder = (ADHolder) convertView.getTag();
                }

                break;
        }
        return convertView;
    }
    class BaseViewHolder {
        ImageView ivHeadpic;
        TextView tvName;
        TextView tvTimeRefresh;
        TextView tvShenheDingNumber;
        TextView tvShenheCaiNumber;
        TextView tvPostsNumber;
        TextView tvCommentNumber;

        public BaseViewHolder(View convertView) {
            //公共的
            ivHeadpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvTimeRefresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
            //bottom
            tvShenheDingNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
            tvShenheCaiNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
            tvPostsNumber = (TextView) convertView.findViewById(R.id.tv_posts_number);
            tvCommentNumber = (TextView) convertView.findViewById(R.id.tv_comment_number);
        }

        /**
         * 设置公共的数据
         *
         * @param mediaItem
         */
        public void setData(DuanziBean.ListBean mediaItem) {
            if (mediaItem.getU() != null && mediaItem.getU().getHeader() != null && mediaItem.getU().getHeader().get(0) != null) {
                x.image().bind(ivHeadpic, mediaItem.getU().getHeader().get(0));
            }
            if (mediaItem.getU() != null && mediaItem.getU().getName() != null) {
                tvName.setText(mediaItem.getU().getName() + "");
            }

            tvTimeRefresh.setText(mediaItem.getPasstime());

            //设置标签
            List<DuanziBean.ListBean.TagsBean> tagsEntities = mediaItem.getTags();
            if (tagsEntities != null && tagsEntities.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < tagsEntities.size(); i++) {
                    buffer.append(tagsEntities.get(i).getName() + " ");
                }
            }

            //设置点赞，踩,转发

            tvShenheDingNumber.setText(mediaItem.getUp());
            tvShenheCaiNumber.setText(mediaItem.getDown() + "");
            tvPostsNumber.setText(mediaItem.getForward() + "");

        }


    }


    class TextHolder extends DuanziAdapter.BaseViewHolder {
        TextView tvContext;

        TextHolder(View convertView) {
            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
        }

        public void setData(DuanziBean.ListBean mediaItem) {
            super.setData(mediaItem);
            //设置文本-所有的都有
            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());
        }
    }


    class ADHolder{
        TextView tvContext;
        ImageView ivImageIcon;
        Button btnInstall;

        ADHolder(View convertView) {
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            btnInstall = (Button) convertView.findViewById(R.id.btn_install);
            ivImageIcon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
        }
    }
}
