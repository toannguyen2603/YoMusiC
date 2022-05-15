package huutoan.yomusic.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import huutoan.yomusic.Adapter.ChartsAdapter;
import huutoan.yomusic.Adapter.MostLikedSongAdapter;
import huutoan.yomusic.Adapter.PlayListAdapter;
import huutoan.yomusic.Adapter.SingerAdapter;
import huutoan.yomusic.Adapter.TopicAdapter;
import huutoan.yomusic.Adapter.TrendingAdapter;
import huutoan.yomusic.Fragment.Fragment_Album_Singer;
import huutoan.yomusic.Fragment.Fragment_Charts;
import huutoan.yomusic.Fragment.Fragment_Most_Liked_Song;
import huutoan.yomusic.Fragment.Fragment_PlayList;
import huutoan.yomusic.Fragment.Fragment_Topic;
import huutoan.yomusic.Fragment.Fragment_Trending_Hits;
import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.Model.PlayListSong;
import huutoan.yomusic.Model.Singer;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.Model.Topic;
import huutoan.yomusic.Model.Trending;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        },3000);
    }
}