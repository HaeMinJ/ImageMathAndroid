package com.haemin.imagemathtutor.Data;

public class Test {
    int testSeq;
    String title;
    long postTime;
    String endDate;
    String lectureDate;
    String solutionFileUrl;
    Lecture lecture;

    public int getTestSeq() {
        return testSeq;
    }

    public void setTestSeq(int testSeq) {
        this.testSeq = testSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(String lectureDate) {
        this.lectureDate = lectureDate;
    }

    public String getSolutionFileUrl() {
        return solutionFileUrl;
    }

    public void setSolutionFileUrl(String solutionFileUrl) {
        this.solutionFileUrl = solutionFileUrl;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
