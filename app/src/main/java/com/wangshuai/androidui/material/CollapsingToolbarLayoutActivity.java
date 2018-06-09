package com.wangshuai.androidui.material;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wangshuai.androidui.R;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsToolbar;
    private AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);

        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsToolbar = (CollapsingToolbarLayout) findViewById(R.id.collaps_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);

        collapsToolbar.setTitle("啊啊啊");

        toolbar.setTitle("嘻嘻嘻");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()){
                    toolbar.setTitleTextColor(getResources().getColor(R.color.black));
                    collapsToolbar.setTitle("呵呵哒");
                }else{
                    collapsToolbar.setTitle("么么哒");
                    toolbar.setTitle("哈哈哈");
                }
            }
        });
    }
}
