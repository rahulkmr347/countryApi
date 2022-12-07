package com.student.studentDetails.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@ToString
public class ValidationException extends RuntimeException{
    private String message;
    private HttpStatus status;


    public ValidationException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
