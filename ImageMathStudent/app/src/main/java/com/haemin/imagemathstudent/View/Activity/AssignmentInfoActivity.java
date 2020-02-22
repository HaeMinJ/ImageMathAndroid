package com.haemin.imagemathstudent.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.Utils.ConfirmStarter;

public class AssignmentInfoActivity extends AppCompatActivity {

    String lectureSeq;

    public static void start(Context context, String lectureSeq) {
        Intent starter = new Intent(context, AssignmentInfoActivity.class);
        starter.putExtra("lectureSeq",lectureSeq);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_info);
        Intent i = getIntent();
        ConfirmStarter.checkIntent(this, i);
        lectureSeq = i.getStringExtra("lectureSeq");

    }
}
