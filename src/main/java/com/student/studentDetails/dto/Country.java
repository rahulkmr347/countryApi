package com.student.studentDetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Country {

    private NameDto name;

    private List<String> tld;

    private String cca2;

    private String ccn3;

    private String cca3;

    private String cioc;

    private boolean independent;

    private String status;

    private boolean unMember;

    private Map<String, NameSymbolDto> currencies;

    private IddDto idd;

    private List<String> capital;

    private List<String> altSpellings;

    private String region;

    private String subregion;

    private Map<String, String> languages;


    private Map<String, OfcCoommonDto> translations;

    private List<Long> latlng;

    private boolean landlocked;

    private List<String> borders;

    private Integer area;

    private DemonymsDto demonyms;

    private String flag;

    private MapDto maps;

    private Long population;
    private Map<String, Double> gini;
    private  String fifa;

    private CarDto car;
    private List<String> timezones;
    private List<String> continents;
    private ImageTypeDto flags;
    private ImageTypeDto coatOfArms;
    private  String startOfWeek;
    private CapitalInfoDto capitalInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PostalCodeDto postalCode;

}
