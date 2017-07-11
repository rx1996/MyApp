package com.atguigu.myapp.pager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.activity.MainActivity;
import com.atguigu.myapp.adapter.FenxiangAdapter;
import com.atguigu.myapp.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/11.
 */

public class FenxiangPager extends BaseFragment {
    @Bind(R.id.ib_title_search)
    ImageButton ibTitleSearch;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_fenxiang_fenlei)
    ImageButton ibFenxiangFenlei;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<BaseFragment> fragments;
    private FenxiangAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_fenxiang, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        fragments = new ArrayList<>();
        fragments.add(new TuijianPager());
        fragments.add(new DuanziPager());

        adapter = new FenxiangAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
