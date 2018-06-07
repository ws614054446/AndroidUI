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

        collapsToolbar.setTitle("");

        toolbar.setNavigationIcon(R.drawable.details_arrow);
        toolbar.setTitle("");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()){
                    toolbar.setTitleTextColor(getResources().getColor(R.color.black));
                    toolbar.setNavigationIcon(R.drawable.details_nav_arrow);
                    collapsToolbar.setTitle("");
                }else{
                    collapsToolbar.setTitle("");
                    toolbar.setTitle("");
                }
            }
        });
    }
}
