package com.haemin.imagemathtutor.Data;

public class Academy extends SelectableData {

    int academySeq;
    String academyName;

    @Override
    public String getListName() {
        return academyName;
    }

    public int getAcademySeq() {
        return academySeq;
    }

    public void setAcademySeq(int academySeq) {
        this.academySeq = academySeq;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }
}
