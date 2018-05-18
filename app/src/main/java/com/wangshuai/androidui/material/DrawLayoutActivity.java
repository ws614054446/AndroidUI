package com.wangshuai.androidui.material;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wangshuai.androidui.R;
import com.wangshuai.androidui.util.StatusBarUtils;

public class DrawLayoutActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //增加一个占位视图(会出现问题)
//        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView().findViewById(android.R.id.content);
//        rootView.setPadding(0, getStatusBarHeight(this), 0, 0);
//        ViewGroup decorView = (ViewGroup) this.getWindow().getDecorView();
//        View statusBarView = new View(this);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                getStatusBarHeight(this));
//        statusBarView.setBackgroundColor(Color.parseColor("#ffee66"));
//        decorView.addView(statusBarView, lp);


        setContentView(R.layout.activity_draw_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * 适配含有侧滑布局的情况
         */
//        //要在内容布局增加状态栏，否则会盖在侧滑菜单上
//        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView().findViewById(android.R.id.content);
//        //DrawerLayout 则需要在第一个子视图即内容试图中添加padding
//        View parentView = rootView.getChildAt(0);
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        View statusBarView = new View(this);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                getStatusBarHeight(this));
//        //设置外层状态栏的颜色
//        statusBarView.setBackgroundColor(Color.parseColor("#3F51B5"));
//        //添加占位状态栏到线性布局中
//        linearLayout.addView(statusBarView, lp);
//        //侧滑菜单
//        DrawerLayout drawerlayout = (DrawerLayout) parentView;
//        //内容视图
//        View content = findViewById(R.id.coor_layout);
//        //将内容视图从 DrawerLayout 中移除
//        drawerlayout.removeView(content);
//        //将内容视图添加到线性布局里
//        linearLayout.addView(content, content.getLayoutParams());
//        //将带有占位状态栏的新的线性布局内容视图设置给 DrawerLayout
//        drawerlayout.addView(linearLayout, 0);


        //给导航栏设置高度和paddingTop值
//        ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
//        layoutParams.height += getStatusBarHeight(this);
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        toolbar.setLayoutParams(layoutParams);
//        toolbar.setPadding(
//                toolbar.getPaddingLeft(),
//                toolbar.getPaddingTop()+getStatusBarHeight(this),
//                toolbar.getPaddingRight(),
//                toolbar.getPaddingBottom());

        StatusBarUtils.with(this)
                .setDrawerLayoutContentId(true,R.id.coor_layout)
                .setColor(Color.parseColor("#3F51B5"))
                .init();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.draw_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}
