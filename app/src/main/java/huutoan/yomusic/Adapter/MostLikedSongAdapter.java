package huutoan.yomusic.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huutoan.yomusic.Model.MostLikedSongs;
import huutoan.yomusic.R;

public class MostLikedSongAdapter extends RecyclerView.Adapter<MostLikedSongAdapter.ViewHolder> {

    View view;
    Context context;
    ArrayList<MostLikedSongs> mostLikedSongsArrayList;
    MostLikedSongs mostLikedSongs;



    public MostLikedSongAdapter(Context context, ArrayList<MostLikedSongs> mostLikedSongsArrayList){
        this.context = context;
        this.mostLikedSongsArrayList = mostLikedSongsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.part_most_liked_song,parent, false);
        return new MostLikedSongAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mostLikedSongs = mostLikedSongsArrayList.get(position);
        if ( mostLikedSongs == null) {
            return;
        }
        holder.textArticle.setText(mostLikedSongs.getArtists());
        holder.textViewTitleMostLikedSong.setText(mostLikedSongs.getNameSong());
        Picasso.get().load(mostLikedSongs.getThumbnail()).into(holder.imageViewMostLikedSong);

    }

    @Override
    public int getItemCount() {
        return mostLikedSongsArrayList != null ? mostLikedSongsArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMostLikedSong;
        TextView textViewTitleMostLikedSong, textArticle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewMostLikedSong = itemView.findViewById(R.id.imageMostLikedSong);
            textViewTitleMostLikedSong = itemView.findViewById(R.id.textViewTitleMostLiked);
            textArticle = itemView.findViewById(R.id.textViewArticle);
        }
    }
}
