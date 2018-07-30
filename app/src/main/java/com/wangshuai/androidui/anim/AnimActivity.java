package com.wangshuai.androidui.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.wangshuai.androidui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimActivity extends AppCompatActivity {

    @BindView(R.id.btn_frame_anim)
    AppCompatButton btnFrameAnim;
    @BindView(R.id.btn_tween_anim)
    AppCompatButton btnTweenAnim;
    @BindView(R.id.btn_property_anim)
    AppCompatButton btnPropertyAnim;
    @BindView(R.id.btn_interpolator)
    AppCompatButton btnInterpolator;
    @BindView(R.id.btn_TypeEvaluator)
    AppCompatButton btnTypeEvaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_frame_anim, R.id.btn_tween_anim, R.id.btn_property_anim, R.id.btn_interpolator, R.id.btn_TypeEvaluator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_frame_anim:
                startActivity(new Intent(AnimActivity.this, FrameAnimActivity.class));
                break;
            case R.id.btn_tween_anim:
                startActivity(new Intent(AnimActivity.this, TweenAnimActivity.class));
                break;
            case R.id.btn_property_anim:
                startActivity(new Intent(AnimActivity.this, PropertyAnimActivity.class));
                break;
            case R.id.btn_interpolator:
                startActivity(new Intent(AnimActivity.this, InterpolatorActivity.class));
                break;
            case R.id.btn_TypeEvaluator:
                startActivity(new Intent(AnimActivity.this, EvaluatorActivity.class));
                break;
        }
    }
}
