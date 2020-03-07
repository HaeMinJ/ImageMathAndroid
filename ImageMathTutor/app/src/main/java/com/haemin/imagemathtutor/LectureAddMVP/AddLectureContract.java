package com.haemin.imagemathtutor.LectureAddMVP;

import com.haemin.imagemathtutor.Data.Academy;
import com.haemin.imagemathtutor.Data.ServerTime;

import java.util.ArrayList;

public interface AddLectureContract {
    interface AddLectureView {
        void showSuccess();

        void showErrorMsg(String msg);
    }

    interface AddLecturePresenter {
        void uploadLecture(Academy academy, String lectureName, ArrayList<ServerTime> serverTimes, String lectureDuration);

        ArrayList<Academy> getAcademyList();
    }
}
