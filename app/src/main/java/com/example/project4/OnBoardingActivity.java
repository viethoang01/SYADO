package com.example.project4;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;

public class OnBoardingActivity extends AppCompatActivity {

    private TextView tv_skip;
    private ViewPager viewPager;
    private RelativeLayout layoutBottom;
    private CircleIndicator circleIndicator;
    private LinearLayout layoutNext;

    private OnbroadingViewPagerAdapter onbroadingViewPagerAdapter;

    private void onBindingView(){
        tv_skip = findViewById(R.id.tv_skip);
        viewPager = findViewById(R.id.view_pager_on_boarding);
        layoutBottom = findViewById(R.id.layout_bottom);
        circleIndicator = findViewById(R.id.circle_indicator);
        layoutNext = findViewById(R.id.layout_next);

        onbroadingViewPagerAdapter = new OnbroadingViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


    }
    private void onBindingAction(){
        viewPager.setAdapter(onbroadingViewPagerAdapter);

        circleIndicator.setViewPager(viewPager);
        tv_skip.setOnClickListener(this:: onClickSkip);

        layoutNext.setOnClickListener(this:: onClickNext);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //dad
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    tv_skip.setVisibility(View.GONE);
                    layoutBottom.setVisibility(View.GONE);
                }else{
                    tv_skip.setVisibility(View.VISIBLE);
                    layoutBottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onClickNext(View view) {
        if(viewPager.getCurrentItem() < 2){
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }
    }

    private void onClickSkip(View view) {
        viewPager.setCurrentItem(2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        onBindingView();
        onBindingAction();
    }
}