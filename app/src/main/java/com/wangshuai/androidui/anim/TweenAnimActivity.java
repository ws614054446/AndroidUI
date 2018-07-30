package com.wangshuai.androidui.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.wangshuai.androidui.R;

public class TweenAnimActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivAnim;
    private AppCompatButton btnTanslate;
    private AppCompatButton btnScale;
    private AppCompatButton btnRotate;
    private AppCompatButton btnAlpha;
    private AppCompatButton btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_anim);

        initViews();
    }

    private void initViews() {
        ivAnim = (ImageView) findViewById(R.id.iv_anim);
        btnTanslate = (AppCompatButton) findViewById(R.id.btn_translate);
        btnScale = (AppCompatButton) findViewById(R.id.btn_scale);
        btnRotate = (AppCompatButton) findViewById(R.id.btn_rotate);
        btnAlpha = (AppCompatButton) findViewById(R.id.btn_alpha);
        btnSet = (AppCompatButton) findViewById(R.id.btn_set);

        btnTanslate.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnAlpha.setOnClickListener(this);
        btnSet.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_translate://平移

                translate2();

                break;
            case R.id.btn_scale://缩放
//                scale1();
                scale2();
                break;
            case R.id.btn_rotate://旋转
//                rotate1();
                rotate2();
                break;
            case R.id.btn_alpha://透明度
                alpha1();
                break;
            case R.id.btn_set://动画集
                setAnim1();
//                setAnim2();
                break;
        }
    }

    private void translate1(){
        //如果此集合中的所有动画都使用与此动画集关联的插值器，则为true。false则使用自己的插值器
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,

                Animation.RELATIVE_TO_PARENT,0.5f,

                Animation.RELATIVE_TO_PARENT,0f,

                Animation.RELATIVE_TO_PARENT,0.5f
        );
        translateAnimation.setDuration(2000);
        animationSet.addAnimation(translateAnimation);
        ivAnim.startAnimation(animationSet);
    }

    private void translate2(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate_demo1);
        ivAnim.startAnimation(animation);
    }

    private void scale1(){
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
        scaleAnimation.setDuration(3000);
        animationSet.addAnimation(scaleAnimation);
        ivAnim.startAnimation(animationSet);
    }

    private void scale2(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_demo1);
        ivAnim.startAnimation(animation);
    }

    private void rotate1(){
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0,90,100,100);
        rotateAnimation.setDuration(3000);
        animationSet.addAnimation(rotateAnimation);
        ivAnim.startAnimation(animationSet);
    }

    private void rotate2(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate_demo1);
        ivAnim.startAnimation(animation);
    }

    private void alpha1(){
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(3000);
        animationSet.setFillAfter(true);
        animationSet.addAnimation(alphaAnimation);
        ivAnim.startAnimation(animationSet);
    }

    private void setAnim1(){
        AnimationSet setAnimation = new AnimationSet(true);
        // 步骤1:创建组合动画对象(设置为true)


        // 步骤2:设置组合动画的属性
        setAnimation.setRepeatMode(Animation.RESTART);
        setAnimation.setRepeatCount(1);

        // 步骤3:逐个创建子动画

        // 子动画1:旋转动画
        Animation rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setRepeatCount(Animation.INFINITE);

        // 子动画2:平移动画
        Animation translate = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT,-0.5f,
                TranslateAnimation.RELATIVE_TO_PARENT,0.5f,
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        translate.setDuration(10000);

        // 子动画3:透明度动画
        Animation alpha = new AlphaAnimation(1,0);
        alpha.setDuration(3000);
        alpha.setStartOffset(7000);

        // 子动画4:缩放动画
        Animation scale = new ScaleAnimation(1,0.5f,1,0.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(1000);
        scale.setStartOffset(4000);

        // 步骤4:将创建的子动画添加到组合动画里
        setAnimation.addAnimation(alpha);
        setAnimation.addAnimation(rotate);
        setAnimation.addAnimation(translate);
        setAnimation.addAnimation(scale);
        ivAnim.startAnimation(setAnimation);
    }

    private void setAnim2(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_set1);
        ivAnim.startAnimation(animation);
    }
}
