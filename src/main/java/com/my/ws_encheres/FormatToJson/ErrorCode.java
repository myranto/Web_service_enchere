package com.my.ws_encheres.FormatToJson;

public class ErrorCode {
    String message;
    int status;


    public ErrorCode(String cd, int i) {
        message=cd;
        status=i;
    }
}
