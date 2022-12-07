package com.student.studentDetails.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class UnAuthorizedException extends RuntimeException {
       private String message;
       private HttpStatus status;


    public UnAuthorizedException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
