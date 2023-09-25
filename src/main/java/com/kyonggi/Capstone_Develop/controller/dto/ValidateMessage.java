package com.kyonggi.Capstone_Develop.controller.dto;

public abstract class ValidateMessage {
    public static final String EMPTY_MESSAGE = "비어있는 항목을 입력해주세요.";
    public static final String MEMBER_PW_MESSAGE = "2자 이상의 16자 이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요.";
    public static final String PASSWORD_FORMAT_MESSAGE = "영문자, 특수문자, 숫자를 포함해야 합니다.";
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    public static final String PASSWORD_FORMAT = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{" + 2 + "," + 16 + "}$";
}
