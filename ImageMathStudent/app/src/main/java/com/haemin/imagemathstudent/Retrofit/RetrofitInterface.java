package com.haemin.imagemathstudent.Retrofit;


import com.haemin.imagemathstudent.Data.*;
import okhttp3.MultipartBody;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.Map;

public interface RetrofitInterface {

    @GET("/alarm")//수정 ok
    ArrayList<Alarm> getAlarmList(@Header("x-access-token") String accessToken);

    @GET("/notice")//수업에 해당하는 공지 출력 ok page구현
    ArrayList<Notice> getNoticeList(@Header("x-access-token") String accessToken, @Query("lectureSeq") String lectureSeq, @Query("page") int page);

    @Multipart
    @POST("/notice")//수정필요
    void addNotice(@Header("x-access-token") String accessToken, @Field("title") String title, @Field("contents") String contents, @Part MultipartBody.Part noticeFiles);
    // imageFiles Parameter name : ?, normalFiles Parameter name : ?

    @GET("/account/school")//경로바꿈 ok
    ArrayList<School> getSchoolList();

    @GET("/assignment")//ok
    ArrayList<Assignment> getAssignmentList(@Header("x-access-token") String accessToken, @Query("lectureSeq")String lectureSeq, @Query("page") int page);

    @GET("/assignment/{assignmentSeq}")//assignment/1  이런식으로 경로작성 ok    내일 만들거 studentAssignment 필요함
    Assignment getAssignmentInfo(@Path("assignmentSeq") String assignmentSeq);

    @Multipart
    @POST("/assignment/{assignmentSeq}")//이건 과제 이미지 올리는거였나? 일단 보류
    void postPicture(@Header("x-access-token") String accessToken, @Path("assignmentSeq") String assignmentSeq, @Part MultipartBody.Part assignmentPart);
    // imageFile Parameter name : ?

    @GET("/lecture")//수업 리스트 출력 ok
    Call<ArrayList<Lecture>> getLectureList();

    @GET("/lecture/student")//ok
    Call<ArrayList<Lecture>> getMyLectureList(@Header("x-access-token")String accessToken, @Query("page") int page);

    @POST("/lecture/add")//경로바꿈 ok
    ArrayList<Lecture> addLecture(@Header("x-access-token") String accessToken, @FieldMap Map<String, String> lectureField);
    /*
    academySeq, name, weekDay, time, totalDate, week, studentNum, userType
     */
    //-------------------------------------------
    @GET("/lecture/recognition/{lectureSeq}")// ok mysql contain 경로바꿈
    ArrayList<User> getRequestUserList(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq, @Query("page") int page);

    @PATCH("/lecture/recognition/{lectureSeq}")//ok
    void recognizeStudents(@Header("x-access-token") String accessToken,@Path("lectureSeq")String lectureSeq, @Field("students") ArrayList<User> recognizedStudents, @Field("isAccept") boolean isAccept);

    @GET("/lecture/student/{lectureSeq}")//ok  mysql contain
    ArrayList<User> getStudentList(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq);
//--------------------------------------------------
    @GET("/test/student/{lectureSeq}")//ok testAdm userSeq, lectureSeq 해당 데이터리스트
    ArrayList<StudentTest> getUserTest(@Header("x-access-token") String accessToken, @Path("lectureSeq") String lectureSeq, @Query("page") int page);

    @GET("/test/student")//ok testAdm accessToken 확인 ->userSeq testSeq 둘다 일치하는거score포함
    StudentTest getStudentTestInfo(@Header("x-access-token") String accessToken, @Query("testSeq") String testSeq);

    @GET("/test/tutor/{lectureSeq}")//ok testInfo
    ArrayList<Test> getTutorTestList(@Header("x-access-token")String accessToken, @Path("lectureSeq")String lectureSeq, @Query("page") int page);

    @GET("/test/tutor")//ok testInfo score제외
    Test getTutorTestInfo(@Header("x-access-token") String accessToken, @Query("testSeq") String testSeq);

    @GET("/test/result")//ok  testAdm score포함 accessToken -> student: 상위 10개 , tutor : 전부 다
    ArrayList<StudentTest> getTestResults(@Header("x-access-token")String accessToken, @Query("testSeq") String testSeq, @Query("page") int page);

    @POST("/auth/register")//ok
    User registerEmail(@FieldMap Map<String, String> registerField);
    /*
    name, eamil, password, birthday, schoolSeq, reqLedctureSeqs, userType, phone, gender
     */

    @GET("/auth/autologin")//ok
    User loginWithToken(@Header("x-access-token") String accessToken);

    @POST("/auth/login")//ok
    User loginWithEmail(@Field("email") String email, @Field("password") String pw);

    /* 페이징 처리는 요청을 보낼때 0페이지면 1~20까지 데이터. 1페이지면 21~40 까지 데이터 */
    /* 테스트 부분 API 작업할때는 UI도 참고하고 질문하기! */
}