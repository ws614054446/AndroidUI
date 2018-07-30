package com.wangshuai.androidui.anim;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.wangshuai.androidui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterpolatorActivity extends AppCompatActivity {

    @BindView(R.id.btn_interpolator)
    AppCompatButton btnInterpolator;
    @BindView(R.id.tv_accelerate_decelerate_interpolator)
    TextView tvAccelerateDecelerateInterpolator;
    @BindView(R.id.tv_accelerate_interpolator)
    TextView tvAccelerateInterpolator;
    @BindView(R.id.tv_anticipate_interpolator)
    TextView tvAnticipateInterpolator;
    @BindView(R.id.tv_anticipate_overshoot_interpolatorr)
    TextView tvAnticipateOvershootInterpolatorr;
    @BindView(R.id.tv_bounce_interpolator)
    TextView tvBounceInterpolator;
    @BindView(R.id.tv_cycle_interpolator)
    TextView tvCycleInterpolator;
    @BindView(R.id.tv_decelerate_interpolator)
    TextView tvDecelerateInterpolator;
    @BindView(R.id.tv_linear_interpolator)
    TextView tvLinearInterpolator;
    @BindView(R.id.tv_overshoot_interpolator)
    TextView tvOvershootInterpolator;
    @BindView(R.id.btn_test1)
    AppCompatButton btnTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_interpolator, R.id.btn_test1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_interpolator:
                fun1();
                break;
            case R.id.btn_test1:
                fun2();
                break;
        }
    }

    private void fun1(){
        TranslateAnimation translateAnimation1 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation3 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation4 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation5 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation6 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation7 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation8 = new TranslateAnimation(0, 720, 0, 0);
        TranslateAnimation translateAnimation9 = new TranslateAnimation(0, 720, 0, 0);

        translateAnimation1.setDuration(5000);
        translateAnimation1.setInterpolator(new AccelerateDecelerateInterpolator());
        tvAccelerateDecelerateInterpolator.startAnimation(translateAnimation1);

        translateAnimation2.setDuration(5000);
        translateAnimation2.setInterpolator(new AccelerateInterpolator());
        tvAccelerateInterpolator.startAnimation(translateAnimation2);

        translateAnimation3.setDuration(5000);
        translateAnimation3.setInterpolator(new AnticipateInterpolator());
        tvAnticipateInterpolator.startAnimation(translateAnimation3);

        translateAnimation4.setDuration(5000);
        translateAnimation4.setInterpolator(new AnticipateOvershootInterpolator());
        tvAnticipateOvershootInterpolatorr.startAnimation(translateAnimation4);

        translateAnimation5.setDuration(5000);
        translateAnimation5.setInterpolator(new BounceInterpolator());
        tvBounceInterpolator.startAnimation(translateAnimation5);

        translateAnimation6.setDuration(5000);
        translateAnimation6.setInterpolator(new CycleInterpolator(3f));
        tvCycleInterpolator.startAnimation(translateAnimation6);

        translateAnimation7.setDuration(5000);
        translateAnimation7.setInterpolator(new DecelerateInterpolator());
        tvDecelerateInterpolator.startAnimation(translateAnimation7);

        translateAnimation8.setDuration(5000);
        translateAnimation8.setInterpolator(new LinearInterpolator());
        tvLinearInterpolator.startAnimation(translateAnimation8);

        translateAnimation9.setDuration(5000);
        translateAnimation9.setInterpolator(new OvershootInterpolator());
        tvOvershootInterpolator.startAnimation(translateAnimation9);
    }

    private void fun2(){
        float curTranslationX = btnTest1.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(btnTest1, "translationX", curTranslationX, 600,curTranslationX);
        animator.setDuration(5000);
        animator.setInterpolator(new DecelerateAccelerateInterpolator());
        animator.start();
    }
}
