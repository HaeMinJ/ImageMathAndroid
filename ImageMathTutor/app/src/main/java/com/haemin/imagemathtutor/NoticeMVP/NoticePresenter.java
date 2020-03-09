package com.haemin.imagemathtutor.NoticeMVP;

import android.util.Log;
import com.haemin.imagemathtutor.AppString;
import com.haemin.imagemathtutor.Data.Notice;
import com.haemin.imagemathtutor.GlobalApplication;
import com.haemin.imagemathtutor.Retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.nio.file.Path;
import java.util.ArrayList;

public class NoticePresenter implements NoticeContract.NoticePresenter {


    ArrayList<Notice> notices;
    NoticeContract.NoticeView noticeView;


    public NoticePresenter(NoticeContract.NoticeView noticeView) {
        this.noticeView = noticeView;
        notices = new ArrayList<>(); // Only initialize in this file!
        //retrofitInterface = TutorApplication.getAPIService();
    }

    @Override
    public void updateData(String lectureSeq) {
        GlobalApplication.getAPIService().getNoticeList(GlobalApplication.getAccessToken(),lectureSeq,0).enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                if(response.code() == 200 && response.body() != null){
                    noticeView.refreshView(response.body());
                }else{
                    noticeView.showErrorMessage(AppString.ERROR_LOAD_NOTICE_LIST);
                    Log.e("NoticePresenter",response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {
                Log.e("NoticePresenter",t.getMessage(),t);
                noticeView.showErrorMessage(AppString.ERROR_NETWORK_MESSAGE);
            }
        });
        /*
        retrofitInterface.getNoticeInfos(TutorApplication.getAccessToken(), lectureSeq).enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                if (response.code() == 200 && response.body() != null) {
                    notices.clear();
                    notices.addAll(response.body());
                    noticeView.refreshView();
                } else {
                    noticeView.showErrorMessage(response.message());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {
                noticeView.showErrorMessage("네트워크 연결이 원활하지 않습니다.\n인터넷 연결을 확인해주세요.");
                Log.e("NOTICE-API", t.getMessage(), t);
            }
        });
         */
    }

    private ArrayList<Notice> addDummyData() {
        ArrayList<Notice> notices = new ArrayList<>();
        notices.add(new Notice());
        return notices;
    }

}
