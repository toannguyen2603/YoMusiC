package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import huutoan.yomusic.Model.PlayList;
import huutoan.yomusic.R;

public class ListSongActivity extends AppCompatActivity {

    PlayList playList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        GetDataIntent();
    }

    private void GetDataIntent(){
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("playlist")) {
                playList = (PlayList) intent.getSerializableExtra("playlist");
                Toast.makeText(this,playList.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}