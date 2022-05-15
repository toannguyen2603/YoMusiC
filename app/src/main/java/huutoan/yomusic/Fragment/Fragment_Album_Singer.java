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

import huutoan.yomusic.Activity.MainActivity;
import huutoan.yomusic.Adapter.SingerAdapter;
import huutoan.yomusic.Model.Singer;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Album_Singer extends Fragment {
    View view;
    SingerAdapter singerAdapter;
    RecyclerView recyclerViewSinger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerViewSinger = view.findViewById(R.id.recyclerViewSinger);
        GetDataSinger();
        return  view;
    }


    public void GetDataSinger(){
        DataService dataservice = APIService.getService();
        Call<List<Singer>> callback = dataservice.GetDataSinger();
        callback.enqueue(new Callback<List<Singer>>() {
            @Override
            public void onResponse(Call<List<Singer>> call, Response<List<Singer>> response) {

                ArrayList<Singer> singers = (ArrayList<Singer>) response.body();

                singerAdapter = new SingerAdapter(getActivity(), singers);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewSinger.setLayoutManager(linearLayoutManager);

//                set adapter for recycle view
                recyclerViewSinger.setAdapter(singerAdapter);
            }

            @Override
            public void onFailure(Call<List<Singer>> call, Throwable t) {

            }
        });
    }


}
