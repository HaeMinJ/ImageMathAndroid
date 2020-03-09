package com.haemin.imagemathtutor.AssignmentFragmentMVP;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Data.Assignment;
import com.haemin.imagemathtutor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentFragment extends Fragment implements AssignmentContract.AssignmentView {
    @BindView(R.id.recycler_assignment)
    RecyclerView recyclerAssignment;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.text_lecture_day)
    TextView textLectureDay;
    AssignmentContract.AssignmentPresenter presenter;
    ArrayList<AssignmentRecyclerAdapter.AssignmentDateHolder> dateHolders;
    ArrayList<Assignment> assignments;

    AssignmentRecyclerAdapter assignmentRecyclerAdapter;

    public AssignmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assignment, container, false);
        ButterKnife.bind(this,v);
        dateHolders = new ArrayList<>();
        presenter = new AssignmentPresenter(this);
        assignments = new ArrayList<>();
        assignmentRecyclerAdapter = new AssignmentRecyclerAdapter(getContext(), dateHolders);
        presenter.requestData();
        refreshLayout.setOnRefreshListener(() -> {
            presenter.requestData();
            refreshLayout.setRefreshing(false);
        });
        recyclerAssignment.setAdapter(assignmentRecyclerAdapter);
        recyclerAssignment.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        return v;
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateRecycler(ArrayList<AssignmentRecyclerAdapter.AssignmentDateHolder> dateHolders) {
        this.dateHolders.clear();
        this.dateHolders.addAll(dateHolders);
        assignmentRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateView(ArrayList<Assignment> assignments) {

    }
}
