package com.atguigu.myapp.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.myapp.base.BaseFragment;

/**
 * Created by Administrator on 2017/7/13.
 */

public class PinpaiGushiFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("gushi内容");
    }
}
