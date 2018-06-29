#### Material Design系列之BottomSheet详解

[BottomSheetBehavior官方文档](https://developer.android.com/reference/android/support/design/widget/BottomSheetBehavior)

[BottomSheetDialog官方文档](https://developer.android.com/reference/android/support/design/widget/BottomSheetDialog)

[BottomSheetDialogFragment官方文档](https://developer.android.com/reference/android/support/design/widget/BottomSheetDialogFragment)

[Material Design官方文档Sheets: bottom的介绍](https://material.io/design/components/sheets-bottom.html)

##### 简介
BottomSheet可以理解为底部对话框，类似popwindow实现的效果

BottomSheet有两种类型：

一、没有蒙层，可以对没有遮盖住的地方进行操作，类似百度地图查询路线的页面；

二、有蒙层效果，就是和正常的popwindow使用效果一样了。

##### 使用

> compile 'com.android.support:design:26.0.0-alpha1'

###### 1、BottomSheetBehavior的使用

依赖于CoordinatorLayout和BottomSheetBehavior，需要将底部菜单布局作为CoordinatorLayout的子View，实现简单但不够灵活，适用于底部菜单布局稳定的情况。

```
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.wangshuai.androidui.material.BottomSheetActivity">

    <include layout="@layout/content_main" />

    <include layout="@layout/content_bottom_sheet" />


</android.support.design.widget.CoordinatorLayout>
```

content_bottom_sheet布局：
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              android:id="@+id/ll_content_bottom_sheet"
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              app:behavior_hideable="true"
              app:behavior_peekHeight="50dp"
              app:layout_behavior="@string/bottom_sheet_behavior"
    android:background="@color/white">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:text="人生若只如初见，何事秋风悲画扇。"
        android:textSize="20sp"
        android:gravity="center"/>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:text="等闲变却故人心，却道故人心易变。"
        android:textSize="20sp"
        android:gravity="center"/>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:text="骊山语罢清宵半，泪雨霖铃终不怨。"
        android:textSize="20sp"
        android:gravity="center"/>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:text="何如薄幸锦衣郎，比翼连枝当日愿。"
        android:textSize="20sp"
        android:gravity="center"/>

</LinearLayout>
```

其中app:behavior_hideable="true"表示可以让bottom sheet完全隐藏，默认为false；

app:behavior_peekHeight="60dp"表示当为STATE_COLLAPSED（折叠）状态的时候bottom sheet残留的高度，默认为0。

app:layout_behavior="@string/bottom_sheet_behavior"这个一定要设置，不然起不到效果。

通过BottomSheetBehavior控制content_bottom_sheet布局的显示和隐藏

```
bottomSheetBehavior = BottomSheetBehavior.from(llContentBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

            case R.id.btn_expand://展开
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.btn_collapsed://折叠
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.btn_hide://隐藏
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
```

Bottom Sheet有五种状态：

- STATE_COLLAPSED：折叠状态，bottom sheets只在底部显示一部分布局。显示高度可以通过 app:behavior_peekHeight 设置；
- STATE_DRAGGING：过渡状态，此时用户正在向上或者向下拖动bottom sheet；
- STATE_SETTLING: 视图从脱离手指自由滑动到最终停下的这一小段时间
- STATE_EXPANDED: 完全展开的状态
- STATE_HIDDEN: 隐藏状态。默认是false，可通过app:behavior_hideable属性设置是否能隐藏

这种使用方法是没有蒙层，可以对其他控件进行操作。

###### 2、BottomSheetDialog的使用

BottomSheetDialog的使用就和对话框差不多。会出现蒙层，只能对弹出的页面进行操作。
```
                if (dialog == null){
                    dialog = new BottomSheetDialog(this);
                }
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(true);
                View view =LayoutInflater.from(BottomSheetActivity.this).inflate(R.layout.content_bottom_sheet_dialog,null);
                TextView tvWechat = view.findViewById(R.id.tv_wechat);
                tvWechat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(BottomSheetActivity.this,"微信",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                dialog.show();
```

BottomSheetDialog部分源码：
```
private View wrapInBottomSheet(int layoutResId, View view, ViewGroup.LayoutParams params) {
        final CoordinatorLayout coordinator = (CoordinatorLayout) View.inflate(getContext(),
                R.layout.design_bottom_sheet_dialog, null);
        if (layoutResId != 0 && view == null) {
            view = getLayoutInflater().inflate(layoutResId, coordinator, false);
        }
        FrameLayout bottomSheet = (FrameLayout) coordinator.findViewById(R.id.design_bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        mBehavior.setBottomSheetCallback(mBottomSheetCallback);
        mBehavior.setHideable(mCancelable);
        if (params == null) {
            bottomSheet.addView(view);
        } else {
            bottomSheet.addView(view, params);
        }
        // We treat the CoordinatorLayout as outside the dialog though it is technically inside
        coordinator.findViewById(R.id.touch_outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCancelable && isShowing() && shouldWindowCloseOnTouchOutside()) {
                    cancel();
                }
            }
        });
        // Handle accessibility events
        ViewCompat.setAccessibilityDelegate(bottomSheet, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host,
                    AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (mCancelable) {
                    info.addAction(AccessibilityNodeInfoCompat.ACTION_DISMISS);
                    info.setDismissable(true);
                } else {
                    info.setDismissable(false);
                }
            }

            @Override
            public boolean performAccessibilityAction(View host, int action, Bundle args) {
                if (action == AccessibilityNodeInfoCompat.ACTION_DISMISS && mCancelable) {
                    cancel();
                    return true;
                }
                return super.performAccessibilityAction(host, action, args);
            }
        });
        bottomSheet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // Consume the event and prevent it from falling through
                return true;
            }
        });
        return coordinator;
    }
```
由源码可知，BottomSheetDialog的setContentView最终是被CoordinatorLayout包裹住。

###### 3、BottomSheetDialogFragment的使用
BottomSheetDialogFragment的使用和fragment一样。可以帮助我们实现全屏的BottomSheet展示效果

```
CustomFragmentDialog fragmentDialog = new CustomFragmentDialog();
fragmentDialog.show(getSupportFragmentManager(),"CustomFragmentDialog");

public class CustomFragmentDialog extends BottomSheetDialogFragment {
    private ArrayList<String> list = new ArrayList<>();

    public CustomFragmentDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_bottom_sheet_fragment_dialog,container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        for (int i = 0;i < 100;i++){
            list.add("条目"+i);
        }
        RecyclerView recyclerView = view.findViewById(R.id.rv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }

}

/**
 * 全屏显示
 */
public class FullSheetDialogFragment extends BottomSheetDialogFragment{

    private BottomSheetBehavior mBehavior;
    private ArrayList<String> list = new ArrayList<>();

    public FullSheetDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.content_bottom_sheet_fragment_dialog, null);
        initViews(view);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());

        return dialog;
    }

    private void initViews(View view) {
        for (int i = 0;i < 100;i++){
            list.add("条目"+i);
        }
        RecyclerView recyclerView = view.findViewById(R.id.rv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//全屏展开
    }

}
```

##### 使用bottom sheet的问题

[BottomSheetDialog沉浸式的一些坑](https://www.jianshu.com/p/08755838c00f)

[解决使用BottomSheetDialog时状态栏变黑的问题](https://blog.csdn.net/maosidiaoxian/article/details/52288597)

[Github示例代码](https://github.com/ws614054446/AndroidUI)