package com.haemin.imagemathstudent.Data;

import java.util.ArrayList;

public class StudentAssignment{
        String userSeq;
        ArrayList<ServerFile> submitFiles;
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

    public ArrayList<ServerFile> getSubmitFiles() {
        return submitFiles;
    }

    public void setSubmitFiles(ArrayList<ServerFile> submitFiles) {
        this.submitFiles = submitFiles;
    }

    public int getSubmitState() {
        return submitState;
    }

    public void setSubmitState(int submitState) {
        this.submitState = submitState;
    }
}
