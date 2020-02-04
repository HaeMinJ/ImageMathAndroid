package com.haemin.imagemathstudent.View.RegisterProcess;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.haemin.imagemathstudent.R;

public class RegisterTermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_terms);
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }
}
