package com.atguigu.myapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.myapp.R;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.fragment.PinpaiGushiFragment;
import com.atguigu.myapp.fragment.PinpaiChanpinFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PinpaiActivity extends AppCompatActivity {


    @Bind(R.id.ib_title_back)
    ImageButton ibTitleBack;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.rb_gushi)
    RadioButton rbGushi;
    @Bind(R.id.rb_chanpin)
    RadioButton rbChanpin;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.activity_pinpai)
    LinearLayout activityPinpai;
    private int position = 0;
    private ArrayList<BaseFragment> fragments;
    private Fragment tempFragment;

    private String image;
    private String name;
    private String id;
    private PinpaiGushiFragment fragment;
    private PinpaiChanpinFragment chanpinFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinpai);
        ButterKnife.bind(this);

        image = getIntent().getStringExtra("image");
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        Log.e("TAG", "id=="+id);
        initData();
        initFragment();
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rgMain.check(R.id.rb_gushi);

        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        Log.e("TAG", "idbundle=="+bundle);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().commit();

        Bundle bundle1 = new Bundle();
        bundle1.putString("id",id);
        Log.e("TAG", "idbundle=="+bundle1);
        chanpinFragment.setArguments(bundle1);
        getSupportFragmentManager().beginTransaction().commit();
    }

    private void initData() {
        tvMeName.setText(name);
        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivMeIcon);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_gushi:
                    position = 0;
                    break;
                case R.id.rb_chanpin:
                    position = 1;
                    break;
            }
            Fragment currentFragment = fragments.get(position);
            switchFragment(currentFragment);
        }
    }

    private void switchFragment(Fragment currentFragment) {
        if (currentFragment != tempFragment) {//不是同一个
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (!currentFragment.isAdded()) {

                //把之前的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.frameLayout, currentFragment);

            } else {
                //把之前的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //把当前的显示
                ft.show(currentFragment);
            }


            //提交
            ft.commit();

            tempFragment = currentFragment;

        }
    }

    private void initFragment() {

        fragment = new PinpaiGushiFragment();
        chanpinFragment = new PinpaiChanpinFragment();
        fragments = new ArrayList<>();
        fragments.add(fragment);
        fragments.add(chanpinFragment);
    }
}
