package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Adapter.PlayListAdapter;
import huutoan.yomusic.Model.PlayListSong;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {

    View view;
    TextView textTitlePlayList;
    RecyclerView recyclerViewPlayList;

    PlayListAdapter playListAdapter;
    ArrayList<PlayListSong> playLists;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_playlist, container, false);
//       get layout
        recyclerViewPlayList = view.findViewById(R.id.recyclerViewPlayList);
       textTitlePlayList = view.findViewById(R.id.textTitlePlayList);

       GetData();
        return view;
    }

    private void GetData(){
//        get service
        DataService dataService = APIService.getService();

//        get list data through the service
        Call<List<PlayListSong>> callback = dataService.GetDataSongPlayList();
        callback.enqueue(new Callback<List<PlayListSong>>() {

//            get data response
            @Override
            public void onResponse(Call<List<PlayListSong>> call, Response<List<PlayListSong>> response) {
                playLists = (ArrayList<PlayListSong>) response.body();

                playListAdapter = new PlayListAdapter(getActivity() ,playLists);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewPlayList.setLayoutManager(linearLayoutManager);
                recyclerViewPlayList.setAdapter(playListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayListSong>> call, Throwable t) {

            }
        });

    }

//    Set Height of ListView at run time in android
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
