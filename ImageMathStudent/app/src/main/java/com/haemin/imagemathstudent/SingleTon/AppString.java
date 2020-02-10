package com.haemin.imagemathstudent.SingleTon;

public class AppString {
    public static final String ERROR_NETWORK_MESSAGE = "인터넷 연결이 원활하지 않습니다. 인터넷 연결을 확인해주세요.";

    public static final String ERROR_TOKEN_EXPIRED = "토큰이 만료되었습니다. 다시 로그인해주세요.";
    public static final String ERROR_EMAIL_EMPTY = "이메일을 입력해주세요.";
    public static final String ERROR_PW_EMPTY = "비밀번호를 입력해주세요.";

    public static final String ERROR_CODE_EMPTY = "인증번호를 입력해주세요.";
    public static final String ERROR_CODE_ERROR = "인증번호가 일치하지 않습니다. 다시 인증해주세요.";

    public static final String ERROR_LOAD_LECTURE_LIST = "진행중인 강의 목록을 정상적으로 불러오지 못했습니다.";
    public static final String ERROR_LOAD_SCHOOL_LIST = "학교 목록을 정상적으로 불러오지 못했습니다.";
    public static final String SUCCESS_CODE_RECOGNITION = "인증이 성공적으로 완료되었습니다.";
    public static final String SUCCESS_REGISTER = "회원가입이 성공적으로 완료되었습니다.";
    public static String SUCCESS_LOGIN_MESSAGE(String name){
        return name+"학생 환영해요! 제출 안한 숙제가 있는지 먼저 확인해주세요!";
    }
}
