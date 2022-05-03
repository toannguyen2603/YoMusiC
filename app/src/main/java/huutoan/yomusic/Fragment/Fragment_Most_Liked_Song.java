package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Adapter.MostLikedSongAdapter;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Most_Liked_Song extends Fragment {

    View view;
    RecyclerView recyclerViewLikeSong;
    MostLikedSongAdapter mostLikedSongAdapter;
    ArrayList<Song> mostLikedSongs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_most_liked_song, container, false);

        recyclerViewLikeSong = view.findViewById(R.id.recyclerViewMostLikedSong);

        GetData();
        return view;
    }

    public void GetData(){
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetDataMostLikedSongsCurrent();

        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(@NonNull Call<List<Song>> call, @NonNull Response<List<Song>> response) {

                mostLikedSongs = (ArrayList<Song>) response.body();

                mostLikedSongAdapter = new MostLikedSongAdapter(getActivity(), mostLikedSongs);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewLikeSong.setLayoutManager(linearLayoutManager);

//                set adapter for recycle view
                recyclerViewLikeSong.setAdapter(mostLikedSongAdapter);

            }

            @Override
            public void onFailure(@NonNull Call<List<Song>> call, @NonNull Throwable t) {

            }
        });
    }
}
