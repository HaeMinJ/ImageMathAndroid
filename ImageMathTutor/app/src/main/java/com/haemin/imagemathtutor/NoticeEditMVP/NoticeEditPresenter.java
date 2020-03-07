package com.haemin.imagemathtutor.NoticeEditMVP;

import android.net.Uri;
import android.util.Log;
import com.haemin.imagemathtutor.Data.ServerFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class NoticeEditPresenter implements NoticeEditContract.NoticeEditPresenter {

    ArrayList<ServerFile> files;
    NoticeEditContract.NoticeEditView noticeEditView;

    public NoticeEditPresenter(NoticeEditContract.NoticeEditView noticeEditView) {
        this.noticeEditView = noticeEditView;
        this.files = new ArrayList<>();
    }

    @Override
    public void addFile(Uri uri, String fileType) {
        File file = new File(uri.getEncodedPath());
        ServerFile serverFile = new ServerFile(files.size(), file.getName(), file.getPath(), fileType);
        files.add(serverFile);
        noticeEditView.refreshFileList(files);
    }

    @Override
    public void deleteFile(int fileSeq) {
        Log.e("LOL", "ClickED!");


        Iterator<ServerFile> iter = files.iterator();
        while (iter.hasNext()) {
            ServerFile s = iter.next();
            if (s.getFileSeq() == fileSeq)
                iter.remove();
        }
        noticeEditView.refreshFileList(files);
    }

    @Override
    public void uploadNotice(int lectureSeq, String title, String content) {

    }
}
