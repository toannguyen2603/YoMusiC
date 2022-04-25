package huutoan.yomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

//       public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
//        super(context, resource, objects);
//    }
//
////    save data first mapping
//    static class ViewHolder {
//        TextView textPlayList;
//        ImageView  imagePlayList;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        ViewHolder viewHolder = null;
//
////        check when running the application, the data is already in the play list or not
//        if(convertView == null){
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView =  inflater.inflate(R.layout.part_playlist, null);
//            viewHolder  = new ViewHolder();
//            viewHolder.textPlayList = convertView.findViewById(R.id.textViewPlayList);
//            viewHolder.imagePlayList = convertView.findViewById(R.id.imageViewPlayList);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        PlayList playList = getItem(position);
//        Picasso.get().load(playList.getThumbnail()).into(viewHolder.imagePlayList);
//        viewHolder.textPlayList.setText(playList.getName());
//        return convertView;
//    }


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
        holder.textPlayList.setText(playList.getName());
        Picasso.get().load(playList.getThumbnail()).into(holder.imagePlayList);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
//                intent.putExtra("intentphobien", arrayListphobien.get(position));
//                context.startActivity(intent);
//            }
//        });
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
