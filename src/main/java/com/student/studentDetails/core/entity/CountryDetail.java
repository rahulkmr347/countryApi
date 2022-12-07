package com.student.studentDetails.core.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRY_DETAIL")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class CountryDetail {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COUNTRY_DETAIL_ID")
    private Long id;

    @Column(name = "COUNTRY_DETAIL_KEY" , nullable = false)
    private String key;

    @Column(name = "COUNTRY_DETAIL_VALUE")
    private String value;

    public CountryDetail(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
