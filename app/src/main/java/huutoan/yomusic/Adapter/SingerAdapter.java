package huutoan.yomusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huutoan.yomusic.Model.Singer;
import huutoan.yomusic.R;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {
    View view;
    Context context;
    ArrayList<Singer> singerArrayList;

    public SingerAdapter(Context context, ArrayList<Singer> singerArrayList){
        this.context = context;
        this.singerArrayList = singerArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.part_album,parent, false);
        return new SingerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Singer singer = singerArrayList.get(position);
        if (singer == null) {
            return;
        }
        holder.textViewSinger.setText(singer.getName());
        Picasso.get().load(singer.getThumbnail()).into(holder.imageViewSinger);
    }

    @Override
    public int getItemCount() {
        return singerArrayList != null ? singerArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSinger;
        ImageView imageViewSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSinger = itemView.findViewById(R.id.textViewAlbumSinger);
            imageViewSinger = itemView.findViewById(R.id.profileImageSinger);
        }
    }
}
