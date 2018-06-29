package com.wangshuai.androidui.material;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wangshuai.androidui.R;
import com.wangshuai.androidui.material.fragment.FourFragment;
import com.wangshuai.androidui.material.fragment.OneFragment;
import com.wangshuai.androidui.material.fragment.ThreeFragment;
import com.wangshuai.androidui.material.fragment.TwoFragment;

import java.lang.reflect.Field;

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
        modifyIconSize(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
        bottomNavigationView.setSelectedItemId(R.id.tab_two);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    /**
     * 修改icon图片的大小
     * @param navigationView
     */
    private void modifyIconSize(BottomNavigationView navigationView) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        try {

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);

                Field mIcon = itemView.getClass().getDeclaredField("mIcon");
                mIcon.setAccessible(true);
                ImageView imageView = (ImageView) mIcon.get(itemView);

                if (itemView.getItemData().isChecked()) {
                    FrameLayout.LayoutParams iconParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                    iconParams.height = 100;
                    iconParams.width = 100;
                    imageView.setLayoutParams(iconParams);

                } else {
                    FrameLayout.LayoutParams iconParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                    iconParams.height = 100;
                    iconParams.width = 100;
                    imageView.setLayoutParams(iconParams);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //由于页面已经固定,故这里把Adapter需要的fragment提前创建
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
