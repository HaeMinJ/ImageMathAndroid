package com.haemin.imagemathtutor.LectureAddMVP;

import com.haemin.imagemathtutor.Data.Academy;
import com.haemin.imagemathtutor.Data.ServerTime;

import java.util.ArrayList;

public class AddLecturePresenter implements AddLectureContract.AddLecturePresenter {

    AddLectureContract.AddLectureView lectureView;
    ArrayList<Academy> academies;

    public AddLecturePresenter(AddLectureContract.AddLectureView lectureView) {
        this.lectureView = lectureView;
        academies = new ArrayList<>();
    }

    @Override
    public void uploadLecture(Academy academy, String lectureName, ArrayList<ServerTime> serverTimes, String lectureDuration) {

    }

    @Override
    public ArrayList<Academy> getAcademyList() {
        Academy dummy = new Academy();
        dummy.setAcademyName("종로 이투스");
        dummy.setAcademySeq(2);
        academies.add(dummy);
        return academies;
    }
}
