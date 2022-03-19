package huutoan.yomusic.Activity;

import huutoan.yomusic.Adapter.HomeViewPagerAdapter;
import huutoan.yomusic.Fragment.Fragment_Home_Page;
import huutoan.yomusic.Fragment.Fragment_Library;
import huutoan.yomusic.Fragment.Fragment_Search;
import huutoan.yomusic.R;
import huutoan.yomusic.Custom.*;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ViewPager2 mainViewPager2;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        call layout
        mainViewPager2 = findViewById(R.id.mainViewPager2);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        initNavigation();

    }

//
    @SuppressLint("NonConstantResourceId")
    private void initNavigation() {
//        add fragment view in main activity
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(this);
        homeViewPagerAdapter.addFragment(new Fragment_Home_Page(), "Home");
        homeViewPagerAdapter.addFragment(new Fragment_Search(), "Search");
        homeViewPagerAdapter.addFragment(new Fragment_Library(), "Library");

//         set add adapter
        mainViewPager2.setAdapter(homeViewPagerAdapter);
//        get animation depth page transformer
        mainViewPager2.setPageTransformer(new DepthPageTransformer());

//        register scroll page to horizontal
        mainViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                case 0:
                    bottomNavigation.getMenu().findItem(R.id.home_page).setChecked(true);
                    break;
                case 1:
                    bottomNavigation.getMenu().findItem(R.id.search).setChecked(true);
                    break;
                case 2:
                    bottomNavigation.getMenu().findItem(R.id.library).setChecked(true);
                }
            }
        });
//        get event switch the page with bottom navigation
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            switch(id){
                case R.id.home_page:
                    mainViewPager2.setCurrentItem(0, true);
                    break;
                case R.id.search:
                    mainViewPager2.setCurrentItem(1, true);
                    break;
                case R.id.library:
                    mainViewPager2.setCurrentItem(2, true);
                    break;
            }
            return true;
        });



    }
}