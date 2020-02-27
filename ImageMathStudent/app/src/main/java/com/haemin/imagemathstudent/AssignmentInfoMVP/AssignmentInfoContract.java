package com.haemin.imagemathstudent.AssignmentInfoMVP;


import android.net.Uri;
import com.esafirm.imagepicker.model.Image;
import com.haemin.imagemathstudent.Data.StudentAssignment;

public interface AssignmentInfoContract {
    interface AssignmentInfoView{
        void updateView(StudentAssignment studentAssignment);

        void showToast(String text);
    }
    interface AssignmentInfoPresenter{
        void updateData(String assignmentSeq);

        void submitPicture(String assignmentSeq, Image imageUri);

        void downloadFile(String url);
    }
}
