### SnackBar的使用详解及源码分析

#### 简介

[SnackBar的官方API文档](https://developer.android.com/reference/android/support/design/widget/Snackbar)

> Snackbars provide lightweight feedback about an operation. They show a brief message at the bottom of the screen on mobile and lower left on larger devices. Snackbars appear above all other elements on screen and only one can be displayed at a time.

> Snackbars can contain an action which is set via setAction(CharSequence, android.view.View.OnClickListener).

> To be notified when a snackbar has been shown or dismissed, you can provide a Snackbar.Callback via addCallback(BaseCallback).

Snackbar是类似Toast的东西，使用方式也类似，但是Snackbar提供了一个轻量级的操作按钮。可以实现类似对话框的效果。从底部弹出，同一时间只允许一个弹出显示，同时添加了监听SnackBar出现和消失的回调.

[Material Design关于Snackbar的介绍](https://material.io/design/components/snackbars.html#usage)

根据google提出的Material Design设计理念，SnackBar适用于action在0-1个的情况下。

#### 简单使用

> compile 'com.android.support:design:26.0.0-alpha1'

```
Snackbar.make(btnClick, "哈哈哈哈", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackBarActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(Color.WHITE)
                        .addCallback(new Snackbar.Callback(){
                            @Override
                            public void onShown(Snackbar sb) {
                                Toast.makeText(SnackBarActivity.this, "onShown", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                Toast.makeText(SnackBarActivity.this, "onDismissed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
```

Snackbar的使用方式和Toast类似，一些常用方法：
- make(@NonNull View view, @NonNull CharSequence text,@Duration int duration)

```
 /**
     *
     * @param view     The view to find a parent from. 寻找合适父布局的起点
     * @param text     The text to show.  Can be formatted text. 显示的文本
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or {@link
     *                 #LENGTH_LONG} 还有一种LENGTH_INDEFINITE（一直显示，触发action点击事件或者有另外一个snackbar显示时消失）
     */
```

- setAction:设置动作
- setActionTextColor:设置动作文本的颜色
- addCallback:添加消失和显示的监听

#### 源码解析

```
 @NonNull
    public static Snackbar make(@NonNull View view, @NonNull CharSequence text,
            @Duration int duration) {
            //寻找父布局
        final ViewGroup parent = findSuitableParent(view);
        if (parent == null) {
            throw new IllegalArgumentException("No suitable parent found from the given view. "
                    + "Please provide a valid view.");
        }

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final SnackbarContentLayout content =
                (SnackbarContentLayout) inflater.inflate(
                        R.layout.design_layout_snackbar_include, parent, false);
        final Snackbar snackbar = new Snackbar(parent, content, content);
        snackbar.setText(text);
        snackbar.setDuration(duration);
        return snackbar;
    }

private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {//如果是CoordinatorLayout，则直接使用它
                // We've found a CoordinatorLayout, use it
                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {//如果是查找到DecorView，即最顶层的view，则直接使用它
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {//如果不是DecorView，则先保存下来
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {//不是DecorView的情况下则继续往上寻找getParent
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);//不是DecorView的情况下，一直循环直到找到最上层的view

        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }
```

> final SnackbarContentLayout content =(SnackbarContentLayout) inflater.inflate(R.layout.design_layout_snackbar_include, parent, false);//创建一个内容布局，其中的design_layout_snackbar_include布局如下：

```
<view
    xmlns:android="http://schemas.android.com/apk/res/android"
    class="android.support.design.internal.SnackbarContentLayout"
    android:theme="@style/ThemeOverlay.AppCompat.Dark"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom">

    <TextView
        android:id="@+id/snackbar_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:paddingTop="@dimen/design_snackbar_padding_vertical"
        android:paddingBottom="@dimen/design_snackbar_padding_vertical"
        android:paddingLeft="@dimen/design_snackbar_padding_horizontal"
        android:paddingRight="@dimen/design_snackbar_padding_horizontal"
        android:textAppearance="@style/TextAppearance.Design.Snackbar.Message"
        android:maxLines="@integer/design_snackbar_text_max_lines"
        android:layout_gravity="center_vertical|left|start"
        android:ellipsize="end"
        android:textAlignment="viewStart"/>

    <Button
        android:id="@+id/snackbar_action"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="@dimen/design_snackbar_extra_spacing_horizontal"
        android:layout_marginStart="@dimen/design_snackbar_extra_spacing_horizontal"
        android:layout_gravity="center_vertical|right|end"
        android:minWidth="48dp"
        android:visibility="gone"
        android:textColor="?attr/colorAccent"
        style="?attr/borderlessButtonStyle"/>

</view>
```

由上面的布局可以分析出，snackbar只能从底部弹出。如果需要自定义实现一些自定义效果，例如改变弹出框的底色或则加载不同的弹出界面，则可以从这入手。
获取到snackbar的布局即SnackbarContentLayout，然后可以根据这两个控件的id来设置一些样式，或者改变这个view的背景。

然后再到这里：
> final Snackbar snackbar = new Snackbar(parent, content, content);

```
private Snackbar(ViewGroup parent, View content, ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);//调用父类（BaseTransientBottomBar<Snackbar> ）的构造方法
    }


    /**
         * Constructor for the transient bottom bar.
         *
         * @param parent The parent for this transient bottom bar.//根布局
         * @param content The content view for this transient bottom bar.//snackbar的内容布局
         * @param contentViewCallback The content view callback for this transient bottom bar.//内容布局的回调
         */
        protected BaseTransientBottomBar(@NonNull ViewGroup parent, @NonNull View content,
                @NonNull ContentViewCallback contentViewCallback) {
            if (parent == null) {
                throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
            }
            if (content == null) {
                throw new IllegalArgumentException("Transient bottom bar must have non-null content");
            }
            if (contentViewCallback == null) {
                throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
            }

            mTargetParent = parent;
            mContentViewCallback = contentViewCallback;
            mContext = parent.getContext();

            ThemeUtils.checkAppCompatTheme(mContext);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            // Note that for backwards compatibility reasons we inflate a layout that is defined
            // in the extending Snackbar class. This is to prevent breakage of apps that have custom
            // coordinator layout behaviors that depend on that layout.
            mView = (SnackbarBaseLayout) inflater.inflate(
                    R.layout.design_layout_snackbar, mTargetParent, false);//获取snackbar的父容器
            mView.addView(content);

            ViewCompat.setAccessibilityLiveRegion(mView,
                    ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE);
            ViewCompat.setImportantForAccessibility(mView,
                    ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);

            // Make sure that we fit system windows and have a listener to apply any insets
            ViewCompat.setFitsSystemWindows(mView, true);
            ViewCompat.setOnApplyWindowInsetsListener(mView,
                    new android.support.v4.view.OnApplyWindowInsetsListener() {
                        @Override
                        public WindowInsetsCompat onApplyWindowInsets(View v,
                                WindowInsetsCompat insets) {
                            // Copy over the bottom inset as padding so that we're displayed
                            // above the navigation bar
                            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(),
                                    v.getPaddingRight(), insets.getSystemWindowInsetBottom());
                            return insets;
                        }
                    });

            mAccessibilityManager = (AccessibilityManager)
                    mContext.getSystemService(Context.ACCESSIBILITY_SERVICE);
        }

        R.layout.design_layout_snackbar:

        <view xmlns:android="http://schemas.android.com/apk/res/android"
              class="android.support.design.widget.Snackbar$SnackbarLayout"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:layout_gravity="bottom"//这便是snackbar从底部弹出的缘故，如果需要从其他地方弹出，比如从底部弹出，则可以修改此处
              android:theme="@style/ThemeOverlay.AppCompat.Dark"
              style="@style/Widget.Design.Snackbar" />
```

这样snackbar就被创建了。

接下来看一下snackbar设置的方法：

```
@NonNull
    public Snackbar setActionTextColor(@ColorInt int color) {
        final SnackbarContentLayout contentLayout = (SnackbarContentLayout) mView.getChildAt(0);
        final TextView tv = contentLayout.getActionView();
        tv.setTextColor(color);
        return this;
    }


 /**
      * Set the action to be displayed in this {@link BaseTransientBottomBar}.
      *
      * @param text     Text to display for the action
      * @param listener callback to be invoked when the action is clicked
      */
     @NonNull
     public Snackbar setAction(CharSequence text, final View.OnClickListener listener) {
         final SnackbarContentLayout contentLayout = (SnackbarContentLayout) mView.getChildAt(0);
         final TextView tv = contentLayout.getActionView();

         if (TextUtils.isEmpty(text) || listener == null) {
             tv.setVisibility(View.GONE);
             tv.setOnClickListener(null);
         } else {
             tv.setVisibility(View.VISIBLE);
             tv.setText(text);
             tv.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     listener.onClick(view);
                     // Now dismiss the Snackbar
                     dispatchDismiss(BaseCallback.DISMISS_EVENT_ACTION);//调用父类BaseTransientBottomBar里的方法
                 }
             });
         }
         return this;
     }


     void dispatchDismiss(@BaseCallback.DismissEvent int event) {
             SnackbarManager.getInstance().dismiss(mManagerCallback, event);
         }
```

SnackbarManager是关于snackbar的一个管理类，负责显示，隐藏，回调，管理snackbar的队列这一些功能

显示方法(SnackbarManager)：

```
 private SnackbarManager() {
        mLock = new Object();
        mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case MSG_TIMEOUT:
                        handleTimeout((SnackbarRecord) message.obj);
                        return true;
                }
                return false;
            }
        });
    }


public void show(int duration, Callback callback) {
        synchronized (mLock) {//加锁，保证只有一个snackbar显示
            if (isCurrentSnackbarLocked(callback)) {//是否是当前的snackbar
                // Means that the callback is already in the queue. We'll just update the duration
                mCurrentSnackbar.duration = duration;

                // If this is the Snackbar currently being shown, call re-schedule it's
                // timeout
                mHandler.removeCallbacksAndMessages(mCurrentSnackbar);//如果这是当前在显示的snackbar，因为重新设置了mCurrentSnackbar.duration，移除掉之前的mCurrentSnackbar
                scheduleTimeoutLocked(mCurrentSnackbar);//把这个重新设置时间的mCurrentSnackbar提交上去
                return;
            } else if (isNextSnackbarLocked(callback)) {//是否是下一个snackbar
                // We'll just update the duration
                mNextSnackbar.duration = duration;//暂未显示,只需要更新显示时间就可以了，在队列等待显示
            } else {//当前的都没有，下一个也没有，即第一次显示
                // Else, we need to create a new record and queue it
                mNextSnackbar = new SnackbarRecord(duration, callback);//新建一个snackbar和队列
            }

            if (mCurrentSnackbar != null && cancelSnackbarLocked(mCurrentSnackbar,
                    Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE)) {
                // If we currently have a Snackbar, try and cancel it and wait in line
                return;
            } else {//第一次显示时 mCurrentSnackbar = null
                // Clear out the current snackbar
                mCurrentSnackbar = null;
                // Otherwise, just show it now
                showNextSnackbarLocked();//把下一个snackbar移到队列当前，并显示
            }
        }
    }

    private void scheduleTimeoutLocked(SnackbarRecord r) {
            if (r.duration == Snackbar.LENGTH_INDEFINITE) {
                // If we're set to indefinite, we don't want to set a timeout
                return;
            }

            int durationMs = LONG_DURATION_MS;
            if (r.duration > 0) {
                durationMs = r.duration;
            } else if (r.duration == Snackbar.LENGTH_SHORT) {
                durationMs = SHORT_DURATION_MS;
            }
            mHandler.removeCallbacksAndMessages(r);
            mHandler.sendMessageDelayed(Message.obtain(mHandler, MSG_TIMEOUT, r), durationMs);
        }

    private void showNextSnackbarLocked() {
           if (mNextSnackbar != null) {
               mCurrentSnackbar = mNextSnackbar;
               mNextSnackbar = null;

               final Callback callback = mCurrentSnackbar.callback.get();
               if (callback != null) {
                   callback.show();
               } else {
                   // The callback doesn't exist any more, clear out the Snackbar
                   mCurrentSnackbar = null;
               }
           }
       }

    private static class SnackbarRecord {
            final WeakReference<Callback> callback;
            int duration;
            boolean paused;

            SnackbarRecord(int duration, Callback callback) {
                this.callback = new WeakReference<>(callback);
                this.duration = duration;
            }

            boolean isSnackbar(Callback callback) {
                return callback != null && this.callback.get() == callback;
            }
        }
```
callback.show();回调在BaseTransientBottomBar实现了显示snackbar的回调：

```
final SnackbarManager.Callback mManagerCallback = new SnackbarManager.Callback() {
        @Override
        public void show() {
            sHandler.sendMessage(sHandler.obtainMessage(MSG_SHOW, BaseTransientBottomBar.this));
        }

        @Override
        public void dismiss(int event) {
            sHandler.sendMessage(sHandler.obtainMessage(MSG_DISMISS, event, 0,
                    BaseTransientBottomBar.this));
        }
    };

  由当前类BaseTransientBottomBar的sHandler发送消息处理显示隐藏
  static {//静态代码块，类一加载就初始化这个handler
          sHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
              @Override
              public boolean handleMessage(Message message) {
                  switch (message.what) {
                      case MSG_SHOW:
                          ((BaseTransientBottomBar) message.obj).showView();
                          return true;
                      case MSG_DISMISS:
                          ((BaseTransientBottomBar) message.obj).hideView(message.arg1);
                          return true;
                  }
                  return false;
              }
          });
      }

最后调用showView方法，显示snackbar：

final void showView() {
        if (mView.getParent() == null) {
            final ViewGroup.LayoutParams lp = mView.getLayoutParams();

            if (lp instanceof CoordinatorLayout.LayoutParams) {
                // If our LayoutParams are from a CoordinatorLayout, we'll setup our Behavior
                final CoordinatorLayout.LayoutParams clp = (CoordinatorLayout.LayoutParams) lp;

                final Behavior behavior = new Behavior();
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
                behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {
                        view.setVisibility(View.GONE);
                        dispatchDismiss(BaseCallback.DISMISS_EVENT_SWIPE);
                    }

                    @Override
                    public void onDragStateChanged(int state) {
                        switch (state) {
                            case SwipeDismissBehavior.STATE_DRAGGING:
                            case SwipeDismissBehavior.STATE_SETTLING:
                                // If the view is being dragged or settling, pause the timeout
                                SnackbarManager.getInstance().pauseTimeout(mManagerCallback);
                                break;
                            case SwipeDismissBehavior.STATE_IDLE:
                                // If the view has been released and is idle, restore the timeout
                                SnackbarManager.getInstance()
                                        .restoreTimeoutIfPaused(mManagerCallback);
                                break;
                        }
                    }
                });
                clp.setBehavior(behavior);
                // Also set the inset edge so that views can dodge the bar correctly
                clp.insetEdge = Gravity.BOTTOM;
            }

            mTargetParent.addView(mView);
        }

        mView.setOnAttachStateChangeListener(
                new BaseTransientBottomBar.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {}

                @Override
                public void onViewDetachedFromWindow(View v) {
                    if (isShownOrQueued()) {
                        // If we haven't already been dismissed then this event is coming from a
                        // non-user initiated action. Hence we need to make sure that we callback
                        // and keep our state up to date. We need to post the call since
                        // removeView() will call through to onDetachedFromWindow and thus overflow.
                        sHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onViewHidden(BaseCallback.DISMISS_EVENT_MANUAL);
                            }
                        });
                    }
                }
            });

        if (ViewCompat.isLaidOut(mView)) {
            if (shouldAnimate()) {
                // If animations are enabled, animate it in
                animateViewIn();
            } else {
                // Else if anims are disabled just call back now
                onViewShown();
            }
        } else {
            // Otherwise, add one of our layout change listeners and show it in when laid out
            mView.setOnLayoutChangeListener(new BaseTransientBottomBar.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int left, int top, int right, int bottom) {
                    mView.setOnLayoutChangeListener(null);

                    if (shouldAnimate()) {
                        // If animations are enabled, animate it in
                        animateViewIn();
                    } else {
                        // Else if anims are disabled just call back now
                        onViewShown();
                    }
                }
            });
        }
    }

    最后是执行onViewShown（已经显示）方法：
    void onViewShown() {
            SnackbarManager.getInstance().onShown(mManagerCallback);
            if (mCallbacks != null) {
                // Notify the callbacks. Do that from the end of the list so that if a callback
                // removes itself as the result of being called, it won't mess up with our iteration
                int callbackCount = mCallbacks.size();
                for (int i = callbackCount - 1; i >= 0; i--) {
                    mCallbacks.get(i).onShown((B) this);
                }
            }
        }

        SnackbarManager的onShown
    /**
         * Should be called when a Snackbar is being shown. This is after any entrance animation has
         * finished.
         */
        public void onShown(Callback callback) {
            synchronized (mLock) {
                if (isCurrentSnackbarLocked(callback)) {
                    scheduleTimeoutLocked(mCurrentSnackbar);//延时处理
                }
            }
        }
```

以上便是整个的显示流程。

Snackbar通过mManagerCallback向SnackbarManager发送消息，
Snackbar的sHandler负责处理两个消息，showView和hideView。SnackbarManager起到一个控制作用。