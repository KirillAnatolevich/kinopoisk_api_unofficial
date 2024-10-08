package com.example.kinopoisk_api_unofficial.dao;

import com.example.kinopoisk_api_unofficial.dio.KinopoiskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KinopoiskDao {
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();
    private HttpEntity<String> entity;


    public void generateHttpEntity(){
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(HEADER_NAME, HEADER_VALUE);
        entity = new HttpEntity<>(httpHeaders);
    }

    public KinopoiskDto addFindByIdFilm(int id) {
        KinopoiskDto kinopoiskDto = new KinopoiskDto();
        try {
            kinopoiskDto = restTemplate.exchange(URL, HttpMethod.GET, entity, KinopoiskDto.class, id).getBody();
        }catch (HttpClientErrorException e){
            System.out.println(e.getStatusCode());
        }
        return kinopoiskDto;
    }
}
