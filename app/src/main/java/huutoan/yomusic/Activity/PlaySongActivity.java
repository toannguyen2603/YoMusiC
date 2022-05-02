package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import huutoan.yomusic.Adapter.ViewPagerPlaySong;
import huutoan.yomusic.Fragment.Fragment_Disk_Song;
import huutoan.yomusic.Fragment.Fragment_Play_List_Songs;
import huutoan.yomusic.Model.MostLikedSongs;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;

public class PlaySongActivity extends AppCompatActivity {

    Toolbar toolbarPlaySong;
    TextView textTimeSong, textTimeTotalSong;
    SeekBar seekBarTimeSong;
    ImageButton imgPlay, imgShuffle, imgNext, imgPrevious;
    ViewPager2 viewPagerPlaySong;
    Fragment_Disk_Song fragment_disk_song;
    Fragment_Play_List_Songs fragment_play_list_songs;

    public static ArrayList<Song> songArrayList;

    public static ArrayList<Song> songArrayListSong = new ArrayList<>();

    public static ViewPagerPlaySong addFragmentSong;

    Song nameSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        fragment_play_list_songs = new Fragment_Play_List_Songs();
        fragment_disk_song = new Fragment_Disk_Song();

        initLayout();
        getDataFromIntent();
    }

    private void initLayout() {
        toolbarPlaySong = findViewById(R.id.toolBarPlaySong);
        textTimeSong = findViewById(R.id.textViewRunTime);
        textTimeTotalSong = findViewById(R.id.textViewTimeTotal);
        seekBarTimeSong = findViewById(R.id.seekBarTime);
        imgPlay = findViewById(R.id.imageButtonPlayOfPause);
        imgNext = findViewById(R.id.imageButtonNext);
        imgPrevious = findViewById(R.id.imageButtonPrevious);
        imgShuffle = findViewById(R.id.imageButtonPrevious);
        viewPagerPlaySong = findViewById(R.id.viewPagerDiskSong);

        setSupportActionBar(toolbarPlaySong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaySong.setNavigationOnClickListener((View view) -> {
            finish();
        });

        toolbarPlaySong.setTitleTextColor(Color.WHITE);

        addFragmentSong = new ViewPagerPlaySong(this);

//        add 2 fragment in viewPaper
        addFragmentSong.addFragment(fragment_disk_song);
        addFragmentSong.addFragment(fragment_play_list_songs);
        viewPagerPlaySong.setAdapter(addFragmentSong);
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();

//        Delete old data to avoid overlapping
        songArrayListSong.clear();

        if (intent != null) {
            if(intent.hasExtra("getSong")) {
                Song song = intent.getParcelableExtra("getSong");
                songArrayListSong.add(song);

            } else if (intent.hasExtra("getSongLike")) {
                MostLikedSongs mostLikedSongs = (MostLikedSongs) intent.getSerializableExtra("getSongLike");

            } else if(intent.hasExtra("getAllSong")){
                ArrayList<Song> songsArray = intent.getParcelableArrayListExtra("getAllSong");
                for(int i = 0; i < songsArray.size(); i++) {
                    Log.d("ass", songsArray.get(i).getNameSong() );
                }
                songArrayListSong = songsArray;
            } else  {
                return;
            }
        } else { return; }
    }
}