package huutoan.yomusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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

import huutoan.yomusic.Activity.AllListActivity;
import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.Model.Topic;
import huutoan.yomusic.R;


public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    View view;
    Context context;
    ArrayList<Topic> topicArrayList;
    Topic topic;

    public TopicAdapter(Context context, ArrayList<Topic> topicArrayList){
        this.context = context;
        this.topicArrayList = topicArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.part_topic,parent, false);
        return new TopicAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        topic = topicArrayList.get(position);
        if (topic == null) {
            return;
        }
        holder.textViewTopic.setText(topic.getName());
        Picasso.get().load(topic.getImage()).into(holder.imageViewTopic);

        view.setOnClickListener((View view) -> {

            Intent intent = new Intent(context, AllListActivity.class);
            intent.putExtra("AllCategory", topicArrayList);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return topicArrayList != null ? topicArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTopic;
        ImageView imageViewTopic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTopic = itemView.findViewById(R.id.textViewTopic);
            imageViewTopic = itemView.findViewById(R.id.imageViewTopic);
        }
    }
}
