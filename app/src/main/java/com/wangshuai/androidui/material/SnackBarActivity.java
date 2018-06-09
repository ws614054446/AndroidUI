package com.wangshuai.androidui.material;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.wangshuai.androidui.R;

public class SnackBarActivity extends AppCompatActivity implements View.OnClickListener {


    private AppCompatButton btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

        btnClick = (AppCompatButton) findViewById(R.id.btn_click);
        btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                Snackbar.make(btnClick, "哈哈哈哈", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackBarActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(Color.WHITE)
                        .addCallback(new Snackbar.Callback(){
                            @Override
                            public void onShown(Snackbar sb) {
                                Toast.makeText(SnackBarActivity.this, "onShown", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                Toast.makeText(SnackBarActivity.this, "onDismissed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
        }
    }
}
