package com.wangshuai.androidui.anim;

import android.animation.TypeEvaluator;


public class PointEvaluator implements TypeEvaluator<Point> {

    public PointEvaluator() {
    }

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {

        // 根据fraction来计算当前动画的x和y的值
        float x = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
        float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());

        // 将计算后的坐标封装到一个新的Point对象中并返回
        Point point = new Point(x, y);
        return point;
    }
}
