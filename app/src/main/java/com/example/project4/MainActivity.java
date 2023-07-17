package com.example.project4;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;

    private void bindingView(){
        mViewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        mViewPager.setOffscreenPageLimit(1); // 2 tab sẽ được chuẩn bị để hiển thị

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);


    }

    private void biningAction(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {    //
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.home_nav).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.search_nav).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.noti_nav).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.more_nav).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_nav:
                        mViewPager.setCurrentItem(0);
                        HomeFragment homeFragment = (HomeFragment) mViewPager.getAdapter().instantiateItem(mViewPager,0);
                        homeFragment.reloadData();
                        break;
                    case R.id.search_nav:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.noti_nav:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.more_nav:
                        mViewPager.setCurrentItem(3);
                        break;

                }
                return true;
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        biningAction();
    }

}