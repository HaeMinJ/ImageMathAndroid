package com.haemin.imagemathstudent.Retrofit;


import com.haemin.imagemathstudent.Data.*;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.Map;

public interface RetrofitInterface {

    @GET("/alarm")//userSeq로 LectureSeqs찾고 해당 알람리스트 출력 수정필요
    Call<ArrayList<Alarm>> getAlarmList(@Header("x-access-token") String accessToken);

    @GET("/notice")//수업에 해당하는 공지 출력 ok page구현
    Call<ArrayList<Notice>> getNoticeList(@Header("x-access-token") String accessToken, @Query("lectureSeq") String lectureSeq, @Query("page") int page);

    @Multipart
    @POST("/notice")//수정필요
    Call<Void> addNotice(@Header("x-access-token") String accessToken, @Field("title") String title, @Field("contents") String contents, @Part MultipartBody.Part noticeFiles);
    // imageFiles Parameter name : ?, normalFiles Parameter name : ?

    @GET("/account/school")//경로바꿈 ok
    Call<ArrayList<School>> getSchoolList();

    @GET("/assignment")//ok  assignmentInfo
    Call<ArrayList<Assignment>> getAssignmentList(@Header("x-access-token") String accessToken, @Query("lectureSeq")String lectureSeq, @Query("page") int page);

    @GET("/assignment/{assignmentSeq}")//assignment/{assignmentSeq}:1  이런식으로 경로작성 ok    내일 만들거 studentAssignment 필요함
    Call<Assignment> getAssignmentInfo(@Path("assignmentSeq") String assignmentSeq);

    @Multipart
    @POST("/assignment/{assignmentSeq}")//이건 과제 이미지 올리는거였나? 일단 보류
    Call<Void> postPicture(@Header("x-access-token") String accessToken, @Path("assignmentSeq") String assignmentSeq, @Part MultipartBody.Part assignmentPart);
    // imageFile Parameter name : ?

    @GET("/lecture")//수업 리스트 출력 ok  tutor : 모든수업, student : 듣는수업
    Call<ArrayList<Lecture>> getLectureList();

    @GET("/lecture/student")
    Call<ArrayList<Lecture>> getMyLectureList(@Header("x-access-token")String accessToken, @Query("page") int page);

    @FormUrlEncoded
    @POST("/lecture/add")//경로바꿈 ok
    Call<ArrayList<Lecture>> addLecture(@Header("x-access-token") String accessToken, @FieldMap Map<String, String> lectureField);
    /*
    academySeq, name, weekDay, time, totalDate, week, studentNum, userType
     */
//------------------------------------------------------------------------
    @GET("/recognition/{lectureSeq}")// 보류 mysql contain
    Call<ArrayList<User>> getRequestUserList(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq, @Query("page") int page);

    @PATCH("/recognition")//보류
    Call<Void> recognizeStudents(@Header("x-access-token") String accessToken, @Field("students") ArrayList<User> recognizedStudents, @Field("isAccept") boolean isAccept);

    @GET("/student/{lectureSeq}")//보류  mysql contain
    Call<ArrayList<User>> getStudentList(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq);

    @GET("/test/student/{lectureSeq}")//보류 testAdm userSeq, lectureSeq 해당 데이터리스트
    Call<ArrayList<StudentTest>> getUserTest(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq, @Query("page") int page);
    //-----------------------------------------------------------
    @GET("/test/student")//ok testAdm accessToken 확인 ->userSeq testSeq 둘다 일치하는거score포함
    Call<StudentTest> getStudentTestInfo(@Header("x-access-token") String accessToken, @Query("testSeq") String testSeq);

    @GET("/test/tutor/{lectureSeq}")//ok testInfo
    Call<ArrayList<Test>> getTutorTestList(@Header("x-access-token")String accessToken, @Path("lectureSeq")String lectureSeq, @Query("page") int page);

    @GET("/test/tutor")//수정필요 testInfo  score제외
    Call<Test> getTutorTestInfo(@Header("x-access-token") String accessToken, @Query("testSeq") String testSeq);

    @GET("/test/result")//수정필요  testAdm score포함 accessToken -> student: 상위 10개 , tutor : 전부 다
    Call<ArrayList<StudentTest>> getTestResults(@Header("x-access-token")String accessToken, @Query("testSeq") String testSeq, @Query("page") int page);


    @FormUrlEncoded
    @POST("/auth/register")
    Call<User> registerEmail(@FieldMap Map<String, String> registerField);
    /*
    name , school~~~~~~~
     */

    @GET("/auth/autologin")
    Call<User> loginWithToken(@Header("x-access-token") String accessToken);

    @FormUrlEncoded
    @POST("/auth/login")
    Call<User> loginWithEmail(@Field("email") String email, @Field("password") String pw);

    /* 페이징 처리는 요청을 보낼때 0페이지면 1~20까지 데이터. 1페이지면 21~40 까지 데이터 */
    /* 테스트 부분 API 작업할때는 UI도 참고하고 질문하기! */
}