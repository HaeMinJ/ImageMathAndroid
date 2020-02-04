package com.haemin.imagemathstudent.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.View.RegisterProcess.RegisterTermsActivity;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.edit_pw)
    EditText editPW;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.text_register)
    TextView textRegister;
    @BindView(R.id.text_find_pw)
    TextView textFindPW;
    @BindView(R.id.toggle_save_email)
    ToggleButton toggleSaveEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        bindListener();
    }
    void bindListener(){
        textRegister.setOnClickListener(v->startActivity(new Intent(LoginActivity.this, RegisterTermsActivity.class)));
        btnLogin.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        });
    }
}
