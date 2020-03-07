package com.haemin.imagemathtutor.NoticeEditMVP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Data.ServerFile;
import com.haemin.imagemathtutor.R;
import com.haemin.imagemathtutor.Utils.ConfirmStarter;
import com.haemin.imagemathtutor.View.UI.FileButton;

import java.util.ArrayList;

public class NoticeEditActivity extends AppCompatActivity implements NoticeEditContract.NoticeEditView {


    NoticeEditContract.NoticeEditPresenter noticeEditPresenter;
    int lectureSeq;
    String lectureName;

    private final int READ_REQUEST_CODE = 123;

    @BindView(R.id.edit_notice_title)
    EditText editNoticeTitle;
    @BindView(R.id.edit_notice_text)
    EditText editNoticeText;
    @BindView(R.id.text_lecture_name)
    TextView textLectureName;
    @BindView(R.id.group_file)
    LinearLayout holderFileGroup;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.btn_add_file)
    ImageButton btnAddFile;
    @BindView(R.id.btn_notice_upload)
    Button btnUploadNotice;

    public static void start(Context context, int lectureSeq, String lectureName) {
        Intent starter = new Intent(context, NoticeEditActivity.class);
        starter.putExtra("lectureSeq", lectureSeq);
        starter.putExtra("lectureName", lectureName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_edit);
        Intent fromOutside = getIntent();
        ConfirmStarter.checkIntent(this, fromOutside);
        ButterKnife.bind(this);

        noticeEditPresenter = new NoticeEditPresenter(this);
        lectureSeq = fromOutside.getIntExtra("lectureSeq", 0);
        lectureName = fromOutside.getStringExtra("lectureName");

        initView();
    }

    private void initView() {
        textLectureName.setText(lectureName);
        btnBack.setOnClickListener(v -> finish());
        btnAddFile.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            startActivityForResult(i, READ_REQUEST_CODE);
        });
        btnUploadNotice.setOnClickListener(v -> {
            noticeEditPresenter.uploadNotice(lectureSeq, editNoticeTitle.getText().toString(), editNoticeText.getText().toString());
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("NOTICE EDIT ACTIVITY", "Uri: " + uri.toString());

                noticeEditPresenter.addFile(uri, ServerFile.FILE_TYPE_NORMAL);
            }
        }
    }

    @Override
    public void refreshFileList(ArrayList<ServerFile> files) {
        holderFileGroup.removeAllViews();
        for (ServerFile file : files) {

            FileButton button = new FileButton(this);
            button.setFile(file);
            button.setOnDeleteClickListener(file1 -> {
                noticeEditPresenter.deleteFile(file.getFileSeq());
            });
            button.setDeleteAble(true);
            holderFileGroup.addView(button);
        }
    }


    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showError(String errorMsg) {

        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();

    }
}
