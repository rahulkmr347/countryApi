package com.student.studentDetails.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StudentResponseDto {

    private String studentName;
    private String emailId;
    private String studentClass;
    private String mobile;
    private boolean activeStatus;
}
