package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Adapter.PlayListAdapter;
import huutoan.yomusic.Model.PlayList;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {

    View view;
    ListView vPlayList;
    TextView textTitlePlayList, textMorePlayList;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> playLists;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_playlist, container, false);
//       get layout
       vPlayList = view.findViewById(R.id.listItemPlayList);
       textTitlePlayList = view.findViewById(R.id.textTitlePlayList);
       textMorePlayList = view.findViewById(R.id.textViewMorePlayList);

       GetData();
        return view;
    }

    private void GetData(){
//        get service
        DataService dataService = APIService.getService();

//        get list data through the service
        Call<List<PlayList>> callback = dataService.GetDataPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {

//            get data response
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                playLists = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1,playLists);
                vPlayList.setAdapter(playListAdapter);
                setListViewHeightBasedOnItems(vPlayList);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

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
