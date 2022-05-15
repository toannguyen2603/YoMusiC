package huutoan.yomusic.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Activity.MainActivity;
import huutoan.yomusic.Adapter.SearchSongsAdapter;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Search extends Fragment {
    View view;
    Toolbar toolbarSearch;
    RecyclerView recyclerViewSearchSong;
    TextView noData;
    SearchSongsAdapter searchSongsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        toolbarSearch = view.findViewById(R.id.toolBarSearchSongs);
        recyclerViewSearchSong = view.findViewById(R.id.ListSearchSong);
        noData = view.findViewById(R.id.noDataSearch);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarSearch);
        toolbarSearch.setTitle("");
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_songs, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchSongs(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchSongs(String query){
        DataService dataService = APIService.getService();

        ProgressDialog dialog = ProgressDialog.show(
                getActivity(),"","Please wait...",true
        );

        Call<List<Song>> callback = dataService.GetDataSearch(query);
        Log.d("QueryACC", String.valueOf(callback));
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {

                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();

                if (songArrayList.size() > 0) {

                    dialog.dismiss();

                    searchSongsAdapter = new SearchSongsAdapter(getActivity(), songArrayList);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearchSong.setLayoutManager(linearLayoutManager);
//                set adapter for recycle view
                    recyclerViewSearchSong.setAdapter(searchSongsAdapter);

                    noData.setVisibility(View.GONE);
                    recyclerViewSearchSong.setVisibility(View.VISIBLE);

                } else {

                    dialog.dismiss();

                    recyclerViewSearchSong.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });

    }
}
