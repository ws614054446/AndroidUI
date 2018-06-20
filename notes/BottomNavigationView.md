#### Material Design系列之BottomNavigationView详解

[Material Design官方文档Bottom navigation的介绍](https://material.io/design/components/bottom-navigation.html)

[BottomNavigationView官方API文档](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView)

##### 简介

BottomNavigationView实现的效果就是常见的app底部导航栏的效果。

Bottom navigation bars make it easy for users to explore and switch between top-level views in a single tap. It should be used when application has three to five top-level destinations.

适用于3到5个tab的情况下。

##### 使用

> compile 'com.android.support:design:26.0.0-alpha1'

```
<android.support.design.widget.BottomNavigationView
        android:id="@+id/bottomnavigationview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:itemIconTint="@drawable/selector_tab_color"
        app:itemTextColor="@drawable/selector_tab_color"
        app:menu="@menu/bottom_navigation_tab">

    </android.support.design.widget.BottomNavigationView>
```

属性：

itemIconTint：icon图片的颜色

itemTextColor：文本的颜色

menu:tab的布局

selector_tab_color：

```
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:state_checked="true" android:color="@color/colorAccent"/>
    <item android:state_checked="false" android:color="@color/colorPrimary"/>

</selector>
```

一般来说图片的颜色是和文字的颜色是一致的。

菜单的布局：

```
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/tab_one"
        android:icon="@drawable/icon_one_selected"
        android:title="首页"/>

    <item
        android:id="@+id/tab_two"
        android:icon="@drawable/icon_two_selected"
        android:title="消息"/>

    <item
        android:id="@+id/tab_three"
        android:icon="@drawable/icon_three_selected"
        android:title="订单"/>

    <item
        android:id="@+id/tab_four"
        android:icon="@drawable/icon_four_selected"
        android:title="我的"/>
</menu>
```

这种情况只适用于图片是纯色且选中和未选中时的图片是一样的。如果是不同的图片需要新建一个selector文件，
设置选中时的图片和未选中时的图片，并且不设置itemIconTint属性。

完整的activity代码：

```
public class BottomNavigationViewActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationview);
        disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
        bottomNavigationView.setSelectedItemId(R.id.tab_two);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.tab_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab_three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tab_four:
                viewPager.setCurrentItem(3);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        menuItem = bottomNavigationView.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void disableShiftMode(BottomNavigationView navigationView) {

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private Fragment[] mFragments = new Fragment[]{new OneFragment(), new TwoFragment(), new ThreeFragment(),new FourFragment()};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
```

##### 注意
1、BottomNavigationView只适用于3到5个的导航栏；

2、当tab个数大余3个时，BottomNavigationView不会均分宽度，一般来说我们都是需要均分宽度。

解决方案：disableShiftMode(bottomNavigationView);

```
public void disableShiftMode(BottomNavigationView navigationView) {

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

来看下关于mShiftingMode这个变量的源码，在BottomNavigationMenuView中：

```
mShiftingMode = mMenu.size() > 3;//当大于3时为true
        for (int i = 0; i < mMenu.size(); i++) {
            mPresenter.setUpdateSuspended(true);
            mMenu.getItem(i).setCheckable(true);
            mPresenter.setUpdateSuspended(false);
            BottomNavigationItemView child = getNewItem();
            mButtons[i] = child;
            child.setIconTintList(mItemIconTint);
            child.setTextColor(mItemTextColor);
            child.setItemBackground(mItemBackgroundRes);
            child.setShiftingMode(mShiftingMode);
            child.initialize((MenuItemImpl) mMenu.getItem(i), 0);
            child.setItemPosition(i);
            child.setOnClickListener(mOnClickListener);
            addView(child);
        }
```

在执行onMeasure方法时：

```
if (mShiftingMode) {
            final int inactiveCount = count - 1;
            final int activeMaxAvailable = width - inactiveCount * mInactiveItemMinWidth;
            final int activeWidth = Math.min(activeMaxAvailable, mActiveItemMaxWidth);
            final int inactiveMaxAvailable = (width - activeWidth) / inactiveCount;
            final int inactiveWidth = Math.min(inactiveMaxAvailable, mInactiveItemMaxWidth);
            int extra = width - activeWidth - inactiveWidth * inactiveCount;
            for (int i = 0; i < count; i++) {
                mTempChildWidths[i] = (i == mSelectedItemPosition) ? activeWidth : inactiveWidth;
                if (extra > 0) {
                    mTempChildWidths[i]++;
                    extra--;
                }
            }
        } else {
            final int maxAvailable = width / (count == 0 ? 1 : count);
            final int childWidth = Math.min(maxAvailable, mActiveItemMaxWidth);
            int extra = width - childWidth * count;
            for (int i = 0; i < count; i++) {
                mTempChildWidths[i] = childWidth;
                if (extra > 0) {
                    mTempChildWidths[i]++;
                    extra--;
                }
            }
        }
```

下图是mShiftingMode为true的情况下debug拿到的数据，再结合效果图，即可分析出：

inactiveCount为闲置的个数，即没有被选中的menuItem的个数，选中的宽度activeWidth和未选中的宽度inactiveWidth不一致。
当mShiftingMode为false执行的代码很容易看出宽度是均分计算的。

##### 其他
源码里面的各个属性的设置：
```
<dimen name="design_bottom_navigation_active_item_max_width">168dp</dimen>//选中时的最大宽度
    <dimen name="design_bottom_navigation_active_text_size">14sp</dimen>//选中时的字体大小
    <dimen name="design_bottom_navigation_elevation">8dp</dimen>//阴影的大小
    <dimen name="design_bottom_navigation_height">56dp</dimen>//高度
    <dimen name="design_bottom_navigation_item_max_width">96dp</dimen>//未选中的最大宽度
    <dimen name="design_bottom_navigation_item_min_width">56dp</dimen>//未选中的最小的宽度
    <dimen name="design_bottom_navigation_margin">8dp</dimen>//icon与文本之间的间距
    <dimen name="design_bottom_navigation_shadow_height">1dp</dimen>//阴影高度
    <dimen name="design_bottom_navigation_text_size">12sp</dimen>//未选中时的字体大小
```

如果要修改这些属性值，在自己项目的dimens定义相同的名字，重新赋值

[Github示例代码](https://github.com/ws614054446/AndroidUI)