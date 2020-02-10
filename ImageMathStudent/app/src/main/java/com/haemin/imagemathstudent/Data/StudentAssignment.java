package com.haemin.imagemathstudent.Data;

public class StudentAssignment{
        String userSeq;
        String assignmentSeq;
        ServerFile submitFile;
        int submitState;

    public String getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(String userSeq) {
        this.userSeq = userSeq;
    }

    public String getAssignmentSeq() {
        return assignmentSeq;
    }

    public void setAssignmentSeq(String assignmentSeq) {
        this.assignmentSeq = assignmentSeq;
    }

    public ServerFile getSubmitFile() {
        return submitFile;
    }

    public void setSubmitFile(ServerFile submitFile) {
        this.submitFile = submitFile;
    }

    public int getSubmitState() {
        return submitState;
    }

    public void setSubmitState(int submitState) {
        this.submitState = submitState;
    }
}
