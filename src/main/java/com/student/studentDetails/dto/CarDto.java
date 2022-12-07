package com.student.studentDetails.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CodePointLength;

import java.util.List;

@Getter @Setter
public class CarDto {

    private List<String> signs;
    private String side;
}
