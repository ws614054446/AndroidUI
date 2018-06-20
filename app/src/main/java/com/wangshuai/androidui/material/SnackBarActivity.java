package com.wangshuai.androidui.material;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.wangshuai.androidui.R;
import com.wangshuai.androidui.material.mysnackbar.MySnackbar;

public class SnackBarActivity extends AppCompatActivity implements View.OnClickListener {


    private AppCompatButton btnClick;
    private AppCompatButton btnChangeColor;
    private AppCompatButton btnShowFromTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

        btnClick = (AppCompatButton) findViewById(R.id.btn_click);
        btnClick.setOnClickListener(this);

        btnChangeColor = (AppCompatButton) findViewById(R.id.btn_change_color);
        btnChangeColor.setOnClickListener(this);

        btnShowFromTop = (AppCompatButton) findViewById(R.id.btn_show_from_top);
        btnShowFromTop.setOnClickListener(this);

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
            case R.id.btn_change_color://改变背景颜色
                Snackbar snackbar = Snackbar.make(btnChangeColor,"呵呵呵",Snackbar.LENGTH_LONG);
                View view = snackbar.getView();
                view.setBackgroundColor(Color.RED);
                snackbar.setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();
                break;
            case R.id.btn_show_from_top://从顶部弹出
                MySnackbar.make(btnShowFromTop,"呵呵哒", MySnackbar.LENGTH_LONG).show();
                break;
        }
    }


}
