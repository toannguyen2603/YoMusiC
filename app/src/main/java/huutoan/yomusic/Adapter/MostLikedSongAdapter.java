package huutoan.yomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnAnimationEndListener;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import huutoan.yomusic.Activity.PlaySongActivity;
import huutoan.yomusic.Model.MostLikedSongs;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;

public class MostLikedSongAdapter extends RecyclerView.Adapter<MostLikedSongAdapter.ViewHolder> implements OnLikeListener,OnAnimationEndListener  {

    View view;
    Context context;
    ArrayList<Song> mostLikedSongsArrayList;
    Song mostLikedSongs;

    public MostLikedSongAdapter(Context context, ArrayList<Song> mostLikedSongsArrayList){
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

        holder.likeButton.setOnLikeListener(this);
        holder.likeButton.setOnAnimationEndListener(this);

        holder.textArticle.setText(mostLikedSongs.getArtists());
        holder.textViewTitleMostLikedSong.setText(mostLikedSongs.getNameSong());
        Picasso.get().load(mostLikedSongs.getThumbnail()).into(holder.imageViewMostLikedSong);
        holder.textLike.setText(mostLikedSongs.getLike() + " likes");
    }

    @Override
    public int getItemCount() {
        return mostLikedSongsArrayList != null ? mostLikedSongsArrayList.size() : 0;
    }

    @Override
    public void onAnimationEnd(LikeButton likeButton) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LikeButton likeButton;
        ImageView imageViewMostLikedSong;
        TextView textViewTitleMostLikedSong, textArticle, textLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewMostLikedSong = itemView.findViewById(R.id.imageMostLikedSong);
            textViewTitleMostLikedSong = itemView.findViewById(R.id.textViewTitleMostLiked);
            textArticle = itemView.findViewById(R.id.textViewArticle);
            textLike = itemView.findViewById(R.id.textLike);
            likeButton = itemView.findViewById(R.id.btn_like);

            itemView.setOnClickListener((View view) -> {
                Intent intent = new Intent(context, PlaySongActivity.class);
                intent.putExtra("getSongLike", (Parcelable) mostLikedSongsArrayList.get(getLayoutPosition()));

                context.startActivity(intent);
            });
        }
    }

    @Override
    public void liked(LikeButton likeButton) {

    }

    @Override
    public void unLiked(LikeButton likeButton) {

    }

}
