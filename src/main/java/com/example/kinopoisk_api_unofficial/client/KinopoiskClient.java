package com.example.kinopoisk_api_unofficial.client;

import com.example.kinopoisk_api_unofficial.config.AppConfig;
import com.example.kinopoisk_api_unofficial.config.Config;
import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.dto.TypedFilmsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class KinopoiskClient {
    private final RestTemplate restTemplate;
    private final AppConfig config;

    public FilmDto addFindByIdFilm(Long id) {
        FilmDto kinopoiskDto = new FilmDto();
        try {
            kinopoiskDto = restTemplate
                    .exchange(
                            String.format(config.getUrlFilmsById(), id),
                            HttpMethod.GET,
                            Config.generateHttpEntity(config.getName(), config.getToken()),
                            FilmDto.class)
                    .getBody();
        }catch (HttpClientErrorException e){
            e.getStatusCode();
        }
        return kinopoiskDto;
    }

    public List<FilmDto> addListFilmsByType(int id, TypeCollections collections){
        TypedFilmsDto items = new TypedFilmsDto();
        try {
            items = restTemplate
                    .exchange(
                            String.format(config.getUrlFilmsTypedCollections(), collections, id),
                            HttpMethod.GET,
                            Config.generateHttpEntity(config.getName(), config.getToken()),
                            TypedFilmsDto.class)
                    .getBody();
        }catch (HttpClientErrorException e){
            e.getStatusCode();
        }
        assert items != null;
        return items.getItems();
    }
}
