package com.haemin.imagemathtutor.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import com.haemin.imagemathtutor.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {


    private static final int UI_ANIMATION_DELAY = 1000;
    private final Handler mHideHandler = new Handler();
    private final Runnable autoLoginAction = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            Intent i = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hide();

        setContentView(R.layout.activity_splash);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(autoLoginAction, UI_ANIMATION_DELAY);
    }
}
