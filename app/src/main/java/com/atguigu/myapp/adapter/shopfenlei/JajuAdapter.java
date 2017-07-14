package com.atguigu.myapp.adapter.shopfenlei;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.bean.shopfenlei.JiaJuBean;
import com.atguigu.myapp.fragment.shopfenlei.FenleiContentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/7.
 */

public class JajuAdapter extends RecyclerView.Adapter<JajuAdapter.ViewHolder> {

    private final FenleiContentActivity content;
    private final List<JiaJuBean.DataBean.ItemsBean> items;



    public JajuAdapter(FenleiContentActivity content, List<JiaJuBean.DataBean.ItemsBean> items) {
        this.content = content;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(content, R.layout.item_jiaju, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JiaJuBean.DataBean.ItemsBean bean = items.get(position);
        holder.tvJiajuName.setText(bean.getGoods_name());
        holder.tvJiaju.setText(bean.getBrand_info().getBrand_name());
        holder.tvPrice.setText("￥" + bean.getPrice());
        holder.tvLike.setText(bean.getLike_count());
        Glide.with(content)
                .load(bean.getGoods_image())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivJiajuImage);
    }


    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
