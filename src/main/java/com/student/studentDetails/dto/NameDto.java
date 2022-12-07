package com.student.studentDetails.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class NameDto {
    private String common;
    private String official;
    private Map<String, OfcCoommonDto> nativeName;
}
