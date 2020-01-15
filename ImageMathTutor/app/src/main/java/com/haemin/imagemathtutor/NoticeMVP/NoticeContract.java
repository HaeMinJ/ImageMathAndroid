package com.haemin.imagemathtutor.NoticeMVP;

import com.haemin.imagemathtutor.Data.Notice;

import java.util.ArrayList;

public interface NoticeContract {
    interface View{
        void refreshView(ArrayList<Notice> notices);
        void showErrorMessage(String error);
    }
    interface Presenter{
        ArrayList<Notice> updateData(int lectureSeq);
    }
}
