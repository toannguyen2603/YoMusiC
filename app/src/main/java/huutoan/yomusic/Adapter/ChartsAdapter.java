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

import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.R;

public class ChartsAdapter extends RecyclerView.Adapter<ChartsAdapter.ViewHolder> {

    Context context;
    ArrayList<Charts> chartsArrayList;
    View view;
    Charts charts;

    public ChartsAdapter(Context context, ArrayList<Charts> chartsArrayList){
        this.context = context;
        this.chartsArrayList = chartsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.part_charts,parent, false);
        return new ChartsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        charts = chartsArrayList.get(position);
        holder.textViewCharts.setText(charts.getName());
        Picasso.get().load(charts.getThumbnail()).into(holder.imageViewCharts);
    }

    @Override
    public int getItemCount() {
        return chartsArrayList != null ? chartsArrayList.size() : 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCharts;
        ImageView imageViewCharts;
        public ViewHolder(@NonNull View view) {
            super(view);
            textViewCharts = view.findViewById(R.id.textViewCharts);
            imageViewCharts = view.findViewById(R.id.imageViewCharts);
        }
    }
}
