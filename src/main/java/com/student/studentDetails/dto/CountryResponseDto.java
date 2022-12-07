package com.student.studentDetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryResponseDto {

    private String cca2;

    private String ccn3;

    private String cca3;

    private String cioc;

    private Boolean independent;

    private String status;

    private Boolean unMember;

    private String region;

    private String subregion;

    private Boolean landlocked;



}
