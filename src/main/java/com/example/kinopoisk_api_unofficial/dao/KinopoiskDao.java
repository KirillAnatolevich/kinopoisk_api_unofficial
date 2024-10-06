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
    private final String URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/{id}";
    private final String HEADER_NAME = "X-API-KEY";
    private final String HEADER_VALUE =  "894ac91a-9cd4-47c0-b468-bd797d07d1e1";

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
