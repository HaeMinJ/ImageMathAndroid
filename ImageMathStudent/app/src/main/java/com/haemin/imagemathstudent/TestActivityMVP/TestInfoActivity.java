package com.haemin.imagemathstudent.TestActivityMVP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathstudent.Data.Lecture;
import com.haemin.imagemathstudent.Data.StudentTest;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.Utils.ConfirmStarter;

public class TestInfoActivity extends AppCompatActivity implements TestActivityContract.TestActivityView {

    String lectureSeq;
    String lectureName;
    String testSeq;
    TestActivityPresenter presenter;

    @BindView(R.id.text_test_score)
    TextView textTestScore;
    @BindView(R.id.text_test_rank)
    TextView textTestRank;
    @BindView(R.id.text_test_name)
    TextView textTestName;
    @BindView(R.id.text_lecture_name)
    TextView textLectureName;
    @BindView(R.id.text_test_notice)
    TextView textTestNotice;
    @BindView(R.id.text_upload_day)
    TextView textUploadDay;
    @BindView(R.id.text_end_day)
    TextView textEndDay;

    public static void start(Context context, Lecture lecture,String testSeq) {
        Intent starter = new Intent(context, TestInfoActivity.class);
        starter.putExtra("lectureSeq", lecture.getLectureSeq());
        starter.putExtra("lectureName",lecture.getName());
        starter.putExtra("testSeq", testSeq);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);
        presenter = new TestActivityPresenter(this);
        Intent fromOutside = getIntent();
        ConfirmStarter.checkIntent(this, fromOutside);
        lectureSeq = fromOutside.getStringExtra("lectureSeq");
        lectureName = fromOutside.getStringExtra("lectureName");
        testSeq = fromOutside.getStringExtra("testSeq");
        ButterKnife.bind(this);
        textLectureName.setText(lectureName);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(StudentTest test) {
        textLectureName.setText(test.getLecture().getName());
        textTestName.setText(test.getTitle());
        textTestNotice.setText(test.getMsg());
        textTestRank.setText(test.getRank()+"등");
        textTestScore.setText(test.getScore()+"점");
        textUploadDay.setText(DateUtils.getRelativeTimeSpanString(test.getPostTime()));
        textEndDay.setText(test.getEndDate());

    }
}
