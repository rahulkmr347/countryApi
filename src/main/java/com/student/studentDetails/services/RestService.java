package com.student.studentDetails.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.studentDetails.dto.Country;
import com.student.studentDetails.exceptionHandler.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Service
public class RestService {

    @Value("${country.allDetailsUrl}" )
    private String allDetailUrl;

    @Value("${country.byNameUrl}")
    private String byNameUrl;

    public ResponseEntity<String> getAllCountries(){

        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.exchange(allDetailUrl, HttpMethod.GET, null, String.class);
        } catch (HttpServerErrorException e){
            throw new HttpServerErrorException( HttpStatus.INTERNAL_SERVER_ERROR, "server is not working now");
        } catch (HttpClientErrorException e){
            throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY, "There is some issue at client side");
        }
    }

    public List<Country> getCountryDetailsByName(String name) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(byNameUrl).path(name);
            ResponseEntity<String> exchange = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, String.class);
            return new ObjectMapper().readValue(exchange.getBody(), new TypeReference<List<Country>>() {});
        } catch (JsonProcessingException e) {
            throw new ValidationException("Not able to parse",HttpStatus.BAD_REQUEST);
        } catch (HttpServerErrorException e){
            throw new HttpServerErrorException( HttpStatus.INTERNAL_SERVER_ERROR, "server is not working now");
        } catch (HttpClientErrorException e){
            throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY, "There is some issue at client side");
        }
    }
}
