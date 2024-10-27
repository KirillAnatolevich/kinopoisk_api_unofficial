package com.example.kinopoisk_api_unofficial.controller;


import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.service.KinoService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(initializers = {KinoControllerTest.Initializer.class})
public class KinoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private KinoService kinoService;

    @BeforeEach
    public void setUp() {
        // Настройка моков для KinoService
        FilmDto filmDto = new FilmDto(); // Заполните поля по необходимости
//        filmDto.setId(1L); // Пример заполнения
//        filmDto.setName("Example Film");

//        when(kinoService.findFilmById(anyLong())).thenReturn(Optional.of(filmDto));
//        when(kinoService.findFilmsByType(anyInt(), Mockito.any())).thenReturn(List.of(filmDto));
    }
//    @Container
//    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName
//            .parse("postgres:latest"));
//
//        @DynamicPropertySource
//    public static void configureProperties(DynamicPropertyRegistry registry){
//        registry.add("spring:datasource:url",  postgres::getJdbcUrl);
//        registry.add("spring:datasource:userName",  postgres::getUsername);
//        registry.add("spring:datasource:password",  postgres::getPassword);
//        registry.add("spring:jpa:generate:ddl",  () -> true);
//    }

    @Test
    @SneakyThrows
    public void testSaveFilms() throws Exception {
            mvc.perform(post("http://localhost:8080/api/v2/films/saveByType?id=1&type=COMICS_THEME"))
                    .andExpect(status().is2xxSuccessful()); // проверяем, что статус запроса 200
    }

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

