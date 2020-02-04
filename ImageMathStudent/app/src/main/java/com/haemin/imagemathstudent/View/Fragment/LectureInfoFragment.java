package com.haemin.imagemathstudent.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathstudent.Adapter.LectureRecyclerAdapter;
import com.haemin.imagemathstudent.Data.Lecture;
import com.haemin.imagemathstudent.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LectureInfoFragment extends Fragment {


    @BindView(R.id.btn_add_lecture)
    Button btnAddLecture;
    @BindView(R.id.toggle_except_expire)
    ToggleButton toggleExceptExpire;
    @BindView(R.id.recycler_lecture)
    RecyclerView recyclerLecture;

    ArrayList<Lecture> lectures;
    LectureRecyclerAdapter lectureRecyclerAdapter;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    public LectureInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lecture_info, container, false);
        ButterKnife.bind(this, v);
        lectures = new ArrayList<>();
        lectureRecyclerAdapter = new LectureRecyclerAdapter(getContext(),lectures);

        recyclerLecture.setAdapter(lectureRecyclerAdapter);
        recyclerLecture.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        refreshLayout.setOnRefreshListener(() -> {
            refresh();
            refreshLayout.setRefreshing(false);
        });

        btnAddLecture.setOnClickListener(v1 -> {
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();



    }

    private void refresh() {
        lectures.add(new Lecture());
        lectureRecyclerAdapter.notifyDataSetChanged();
    }


}
