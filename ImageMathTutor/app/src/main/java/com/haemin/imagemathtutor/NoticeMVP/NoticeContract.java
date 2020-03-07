package com.haemin.imagemathtutor.NoticeMVP;

import com.haemin.imagemathtutor.Data.Notice;

import java.util.ArrayList;

public interface NoticeContract {
    interface NoticeView {
        void refreshView();

        void showErrorMessage(String error);
    }

    interface NoticePresenter {
        void updateData(int lectureSeq);

        ArrayList<Notice> getData();
    }
}
