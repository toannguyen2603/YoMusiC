package huutoan.yomusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import huutoan.yomusic.R;

public class AllListActivity extends AppCompatActivity {

    Toolbar toolbarGetAllList;
    RecyclerView recyclerViewAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        getLayOut();
        initData();
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