package com.wangshuai.androidui.material;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangshuai.androidui.R;

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
        viewPager.setOffscreenPageLimit(1);//预加载多少个fragment页面
        tablayout.setupWithViewPager(viewPager);//必须在 viewPager.setAdapter()之后使用
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tablayout.addTab(tablayout.newTab().setText("新增标签"));//代码添加标签

//        for (int i = 0; i < tabTitles.length; i++){
//            TabLayout.Tab itemTab = tablayout.getTabAt(i);
//            if (itemTab!=null){
//                itemTab.setCustomView(R.layout.tab_custom_layout);
//                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_custom);
//                itemTv.setText("自定义标签"+i);
//            }
//        }

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }
}
