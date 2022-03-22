package huutoan.yomusic.Activity;

import huutoan.yomusic.Fragment.Fragment_Account;
import huutoan.yomusic.Fragment.Fragment_Home_Page;
import huutoan.yomusic.Fragment.Fragment_Library;
import huutoan.yomusic.Fragment.Fragment_Search;
import huutoan.yomusic.R;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        call layout
        bottomNavigation = findViewById(R.id.bottom_navigation);

//        run fragment home first when show UI
        getSupportFragmentManager().beginTransaction().replace(R.id.mainViewPager2, new Fragment_Home_Page()).commit();

//        get event when click navigation
        bottomNavigation.setOnItemSelectedListener(onItemSelectedListener);
    }

    @SuppressLint("NonConstantResourceId")
    private final NavigationBarView.OnItemSelectedListener onItemSelectedListener = (item -> {
        int id = item.getItemId();
        Fragment selectedFragment =  null;
        switch (id) {
            case R.id.home_page:
                selectedFragment = new Fragment_Home_Page();
                break;
            case R.id.search:
                selectedFragment = new Fragment_Search();
                break;
            case R.id.library:
                selectedFragment = new Fragment_Library();
                break;
            case R.id.account:
                selectedFragment = new Fragment_Account();
                break;
        }
        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainViewPager2, selectedFragment).commit();
        return true;
    });

}