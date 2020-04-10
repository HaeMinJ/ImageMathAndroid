package com.haemin.imagemathtutor.View.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Data.ServerFile;
import com.haemin.imagemathtutor.R;


public class FileButton extends RelativeLayout implements View.OnClickListener {

    Context context;
    @BindView(R.id.text_file_name)
    TextView fileName;
    @BindView(R.id.btn_delete_file)
    ImageView btnDeleteFile;
    @BindView(R.id.recycler_file)
    RelativeLayout fileView;
    View currentView;

    boolean isDeleteAble = false;
    OnDeleteClickListener onDeleteClickListener;

    ServerFile file;


    public FileButton(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FileButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
        getAttrs(attrs);
    }

    public FileButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
        getAttrs(attrs);
    }

    public FileButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
        getAttrs(attrs);
    }

    @Override
    public void onClick(View v) {
        if (file == null) {
            return;
        }
        if (file.getFileUrl() == null || file.getFileUrl().equals("")) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(file.getFileUrl()));
        context.startActivity(i);
        Toast.makeText(context, "파일 다운로드를 위해 브라우저로 이동합니다.\n크롬 브라우저를 선택해주세요.", Toast.LENGTH_SHORT).show();

    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
        setDeleteAble(true);
    }

    public void setFile(ServerFile file) {
        this.file = file;
        fileName.setText(file.getFileName());
    }

    public void setDeleteAble(boolean deleteAble) {
        isDeleteAble = deleteAble;
        if (isDeleteAble) {
            fileView.setOnClickListener(null);
            fileView.setClickable(false);
            btnDeleteFile.setClickable(true);
            btnDeleteFile.setVisibility(VISIBLE);
            if (onDeleteClickListener != null)
                btnDeleteFile.setOnClickListener(v1 -> onDeleteClickListener.onDeleteClick(this, file));
        } else {
            btnDeleteFile.setVisibility(GONE);
        }
    }

    private void getAttrs(AttributeSet attrs) {
        setDeleteAble(attrs.getAttributeBooleanValue(R.styleable.FileButton_deleteable, false));
    }

    private void init() {
        currentView = LayoutInflater.from(context).inflate(R.layout.btn_file, this, false);
        addView(currentView);
        ButterKnife.bind(this, currentView);
        if (!isDeleteAble) fileView.setOnClickListener(this);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(FileButton fileButton, ServerFile file);
    }

}
