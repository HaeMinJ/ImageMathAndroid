package com.haemin.imagemathtutor.View.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Data.ServerFile;
import com.haemin.imagemathtutor.R;

public class FileButton extends RelativeLayout {

    Context context;
    @BindView(R.id.text_file_name)
    TextView fileName;
    @BindView(R.id.btn_delete_file)
    ImageView btnDeleteFile;

    boolean isDeleteAble = false;
    OnDeleteClickListener onDeleteClickListener;

    ServerFile file;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
        if(onDeleteClickListener != null)
            btnDeleteFile.setOnClickListener(v1 -> onDeleteClickListener.onDeleteClick(this, file));
    }

    public void setFile(ServerFile file) {
        this.file = file;
        fileName.setText(file.getFileName());
    }

    public void setDeleteAble(boolean deleteAble) {
        isDeleteAble = deleteAble;
        if(isDeleteAble){
            btnDeleteFile.setVisibility(VISIBLE);
        }else{
            btnDeleteFile.setVisibility(GONE);
        }
    }

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

    private void getAttrs(AttributeSet attrs) {
        setDeleteAble(attrs.getAttributeBooleanValue(R.styleable.FileButton_deleteable,false));
    }

    private void init() {
        View v = LayoutInflater.from(context).inflate(R.layout.btn_file,this,false);
        addView(v);
        ButterKnife.bind(this,v);
    }
    public interface OnDeleteClickListener{
        void onDeleteClick(FileButton fileButton, ServerFile file);
    }

}
