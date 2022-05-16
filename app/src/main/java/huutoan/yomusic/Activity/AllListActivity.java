package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import huutoan.yomusic.Adapter.AllListAdapter;
import huutoan.yomusic.Adapter.PlayListAdapter;
import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.Model.Topic;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllListActivity extends AppCompatActivity {

    Toolbar toolbarGetAllList;
    RecyclerView recyclerViewAllList;
    AllListAdapter allListAdapter;
    Topic topic;
    public static ArrayList<Charts> chartsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        getLayOut();
        initData();
        getDataIntent();

        allListAdapter = new AllListAdapter(AllListActivity.this, chartsArrayList);
        recyclerViewAllList.setLayoutManager(new GridLayoutManager(AllListActivity.this,2));
        recyclerViewAllList.setAdapter(allListAdapter);

    }

    private void getDataIntent() {
        Intent intent = new Intent();
        if(intent != null) {
            if(intent.hasExtra("AllCategory")) {
                topic = (Topic) intent.getSerializableExtra("AllCategory");
                chartsArrayList.add((Charts) topic.getCategories());
            }
        }
    }

    private void initData() {
        setSupportActionBar(toolbarGetAllList);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" List");
        toolbarGetAllList.setTitleTextColor(getResources().getColor(R.color.purple_500));
        toolbarGetAllList.setNavigationOnClickListener((View view) -> {
            finish();
        });

    }

    private void getLayOut() {
        toolbarGetAllList = findViewById(R.id.toolBarGetAllList);
        recyclerViewAllList = findViewById(R.id.recyclerViewAllList);
    }

}