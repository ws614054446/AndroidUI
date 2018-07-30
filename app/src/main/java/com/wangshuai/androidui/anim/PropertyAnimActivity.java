package com.wangshuai.androidui.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import com.wangshuai.androidui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PropertyAnimActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    AppCompatButton btn1;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.btn2)
    AppCompatButton btn2;
    @BindView(R.id.btn_add_view)
    AppCompatButton btnAddView;
    @BindView(R.id.btn_remove_view)
    AppCompatButton btnRemoveView;
    @BindView(R.id.btn_1)
    AppCompatButton btn_1;
    @BindView(R.id.btn_2)
    AppCompatButton btn_2;
    @BindView(R.id.btn_state_list_anim)
    AppCompatButton btnStateListAnim;
    @BindView(R.id.btn_view_property)
    AppCompatButton btnViewProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        btn1 = (AppCompatButton) findViewById(R.id.btn1);
        btn2 = (AppCompatButton) findViewById(R.id.btn2);
        iv1 = (ImageView) findViewById(R.id.iv1);

        ofFloat();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.btn1, R.id.btn2, R.id.btn_add_view, R.id.btn_remove_view, R.id.btn_state_list_anim,R.id.btn_view_property})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ofInt1();
                break;
            case R.id.btn2:
                animatorSet1();
                break;
            case R.id.btn_add_view:
                if (btn_1.getVisibility() == View.GONE) {
                    btn_1.setVisibility(View.VISIBLE);
                    return;
                } else {
                    if (btn_2.getVisibility() == View.GONE) {
                        btn_2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.btn_remove_view:
                if (btn_1.getVisibility() == View.VISIBLE) {
                    btn_1.setVisibility(View.GONE);
                    return;
                } else {
                    if (btn_2.getVisibility() == View.VISIBLE) {
                        btn_2.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.btn_state_list_anim:
                StateListAnimator animator = AnimatorInflater.loadStateListAnimator(this, R.animator.selector_animate_scale);
                btnStateListAnim.setStateListAnimator(animator);
                break;
            case R.id.btn_view_property:
                btnViewProperty.animate().x(800).y(500).start();
                break;
        }
    }

    /**
     * 将btn1的宽度由200px延展到800px
     */
    private void ofInt1() {
        //1、设置属性数值的初始值 & 结束值
        ValueAnimator valueAnimator = ValueAnimator.ofInt(300, 800);

        //2：设置动画的播放各种属性
        valueAnimator.setDuration(3000);

        //3、添加监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获得每次变化后的属性值
                int currentValue = (int) animation.getAnimatedValue();

                //手动将变化的值赋给对象的属性
                btn1.getLayoutParams().width = currentValue;

                //刷新视图
                btn1.requestLayout();
            }
        });

        //4、启动动画
        valueAnimator.start();

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
        });
    }

    private void ofFloat() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv1, "alpha", 0f, 1f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    private void animatorSet1() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(btn2, "translationX", 0f, 100f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(btn2, "translationY", 0f, 200f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(btn2, "rotation", 0f, 270f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator1).after(objectAnimator2).with(objectAnimator3);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

}
