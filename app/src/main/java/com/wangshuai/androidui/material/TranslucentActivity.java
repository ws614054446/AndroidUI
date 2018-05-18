package com.wangshuai.androidui.material;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.wangshuai.androidui.R;

public class TranslucentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//        增加一个占位视图
//        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView().findViewById(android.R.id.content);
//        rootView.setPadding(0, getStatusBarHeight(this), 0, 0);
//        ViewGroup decorView = (ViewGroup) this.getWindow().getDecorView();
//        View statusBarView = new View(this);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                getStatusBarHeight(this));
//        statusBarView.setBackgroundColor(Color.parseColor("#ffee66"));
//        decorView.addView(statusBarView, lp);

        setContentView(R.layout.activity_translucent);

        initViews();

        AppCompatButton btnDrawlayout = (AppCompatButton) findViewById(R.id.btn_drawlayout);
        btnDrawlayout.setOnClickListener(this);
    }

    private void initViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //给导航栏设置高度和paddingTop值
        ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
        layoutParams.height += getStatusBarHeight(this);
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        toolbar.setLayoutParams(layoutParams);
        toolbar.setPadding(
                toolbar.getPaddingLeft(),
                toolbar.getPaddingTop()+getStatusBarHeight(this),
                toolbar.getPaddingRight(),
                toolbar.getPaddingBottom());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_drawlayout:
                startActivity(new Intent(TranslucentActivity.this,DrawLayoutActivity.class));
                break;
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("status_bar_height=",result+"");
        return result;
    }

    /**
     这个方法详细一点，知道怎么获取的
     * 获取状态栏的高度
     * @param context
     * @return
     */
    private int getStatusBarHeight2(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField("status_bar_height").get(object).toString();
            int height = Integer.parseInt(heightStr);
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
