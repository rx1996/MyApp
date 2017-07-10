package com.atguigu.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PeopleInfoActivity extends AppCompatActivity {

    @InjectView(R.id.iv_people_image)
    ImageView ivPeopleImage;
    @InjectView(R.id.tv_people_name)
    TextView tvPeopleName;
    @InjectView(R.id.tv_people_work)
    TextView tvPeopleWork;
    @InjectView(R.id.ll_jianjie)
    LinearLayout llJianjie;
    @InjectView(R.id.bt_letter)
    Button btLetter;
    @InjectView(R.id.bt_follow)
    Button btFollow;
    @InjectView(R.id.rl_xinxi)
    RelativeLayout rlXinxi;
    @InjectView(R.id.tv_like_num)
    TextView tvLikeNum;
    @InjectView(R.id.ll_like)
    LinearLayout llLike;
    @InjectView(R.id.tv_recommend_num)
    TextView tvRecommendNum;
    @InjectView(R.id.ll_recommend)
    LinearLayout llRecommend;
    @InjectView(R.id.tv_guanzhu_num)
    TextView tvGuanzhuNum;
    @InjectView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @InjectView(R.id.tv_fans_num)
    TextView tvFansNum;
    @InjectView(R.id.ll_fans)
    LinearLayout llFans;
    @InjectView(R.id.linearLayout)
    LinearLayout linearLayout;
    @InjectView(R.id.activity_people_info)
    RelativeLayout activityPeopleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_info);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.ll_like, R.id.ll_recommend, R.id.ll_guanzhu, R.id.ll_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_like:
                Toast.makeText(PeopleInfoActivity.this, "喜欢", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_recommend:
                Toast.makeText(PeopleInfoActivity.this, "推荐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_guanzhu:
                Toast.makeText(PeopleInfoActivity.this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fans:
                Toast.makeText(PeopleInfoActivity.this, "粉丝", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
