package com.haemin.imagemathstudent.TestFragmentMVP;

import com.haemin.imagemathstudent.Data.StudentTest;

import java.util.ArrayList;

public interface TestFragmentContract {
    interface TestFragmentView{
        void showToast();
        void showData(ArrayList<StudentTest> studentTests);
    }
    interface TestFragmentPresenter{
        void updateData(String lectureSeq);

    }
}
