package com.haemin.imagemathstudent.Data;

public class Video {
    int videoSeq;
    int userSeq;
    long uploadTime;
    String title;
    String contents;

    public int getVideoSeq() {
        return videoSeq;
    }

    public void setVideoSeq(int videoSeq) {
        this.videoSeq = videoSeq;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
