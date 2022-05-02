package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

    public static ArrayList<Song> songArrayListSong = new ArrayList<>();

    public static ViewPagerPlaySong addFragmentSong;

    Song nameSong;

//    play some music
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

//        check network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        fragment_play_list_songs = new Fragment_Play_List_Songs();
        fragment_disk_song = new Fragment_Disk_Song();

        getDataFromIntent();
        initLayout();
        evenClick();
    }

    public void evenClick(){
        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(addFragmentSong.createFragment(1) != null) {
//                    if(songArrayListSong.size() > 0) {
//                        fragment_disk_song.ImageSong(songArrayListSong.get(0).getThumbnail());
//                        handler.removeCallbacks(this);
//                    } else {
//                        handler.postDelayed(this, 300);
//                    }
//                }
//            }
//        }, 500);
        imgPlay.setOnClickListener((View view) -> {
//            check media is running
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                imgPlay.setImageResource(R.drawable.play_button);
            } else  {
                mediaPlayer.start();
                imgPlay.setImageResource(R.drawable.pause);
            }
        });
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


//        fragment_disk_song = (Fragment_Disk_Song) addFragmentSong.createFragment(1);

//        play first song
        if(songArrayListSong.size() > 0) {
            getSupportActionBar().setTitle(songArrayListSong.get(0).getNameSong());
            new playMusic().execute(songArrayListSong.get(0).getLink());
            imgPlay.setImageResource(R.drawable.pause);
        }
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

    class playMusic extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                });

                mediaPlayer.setDataSource(song);

//                used to play song music
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
//            update total time of song
            TimeSong();
        }

    }

    private void TimeSong(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textTimeTotalSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));

//        drag seekbar -> update time for seekbar
        seekBarTimeSong.setMax(mediaPlayer.getDuration());
    }


}