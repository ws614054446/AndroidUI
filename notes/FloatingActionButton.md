### Material Design系列之FloatingActionButton详解与适配

#### 简介
FloatingActionButton是悬浮动作按钮，是Material Design 中的一个控件。一般用于一个页面中最主要的操作。继承ImageView。

#### 使用

> compile 'com.android.support:design:26.0.0-alpha1'

```
<android.support.design.widget.FloatingActionButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|right"
        android:layout_margin="24dp"
        app:backgroundTint="?attr/colorPrimary"
        android:elevation="10dp"
        app:fabSize="mini"
        app:rippleColor="#ff0"
        android:clickable="true"
        app:pressedTranslationZ="10dp"
        app:borderWidth="0dp"
        app:srcCompat="@android:drawable/ic_dialog_email"/>
```

```

app:backgroundTint - 设置FAB的背景颜色。
app:rippleColor - 设置FAB点击时的背景颜色。
app:borderWidth - 该属性尤为重要，如果不设置0dp，那么在4.1的sdk上FAB会显示为正方形，而且在5.0以后的sdk没有阴影效果。所以设置为borderWidth="0dp"。
app:elevation - FAB的阴影大小。
app:pressedTranslationZ - 点击时候FAB的阴影大小。
app:fabSize - 设置FAB的大小，该属性有三个值，分别为auto,normal和mini，对应的FAB大小分别为56dp和40dp。
android:src - 设置FAB的图标，Google建议符合Design设计的该图标大小为24dp。
```

#### 特性
1、阴影以及点击按钮阴影加深效果

设置pressedTranslationZ属性值

2、水波纹效果

设置app:rippleColor="#ff0"和android:clickable="true"属性

#### 适配
点击阴影加深效果和水波纹效果在5.0以上才有效果，5.0以下需要自己实现。
一般都是生成一个特定的layout-v21去兼容5.0以上系统。
