package com.example.kinopoisk_api_unofficial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    public static RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    @Scope("Prototype")
    public static HttpEntity<String> generateHttpEntity(String name, String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(name, token);
        return new HttpEntity<>(httpHeaders);
    }

}
