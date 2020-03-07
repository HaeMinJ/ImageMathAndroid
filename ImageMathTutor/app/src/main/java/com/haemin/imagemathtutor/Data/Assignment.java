package com.haemin.imagemathtutor.Data;

public class Assignment extends ServerData {
    int assignmentSeq;
    String title;
    String contents;
    long postTime;
    long endDate;
    long lectureDate;
    ServerFile solutionFile;
    String lectureName;

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

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(long lectureDate) {
        this.lectureDate = lectureDate;
    }


    public ServerFile getSolutionFile() {
        return solutionFile;
    }

    public void setSolutionFile(ServerFile solutionFile) {
        this.solutionFile = solutionFile;
    }
}
