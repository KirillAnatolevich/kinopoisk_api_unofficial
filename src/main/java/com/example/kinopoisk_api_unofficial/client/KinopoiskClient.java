package com.example.kinopoisk_api_unofficial.client;

import com.example.kinopoisk_api_unofficial.config.Config;
import com.example.kinopoisk_api_unofficial.dto.KinoDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.dto.TypedFilmsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpMethod;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;


import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j

public class KinopoiskClient {
    @Value("${myapp.api.urlFilmsById}")
    private String urlFilmsById;
    @Value("${myapp.api.urlFilmsTypedCollections}")
    private String urlFilmsTypedCollections;
    @Value("${myapp.api.name}")
    private String name;
    @Value("${myapp.api.token}")
    private String token;

    public KinoDto addFindByIdFilm(Long id) {
        KinoDto kinopoiskDto = new KinoDto();
        try {
            kinopoiskDto = Config.restTemplate()
                    .exchange(
                            String.format(urlFilmsById, id),
                            HttpMethod.GET,
                            Config.generateHttpEntity(name, token),
                            KinoDto.class)
                    .getBody();
        }catch (HttpClientErrorException e) {
            log.error("Error fetching film with id {}: {}", id, e.getStatusCode());
            e.getStatusCode();
        }
        return kinopoiskDto;
    }
    public List<KinoDto> addListFilmsByType(int id, TypeCollections collections){
        TypedFilmsDto items = new TypedFilmsDto();
        try {
            items = Config.restTemplate()
                    .exchange(
                            String.format(urlFilmsTypedCollections, collections, id),
                            HttpMethod.GET,
                            Config.generateHttpEntity(name, token),
                            TypedFilmsDto.class)
                    .getBody();
        }catch (HttpClientErrorException e){
            log.error("Error fetching film with id {}: {}", id, e.getStatusCode());
            e.getStatusCode();
        }
        assert items != null;
        return items.getItems();
    }
}
