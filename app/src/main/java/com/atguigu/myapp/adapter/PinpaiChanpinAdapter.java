package com.atguigu.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.bean.PinpaiShangpinBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PinpaiChanpinAdapter extends BaseAdapter {
    private final Context context;
    private final List<PinpaiShangpinBean.DataBean.ItemsBean> items;

    public PinpaiChanpinAdapter(Context context, List<PinpaiShangpinBean.DataBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_jiaju, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PinpaiShangpinBean.DataBean.ItemsBean bean = items.get(position);
        viewHolder.tvJiajuName.setText(bean.getGoods_name());
        viewHolder.tvJiaju.setText(bean.getBrand_info().getBrand_name());
        viewHolder.tvPrice.setText("￥" + bean.getDiscount_price());
        viewHolder.tvLike.setText(bean.getLike_count());
        Glide.with(context)
                .load(bean.getGoods_image())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivJiajuImage);
        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.iv_jiaju_image)
        ImageView ivJiajuImage;
        @Bind(R.id.tv_jiaju_name)
        TextView tvJiajuName;
        @Bind(R.id.tv_jiaju)
        TextView tvJiaju;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_like)
        TextView tvLike;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
