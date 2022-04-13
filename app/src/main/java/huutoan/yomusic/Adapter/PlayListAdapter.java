package huutoan.yomusic.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import java.util.List;
import huutoan.yomusic.Model.PlayList;
import huutoan.yomusic.R;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

//    save data first mapping
    static class ViewHolder {
        TextView textPlayList;
        ImageView  imagePlayList;
        ImageButton imageBackground;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;

//        check when running the application, the data is already in the play list or not
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView =  inflater.inflate(R.layout.part_playlist, null);
            viewHolder  = new ViewHolder();
            viewHolder.textPlayList = convertView.findViewById(R.id.textViewPlayList);
            viewHolder.imagePlayList = convertView.findViewById(R.id.imageViewPlayList);
            viewHolder.imageBackground = convertView.findViewById(R.id.btnPlayList);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PlayList playList = getItem(position);
        Log.d("Play", playList.getName());
        Picasso.get().load(playList.getImage()).into(viewHolder.imageBackground);
        Picasso.get().load(playList.getThumbnail()).into(viewHolder.imagePlayList);
        viewHolder.textPlayList.setText(playList.getName());
        return convertView;
    }
}
