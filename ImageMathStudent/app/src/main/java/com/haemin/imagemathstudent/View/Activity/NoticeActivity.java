package com.haemin.imagemathstudent.View.Activity;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.haemin.imagemathstudent.R;

public class NoticeActivity extends AppCompatActivity {


    String lectureSeq;

    public static void start(Context context, String lectureSeq) {
        Intent starter = new Intent(context, NoticeActivity.class);
        starter.putExtra("lectureSeq",lectureSeq);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Intent fromOutside = getIntent();
        lectureSeq = fromOutside.getStringExtra("lectureSeq");

    }
}
