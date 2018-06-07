### Material Design系列之TabLayout详解

TabLayout位于design包下，使用时导入design包。

> compile 'com.android.support:design:26.0.0-alpha1'

一般这个控件是结合viewpager和fragment使用。[官方文档](https://developer.android.com/reference/android/support/design/widget/TabLayout)

效果图：

![](https://github.com/ws614054446/AndroidUI/blob/master/imgs/tablayout.png)


##### 使用方式一
代码动态添加tab项

布局如下：

```
<android.support.design.widget.TabLayout
        android:id="@+id/tablayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </android.support.design.widget.TabLayout>
    <android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </android.support.v4.view.ViewPager>
```

```
public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private String[] tabTitles = {"头条","资讯","体育","娱乐","经济","科技","军事","热点","社会","国际","国内"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        initViews();
    }

    private void initViews() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);//必须在 viewPager.setAdapter()之后使用
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tablayout.addTab(tablayout.newTab().setText("新增标签"));//代码添加标签
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TabLayoutFragment.newInstance(tabTitles[position]);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
```

##### 使用方式二
直接在布局中使用TabItem定死tab项

```
<android.support.design.widget.TabLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <android.support.design.widget.TabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="标签1"/>
        <android.support.design.widget.TabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="标签2"
            android:icon="@mipmap/ic_launcher"/>
        <android.support.design.widget.TabItem
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout="@layout/tab_custom_layout"/>
    </android.support.design.widget.TabLayout>
```

##### 重要属性详解

- app:tabSelectedTextColor：选中字体的颜色
- app:tabTextColor：未选中字体的颜色
- app:tabIndicatorColor：指示器下标的颜色
- app:tabIndicatorHeight：指示器下标的高度
- app:tabGravity：tab中布局位置，有两种模式center和fill
- app:tabTextAppearance：字体大小
- app:tabBackground：设置背景
- app:tabMode：设置tablayout的排列模式，有两种scrollable和fixed；默认是fixed。fixed是固定的，适用于标签较少，scrollable是可滚动的，适用于标签较多的情况下

##### 自定义标签布局
示例：

```
for (int i = 0; i < tabTitles.length; i++){
            TabLayout.Tab itemTab = tablayout.getTabAt(i);
            if (itemTab!=null){
                itemTab.setCustomView(R.layout.tab_custom_layout);
                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_custom);
                itemTv.setText("自定义标签"+i);
            }
        }
```

##### 注意
tablayout.setupWithViewPager(viewPager);//必须在 viewPager.setAdapter()之后使用

当结合viewpager使用时，viewpager会有一个预加载机制，可以通过viewpager的setOffscreenPageLimit方法设置数目。若不想页面在不可见的情况下加载数据，需
自己在fragment中自行处理数据加载问题。

还有一个就是fragment重新创建问题，可以在viewpager的适配器中的重写destroyItem方法，去掉super方法调用，进行自己的操作

```
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
```

[Github示例代码](https://github.com/ws614054446/AndroidUI)