package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import huutoan.yomusic.Model.PlayListSong;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;


public class ListSongActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewListSong;
    FloatingActionButton floatingActionButton;
    ImageView imageViewListSong;
    PlayListSong playListSong;

    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        getLayout();
        GetDataIntent();

        Log.d("AAA", playListSong.getName());
        init();
        if(playListSong != null) {
            Log.d("Run", "Is running");
            setDataInView(playListSong.getName(), playListSong.getImage());
            getDataInTopic();
        }
    }
    private void getLayout(){
        toolbar = findViewById(R.id.ToolbarList);
        coordinatorLayout = findViewById(R.id.coordinatorLayoutListSong);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        recyclerViewListSong = findViewById(R.id.recycleViewListSong);
        floatingActionButton = findViewById(R.id.floatingActionListSong);
        imageViewListSong = findViewById(R.id.imageViewListSong);

    }

    private void init(){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener((View view) -> {
            finish();
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.bar_trans));

    }


    public void setDataInView(String name, String image){

        collapsingToolbarLayout.setTitle(name);
        try {

            URL url = new URL(image);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

//            set background collapsing
            collapsingToolbarLayout.setBackground(bitmapDrawable);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.get().load(image).into(imageViewListSong);
    }


    public void getDataInTopic(){
        songs = (ArrayList<Song>) playListSong.getSongs();
        Log.d("BBB", songs.get(0).getArtists());
    };



    private void GetDataIntent(){
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("playlist")) {
                playListSong = (PlayListSong) intent.getSerializableExtra("playlist");
                Toast.makeText(this,playListSong.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}