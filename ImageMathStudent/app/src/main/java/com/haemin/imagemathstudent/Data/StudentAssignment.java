package com.haemin.imagemathstudent.Data;

public class StudentAssignment{
        String userSeq;
        String submitFileUrl;
        int submitState;
        Assignment assignment;

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public String getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(String userSeq) {
        this.userSeq = userSeq;
    }

    public String getSubmitFileUrl() {
        return submitFileUrl;
    }

    public void setSubmitFileUrl(String submitFileUrl) {
        this.submitFileUrl = submitFileUrl;
    }

    public int getSubmitState() {
        return submitState;
    }

    public void setSubmitState(int submitState) {
        this.submitState = submitState;
    }
}
