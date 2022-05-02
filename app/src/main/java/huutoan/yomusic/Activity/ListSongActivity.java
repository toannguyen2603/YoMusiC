package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

import huutoan.yomusic.Adapter.ListSongAdapter;
import huutoan.yomusic.Adapter.MostLikedSongAdapter;
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


    ListSongAdapter listSongAdapter;
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);

        getLayout();
        GetDataIntent();

        Log.d("Listof", playListSong.getImage());

        init();
        if(playListSong != null & !playListSong.getName().equals("")) {
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
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

    }


    public void setDataInView(String name, String image){
        collapsingToolbarLayout.setTitle(name);

        Picasso.get().load(image).into(imageViewListSong);
    }


    public void getDataInTopic(){
        songs = (ArrayList<Song>) playListSong.getSongs();

        listSongAdapter = new ListSongAdapter(ListSongActivity.this, songs);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListSongActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListSong.setLayoutManager(linearLayoutManager);

//                set adapter for recycle view
        recyclerViewListSong.setAdapter(listSongAdapter);
    }



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