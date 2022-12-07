package com.student.studentDetails.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.studentDetails.constants.AppConstants;
import com.student.studentDetails.core.entity.CountryDetail;
import com.student.studentDetails.core.repository.CountryDetailRepository;
import com.student.studentDetails.dto.Country;
import com.student.studentDetails.exceptionHandler.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CountryDetailsService {

    @Autowired
    private CountryDetailRepository countryDetailRepository;

    @Autowired
    private RestService restService;


    public List<CountryDetail>  saveAllcountryByKey(Set<String> keys) {
        if(!AppConstants.keys.containsAll(keys)){
            throw  new ValidationException("please provide correct keys ", HttpStatus.BAD_REQUEST);

        }
        countryDetailRepository.deleteByKey(keys);
        List<CountryDetail> countryDetails = new ArrayList<>();
        try {
            List<Country> countries = new ObjectMapper().readValue(restService.getAllCountries().getBody(), new TypeReference<List<Country>>() {});
            countries.forEach(country -> {
                        if(keys.contains("cca2")){
                            countryDetails.add(new CountryDetail("cca2", country.getCca2()));
                        }

                        if(keys.contains("cca3")){
                            countryDetails.add(new CountryDetail("cca3", country.getCca3()));
                        }

                        if(keys.contains("ccn3")){
                            countryDetails.add(new CountryDetail("ccn3", country.getCcn3()));
                        }

                        if(keys.contains("cioc")){
                            countryDetails.add(new CountryDetail("cioc", country.getCioc()));
                        }

                        if(keys.contains("independent")){
                            countryDetails.add(new CountryDetail("independent", String.valueOf(country.isIndependent())));
                        }

                        if(keys.contains("landlocked")){
                            countryDetails.add(new CountryDetail("landlocked", String.valueOf(country.isLandlocked())));
                        }

                        if(keys.contains("region")){
                            countryDetails.add(new CountryDetail("region", country.getRegion()));
                        }

                        if(keys.contains("status")){
                            countryDetails.add(new CountryDetail("status", country.getStatus()));
                        }

                        if(keys.contains("subregion")){
                            countryDetails.add(new CountryDetail("subregion", country.getSubregion()));
                        }

                        if(keys.contains("unMember")){
                            countryDetails.add(new CountryDetail("unMember", String.valueOf(country.isUnMember())));
                        }

                    });
            countryDetailRepository.saveAll(countryDetails);

        } catch (JsonProcessingException e) {
            throw new ValidationException("Wrong json", HttpStatus.BAD_REQUEST);
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException( HttpStatus.INTERNAL_SERVER_ERROR, "server is not working now");
        } catch (HttpClientErrorException e){
            throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY, "There is some issue at client side");
        }
        return countryDetails;
    }

}
