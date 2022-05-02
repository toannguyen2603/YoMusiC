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
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import huutoan.yomusic.Activity.PlaySongActivity;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.R;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {

    View view;
    Context context;
    ArrayList<Song> songArrayList;
    Song song;

    public ListSongAdapter(Context context, ArrayList<Song> songArrayList){
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.part_list_song,parent, false);
        return new ListSongAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        song = songArrayList.get(position);
        if ( song == null) {
            return;
        }
        holder.textArticleListSong.setText(song.getArtists());
        holder.textViewTitleListSong.setText(song.getNameSong());
        Picasso.get().load(song.getThumbnail()).into(holder.imageViewListSong);
    }

    @Override
    public int getItemCount() {
        return songArrayList != null ? songArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewListSong;
        TextView textViewTitleListSong, textArticleListSong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewListSong = itemView.findViewById(R.id.imageListSong);
            textViewTitleListSong = itemView.findViewById(R.id.textViewTitleListSong);
            textArticleListSong = itemView.findViewById(R.id.textViewArticleListSong);

            itemView.setOnClickListener((View view) -> {
                Intent intent = new Intent(context, PlaySongActivity.class);
                intent.putExtra("getSong", (Parcelable) songArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
