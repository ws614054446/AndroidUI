package com.wangshuai.androidui.material;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.wangshuai.androidui.R;

public class MaterialDesignActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        initViews();
    }

    private void initViews() {
        AppCompatButton btnRecyclerView = (AppCompatButton) findViewById(R.id.btn_recyclerview);
        btnRecyclerView.setOnClickListener(this);
        AppCompatButton btnPalette = (AppCompatButton) findViewById(R.id.btn_palette);
        btnPalette.setOnClickListener(this);
        AppCompatButton btnTablayout = (AppCompatButton) findViewById(R.id.btn_tablayout);
        btnTablayout.setOnClickListener(this);
        AppCompatButton btnTranslucent = (AppCompatButton) findViewById(R.id.btn_translucent);
        btnTranslucent.setOnClickListener(this);
        AppCompatButton btnNavigationbar = (AppCompatButton) findViewById(R.id.btn_navigationbar);
        btnNavigationbar.setOnClickListener(this);
        AppCompatButton btnCardview = (AppCompatButton) findViewById(R.id.btn_cardview);
        btnCardview.setOnClickListener(this);
        AppCompatButton btnFab = (AppCompatButton) findViewById(R.id.btn_fab);
        btnFab.setOnClickListener(this);
        AppCompatButton btnCoordinatorlayout = (AppCompatButton) findViewById(R.id.btn_coordinatorlayout);
        btnCoordinatorlayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_recyclerview://recyclerview
                startActivity(new Intent(MaterialDesignActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.btn_palette://调色板
                startActivity(new Intent(MaterialDesignActivity.this, PaletteActivity.class));
                break;
            case R.id.btn_tablayout://tablayout
                startActivity(new Intent(MaterialDesignActivity.this, TabLayoutActivity.class));
                break;
            case R.id.btn_translucent://沉浸式状态栏
                startActivity(new Intent(MaterialDesignActivity.this, TranslucentActivity.class));
                break;
            case R.id.btn_cardview://cardview的使用
                startActivity(new Intent(MaterialDesignActivity.this, CardViewActivity.class));
                break;
            case R.id.btn_fab://FloatingActionButton
                startActivity(new Intent(MaterialDesignActivity.this, FloatingActionButtonActivity.class));
                break;
            case R.id.btn_coordinatorlayout://coordinatorlayout
                startActivity(new Intent(MaterialDesignActivity.this, CoordinatorLayoutActivity.class));
                break;

        }
    }
}
