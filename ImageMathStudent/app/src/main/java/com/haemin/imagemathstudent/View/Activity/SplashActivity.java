package com.haemin.imagemathstudent.View.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.GoogleApiAvailability;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.haemin.imagemathstudent.Data.User;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.SingleTon.AppString;
import com.haemin.imagemathstudent.SingleTon.GlobalApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {


    private static final int UI_ANIMATION_DELAY = 1000;
    private final Handler mHideHandler = new Handler();
    private final Runnable autoLoginAction = () -> autoLoginProcess();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleApiAvailability apiAvailability = new GoogleApiAvailability();
        apiAvailability.makeGooglePlayServicesAvailable(this);

        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        hide();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        finish();
                    }
                })
                .setRationaleMessage("앱을 이용하기 위해서는 메모리 사용 권한이 필요합니다.")
                .setDeniedMessage("권한 설정에 동의하셔야 앱을 이용할 수 있습니다.\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();

        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GoogleApiAvailability apiAvailability = new GoogleApiAvailability();
        apiAvailability.makeGooglePlayServicesAvailable(this);
    }

    private void autoLoginProcess() {
        if (GlobalApplication.getAccessToken() != null) {
            GlobalApplication.getAPIService().loginWithToken(GlobalApplication.getAccessToken())
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.code() == 200 && response.body() != null) {
                                GoToMain(response.body());
                                Log.e("SplashActivity",response.body().getAccessToken()+"");
                            } else {
                                Toast.makeText(SplashActivity.this, AppString.ERROR_TOKEN_EXPIRED, Toast.LENGTH_SHORT).show();
                                GoToLogin();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(SplashActivity.this, AppString.ERROR_SERVER_INSPECT, Toast.LENGTH_SHORT).show();
                            Log.e("SplashActivity",t.getMessage(),t);
                            finish();
                        }
                    });

        } else {
            GoToLogin();
        }
    }

    private void GoToMain(User user) {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        Toast.makeText(this, AppString.SUCCESS_LOGIN_MESSAGE(user.getName()), Toast.LENGTH_LONG).show();
        finish();
    }

    private void GoToLogin() {
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
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
