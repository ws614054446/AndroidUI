package com.wangshuai.androidui.anim;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import com.wangshuai.androidui.R;

/**
 * 逐帧动画
 */
public class FrameAnimActivity extends AppCompatActivity {

    private AnimationDrawable animationDrawable;
    private ImageView ivFramge;
    private AppCompatButton btnStartAnim;
    private AppCompatButton btnStopAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);

        ivFramge = (ImageView) findViewById(R.id.iv_frame);
        btnStartAnim = (AppCompatButton) findViewById(R.id.btn_start_anim);
        btnStopAnim = (AppCompatButton) findViewById(R.id.btn_stop_anim);

        frameAnimByCode();
    }

    /**
     * xml设置帧动画
     */
    private void frameAnimByXml() {

        btnStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivFramge.setImageResource(R.drawable.frame_anim);
                animationDrawable = (AnimationDrawable) ivFramge.getDrawable();
                if (animationDrawable != null){
                    animationDrawable.start();
                }
            }
        });

        btnStopAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animationDrawable != null){
                    animationDrawable.stop();
                }
            }
        });
    }

    /**
     * 代码设置帧动画
     */
    private void frameAnimByCode(){
        animationDrawable = new AnimationDrawable();
        Drawable drawable1 = getResources().getDrawable(R.drawable.frame01);
        Drawable drawable2 = getResources().getDrawable(R.drawable.frame02);
        Drawable drawable3 = getResources().getDrawable(R.drawable.frame03);
        Drawable drawable4 = getResources().getDrawable(R.drawable.frame04);

        //将drawable设置到AnimationDrawable
        animationDrawable.addFrame(drawable1,500);
        animationDrawable.addFrame(drawable2,500);
        animationDrawable.addFrame(drawable3,500);
        animationDrawable.addFrame(drawable4,500);
        animationDrawable.setOneShot(true);

        btnStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivFramge.setImageDrawable(animationDrawable);

                if (animationDrawable != null){
                    animationDrawable.stop();//调用start前要先stop，不然在第一次动画之后会停在最后一帧，这样动画就只会触发一次
                    animationDrawable.start();
                }
            }
        });

        btnStopAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animationDrawable != null){
                    animationDrawable.stop();
                }
            }
        });
    }
}
