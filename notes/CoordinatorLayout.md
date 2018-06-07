### Material Design系列之CoordinatorLayout详解

#### 简介

> CoordinatorLayout is a super-powered FrameLayout

> CoordinatorLayout is intended for two primary use cases:

- As a top-level application decor or chrome layout;

- As a container for a specific interaction with one or more child views

CoordinatorLayout通过协调并调度里面的子控件或者布局来实现触摸（一般是指滑动）产生一些相关的动画效果。
可以通过设置view的Behavior来实现触摸的动画调度。

[CoordinatorLayout官方文档](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout)

> compile 'com.android.support:design:26.0.0-alpha1'

#### CoordinatorLayout与FloatingActionButton
实现列表滑动时FloatingActionButton显示和隐藏，还有Snackbar显示时FloatingActionButton上移效果。

前提是FloatingActionButton要使用FloatingActionButton.Behavior。FloatingActionButton是默认使用这个
Behavior，但是如果FloatingActionButton和列表一起使用，并且需要自定义Behavior时，需继承FloatingActionButton.Behavior，
如果是继承CoordinatorLayout.Behavior<FloatingActionButton>，Snackbar会遮住FloatingActionButton。

#### AppBarLayout的使用详解

AppBarLayout源码注释：

```
/**
 * AppBarLayout is a vertical {@link LinearLayout} which implements many of the features of
 * material designs app bar concept, namely scrolling gestures.
 * <p>
 * Children should provide their desired scrolling behavior through
 * {@link LayoutParams#setScrollFlags(int)} and the associated layout xml attribute:
 * {@code app:layout_scrollFlags}.
 *
 * <p>
 * This view depends heavily on being used as a direct child within a {@link CoordinatorLayout}.
 * If you use AppBarLayout within a different {@link ViewGroup}, most of it's functionality will
 * not work.
 * <p>
 * AppBarLayout also requires a separate scrolling sibling in order to know when to scroll.
 * The binding is done through the {@link ScrollingViewBehavior} behavior class, meaning that you
 * should set your scrolling view's behavior to be an instance of {@link ScrollingViewBehavior}.
 * A string resource containing the full class name is available.
 *
 * <pre>
 * &lt;android.support.design.widget.CoordinatorLayout
 *         xmlns:android=&quot;http://schemas.android.com/apk/res/android&quot;
 *         xmlns:app=&quot;http://schemas.android.com/apk/res-auto&quot;
 *         android:layout_width=&quot;match_parent&quot;
 *         android:layout_height=&quot;match_parent&quot;&gt;
 *
 *     &lt;android.support.v4.widget.NestedScrollView
 *             android:layout_width=&quot;match_parent&quot;
 *             android:layout_height=&quot;match_parent&quot;
 *             app:layout_behavior=&quot;@string/appbar_scrolling_view_behavior&quot;&gt;
 *
 *         &lt;!-- Your scrolling content --&gt;
 *
 *     &lt;/android.support.v4.widget.NestedScrollView&gt;
 *
 *     &lt;android.support.design.widget.AppBarLayout
 *             android:layout_height=&quot;wrap_content&quot;
 *             android:layout_width=&quot;match_parent&quot;&gt;
 *
 *         &lt;android.support.v7.widget.Toolbar
 *                 ...
 *                 app:layout_scrollFlags=&quot;scroll|enterAlways&quot;/&gt;
 *
 *         &lt;android.support.design.widget.TabLayout
 *                 ...
 *                 app:layout_scrollFlags=&quot;scroll|enterAlways&quot;/&gt;
 *
 *     &lt;/android.support.design.widget.AppBarLayout&gt;
 *
 * &lt;/android.support.design.widget.CoordinatorLayout&gt;
 * </pre>
 *
 * @see <a href="http://www.google.com/design/spec/layout/structure.html#structure-app-bar">
 *     http://www.google.com/design/spec/layout/structure.html#structure-app-bar</a>
 */
```

大体的意思就是：这个控件继承LinearLayout，添加了滑动手势，它可以让你在某个可滑动的View滑动手势发生改变时，内部的子View 该做什么动作。
这个控件极度依赖CoordinatorLayout。同时可滑动的控件也需要设置ScrollingViewBehavior。app:layout_behavior="@string/appbar_scrolling_view_behavior"

[AppBarLayout官方文档链接](https://developer.android.com/reference/android/support/design/widget/AppBarLayout)

AppBarLayout内部子view的app:layout_scrollFlags属性共有五种情况：

- scroll：可以随着可滑动的view一起往上滑动，往下滑到最底时出现
- enterAlways：可以随着可滑动的view一起往上滑动，一往下滑子view就出现(和scroll一起使用)
- enterAlwaysCollapsed:这个是对enterAlways的补充，上面有的效果都有，增加的就是往下滑出现折叠高度，滑到最底时，子view剩下部分开始滑动。
这个折叠的高度由view的minHeight来指定。
```
<android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <android.support.v7.widget.Toolbar
            android:layout_width="match_parent"
            android:layout_height="300dp"
            android:minHeight="?attr/actionBarSize"
            app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed">

        </android.support.v7.widget.Toolbar>
    </android.support.design.widget.AppBarLayout>
```

- exitUntilCollapsed：当可滑动view向上滑动时，设置这个属性的view先响应滑动事件，滑到折叠高度后固定不动了（一般配合scroll使用）

- snap:当可滑动的view向上滑动超过一定距离时，设置这个属性的子view才会滑动，否则会回复原样；向下滑动也是类似效果

AppBarLayout的一些方法：

- addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener)：当AppbarLayout 的偏移发生改变的时候回调，也就是子View滑动。
- removeOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener)
- setExpanded(boolean expanded, boolean animate):是否展开，切换时是否需要动画
- setExpanded(boolean expanded):是否展开，默认带动画效果
- setOrientation(int orientation)：设置方向

#### CollapsingToolbarLayout使用详解
> CollapsingToolbarLayout is a wrapper for Toolbar which implements a collapsing app bar. It is designed to be used as a direct child of a AppBarLayout

CollapsingToolbarLayout是对Toolbar的包装并且实现了折叠app bar效果，使用时，要作为 AppbarLayout 的直接子View来使用。[CollapsingToolbarLayout官方文档](https://developer.android.com/reference/android/support/design/widget/CollapsingToolbarLayout)

主要的特性有：

1、Collapsing title：
> A title which is larger when the layout is fully visible but collapses and becomes smaller as the layout is scrolled off screen. You can set the title to display via setTitle(CharSequence). The title appearance can be tweaked via the collapsedTextAppearance and expandedTextAppearance attributes.

当折叠布局完全展示时，这个title是最大的，这个title会随布局折叠渐渐缩小

2、Content scrim：
> A full-bleed scrim which is show or hidden when the scroll position has hit a certain threshold. You can change this via setContentScrim(Drawable).

折叠后的背景

3、Status bar scrim：
> A scrim which is show or hidden behind the status bar when the scroll position has hit a certain threshold. You can change this via setStatusBarScrim(Drawable). This only works on LOLLIPOP devices when we set to fit system windows.

设置状态栏的背景，只有5.0以上才起作用。

4、Parallax scrolling children：

> Child views can opt to be scrolled within this layout in a parallax fashion. See COLLAPSE_MODE_PARALLAX and setParallaxMultiplier(float).

在折叠的时候会有折叠视差效果。一般搭配layout_collapseParallaxMultiplier=“0.5”视差的明显程度,be between 0.0 and 1.0.

5、Pinned position children

> hild views can opt to be pinned in space globally. This is useful when implementing a collapsing as it allows the Toolbar to be fixed in place even though this layout is moving. See COLLAPSE_MODE_PIN.

在折叠的时候最后固定在顶端

主要的属性：
- app:layout_collapseMode:共有3种模式，parallax，pin，none；效果同特性4,5
- app:contentScrim：同特性2
- app:statusBarScrim：同特性3

其他的属性都可以根据字面意思来判断了，如：
```
app:scrimAnimationDuration=""
app:collapsedTitleGravity=""
app:collapsedTitleTextAppearance=""
app:expandedTitleGravity=""
app:expandedTitleMargin=""
app:expandedTitleMarginBottom=""
app:expandedTitleTextAppearance=""
```
还有一些其他的属性便不多说了。

> 注意：Toolbar 和CollapsingToolbarLayout 同时设置了title时，不会显示Toolbartitle而是显示CollapsingToolbarLayout 的title。如果要显示Toolbar的title需要把CollapsingToolbarLayout的title设置为空。


[Github示例代码](https://github.com/ws614054446/AndroidUI)



