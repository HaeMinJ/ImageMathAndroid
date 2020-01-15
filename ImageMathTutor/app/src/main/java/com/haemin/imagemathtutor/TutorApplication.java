package com.haemin.imagemathtutor;

import android.app.Application;
import com.haemin.imagemathtutor.Retrofit.RetrofitInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TutorApplication extends Application {
    private static Retrofit retrofit = null;
    private static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        TutorApplication.accessToken = accessToken;
    }

    private static Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static RetrofitInterface getAPIService(){
        return getRetrofit().create(RetrofitInterface.class);
    }
}
