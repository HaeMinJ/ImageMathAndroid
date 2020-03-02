package com.haemin.imagemathstudent.SingleTon;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.haemin.imagemathstudent.Retrofit.RetrofitInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalApplication extends Application {

    private static Retrofit retrofit = null;
    private static String accessToken;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        if(context == null)
            context = this;
    }

    public static String getAccessToken() {
        SharedPreferences pref = context.getSharedPreferences("ImageMathStudent",MODE_PRIVATE);
        accessToken = pref.getString("accessToken", null);
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        GlobalApplication.accessToken = accessToken;
        SharedPreferences pref = context.getSharedPreferences("ImageMathStudent",MODE_PRIVATE);
        pref.edit().putString("accessToken",accessToken).apply();

    }

    private static Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://ec2-54-180-115-237.ap-northeast-2.compute.amazonaws.com:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static RetrofitInterface getAPIService(){
        return getRetrofit().create(RetrofitInterface.class);
    }
}
