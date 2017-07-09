package com.atguigu.myapp.pager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myapp.R;
import com.atguigu.myapp.activity.MainActivity;
import com.atguigu.myapp.adapter.ShopPagerAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.fragment.ShopFenleiFragment;
import com.atguigu.myapp.fragment.ShopLiwuFragment;
import com.atguigu.myapp.fragment.ShopPinpaiFragment;
import com.atguigu.myapp.fragment.ShopShouyeFragment;
import com.atguigu.myapp.fragment.ShopZhuantiFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ShopPager extends BaseFragment {


    @InjectView(R.id.ib_title_search)
    ImageButton ibTitleSearch;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_shopping_cart)
    ImageButton ibShoppingCart;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<BaseFragment> fragments;
    private ShopPagerAdapter pagerAdapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_shop, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        fragments = new ArrayList<>();
        fragments.add(new ShopFenleiFragment());
        fragments.add(new ShopPinpaiFragment());
        fragments.add(new ShopShouyeFragment());
        fragments.add(new ShopZhuantiFragment());
        fragments.add(new ShopLiwuFragment());

        MainActivity mainActivity = (MainActivity) context;
        pagerAdapter = new ShopPagerAdapter(mainActivity.getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ib_title_search, R.id.ib_shopping_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_title_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_shopping_cart:
                Toast.makeText(context, "购物车", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
