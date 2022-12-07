package com.student.studentDetails.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ErrorDto {
    String message;
    int status;

    public ErrorDto(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
