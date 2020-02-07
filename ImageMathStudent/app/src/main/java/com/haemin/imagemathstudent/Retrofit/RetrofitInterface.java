package com.haemin.imagemathstudent.Retrofit;


import com.haemin.imagemathstudent.Data.*;
import okhttp3.MultipartBody;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.Map;

public interface RetrofitInterface {

    @GET("/alarm")//알림리스트 출력 ok
    ArrayList<Alarm> getAlarmList(@Header("x-access-token") String accessToken);

    @GET("/notice")//수업에 해당하는 공지 출력 ok page구현 안함
    ArrayList<Notice> getNoticeList(@Header("x-access-token") String accessToken, @Query("lectureSeq") String lectureSeq, @Query("page") int page);

    @Multipart
    @POST("/notice")//수정필요
    void addNotice(@Header("x-access-token") String accessToken, @Field("name") String name, @Field("contents") String contents, @Part MultipartBody.Part noticeFiles);
    // imageFiles Parameter name : ?, normalFiles Parameter name : ?


    @GET("/account/school")//경로바꿈 ok
    ArrayList<School> getSchoolList();

    @GET("/assignment")//page 안함  ok
    ArrayList<Assignment> getAssignmentList(@Header("x-access-token") String accessToken, @Query("lectureSeq")String lectureSeq, @Query("page") int page);

    @GET("/assignment/{assignmentSeq}")//보류
    Assignment getAssignmentInfo(@Path("assignmentSeq") String assignmentSeq);

    @Multipart
    @POST("/assignment/{assignmentSeq}")//이건 과제 이미지 올리는거였나? 일단 보류
    void postPicture(@Header("x-access-token") String accessToken, @Path("assignmentSeq") String assignmentSeq, @Part MultipartBody.Part assignmentPart);
    // imageFile Parameter name : ?

    @GET("/lecture")//수업 리스트 출력 ok
    ArrayList<Lecture> getLectureList(@Header("x-access-token") String accessToken, @Query("page") int page);

    @POST("/lecture/add")//경로바꿈
    ArrayList<Lecture> addLecture(@Header("x-access-token") String accessToken, @FieldMap Map<String, String> lectureField);
    /*
    academySeq, name, weekDay, time, totalDate, week, studentNum, userType
     */

    @GET("/recognition/{lectureSeq}")// 보류
    ArrayList<User> getRequestUserList(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq, @Query("page") int page);

    @PATCH("/recognition")//보류
    void recognizeStudents(@Header("x-access-token") String accessToken, @Field("students") ArrayList<User> recognizedStudents, @Field("isAccept") boolean isAccept);

    @GET("/student/{lectureSeq}")//보류
    ArrayList<User> getStudentList(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq);
//---------------------------------------------------
    @GET("/test/student/{lectureSeq}")
    ArrayList<StudentTest> getUserTest(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq, @Query("page") int page);

    @GET("/test/student")
    StudentTest getStudentTestInfo(@Header("x-access-token") String accessToken, @Query("testSeq") String testSeq);

    @GET("/test/tutor/{lectureSeq}")
    ArrayList<Test> getTutorTestList(@Header("x-access-token")String accessToken, @Path("lectureSeq")String lectureSeq, @Query("page") int page);
    @GET("/test/tutor")
    Test getTutorTestInfo(@Header("x-access-token") String accessToken, @Query("testSeq") String testSeq);

    @GET("/test/result")
    ArrayList<StudentTest> getTestResults(@Header("x-access-token")String accessToken, @Query("testSeq") String testSeq, @Query("page") int page);


    @POST("/auth/register")
    User registerEmail(@FieldMap Map<String, String> registerField);
    /*
    name , school~~~~~~~
     */

    @GET("/auth/autologin")
    User loginWithToken(@Header("x-access-token") String accessToken);

    @POST("/auth/login")
    User loginWithEmail(@Field("email") String email, @Field("password") String pw);

    /* 페이징 처리는 요청을 보낼때 0페이지면 1~20까지 데이터. 1페이지면 21~40 까지 데이터 */
    /* 테스트 부분 API 작업할때는 UI도 참고하고 질문하기! */
}
