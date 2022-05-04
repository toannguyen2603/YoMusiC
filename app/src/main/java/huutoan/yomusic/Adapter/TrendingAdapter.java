package huutoan.yomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import huutoan.yomusic.Activity.ListSongActivity;
import huutoan.yomusic.Activity.PlaySongActivity;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.Model.Trending;
import huutoan.yomusic.Model.User;
import huutoan.yomusic.R;

public class TrendingAdapter extends PagerAdapter {
    Context context;
    ArrayList<Trending> trendingArrayList;

    ImageView imageViewTrending, imageTrending;
    TextView textTitle, textDescription;



    public TrendingAdapter(Context context, ArrayList<Trending> trendingsArrayList) {
        this.context = context;
        this.trendingArrayList = trendingsArrayList;
    }

    @Override
    public int getCount() {
        return trendingArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.part_trending, null);

        imageViewTrending = view.findViewById(R.id.imageViewTrending);
        imageTrending = view.findViewById(R.id.imageTrending);
        textTitle = view.findViewById(R.id.textTitle);
        textDescription = view.findViewById(R.id.textDescription);

//       get data from list
        textDescription.setText(trendingArrayList.get(position).getDescription());
        Picasso.get().load(trendingArrayList.get(position).getImage()).into(imageViewTrending);
        Picasso.get().load(trendingArrayList.get(position).getImage()).into(imageTrending);
        container.addView(view);

        view.setOnClickListener((View v) -> {
            Intent intent = new Intent(context, PlaySongActivity.class);
            intent.putExtra("songTrending", (Serializable) trendingArrayList.get(position).getSongId());
            context.startActivity(intent);
        }) ;

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
