package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Adapter.TrendingAdapter;
import huutoan.yomusic.Model.Trending;
import huutoan.yomusic.R;
import huutoan.yomusic.Service.APIService;
import huutoan.yomusic.Service.DataService;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Trending_Hits extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    TrendingAdapter trendingAdapter;
    int currentItem;

//    Animation auto switch
    Runnable runnable;
    Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trending_hits, container,false);
        GetData();
        viewPager = view.findViewById(R.id.trendingViewFlipper);
        circleIndicator = view.findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
        return view;
    }

//    get data from server
    private void GetData(){
        DataService dataService = APIService.getService();
        Call<List<Trending>> callback = dataService.GetDataTrending();
        callback.enqueue(new Callback<List<Trending>>() {
            @Override
            public void onResponse(Call<List<Trending>> call, Response<List<Trending>> response) {
                ArrayList<Trending> trendings = (ArrayList<Trending>) response.body();
                trendingAdapter = new TrendingAdapter(getActivity(), trendings);
                viewPager.setAdapter(trendingAdapter);
//                manager
                handler = new Handler();

//                implement work when handler call
                runnable = new Runnable() {
                    @Override

//                    run automation switch view page
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 3000);
                    }
                };
                handler.postDelayed(runnable, 3000);
            }


            @Override
            public void onFailure(Call<List<Trending>> call, Throwable t) {

            }

        });
    }
}