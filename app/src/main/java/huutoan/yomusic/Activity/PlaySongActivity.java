package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

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

import huutoan.yomusic.Model.MostLikedSongs;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;

public class PlaySongActivity extends AppCompatActivity {

    Toolbar toolbarPlaySong;
    TextView textTimeSong, textTimeTotalSong;
    SeekBar seekBarTimeSong;
    ImageButton imgPlay, imgShuffle, imgNext, imgPrevious;
    ViewPager viewPagerPlaySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        initLayout();

        Intent intent = getIntent();
        if(intent.hasExtra("getSong")) {
            Song song = intent.getParcelableExtra("getSong");
            Toast.makeText(this, song.getNameSong(), Toast.LENGTH_SHORT).show();
        } else if (intent.hasExtra("getSongLike")) {
            MostLikedSongs mostLikedSongs = (MostLikedSongs) intent.getSerializableExtra("getSongLike");
            Toast.makeText(this, mostLikedSongs.getNameSong(), Toast.LENGTH_SHORT).show();
        } else if(intent.hasExtra("getAllSong")){
            ArrayList<Song> songArrayList = intent.getParcelableArrayListExtra("getAllSong");
            for(int i = 0; i < songArrayList.size(); i++) {
                Log.i("sss", songArrayList.get(i).getNameSong());
            }
        } else  {
            return;
        }
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

    }
}