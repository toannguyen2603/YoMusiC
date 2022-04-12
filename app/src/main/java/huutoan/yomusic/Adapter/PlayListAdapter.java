package huutoan.yomusic.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import huutoan.yomusic.Model.Post;

public class PlayListAdapter extends ArrayAdapter<Post> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {

    }
}
