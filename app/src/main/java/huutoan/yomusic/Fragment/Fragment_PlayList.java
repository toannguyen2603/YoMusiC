package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Model.Post;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_playlist, container, false);
       vPlayList = view.findViewById(R.id.listItemPlayList);
       textTitlePlayList = view.findViewById(R.id.textTitlePlayList);
       textMorePlayList = view.findViewById(R.id.textViewMorePlayList);

       GetData();
        return view;
    }

    private void GetData(){
        DataService dataService = APIService.getService();
        Call<List<Post>> callback = dataService.GetDataPost();
        callback.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                ArrayList<Post> posts = (ArrayList<Post>) response.body();
                Log.d("AAA", posts.get(0).getBody());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }
}
