package com.atguigu.myapp.adapter.shop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.bean.ShopPinpaiBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class PinpaiAdapter extends BaseAdapter {

    private final Context context;
    private final List<ShopPinpaiBean.DataBean.ItemsBean> items;

    public PinpaiAdapter(Context context, List<ShopPinpaiBean.DataBean.ItemsBean> items) {
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
            convertView = View.inflate(context, R.layout.item_pinpai, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShopPinpaiBean.DataBean.ItemsBean bean = items.get(position);
        viewHolder.tvName.setText(bean.getBrand_name());
        Glide.with(context)
                .load(bean.getBrand_logo())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivIcon);
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}