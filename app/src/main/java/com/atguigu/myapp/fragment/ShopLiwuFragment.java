package com.atguigu.myapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ShopLiwuFragment extends BaseFragment {

    @Bind(R.id.iv_festival)
    ImageView ivFestival;
    @Bind(R.id.iv_love)
    ImageView ivLove;
    @Bind(R.id.iv_birthday)
    ImageView ivBirthday;
    @Bind(R.id.iv_friend)
    ImageView ivFriend;
    @Bind(R.id.iv_children)
    ImageView ivChildren;
    @Bind(R.id.iv_parent)
    ImageView ivParent;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_liwu, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }
}
