package com.atguigu.myapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ShopLiwuFragment extends BaseFragment {

    @InjectView(R.id.iv_festival)
    ImageView ivFestival;
    @InjectView(R.id.iv_love)
    ImageView ivLove;
    @InjectView(R.id.iv_birthday)
    ImageView ivBirthday;
    @InjectView(R.id.iv_friend)
    ImageView ivFriend;
    @InjectView(R.id.iv_children)
    ImageView ivChildren;
    @InjectView(R.id.iv_parent)
    ImageView ivParent;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_liwu, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
