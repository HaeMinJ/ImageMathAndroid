package com.haemin.imagemathtutor.Data;

import java.util.ArrayList;

public class Assignment extends ServerData {
    int assignmentSeq;
    int submitNum;
    int acceptNum;
    int studentNum;
    String title;
    String contents;
    long postTime;
    long endTime;
    long lectureTime;
    ArrayList<ServerFile> solutionFiles;
    String lectureName;

    public void set(Assignment assignment) {
        this.assignmentSeq = assignment.assignmentSeq;
        this.submitNum = assignment.submitNum;
        this.acceptNum = assignment.acceptNum;
        this.studentNum = assignment.studentNum;
        this.title = assignment.title;
        this.contents = assignment.contents;
        this.postTime = assignment.postTime;
        this.endTime = assignment.endTime;
        this.lectureTime = assignment.lectureTime;
        this.solutionFiles = assignment.solutionFiles;
        this.lectureName = assignment.lectureName;
    }

    public int getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(int submitNum) {
        this.submitNum = submitNum;
    }

    public int getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(int acceptNum) {
        this.acceptNum = acceptNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public int getAssignmentSeq() {
        return assignmentSeq;
    }

    public void setAssignmentSeq(int assignmentSeq) {
        this.assignmentSeq = assignmentSeq;
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getLectureTime() {
        return lectureTime;
    }

    public void setLectureTime(long lectureTime) {
        this.lectureTime = lectureTime;
    }


    public ArrayList<ServerFile> getSolutionFiles() {
        return solutionFiles;
    }

    public void setSolutionFiles(ArrayList<ServerFile> solutionFiles) {
        this.solutionFiles = solutionFiles;
    }
}
