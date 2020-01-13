package com.haemin.imagemathtutor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Model.Lecture;
import com.haemin.imagemathtutor.R;
import com.haemin.imagemathtutor.View.UI.ToggleConstraintLayout;

import java.util.ArrayList;

public class LectureRecyclerAdapter extends RecyclerView.Adapter<LectureRecyclerAdapter.LectureViewHolder> {

    Context context;
    ArrayList<Lecture> lectures;

    public LectureRecyclerAdapter(Context context, ArrayList<Lecture> lectures) {
        this.context = context;
        this.lectures = lectures;
    }

    @NonNull
    @Override
    public LectureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_lecture,parent,false);

        return new LectureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }

    class LectureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.toggle_whole_recycler)
        ToggleConstraintLayout toggleWhole;
        @BindView(R.id.btn_list_group)
        ToggleConstraintLayout toggleBtnList;

        @BindView(R.id.image_end_class)
        ImageView imageEndClass;

        @BindView(R.id.text_class_name)
        TextView textClassName;
        @BindView(R.id.text_class_times)
        TextView textClassTimes;
        @BindView(R.id.text_academy_name)
        TextView textAcademyName;
        @BindView(R.id.text_class_duration)
        TextView textClassDuration;
        @BindView(R.id.text_pass)
        TextView textPass;
        @BindView(R.id.text_unsubmit)
        TextView textUnSubmit;
        @BindView(R.id.text_neglect)
        TextView textNeglect;

        @BindView(R.id.btn_notice_group)
        ConstraintLayout btnNoticeGroup;

        @BindView(R.id.text_notice_preview)
        TextView notice_preview;

        @BindView(R.id.btn_assignment)
        Button btnAssignment;
        @BindView(R.id.btn_test)
        Button btnTest;

        public LectureViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
