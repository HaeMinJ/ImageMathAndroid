package com.haemin.imagemathtutor.NoticeMVP;

import android.util.Log;
import com.haemin.imagemathtutor.Data.Notice;
import com.haemin.imagemathtutor.Retrofit.RetrofitInterface;
import com.haemin.imagemathtutor.TutorApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class NoticeModel {




    void updateModel(APIListener apiListener, int lectureSeq){
        RetrofitInterface retrofitInterface = TutorApplication.getAPIService();
        retrofitInterface.getNoticeInfos(TutorApplication.getAccessToken(), lectureSeq).enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                if(response.isSuccessful()){
                    apiListener.onSuccess(response);
                }else{
                    apiListener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {
                apiListener.onError("네트워크 연결이 원활하지 않습니다.\n인터넷 연결을 확인해주세요.");
                Log.e("NOTICE-API",t.getMessage(),t);
            }
        });
    }

    public interface APIListener{
        void onSuccess(Response<ArrayList<Notice>> response);
        void onError(String errorMsg);
    }

}
