package com.wangshuai.androidui.material;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wangshuai.androidui.R;

import java.util.ArrayList;

public class CoordinatorLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    private FloatingActionButton btnFab;
    private ArrayList<String> dataList = new ArrayList<>();
    private RecyclerViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        initViews();
    }

    private void initViews() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnFab = (FloatingActionButton) findViewById(R.id.btn_fab);

        setSupportActionBar(toolbar);
        toolbar.setTitle("CoordinatorLayout");
        initData();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(dataList);
        recyclerview.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 100;i++){
            dataList.add("测试数据-"+String.valueOf(i));
        }
    }
}
