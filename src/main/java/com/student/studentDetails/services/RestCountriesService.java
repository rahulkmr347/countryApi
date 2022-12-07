package com.student.studentDetails.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.studentDetails.constants.AppConstants;
import com.student.studentDetails.core.repository.CountryRepository;
import com.student.studentDetails.dto.Country;
import com.student.studentDetails.dto.CountryResponseDto;
import com.student.studentDetails.exceptionHandler.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestCountriesService {

    @Autowired
    CountryRepository countrysRepository;

    @Autowired
    private RestService restService;

    public List<Country> getAllCountries(){
        ResponseEntity<String> responseEntity =  restService.getAllCountries();
        try {
            return new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<List<Country>>() {});
        } catch (JsonProcessingException e){
            throw new ValidationException( "Not able to parse",HttpStatus.BAD_REQUEST);
        }
    }

    public List<Country> getCountryDetailsByName(String name) {
        return restService.getCountryDetailsByName(name);
    }

    public Set<String> getAllCountriesKey(){
        try {
           List<Map<String,Object>> countries =  new ObjectMapper().readValue(restService.getAllCountries().getBody(), new TypeReference<List<Map<String,Object>>>() {
            });
           Set<String> keys = new HashSet<>();
            for(Map.Entry<String,Object> p : countries.get(0).entrySet()){
                if(AppConstants.keys.contains(p.getKey())){
                    keys.add(p.getKey());
                }
            }
            return keys;
        } catch (JsonProcessingException e) {
            throw new ValidationException( "Not able to parse",HttpStatus.BAD_REQUEST);
        }
    }


    public List<CountryResponseDto> saveAllcountryByKey(Set<String> keys) {
        if(!AppConstants.keys.containsAll(keys)){
            throw  new ValidationException("please provide correct keys ", HttpStatus.BAD_REQUEST);

        }
        List<com.student.studentDetails.core.entity.Country> countryResponse =new ArrayList<>();
        List<CountryResponseDto> countryResponseDtos = new ArrayList<>();
        List<Country> countries =  getAllCountries();
        for (Country country : countries) {
            com.student.studentDetails.core.entity.Country country1 =   countrysRepository.findByCountryName(country.getName().getCommon());
            CountryResponseDto countryResponseDto = new CountryResponseDto();
            if(country1 == null){
                country1 =  new com.student.studentDetails.core.entity.Country();
                country1.setCountryName(country.getName().getCommon());
            }

            if(keys.contains("cca2")){
                countryResponseDto.setCca2(country.getCca2());
                country1.setCca2(country.getCca2());
            }

            if(keys.contains("cca3")){
                countryResponseDto.setCca3(country.getCca3());
                country1.setCca3(country.getCca3());
            }

            if(keys.contains("ccn3")){
                countryResponseDto.setCcn3(country.getCcn3());
                country1.setCcn3(country.getCcn3());
            }

            if(keys.contains("cioc")){
                countryResponseDto.setCioc(country.getCioc());
                country1.setCioc(country.getCioc());
            }

            if(keys.contains("independent")){
                countryResponseDto.setIndependent(country.isIndependent());
                country1.setIndependent(country.isIndependent());
            }

            if(keys.contains("landlocked")){
                countryResponseDto.setLandlocked(country.isLandlocked());
                country1.setLandlocked(country.isLandlocked());
            }

            if(keys.contains("region")){
                countryResponseDto.setRegion(country.getRegion());
                country1.setRegion(country.getRegion());
            }

            if(keys.contains("status")){
                countryResponseDto.setStatus(country.getStatus());
                country1.setStatus(country.getStatus());
            }

            if(keys.contains("subregion")){
                countryResponseDto.setSubregion(country.getSubregion());
                country1.setSubregion(country.getSubregion());
            }

            if(keys.contains("unMember")){
                countryResponseDto.setUnMember(country.isUnMember());
                country1.setUnMember(country.isUnMember());
            }
            countryResponse.add(country1);
            countryResponseDtos.add(countryResponseDto);
        }
        countrysRepository.saveAll(countryResponse);
        return countryResponseDtos;
    }

    public List<com.student.studentDetails.core.entity.Country> fetchAll() {
       return countrysRepository.findAll();
    }

    public List<CountryResponseDto>  fetchByKeyFromDb(Set<String> strings) {

        List<com.student.studentDetails.core.entity.Country> countries = fetchAll();
        List<CountryResponseDto> countryResponseDtos = new ArrayList<>();

        for (com.student.studentDetails.core.entity.Country country: countries) {
            CountryResponseDto country1 = new CountryResponseDto();

            if(strings.contains("cca2")){
                country1.setCca2(country.getCca2());
            }

            if(strings.contains("cca3")){
                country1.setCca3(country.getCca3());
            }

            if(strings.contains("ccn3")){
                country1.setCcn3(country.getCcn3());
            }

            if(strings.contains("cioc")){
                country1.setCioc(country.getCioc());
            }

            if(strings.contains("independent")){
                country1.setIndependent(country.getIndependent());
            }

            if(strings.contains("landlocked")){
                country1.setLandlocked(country.getLandlocked());
            }

            if(strings.contains("region")){
                country1.setRegion(country.getRegion());
            }

            if(strings.contains("status")){
                country1.setStatus(country.getStatus());
            }

            if(strings.contains("subregion")){
                country1.setSubregion(country.getSubregion());
            }

            if(strings.contains("unMember")){
                country1.setUnMember(country.getUnMember());
            }
            countryResponseDtos.add(country1);

        }
        return countryResponseDtos;
    }
}
