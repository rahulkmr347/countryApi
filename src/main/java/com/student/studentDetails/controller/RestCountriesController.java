package com.student.studentDetails.controller;

import com.student.studentDetails.core.entity.CountryDetail;
import com.student.studentDetails.dto.Country;
import com.student.studentDetails.dto.CountryResponseDto;
import com.student.studentDetails.services.CountryDetailsService;
import com.student.studentDetails.services.RestCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/countries")
public class RestCountriesController {

    @Autowired
    private RestCountriesService restCountriesService;
    @Autowired
    private CountryDetailsService countryDetailsService;


    @GetMapping("/all")
    public List<Country> getAllCountriesDetails(){
        return restCountriesService.getAllCountries();
    }

    @GetMapping("/byName/{name}")
    public List<Country> getCountryDetailsByName(@PathVariable(value = "name") String name){
        return restCountriesService.getCountryDetailsByName(name);
    }

    @GetMapping("/fetch/key")
    public Set<String> getAllCountriesKey(){
        return restCountriesService.getAllCountriesKey();
    }

    /*
    First approach - Here saving all the data with different column
    */
    @PostMapping("/saveByKey")
    public List<CountryResponseDto> saveAllCountryByKeyFirstApproach(@RequestBody Set<String> strings){
        return restCountriesService.saveAllcountryByKey(strings);
    }

    /*
        Second approach - Here saving all the data with single column
        and adding all the key in one column
        and all the value in another column
        */
    @PostMapping("/save-By-Key")
    public List<CountryDetail> saveAllCountryByKeySecondApproach(@RequestBody Set<String> strings){
        return countryDetailsService.saveAllcountryByKey(strings);
    }

    @GetMapping("/get/all")
    public List<com.student.studentDetails.core.entity.Country> fetchAll(){
        return restCountriesService.fetchAll();
    }

    @PostMapping("/fetchbykey/fromdb")
    public List<CountryResponseDto> fechBykeyFromDb(@RequestBody Set<String> strings){
        return restCountriesService.fetchByKeyFromDb(strings);
    }
}
