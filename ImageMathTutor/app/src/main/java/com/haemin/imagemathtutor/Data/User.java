package com.haemin.imagemathtutor.Data;

public class User extends ServerData {
    int userSeq;
    String name;
    String birthday;
    String email;
    String password;
    String lectureSeqs; // "3,5,6,~"
    String reqLectureSeqs;
    int schoolSeq;
    String phone;
    String accessToken;


    public User(int userSeq, String name, String birthday, String email, String password, String lectureSeqs, String reqLectureSeqs, int schoolSeq, String phone) {
        this.userSeq = userSeq;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.lectureSeqs = lectureSeqs;
        this.reqLectureSeqs = reqLectureSeqs;
        this.schoolSeq = schoolSeq;
        this.phone = phone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLectureSeqs() {
        return lectureSeqs;
    }

    public void setLectureSeqs(String lectureSeqs) {
        this.lectureSeqs = lectureSeqs;
    }

    public String getReqLectureSeqs() {
        return reqLectureSeqs;
    }

    public void setReqLectureSeqs(String reqLectureSeqs) {
        this.reqLectureSeqs = reqLectureSeqs;
    }

    public int getSchoolSeq() {
        return schoolSeq;
    }

    public void setSchoolSeq(int schoolSeq) {
        this.schoolSeq = schoolSeq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
