package com.haemin.imagemathstudent.View.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.GoogleApiAvailability;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.haemin.imagemathstudent.BuildConfig;
import com.haemin.imagemathstudent.Data.User;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.SingleTon.AppString;
import com.haemin.imagemathstudent.SingleTon.GlobalApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {


    private static final int UI_ANIMATION_DELAY = 1000;
    private final Handler mHideHandler = new Handler();
    String version;
    String marketVersion;
    AlertDialog.Builder mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleApiAvailability apiAvailability = new GoogleApiAvailability();
        apiAvailability.makeGooglePlayServicesAvailable(this);
        mDialog = new AlertDialog.Builder(this);

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
    private void checkNewVersion(){
        new getMarketVersion().execute();
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
        checkNewVersion();
    }
    @SuppressLint("StaticFieldLeak")
    private class getMarketVersion extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String marketVersion = null;
            try {
                Document doc = Jsoup
                        .connect(
                                "https://play.google.com/store/apps/details?id=com.haemin.imagemathstudent" )
                        .get();
                Elements Version = doc.select(".htlgb ");

                for (int i = 0; i < Version.size() ; i++) {
                    marketVersion = Version.get(i).text();
                    if (Pattern.matches("^[0-9]{1}.[0-9]{1}-release$", marketVersion)) {
                        break;
                    }
                }
                return marketVersion;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            version = BuildConfig.VERSION_NAME;
            marketVersion = result;
            Log.e("CurrentMarketVersion",result+"");
            if(marketVersion == null){
                mHideHandler.postDelayed(SplashActivity.this::autoLoginProcess, 1500);
                return;
            }
            if (!version.equals(marketVersion)) {
                mDialog.setMessage("새로운 버전으로 업데이트가 가능합니다.")
                        .setCancelable(false)
                        .setPositiveButton("업데이트 바로가기",
                                (dialog, id) -> {
                                    Intent marketLaunch = new Intent(
                                            Intent.ACTION_VIEW);
                                    marketLaunch.setData(Uri
                                            .parse("https://play.google.com/store/apps/details?id=com.haemin.imagemathstudent"));
                                    startActivity(marketLaunch);
                                    finish();
                                })
                .setNegativeButton("그냥 사용하기",(dialog, which) -> {
                    Toast.makeText(SplashActivity.this,"최신버전에서 해결된 오류가 발생할 수 있습니다.",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    mHideHandler.postDelayed(SplashActivity.this::autoLoginProcess, 1500);
                });
                AlertDialog alert = mDialog.create();
                alert.setTitle("안 내");
                alert.show();
            }else{
                mHideHandler.postDelayed(SplashActivity.this::autoLoginProcess, 1500);
            }

            super.onPostExecute(result);
        }
    }

}
