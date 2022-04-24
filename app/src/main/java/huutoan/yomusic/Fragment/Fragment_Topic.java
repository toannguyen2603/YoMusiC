package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Model.Topic;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Topic extends Fragment {
    View view;
    ArrayList<Topic> topics;
    HorizontalScrollView horizontalScrollView;
    TextView seeMore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic, container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        seeMore = view.findViewById(R.id.seeMore);
        return  view;
    }

    private void getData(){
        DataService dataService = APIService.getService();
        Call<List<Topic>> callback = dataService.GetDataTopic();

        callback.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                topics = (ArrayList<Topic>) response.body();
                Log.i("BBB", topics.get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {

            }
        });
    }

}
