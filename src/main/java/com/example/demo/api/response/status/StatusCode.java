package com.example.demo.api.response.status;

import org.springframework.http.HttpStatus;

public enum StatusCode {

    OK(200)
    , NO_DATA(555)
    , OVERLAP(666);

    public int code;

    StatusCode(int code){
        this.code = code;
    }
}
