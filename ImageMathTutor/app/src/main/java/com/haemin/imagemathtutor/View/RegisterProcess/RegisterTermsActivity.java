package com.haemin.imagemathtutor.View.RegisterProcess;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.haemin.imagemathtutor.R;

public class RegisterTermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_terms);
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }
}
