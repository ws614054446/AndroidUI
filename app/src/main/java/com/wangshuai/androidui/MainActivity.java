package com.wangshuai.androidui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.wangshuai.androidui.material.MaterialDesignActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btnMaterialDesign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        btnMaterialDesign = (AppCompatButton) findViewById(R.id.btn_material_design);
        btnMaterialDesign.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_material_design:
                startActivity(new Intent(MainActivity.this,MaterialDesignActivity.class));
                break;
        }
    }
}
