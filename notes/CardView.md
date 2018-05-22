### Material Design系列之CardView详解与适配

#### 简介

> A FrameLayout with a rounded corner background and shadow.

CardView是v7包中的组件（继承FrameLayout），主要用来设置布局的边框为圆角、z轴的偏移量（这个是5.0以后才有的概念，也就是阴影的效果）。
可以包含其他的布局容器和控件，使得控件具有立体性。

#### 用法

首先添加依赖包：

> compile 'com.android.support:cardview-v7:26.0.0-alpha1'

布局使用：

```
<android.support.v7.widget.CardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="20dp">
        <ImageView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:src="@drawable/img1"/>
    </android.support.v7.widget.CardView>
```

#### 属性
```
app:cardBackgroundColor:设置背景颜色
app:cardCornerRadius:设置圆角大小
app:cardElevation:设置z轴的阴影
app:cardMaxElevation:设置z轴的最大高度值
app:cardUseCompatPadding:是否使用CompatPadding
app:cardPreventCornerOverlap:是否使用PreventCornerOverlap
app:contentPadding:设置内容的padding
```

#### 特性
1、边框圆角效果

> Due to expensive nature of rounded corner clipping, on platforms before L, CardView does not clip its children that intersect with rounded corners. Instead, it adds padding to avoid such intersection (See setPreventCornerOverlap(boolean) to change this behavior).

边框圆角在5.0以上才会有效果，5.0以下就不能变成圆角，需要自己去实现，没有兼容的API可调用。
在API 21版本之前，CardView不会裁剪内容元素以满足圆角需求，而是使用添加padding的替代方案，从而使内容元素不会覆盖CardView的圆角。而控制这个行为的属性就是cardPreventCornerOverlap，默认值为true。

2、阴影效果
> CardView uses elevation property on L for shadows and falls back to a custom shadow implementation on older platforms.

>  Before L, CardView adds padding to its content and draws shadows to that area. This padding amount is equal to maxCardElevation + (1 - cos45) * cornerRadius on the sides and maxCardElevation * 1.5 + (1 - cos45) * cornerRadius on top and bottom.

>  Since padding is used to offset content for shadows, you cannot set padding on CardView. Instead, you can use content padding attributes in XML or setContentPadding(int, int, int, int) in code to set the padding between the edges of the Card and children of CardView.

>  Note that, if you specify exact dimensions for the CardView, because of the shadows, its content area will be different between platforms before L and after L. By using api version specific resource values, you can avoid these changes. Alternatively, If you want CardView to add inner padding on platforms L and after as well, you can set setUseCompatPadding(boolean) to true.

在Android Lollipop之前的系统，CardView会自动添加一些额外的padding空间来绘制阴影部分，这也导致了以Lollipop为分界线的不同系统上CardView的尺寸大小不同。为了解决这个问题，有两种方法：第一种，使用不同API版本的dimension资源适配（也就是借助values和values-21文件夹中不同的dimens.xml文件）；第二种，就是使用cardUseCompatPadding属性，设置为true（默认值为false），让CardView在不同系统中使用相同的padding值。

3、Ripple水波纹效果

在5.0以上才会有效果。5.0以下需要自己去实现。实现水波纹效果CardView需要设置android:foreground和clickable属性。

4、按下的互动效果---下沉，松开弹起---Z轴位移效果

![](https://github.com/ws614054446/AndroidUI/blob/master/imgs/CardView-samples.gif)

为CardView添加一个属性动画： android:stateListAnimator="@drawable/z_translation"

```
<selector
    xmlns:android="http://schemas.android.com/apk/res/android"
    >
    <item
        android:state_pressed="true">
        <objectAnimator
           android:duration="@android:integer/config_shortAnimTime"
           android:propertyName="translationZ"
           android:valueTo="15dp"
           android:valueType="floatType"
            ></objectAnimator>
    </item>

    <item
        >
        <objectAnimator
           android:duration="@android:integer/config_shortAnimTime"
           android:propertyName="translationZ"
           android:valueTo="0dp"
           android:valueType="floatType"
            ></objectAnimator>
    </item>
</selector>
```

#### 适配问题
因为cardview大多数特性在5.0以上才能有效果，所以一般都是生成一个特定的layout-v21去兼容5.0以上系统，5.0以下的
这些效果大多都要自己去实现。
