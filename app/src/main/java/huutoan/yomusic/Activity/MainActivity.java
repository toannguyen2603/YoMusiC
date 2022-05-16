package huutoan.yomusic.Activity;

import huutoan.yomusic.Adapter.ChartsAdapter;
import huutoan.yomusic.Adapter.MostLikedSongAdapter;
import huutoan.yomusic.Adapter.PlayListAdapter;
import huutoan.yomusic.Adapter.SingerAdapter;
import huutoan.yomusic.Adapter.TopicAdapter;

import huutoan.yomusic.Fragment.Fragment_Account;
import huutoan.yomusic.Fragment.Fragment_Album_Singer;
import huutoan.yomusic.Fragment.Fragment_Charts;
import huutoan.yomusic.Fragment.Fragment_Home_Page;
import huutoan.yomusic.Fragment.Fragment_Library;
import huutoan.yomusic.Fragment.Fragment_Most_Liked_Song;
import huutoan.yomusic.Fragment.Fragment_PlayList;
import huutoan.yomusic.Fragment.Fragment_Search;
import huutoan.yomusic.Fragment.Fragment_Topic;

import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.Model.PlayListSong;
import huutoan.yomusic.Model.Singer;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.Model.Topic;

import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        check network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



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