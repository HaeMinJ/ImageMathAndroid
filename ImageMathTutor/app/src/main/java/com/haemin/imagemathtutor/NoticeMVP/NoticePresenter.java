package com.haemin.imagemathtutor.NoticeMVP;

import com.haemin.imagemathtutor.Data.Notice;
import retrofit2.Response;

import java.util.ArrayList;

public class NoticePresenter implements NoticeContract.NoticePresenter {


    ArrayList<Notice> notices;
    NoticeContract.NoticeView noticeView;
    NoticeModel model;


    public NoticePresenter(NoticeContract.NoticeView noticeView) {
        this.noticeView = noticeView;
        this.model = new NoticeModel();
        notices = new ArrayList<>(); // Only initialize in this file!
    }
    @Override
    public void updateData(int lectureSeq) {
        notices.addAll(addDummyData());
        noticeView.refreshView();
        /*
        model.updateModel(new NoticeModel.APIListener() {
            @Override
            public void onSuccess(Response<ArrayList<Notice>> response) {
                notices.clear();
                assert response.body() != null;
                notices.addAll(response.body());
                noticeView.refreshView();
            }

            @Override
            public void onError(String errorMsg) {
                noticeView.showErrorMessage(errorMsg);
            }
        },lectureSeq);

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
