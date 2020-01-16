package com.haemin.imagemathtutor.NoticeEditMVP;

import android.net.Uri;
import com.haemin.imagemathtutor.Data.ServerFile;

import java.util.ArrayList;

public interface NoticeEditContract {
    interface NoticeEditView {

        void refreshFileList(ArrayList<ServerFile> files);

        void showSuccess(String message);

        void showError(String errorMsg);

    }

    interface NoticeEditPresenter {
        void addFile(Uri uri, String fileType);

        void deleteFile(int fileSeq);

        void uploadNotice(int lectureSeq, String name, String content);
    }
}
