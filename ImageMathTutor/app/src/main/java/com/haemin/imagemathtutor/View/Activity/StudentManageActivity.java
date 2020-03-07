package com.haemin.imagemathtutor.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.haemin.imagemathtutor.Data.Lecture;
import com.haemin.imagemathtutor.R;
import com.haemin.imagemathtutor.Utils.ConfirmStarter;

public class StudentManageActivity extends AppCompatActivity {

    String lectureSeq;

    public static void start(Context context, Lecture lecture) {
        Intent starter = new Intent(context, StudentManageActivity.class);
        starter.putExtra("lectureSeq",lecture.getLectureSeq());
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manage);
        Intent fromOutside = getIntent();
        ConfirmStarter.checkIntent(this, fromOutside);
        lectureSeq = fromOutside.getStringExtra("lectureSeq");
    }
}
