package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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
    ImageButton imgPlay, imgShuffle, imgNext, imgPrevious, imgRepeat;
    ViewPager2 viewPagerPlaySong;

    Fragment_Disk_Song fragment_disk_song;
    Fragment_Play_List_Songs fragment_play_list_songs;

    public static ArrayList<Song> songArrayListSong = new ArrayList<>();
    public static ViewPagerPlaySong addFragmentSong;

    Song nameSong;

//    play some music
    MediaPlayer mediaPlayer;

    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean nextSong = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

//        check network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        initLayout();

        fragment_play_list_songs = new Fragment_Play_List_Songs();
        fragment_disk_song = new Fragment_Disk_Song();
        getDataFromIntent();
        initDataInView();
        evenClick();
    }

    public void evenClick(){

        Handler handler = new Handler();

//        create delay when click event
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if(addFragmentSong.createFragment(0) != null) {
                    if(songArrayListSong.size() > 0) {
                        fragment_disk_song.ImageSong(songArrayListSong.get(0).getThumbnail());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 300);


        eventSeekBarWithPlaySong();

//        event for song
        evenClickPlaySong();
        evenClickRepeatSong();
        evenClickRandomSong();
        eventClickPreviousSong();
        eventClickNextSong();

    }


    private void evenClickPlaySong(){

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

    private void evenClickRepeatSong(){

        //        click repeat
        imgRepeat.setOnClickListener((View view) -> {

            if (repeat == false) {
                if (checkRandom == true) {
                    checkRandom = false;
                    imgRepeat.setImageResource(R.drawable.repeat_violet);
                    imgShuffle.setImageResource(R.drawable.shuffle);
                }
                imgRepeat.setImageResource(R.drawable.repeat_violet);
                repeat = true;
            } else {
                imgRepeat.setImageResource(R.drawable.repeat);
                repeat = false;
            }
        });
    }

    private void evenClickRandomSong(){
//        click random song
        imgShuffle.setOnClickListener((View view) -> {
            if (checkRandom == false) {
                if (repeat == true) {
                    repeat = false;
                    imgShuffle.setImageResource(R.drawable.shuffle_violet);
                    imgRepeat.setImageResource(R.drawable.repeat);
                }
                imgShuffle.setImageResource(R.drawable.shuffle_violet);
                checkRandom = true;
            } else {
                imgShuffle.setImageResource(R.drawable.shuffle);
                checkRandom = false;
            }
        });
    }

    private void eventSeekBarWithPlaySong(){
        //        seek bar time
        seekBarTimeSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void eventClickNextSong() {

        imgNext.setOnClickListener((View view) -> {

            Log.d("ArrayList", String.valueOf(songArrayListSong.size()));

            Log.d("PositionSong", String.valueOf(position));

            if (songArrayListSong.size() > 0 ) {

                Log.d("CheckMedia", String.valueOf(mediaPlayer));

                if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                    mediaPlayer.stop();

                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                if (position < (songArrayListSong.size())) {

                    imgPlay.setImageResource(R.drawable.pause);

                    position++;

                    if (repeat == true) {

                        if (position == 0) {
                            position = songArrayListSong.size();
                        }
                        position -= 1;

                    }

                    if (checkRandom == true) {
                        Random random = new Random();
                        int index = random.nextInt(songArrayListSong.size());

                        if(index == position) {
                            position = index - 1;
                        }
                        position = index;
                    }

                    if (position > (songArrayListSong.size() - 1)) {
                        position = 0;
                    }
                }

                new playMusic().execute(songArrayListSong.get(position).getLink());
                fragment_disk_song.ImageSong(songArrayListSong.get(position).getThumbnail());
                getSupportActionBar().setTitle(songArrayListSong.get(position).getNameSong());


                imgPrevious.setClickable(false);
                imgNext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPrevious.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);

            }
        });

    }


    private void eventClickPreviousSong() {

        imgPrevious.setOnClickListener((View view) -> {
            if (songArrayListSong.size() > 0 ) {

                if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                if (position < (songArrayListSong.size())) {
                    imgPlay.setImageResource(R.drawable.pause);
                    position--;
                    if (position < 0) {
                        position = songArrayListSong.size() - 1;
                    } else if (repeat == true) {
                        if (position == 0) {
                            position = songArrayListSong.size();
                        }
                        position += 1;
                    } else if (checkRandom == true) {
                        Random random = new Random();
                        int index = random.nextInt(songArrayListSong.size());

                        if(index == position) {
                            position = index - 1;
                        }
                        position = index;
                    }

                    new playMusic().execute(songArrayListSong.get(position).getLink());
                    fragment_disk_song.ImageSong(songArrayListSong.get(position).getThumbnail());
                    getSupportActionBar().setTitle(songArrayListSong.get(position).getNameSong());
                }

                imgPrevious.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPrevious.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);

            }
        });
    }


    private void initLayout() {

        toolbarPlaySong = findViewById(R.id.toolBarPlaySong);
        textTimeSong = findViewById(R.id.textViewRunTime);
        textTimeTotalSong = findViewById(R.id.textViewTimeTotal);
        seekBarTimeSong = findViewById(R.id.seekBarTime);
        imgPlay = findViewById(R.id.imageButtonPlayOfPause);
        imgNext = findViewById(R.id.imageButtonNextSong);
        imgPrevious = findViewById(R.id.imageButtonPrevious);
        imgShuffle = findViewById(R.id.imageButtonShuffle);
        imgRepeat = findViewById(R.id.imageButtonLoop);
        viewPagerPlaySong = findViewById(R.id.viewPagerDiskSong);

    }

    private void initDataInView(){

        setSupportActionBar(toolbarPlaySong);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbarPlaySong.setNavigationOnClickListener((View view) -> {
            finish();
            mediaPlayer.stop();
            songArrayListSong.clear();
        });

        toolbarPlaySong.setTitleTextColor(Color.WHITE);

        addFragmentSong = new ViewPagerPlaySong(this);

//        add 2 fragment in viewPaper
        addFragmentSong.addFragment(fragment_disk_song);
        addFragmentSong.addFragment(fragment_play_list_songs);
        viewPagerPlaySong.setAdapter(addFragmentSong);

        fragment_disk_song = (Fragment_Disk_Song) addFragmentSong.createFragment(0);

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

            } else if (intent.hasExtra("getAllSingOfSong")){

                ArrayList<Song> songsArray = intent.getParcelableArrayListExtra("getAllSingOfSong");

                songArrayListSong = songsArray;

                Log.d("GetAllPosition", String.valueOf(songsArray.size()));


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

    private void TimeSong() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textTimeTotalSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));

//        drag seekbar -> update time for seekbar
        seekBarTimeSong.setMax(mediaPlayer.getDuration());
    }


}