package com.haemin.imagemathtutor.NoticeMVP;

import com.haemin.imagemathtutor.Data.Notice;
import com.haemin.imagemathtutor.Retrofit.RetrofitInterface;

import java.util.ArrayList;

public class NoticePresenter implements NoticeContract.NoticePresenter {


    ArrayList<Notice> notices;
    NoticeContract.NoticeView noticeView;
    RetrofitInterface retrofitInterface;


    public NoticePresenter(NoticeContract.NoticeView noticeView) {
        this.noticeView = noticeView;
        notices = new ArrayList<>(); // Only initialize in this file!
        //retrofitInterface = TutorApplication.getAPIService();
    }

    @Override
    public void updateData(int lectureSeq) {
        notices.addAll(addDummyData());
        noticeView.refreshView();
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

    @Override
    public ArrayList<Notice> getData() {
        return notices;
    }

    private ArrayList<Notice> addDummyData() {
        ArrayList<Notice> notices = new ArrayList<>();
        notices.add(new Notice());
        return notices;
    }

}
