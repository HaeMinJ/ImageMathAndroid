package com.haemin.imagemathstudent.Data;

import java.util.ArrayList;

public class Notice extends ServerData{
    int noticeSeq;
    String title;
    ArrayList<ServerFile> files;

    public int getNoticeSeq() {
        return noticeSeq;
    }

    public void setNoticeSeq(int noticeSeq) {
        this.noticeSeq = noticeSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ServerFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<ServerFile> files) {
        this.files = files;
    }
}
