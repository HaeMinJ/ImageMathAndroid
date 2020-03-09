package com.haemin.imagemathtutor.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.haemin.imagemathtutor.R;

public class AssignmentEditActivity extends AppCompatActivity {

    public static void start(Context context, String assignmentSeq) {
        Intent starter = new Intent(context, AssignmentEditActivity.class);
        starter.putExtra("assignmentSeq",assignmentSeq);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_edit);
    }
}
