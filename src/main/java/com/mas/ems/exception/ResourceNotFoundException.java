package com.mas.ems.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    // public ResourceNotFoundException(String message, Throwable cause) {
    //     super(message, cause);
    // }
     
}
