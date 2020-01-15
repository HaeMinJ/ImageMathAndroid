package com.haemin.imagemathtutor.Retrofit;

import com.haemin.imagemathtutor.Data.Notice;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.ArrayList;

public interface RetrofitInterface {
    @GET("")
    Call<ArrayList<Notice>> getNoticeInfos(@Header("Access-Token") String token, @Field("lectureSeq") int lectureSeq);
}
