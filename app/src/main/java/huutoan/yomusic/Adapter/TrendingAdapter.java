package huutoan.yomusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import huutoan.yomusic.Model.Song;
import huutoan.yomusic.Model.User;
import huutoan.yomusic.R;

public class TrendingAdapter extends PagerAdapter {
    Context context;
    ArrayList<User> userArrayList;

    ImageView imageViewTrending, imageTrending;
    TextView textTitle, textDescription;

    public TrendingAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @Override
    public int getCount() {
        return userArrayList.size();
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
        textTitle.setText(userArrayList.get(position).getName());
        textDescription.setText(userArrayList.get(position).getEmail());
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
