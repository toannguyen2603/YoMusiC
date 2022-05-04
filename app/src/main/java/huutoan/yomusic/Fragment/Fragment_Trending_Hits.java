package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        viewPager = view.findViewById(R.id.trendingViewFlipper);
        circleIndicator = view.findViewById(R.id.indicator);
        GetData();
        return view;
    }

//    get data from server
    private void GetData(){
        DataService dataService = APIService.getService();
        Call<List<Trending>> callback = dataService.GetDataTrending();
        callback.enqueue(new Callback<List<Trending>>() {
            @Override
            public void onResponse(@NonNull Call<List<Trending>> call, @NonNull Response<List<Trending>> response) {

                ArrayList<Trending> trending = (ArrayList<Trending>) response.body();

                for(int i = 0; i < trending.size(); i++){
                    Log.d("trending", trending.get(i).getSongId().getNameSong());
                }

                trendingAdapter = new TrendingAdapter(getActivity(), trending);
//
                viewPager.setAdapter(trendingAdapter);
                circleIndicator.setViewPager(viewPager);
//                manager
                handler = new Handler();
//                implement work when handler call
//                run automation switch view page
                runnable = () -> {
                    currentItem = viewPager.getCurrentItem();
                    currentItem++;
                    if(currentItem >= Objects.requireNonNull(viewPager.getAdapter()).getCount()){
                        currentItem = 0;
                    }
                    viewPager.setCurrentItem(currentItem, true);
                    handler.postDelayed(runnable, 3000);
                };
                handler.postDelayed(runnable, 3000);
            }


            @Override
            public void onFailure(@NonNull Call<List<Trending>> call, @NonNull Throwable t) {

            }

        });
    }
}
