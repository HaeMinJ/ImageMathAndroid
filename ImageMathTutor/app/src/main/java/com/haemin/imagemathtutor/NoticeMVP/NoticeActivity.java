package com.haemin.imagemathtutor.NoticeMVP;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Data.Notice;
import com.haemin.imagemathtutor.R;
import com.haemin.imagemathtutor.Utils.ConfirmStarter;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity implements NoticeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_notification)
    ImageButton btnNotification;
    @BindView(R.id.btn_setting)
    ImageButton btnSetting;
    @BindView(R.id.text_lecture_name)
    TextView textLectureName;
    @BindView(R.id.btn_add_notice)
    Button btnAddNotice;
    @BindView(R.id.notice_recycler)
    RecyclerView noticeRecycler;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    NoticeRecyclerAdapter recyclerAdapter;
    NoticeContract.Presenter presenter;

    int lectureSeq;
    String lectureName;

    public static void start(Context context, int lectureSeq, String lectureName) {
        Intent starter = new Intent(context, NoticeActivity.class);
        starter.putExtra("lectureSeq",lectureSeq);
        starter.putExtra("lectureName",lectureName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Intent fromOutside= getIntent();
        ConfirmStarter.checkIntent(this, fromOutside);

        presenter = new NoticePresenter(this);
        recyclerAdapter = new NoticeRecyclerAdapter(presenter,this);
        noticeRecycler.setAdapter(recyclerAdapter);
        noticeRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        lectureSeq = fromOutside.getIntExtra("lectureSeq",lectureSeq);
        lectureName = fromOutside.getStringExtra("lectureName");

        presenter.updateData(lectureSeq);
        textLectureName.setText(lectureName);

        bindListeners();
    }

    private void bindListeners() {
        refreshLayout.setOnRefreshListener(() -> {
            presenter.updateData(lectureSeq);
            refreshLayout.setRefreshing(false);
        });
        btnAddNotice.setOnClickListener(v -> {

        });
    }

    @Override
    public void refreshView(ArrayList<Notice> notices) {
        recyclerAdapter.notifyData(notices);
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
