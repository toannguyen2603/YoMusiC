package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import huutoan.yomusic.Model.MostLikedSongs;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;

public class PlaySongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        Intent intent = getIntent();
        if(intent.hasExtra("getSong")) {
            Song song = intent.getParcelableExtra("getSong");
            Toast.makeText(this, song.getNameSong(), Toast.LENGTH_SHORT).show();
        } else if (intent.hasExtra("getSongLike")) {
            MostLikedSongs mostLikedSongs = (MostLikedSongs) intent.getSerializableExtra("getSongLike");
            Toast.makeText(this, mostLikedSongs.getNameSong(), Toast.LENGTH_SHORT).show();
        } else {
            return;
        }
    }
}