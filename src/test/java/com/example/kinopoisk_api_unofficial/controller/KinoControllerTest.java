package com.example.kinopoisk_api_unofficial.controller;

import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.model.Film;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class KinoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName
            .parse("postgres:latest"));

    @DynamicPropertySource
    public static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",  postgres::getJdbcUrl);
        registry.add("spring.datasource.userName",  postgres::getUsername);
        registry.add("spring.datasource.password",  postgres::getPassword);
    }
    @Test
    public void testSaveFilms() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/v2/films/saveByType?id=1&type=COMICS_THEME"))
                .andExpect(status().is2xxSuccessful()); // проверяем, что статус запроса 200
        log.info("testSaveFilms() - true");

    }
    @Test
    public void testPageByRaitingFilms() throws Exception{
    }
    @Test
    public void testPageByYarFilms() throws Exception{
    }
    @Test
    public void testPageByNameFilms () throws Exception{
    }

    //region ADD BD


    @Test
    public void testFindAll() throws Exception{
    }

    @Test
    public void testFindByFilmName() throws Exception {
    }

    @Test
    public void testFindById() throws Exception {

    }


    @Test
    public void testFindByFilmId() throws Exception {

    }

    @Test
    public void testFindFilmDtoById() throws Exception {

    }
}

