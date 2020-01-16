package com.haemin.imagemathtutor.NoticeMVP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Data.Notice;
import com.haemin.imagemathtutor.R;

import java.util.ArrayList;

public class NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.NoticeViewHolder>
{
    NoticeContract.NoticePresenter noticePresenter;
    Context context;
    ArrayList<Notice> notices;

    public NoticeRecyclerAdapter(NoticeContract.NoticePresenter noticePresenter, Context context) {
        this.noticePresenter = noticePresenter;
        this.context = context;
        this.notices = noticePresenter.getData();
    }
    public void notifyData(ArrayList<Notice> notices){
        this.notices = notices;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.recycler_notice,parent,false);
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.btn_delete)
        ImageButton btnDelete;
        @BindView(R.id.group_file)
        LinearLayout groupFile;
        @BindView(R.id.text_notice_time)
        TextView textNoticeTime;
        @BindView(R.id.text_notice_number)
        TextView textNoticeNumber;
        @BindView(R.id.text_notice_title)
        TextView textNoticeTitle;
        @BindView(R.id.text_notice_text)
        TextView textNoticeText;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
