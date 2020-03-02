package com.haemin.imagemathstudent.Data;

import java.util.ArrayList;

public class Notice extends ServerData{
    int noticeSeq;
    String title;
    long uploadTime;
    String text;
    ArrayList<ServerFile> files;

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
