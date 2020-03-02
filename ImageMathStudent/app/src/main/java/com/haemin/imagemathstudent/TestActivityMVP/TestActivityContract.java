package com.haemin.imagemathstudent.TestActivityMVP;

import com.haemin.imagemathstudent.Data.StudentTest;

public interface TestActivityContract {
    interface TestActivityView{
        void showToast(String text);
        void updateView(StudentTest test);
    }
    interface TestActivityPresenter{
        void updateData(String lectureSeq);
        void downloadAnswer();
    }
}
