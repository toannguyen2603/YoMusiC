package huutoan.yomusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huutoan.yomusic.Activity.AllListActivity;
import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.R;

public class AllListAdapter extends RecyclerView.Adapter<AllListAdapter.ViewHolder> {

    View view;
    Context context;
    ArrayList<Charts> chartsArrayList;
    Charts charts;

    public  AllListAdapter(Context context ,ArrayList<Charts> chartsArrayList) {
        this.context = context;
        this.chartsArrayList = chartsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.part_all_list ,parent, false);
        return new AllListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        charts = chartsArrayList.get(position);
        if ( charts == null) {
            return;
        }
        holder.textTitleAllList.setText(charts.getTitle());
        holder.textArticleAllList.setText(charts.getName());
        Picasso.get().load(charts.getName()).into(holder.imageAllList);
    }

    @Override
    public int getItemCount() {
        return chartsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAllList;
        TextView textTitleAllList, textArticleAllList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAllList = itemView.findViewById(R.id.imageViewAllList);
            textTitleAllList = itemView.findViewById(R.id.textTitleAllList);
            textArticleAllList = itemView.findViewById(R.id.textArticleAllList);
        }
    }
}
