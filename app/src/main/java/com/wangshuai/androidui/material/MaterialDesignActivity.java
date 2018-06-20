package com.wangshuai.androidui.material;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
        AppCompatButton btnPalette = (AppCompatButton) findViewById(R.id.btn_palette);
        btnPalette.setOnClickListener(this);
        AppCompatButton btnTablayout = (AppCompatButton) findViewById(R.id.btn_tablayout);
        btnTablayout.setOnClickListener(this);
        AppCompatButton btnTextInputLayout = (AppCompatButton) findViewById(R.id.btn_textinputlayout);
        btnTextInputLayout.setOnClickListener(this);
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
        AppCompatButton btnAppbarlayout = (AppCompatButton) findViewById(R.id.btn_appbarlayout);
        btnAppbarlayout.setOnClickListener(this);
        AppCompatButton btnCollapsingtoolbarlayout = (AppCompatButton) findViewById(R.id.btn_collapsingtoolbarlayout);
        btnCollapsingtoolbarlayout.setOnClickListener(this);
        AppCompatButton btnBottomnavigationview = (AppCompatButton) findViewById(R.id.btn_bottomnavigationview);
        btnBottomnavigationview.setOnClickListener(this);
        AppCompatButton btnNavigationview = (AppCompatButton) findViewById(R.id.btn_navigationview);
        btnNavigationview.setOnClickListener(this);
        AppCompatButton btnCustombehavior = (AppCompatButton) findViewById(R.id.btn_custombehavior);
        btnCustombehavior.setOnClickListener(this);
        AppCompatButton btnSnackbar = (AppCompatButton) findViewById(R.id.btn_snackbar);
        btnSnackbar.setOnClickListener(this);
        AppCompatButton btnBottomsheet = (AppCompatButton) findViewById(R.id.btn_bottomsheet);
        btnBottomsheet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_palette://调色板
                startActivity(new Intent(MaterialDesignActivity.this, PaletteActivity.class));
                break;
            case R.id.btn_tablayout://tablayout
                startActivity(new Intent(MaterialDesignActivity.this, TabLayoutActivity.class));
                break;
            case R.id.btn_snackbar:
                startActivity(new Intent(MaterialDesignActivity.this, SnackBarActivity.class));
                break;
            case R.id.btn_textinputlayout://
                startActivity(new Intent(MaterialDesignActivity.this, TextInputLayoutActivity.class));
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
            case R.id.btn_appbarlayout://appbarlayout
                startActivity(new Intent(MaterialDesignActivity.this, AppBarLayoutActivity.class));
                break;
            case R.id.btn_collapsingtoolbarlayout://Collapsingtoolbarlayout
                startActivity(new Intent(MaterialDesignActivity.this, CollapsingToolbarLayoutActivity.class));
                break;
            case R.id.btn_bottomnavigationview://
                startActivity(new Intent(MaterialDesignActivity.this,BottomNavigationViewActivity.class));
                break;
            case R.id.btn_navigationview:
                startActivity(new Intent(MaterialDesignActivity.this,DrawLayoutActivity.class));
                break;
            case R.id.btn_bottomsheet://
                startActivity(new Intent(MaterialDesignActivity.this,BottomSheetActivity.class));
                break;
            case R.id.btn_custombehavior://
                break;
        }
    }
}
