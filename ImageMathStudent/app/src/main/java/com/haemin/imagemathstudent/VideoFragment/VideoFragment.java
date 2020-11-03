package com.haemin.imagemathstudent.VideoFragment;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathstudent.Data.Video;
import com.haemin.imagemathstudent.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements VideoContract.VideoView {

    VideoAdapter adapter;
    ArrayList<Video> videos;
    VideoContract.VideoPresenter presenter;

    @BindView(R.id.text_no_video)
    TextView textNoVideo;
    @BindView(R.id.recycler_video)
    RecyclerView recyclerVideo;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, v);
        videos = new ArrayList<>();
        adapter = new VideoAdapter(getContext(), videos);
        presenter = new VideoPresenter(this);

        presenter.requestData();
        swipeRefresh.setOnRefreshListener(() -> {
            presenter.requestData();
            swipeRefresh.setRefreshing(false);
        });

        recyclerVideo.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerVideo.setAdapter(adapter);

        return v;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateRecycler(ArrayList<Video> videos) {
        this.videos.clear();
        this.videos.addAll(videos);
        adapter.notifyDataSetChanged();
        if(this.videos.size() == 0){
            textNoVideo.setVisibility(View.VISIBLE);
        }else{
            textNoVideo.setVisibility(View.GONE);
        }
    }
}
