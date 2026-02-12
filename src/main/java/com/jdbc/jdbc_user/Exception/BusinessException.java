package com.jdbc.jdbc_user.Exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{

    private final Integer code;

    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode(){
        return map(this.code);
    }

    public static HttpStatus map(int oracleCode) {

        if (oracleCode >= 20000 && oracleCode <= 20099) {
            return HttpStatus.BAD_REQUEST;
        }
        if (oracleCode >= 20100 && oracleCode <= 20199) {
            return HttpStatus.NOT_FOUND;
        }
        if (oracleCode >= 20200 && oracleCode <= 20299) {
            return HttpStatus.CONFLICT;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
