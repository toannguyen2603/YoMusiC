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

import huutoan.yomusic.Activity.MainActivity;
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
    public static ViewPager viewPager;
    public static CircleIndicator circleIndicator;

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
        GetDataTrending();
        return view;

    }

    public void GetDataTrending() {

        DataService dataService = APIService.getService();
        Call<List<Trending>> callback = dataService.GetDataTrending();
        callback.enqueue(new Callback<List<Trending>>() {
            @Override
            public void onResponse(@NonNull Call<List<Trending>> call, @NonNull Response<List<Trending>> response) {

                ArrayList<Trending> trending = (ArrayList<Trending>) response.body();

                trendingAdapter = new TrendingAdapter(getActivity(), trending);

                viewPager.setAdapter(trendingAdapter);
                circleIndicator.setViewPager(Fragment_Trending_Hits.viewPager);
//                manager

                if(trending.size() > 0) {

                    handler = new Handler();
                    runnable = () -> {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(trending.size() <= 0) {
                            currentItem = 0;
                        }
                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem = 0;
                        } else {
                            viewPager.getAdapter().getCount();
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 3000);
                    };

                    handler.postDelayed(runnable, 3000);
                }
                else  {
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Trending>> call, @NonNull Throwable t) {

            }

        });
    }

}
