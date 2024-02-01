package com.example.demo.config.api.response.status;

public class StatusEntity<T> {

    public T data;
    public StatusCode code;

    public StatusEntity (T data, StatusCode code){
        this.data = data;
        this.code = code;
    }

    public StatusEntity (StatusCode code){
        this.code = code;
    }
}
