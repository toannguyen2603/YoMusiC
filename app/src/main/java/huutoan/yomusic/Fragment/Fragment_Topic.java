package huutoan.yomusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import huutoan.yomusic.R;

public class Fragment_Topic extends Fragment {
    View view;
    public static RecyclerView recyclerViewTopic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic, container,false);

        recyclerViewTopic = view.findViewById(R.id.recyclerViewTopic);

        return  view;
    }
}
