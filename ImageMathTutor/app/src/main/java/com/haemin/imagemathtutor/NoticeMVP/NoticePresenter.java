package com.haemin.imagemathtutor.NoticeMVP;

import com.haemin.imagemathtutor.Data.Notice;
import retrofit2.Response;

import java.util.ArrayList;

public class NoticePresenter implements NoticeContract.Presenter {


    ArrayList<Notice> notices;
    NoticeContract.View view;
    NoticeModel model;


    public NoticePresenter(NoticeContract.View view) {
        this.view = view;
        this.model = new NoticeModel();
        notices = new ArrayList<>(); // Only initialize in this file!
    }
    @Override
    public ArrayList<Notice> updateData(int lectureSeq) {
        model.updateModel(new NoticeModel.APIListener() {
            @Override
            public void onSuccess(Response<ArrayList<Notice>> response) {
                view.refreshView(response.body());
            }

            @Override
            public void onError(String errorMsg) {
                view.showErrorMessage(errorMsg);
            }
        },lectureSeq);
        return notices;
    }

}
