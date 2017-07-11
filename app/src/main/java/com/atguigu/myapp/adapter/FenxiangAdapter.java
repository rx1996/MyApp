package com.atguigu.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.atguigu.myapp.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/11.
 */

public class FenxiangAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<BaseFragment> fragments;
    private String[] titles = new String[]{"推荐", "段子"};

    public FenxiangAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
