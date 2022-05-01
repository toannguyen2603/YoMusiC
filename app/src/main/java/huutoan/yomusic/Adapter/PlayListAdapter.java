package huutoan.yomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huutoan.yomusic.Activity.ListSongActivity;
import huutoan.yomusic.Model.PlayList;
import huutoan.yomusic.R;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {
    View view;
    Context context;
    ArrayList<PlayList> arrayListPlayList;

    public PlayListAdapter(Context context, ArrayList<PlayList> arrayListPlayList) {
        this.context = context;
        this.arrayListPlayList = arrayListPlayList;
    }
    
    @NonNull
    @Override
    public PlayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.part_playlist,parent, false);
        return new PlayListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListAdapter.ViewHolder holder, int position) {
        PlayList playList = arrayListPlayList.get(position);
        if (playList == null) {
            return;
        }
        holder.textPlayList.setText(playList.getName());
        Picasso.get().load(playList.getThumbnail()).into(holder.imagePlayList);

        view.setOnClickListener(view -> {

                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("playlist", arrayListPlayList.get(position));
                context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return arrayListPlayList != null ? arrayListPlayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textPlayList;
        ImageView imagePlayList;
        public ViewHolder(@NonNull View view) {
            super(view);
            imagePlayList = view.findViewById(R.id.imageViewPlayList);
            textPlayList = view.findViewById(R.id.textViewPlayList);
        }
    }
}
