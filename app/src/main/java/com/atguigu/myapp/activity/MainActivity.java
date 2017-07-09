package com.atguigu.myapp.activity;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.atguigu.myapp.R;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.pager.DarenPager;
import com.atguigu.myapp.pager.DuanziPager;
import com.atguigu.myapp.pager.MagazinePager;
import com.atguigu.myapp.pager.ShopPager;
import com.atguigu.myapp.pager.TuijianPager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
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
                case R.id.rb_tuijian:
                    position = 3;
                    break;
                case R.id.rb_duanzi:
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
        fragments.add(new TuijianPager());
        fragments.add(new DuanziPager());
    }
}
