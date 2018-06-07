package com.wangshuai.androidui.material;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * FloatingActionButton自定义Behavior
 */

public class FabBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private boolean visible = true;//FloatingActionButton是否可见

    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 监听观察的view开始滑动事件
     * @param coordinatorLayout 最外层coordinatorLayout布局
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes 滑动的关联轴
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        //只监听垂直的滑动
        return ViewCompat.SCROLL_AXIS_VERTICAL == nestedScrollAxes ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     *  监听观察的view正在滑动事件
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        if (dyConsumed > 0 && visible){
            //show
            visible = false;
            onHide(child);
        }else if (dyConsumed < 0){
            visible = true;
            onShow(child);
        }
    }

    private void onHide(FloatingActionButton fab){
        ViewCompat.animate(fab).scaleX(0f).scaleY(0f).start();
    }

    private void onShow(FloatingActionButton fab){
        ViewCompat.animate(fab).scaleX(1f).scaleY(1f).start();
    }
}
