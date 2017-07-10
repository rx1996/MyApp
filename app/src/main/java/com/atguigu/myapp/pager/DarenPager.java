package com.atguigu.myapp.pager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myapp.R;
import com.atguigu.myapp.adapter.daren.DarenAdapter;
import com.atguigu.myapp.base.BaseFragment;
import com.atguigu.myapp.bean.daren.ShouyeBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/6.
 */

public class DarenPager extends BaseFragment {

    @InjectView(R.id.gv)
    GridView gv;
    @InjectView(R.id.ib_title_search)
    ImageButton ibTitleSearch;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_daren_fenlei)
    ImageButton ibDarenFenlei;

    private DarenAdapter adapter;
    private PopupWindow popupWindow;
    private LinearLayout llFenlei;
    private TextView tvMoRen;

    private String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=1&sig=BF287AF953103F390674E73DDA18CFD8|639843030233268&v=1.0";
    private TextView tvZuiDuo;
    private TextView tvHuanYing;
    private TextView tvTuiJian;
    private TextView tvJiaRu;
    private static final int DAREN_FENLEI = 1;
    private static final int ACTION_COMPLETE = 2;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager_daren, null);
        ButterKnife.inject(this, view);
        ibDarenFenlei.setTag(DAREN_FENLEI);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
    }

    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "请求失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("TAG", "请求成功==");
            processData(response);

        }
    }

    private void processData(String json) {
        ShouyeBean bean = new Gson().fromJson(json, ShouyeBean.class);
        adapter = new DarenAdapter(context, bean.getData().getItems());
        gv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.ib_title_search, R.id.ib_daren_fenlei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_title_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_daren_fenlei:
//                Toast.makeText(context, "分类", Toast.LENGTH_SHORT).show();
                int tag = (int) ibDarenFenlei.getTag();
                if(tag ==DAREN_FENLEI){
                    showPopupWindow(view);
                }else{
                    hideDelete();
                }
                break;
        }
    }

    private void hideDelete() {
        ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
        ibDarenFenlei.setTag(DAREN_FENLEI);
    }

    private void showPopupWindow(View view) {
        ibDarenFenlei.setTag(ACTION_COMPLETE);
        ibDarenFenlei.setImageResource(R.drawable.abc_ic_clear_mtrl_alpha);
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_daren, null);
        llFenlei = (LinearLayout) contentView.findViewById(R.id.ll_fenlei);
        tvMoRen = (TextView) contentView.findViewById(R.id.tv_moren);
        tvZuiDuo = (TextView) contentView.findViewById(R.id.tv_zuiduo);
        tvHuanYing = (TextView) contentView.findViewById(R.id.tv_huanying);
        tvTuiJian = (TextView) contentView.findViewById(R.id.tv_zuixin_tuijian);
        tvJiaRu = (TextView) contentView.findViewById(R.id.tv_zuixin_jiaru);
        tvZuiDuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=goods_sum&page=1&sig=05D2057FE3D726A43A94505807516FC3%7C136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());

                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                popupWindow.dismiss();
            }
        });
        tvHuanYing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=followers&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                popupWindow.dismiss();
            }
        });
        tvTuiJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=reg_time&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                popupWindow.dismiss();
            }
        });
        tvJiaRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=action_time&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(contentView, GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.showAsDropDown(view);
    }
}
