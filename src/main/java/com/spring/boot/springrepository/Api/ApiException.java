package com.spring.boot.springrepository.Api;

public class ApiException extends RuntimeException{
    ApiException(String message){
        super(message);
    }
}
