package com.haemin.imagemathstudent.Data;

import android.annotation.SuppressLint;

public class Lecture extends SelectableData{
    int lectureSeq;
    String time;
    String name;
    String totalDate; // ,를 구분자로 사용함.
    //String week;
    @SuppressLint("reqStudentCnt")
    int reqStudentCount;
    String studentNum;
    Academy academy;
    boolean isExpired;

    @Override
    public String getListName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLectureSeq() {
        return lectureSeq;
    }

    public void setLectureSeq(int lectureSeq) {
        this.lectureSeq = lectureSeq;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(String totalDate) {
        this.totalDate = totalDate;
    }

    /*
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
    */

    public int getReqStudentCount() {
        return reqStudentCount;
    }

    public void setReqStudentCount(int reqStudentCount) {
        this.reqStudentCount = reqStudentCount;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }
}
