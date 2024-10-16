package com.example.kinopoisk_api_unofficial.service;

import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.repository.CrudRepositoryFilms;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class KinoServiceTest {
    @Mock
    private CrudRepositoryFilms crudRepositoryFilms;
    @InjectMocks
    private KinoService kinoService;
    @Test
    void saveFilms() {
    }

    @Test
    void save() {
        Film film = new Film(1L,    1L, "test", 1000, 5.0, "test");

        Film is = kinoService.save(film);
        Assertions.assertEquals(film, is);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByFilmName() {
        kinoService.findByFilmName("");
    }

    @Test
    void findByFilmId() {
    }

    @Test
    void findByYearGreaterThan() {
    }

    @Test
    void findByRatingGreaterThan() {
    }

    @Test
    void findByRatingLessThanEqual() {
    }

    @Test
    void addFilmDtoById() {
    }

    @Test
    void addListFilmsByType() {
    }

    @Test
    void findFilmDtoById() {
    }

    @Test
    void findFilmDtoByNameRu() {
    }

    @Test
    void findFilmsByRatingFrom() {
    }

    @Test
    void findFilmsByRatingToo() {
    }
}