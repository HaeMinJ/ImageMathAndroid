package com.haemin.imagemathstudent.NoticeActivityMVP;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathstudent.Data.Notice;
import com.haemin.imagemathstudent.Data.ServerFile;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.View.UI.FileButton;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {
    Context context;
    ArrayList<Notice> notices;

    public NoticeAdapter(Context context, ArrayList<Notice> notices) {
        this.context = context;
        this.notices = notices;
    }

    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_notice,parent,false);

        return new NoticeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeHolder holder, int position) {
        Notice notice = notices.get(position);
        holder.textNoticeNumber.setText("NO."+notice.getNoticeSeq());
        holder.textNoticeTime.setText(DateUtils.getRelativeTimeSpanString(notice.getPostTime())+ " 게시");
        holder.textNoticeText.setText(notice.getContents());
        holder.textNoticeTitle.setText(notice.getTitle());
        ArrayList<ServerFile> files = notice.getFiles();
        if(files != null && files.size() != 0){
            for (ServerFile file :
                    files) {
                FileButton fileButton = new FileButton(context);
                fileButton.setFile(file);
                fileButton.setDeleteAble(false);
                holder.groupFile.addView(fileButton);
            }
        }

    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    class NoticeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_notice_time)
        TextView textNoticeTime;
        @BindView(R.id.text_notice_number)
        TextView textNoticeNumber;
        @BindView(R.id.text_notice_text)
        TextView textNoticeText;
        @BindView(R.id.text_notice_title)
        TextView textNoticeTitle;
        @BindView(R.id.group_file)
        LinearLayout groupFile;

        public NoticeHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
