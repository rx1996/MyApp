package com.atguigu.myapp.activity;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atguigu.myapp.R;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.pager.DarenPager;
import com.atguigu.myapp.pager.DuanziPager;
import com.atguigu.myapp.pager.FenxiangPager;
import com.atguigu.myapp.pager.GerenPager;
import com.atguigu.myapp.pager.MagazinePager;
import com.atguigu.myapp.pager.ShopPager;
import com.atguigu.myapp.pager.TuijianPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_shop)
    RadioButton rbShop;
    @Bind(R.id.rb_magazine)
    RadioButton rbMagazine;
    @Bind(R.id.rb_da_ren)
    RadioButton rbDaRen;
    @Bind(R.id.rb_geren)
    RadioButton rbGeren;
    @Bind(R.id.rb_fenxiang)
    RadioButton rbFenxiang;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
//    @Bind(R.id.activity_main)
//    LinearLayout activityMain;

    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rgMain.check(R.id.rb_shop);
    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_shop:
                    position = 0;
                    break;
                case R.id.rb_magazine:
                    position = 1;
                    break;
                case R.id.rb_da_ren:
                    position = 2;
                    break;
                case R.id.rb_fenxiang:
                    position = 3;
                    break;
                case R.id.rb_geren:
                    position = 4;
                    break;
            }
            Fragment currentFragment = fragments.get(position);
            switchFragment(currentFragment);
        }
    }
    private void switchFragment(Fragment currentFragment) {
        if(currentFragment != tempFragment){//不是同一个
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if(!currentFragment.isAdded()){

                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.frameLayout,currentFragment);

            }else{
                //把之前的隐藏
                if(tempFragment!= null){
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
        fragments = new ArrayList<>();
        fragments.add(new ShopPager());
        fragments.add(new MagazinePager());
        fragments.add(new DarenPager());
        fragments.add(new FenxiangPager());
        fragments.add(new GerenPager());
    }
}
