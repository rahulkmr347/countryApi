package com.student.studentDetails.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRY")
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country {

    @Id
    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "CCA2")
    private String cca2;

    @Column(name = "CCN3")
    private String ccn3;

    @Column(name = "CCA3")
    private String cca3;

    @Column(name = "CIOC")
    private String cioc;

    @Column(name = "INDEPENDENT")
    private Boolean independent;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "UNMEMBER")
    private Boolean unMember;

    @Column(name = "REGION")
    private String region;

    @Column(name = "SUBREGION")
    private String subregion;

    @Column(name = "LANDLOCKED")
    private Boolean landlocked;

}
