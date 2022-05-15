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


public class Fragment_Charts extends Fragment {

    View view;
    public static RecyclerView recyclerViewCharts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_charts, container, false);
        recyclerViewCharts = view.findViewById(R.id.recyclerViewCharts);
        return view;
    }
}
