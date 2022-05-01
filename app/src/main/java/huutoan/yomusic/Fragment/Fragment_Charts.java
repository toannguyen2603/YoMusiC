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

import huutoan.yomusic.Adapter.ChartsAdapter;
import huutoan.yomusic.Adapter.SingerAdapter;
import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.Model.Singer;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Charts extends Fragment {

    View view;
    RecyclerView recyclerViewCharts;
    ArrayList<Charts> charts;
    ChartsAdapter chartsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_charts, container, false);
        recyclerViewCharts = view.findViewById(R.id.recyclerViewCharts);
        GetData();
        return view;
    }

    public void GetData(){
        DataService dataService = APIService.getService();
        Call<List<Charts>> callback = dataService.GetDataCharts();

        callback.enqueue(new Callback<List<Charts>>() {
            @Override
            public void onResponse(Call<List<Charts>> call, Response<List<Charts>> response) {

                charts = (ArrayList<Charts>) response.body();
                chartsAdapter = new ChartsAdapter(getActivity(), charts);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewCharts.setLayoutManager(linearLayoutManager);

//                set adapter for recycle view
                recyclerViewCharts.setAdapter(chartsAdapter);
            }

            @Override
            public void onFailure(Call<List<Charts>> call, Throwable t) {

            }
        });
    }
}
