package com.example.kinopoisk_api_unofficial.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppConfig {
    @Value("${myapp.api.urlFilmsById}")
    private String urlFilmsById;
    @Value("${myapp.api.urlFilmsTypedCollections}")
    private String urlFilmsTypedCollections;
    @Value("${myapp.api.name}")
    private String name;
    @Value("${myapp.api.token}")
    private String token;
}































