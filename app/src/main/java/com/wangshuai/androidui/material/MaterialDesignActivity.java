package com.wangshuai.androidui.material;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.wangshuai.androidui.R;

public class MaterialDesignActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btnRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        initViews();
    }

    private void initViews() {
        btnRecyclerView = (AppCompatButton) findViewById(R.id.btn_recyclerview);
        btnRecyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_recyclerview://recyclerview
                startActivity(new Intent(MaterialDesignActivity.this, RecyclerViewActivity.class));
                break;
        }
    }
}
