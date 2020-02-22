package com.haemin.imagemathstudent.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.Utils.ConfirmStarter;

public class TestInfoActivity extends AppCompatActivity {

    String lectureSeq;
    public static void start(Context context, String lectureSeq) {
        Intent starter = new Intent(context, TestInfoActivity.class);
        starter.putExtra("lectureSeq",lectureSeq);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);
        Intent fromOutside = getIntent();
        ConfirmStarter.checkIntent(this, fromOutside);
        lectureSeq = fromOutside.getStringExtra("lectureSeq");
    }
}
