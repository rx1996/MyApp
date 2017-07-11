package com.atguigu.myapp.adapter.daren;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.activity.PeopleInfoActivity;
import com.atguigu.myapp.adapter.shop.FenleiAdapter;
import com.atguigu.myapp.bean.ShopFenleiBean;
import com.atguigu.myapp.bean.daren.LikeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/11.
 */

public class LikeAdapter extends BaseAdapter {
    private final PeopleInfoActivity context;
    private final List<LikeBean.DataBean.ItemsBean.GoodsBean> goods;

    public LikeAdapter(PeopleInfoActivity context, List<LikeBean.DataBean.ItemsBean.GoodsBean> goods) {
        this.context = context;
        this.goods = goods;
    }

    @Override
    public int getCount() {
        return goods == null ? 0 : goods.size();
    }

    @Override
    public Object getItem(int position) {
        return goods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_like, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LikeBean.DataBean.ItemsBean.GoodsBean bean = goods.get(position);
        Glide.with(context)
                .load(bean.getGoods_image())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivLikeImage);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_like_image)
        ImageView ivLikeImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
