package com.example.kinopoisk_api_unofficial.service;

import com.example.kinopoisk_api_unofficial.client.KinopoiskClient;
import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.mupstruct.MappingFulms;
import com.example.kinopoisk_api_unofficial.repository.CrudRepositoryFilms;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KinoServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(KinoServiceTest.class);
    @Mock
    private CrudRepositoryFilms repositoryFilms;
    @Mock
    private MappingFulms mappingFulmsImpl;
    @Mock
    private KinopoiskClient kinopoiskClient;
    @InjectMocks
    private KinoService kinoService;

    @Test
    void saveFilms() {
        // LIST DTO
        FilmDto filmDto1 = new FilmDto(1L, "kinopoiskHDId1", "nameRu1", 3000, 1.0, "slogan1", "description1", "shortDescription1");
        FilmDto filmDto2 = new FilmDto(2L, "kinopoiskHDId2", "nameRu2", 4000, 2.0, "slogan2", "description2", "shortDescription2");
        List<FilmDto> filmsDto = new ArrayList<>();
        filmsDto.add(filmDto1);
        filmsDto.add(filmDto2);
        Film film1 = new Film(1L, 1L, "nameRu1", 3000, 1.0, "slogan1\ndescription1\nshortDescription1");
        Film film2 = new Film(2L, 2L, "nameRu2", 4000, 2.0, "slogan2\ndescription2\nshortDescription2");

        when(kinopoiskClient.addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME)).thenReturn(filmsDto);
        when(mappingFulmsImpl.enrichFilms(filmsDto)).thenReturn(List.of(film1, film2));
        when(repositoryFilms.findByFilmName("nameRu1")).thenReturn(film1);
        when(repositoryFilms.findByFilmName("nameRu2")).thenReturn(null);
        List<Film> result = kinoService.saveFilms(1, TypeCollections.CATASTROPHE_THEME);

        logger.info("We check that the data is not null");
        assertTrue(result != null);
        logger.info("not null\n");

        logger.info("Checking for the correct number of saved items");
        assertEquals(1, result.size());
        logger.info("Correctly stores unique values\n");

        logger.info("Checking for the correct number of method calls");
        verify(kinopoiskClient, times(1)).addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME);
        verify(mappingFulmsImpl, times(1)).enrichFilms(filmsDto);
        verify(repositoryFilms, times(1)).save(any(Film.class));
        logger.info("The check for the number of method calls was successful\n");

    }

    @Test
    void save() {
        Film film = new Film(1L,
                1L,
                "filmName",
                3000,
                10.0,
                "description");

        when(repositoryFilms.save(film)).thenReturn(film);
        Object obj = repositoryFilms.save(film);

        logger.info("Object is not null: {}\n", obj);
        assertNotNull(obj, "Object should not be null");
        logger.info("Object is not null: {}\n", obj);

        logger.info("Checking for the correct answer");
        assertEquals(film, obj);
        logger.info("the object: {} under test is equivalent to the result: {}\n", film, obj);

        logger.info("Checking for the correct number of calls to the method under test");
        verify(repositoryFilms, times(1)).save(film);
        logger.info("the method was called the required number of times");
    }

    @Test
    void findAll() {
        Film film1 = new Film(1L, 1L, "nameRu1", 3000, 1.0, "slogan1\ndescription1\nshortDescription1");
        Film film2 = new Film(2L, 2L, "nameRu2", 4000, 2.0, "slogan2\ndescription2\nshortDescription2");
        List<Film> films = List.of(film1, film2);

        when(repositoryFilms.findAll()).thenReturn(films);

        Object obj = kinoService.findAll();

        logger.info("Checking that the method under test does not return null, that it returns the correct value, and that it is called the right number of times");
        assertNotNull(obj);
        assertEquals(films, obj);
        verify(repositoryFilms, times(1)).findAll();
        logger.info("the method findAll() works correctly");
    }

    @Test
    void findById() {
        Film film1 = new Film(1L, 1L, "nameRu1", 3000, 1.0, "slogan1\ndescription1\nshortDescription1");

        when(repositoryFilms.findById(1L)).thenReturn(Optional.of(film1));
        Optional<Film> result = kinoService.findById(1L);

        logger.info("Checking that the method under test does not return null, that it returns the correct value, and that it is called the right number of times");
        assertNotNull(result);
        assertEquals(Optional.of(film1), result);
        verify(repositoryFilms, times(1)).findById(1L);
        logger.info("the method findAll() works correctly");
    }

    @Test
    void findByFilmName() {
        Film film1 = new Film(1L, 1L, "nameRu1", 3000, 1.0, "slogan1\ndescription1\nshortDescription1");

        when(repositoryFilms.findByFilmName("nameRu1")).thenReturn(film1);
        Film result = kinoService.findByFilmName("nameRu1");

        logger.info("Checking that the method under test does not return null, that it returns the correct value, and that it is called the right number of times");
        assertNotNull(result);
        assertEquals(film1, result);
        verify(repositoryFilms, times(1)).findByFilmName("nameRu1");
        logger.info("the method findAll() works correctly");
    }

    @Test
    void findByYearGreaterThan() {
        Film film1 = new Film(1L, 1L, "nameRu1", 3000, 1.0, "slogan1\ndescription1\nshortDescription1");

        when(repositoryFilms.findByFilmName("nameRu1")).thenReturn(film1);
        Film result = kinoService.findByFilmName("nameRu1");

        logger.info("Checking that the method under test does not return null, that it returns the correct value, and that it is called the right number of times");
        assertNotNull(result);
        assertEquals(film1, result);
        verify(repositoryFilms, times(1)).findByFilmName("nameRu1");
        logger.info("the method findAll() works correctly");
    }

    @Test
    void findByRatingGreaterThan() {
        Film film1 = new Film(1L, 1L, "nameRu1", 3000, 1.0, "slogan1\ndescription1\nshortDescription1");
        Film film2 = new Film(2L, 2L, "nameRu2", 4000, 2.0, "slogan2\ndescription2\nshortDescription2");

        when(repositoryFilms.findByRatingGreaterThan(1.0)).thenReturn(List.of(film1, film2));
        List<Film> result = kinoService.findByRatingGreaterThan(1.0);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repositoryFilms, times(1)).findByRatingGreaterThan(1.0);
    }

    @Test
    void findByRatingLessThanEqual() {
        Film film1 = new Film(1L, 1L, "nameRu1", 3000, 1.0, "slogan1\ndescription1\nshortDescription1");
        Film film2 = new Film(2L, 2L, "nameRu2", 4000, 2.0, "slogan2\ndescription2\nshortDescription2");

        when(repositoryFilms.findByRatingLessThanEqual(2.0)).thenReturn(List.of(film1, film2));
        List<Film> result = kinoService.findByRatingLessThanEqual(2.0);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repositoryFilms, times(1)).findByRatingLessThanEqual(2.0);
    }

    @Test
    void addFilmDtoById() {
        FilmDto filmDto = new FilmDto(1L, "kinopoiskHDId1", "nameRu1", 3000, 1.0, "slogan1", "description1", "shortDescription1");

        when(kinopoiskClient.addFindByIdFilm(1L)).thenReturn(filmDto);
        Optional<FilmDto> result = kinoService.addFilmDtoById(1L);

        assertTrue(result.isPresent());
        assertEquals(result.get(), filmDto);
        verify(kinopoiskClient, times(1)).addFindByIdFilm(1L);
    }

    @Test
    void addListFilmsByType() {
        FilmDto filmDto1 = new FilmDto(1L, "kinopoiskHDId1", "nameRu1", 3000, 1.0, "slogan1", "description1", "shortDescription1");
        FilmDto filmDto2 = new FilmDto(2L, "kinopoiskHDId2", "nameRu2", 4000, 2.0, "slogan2", "description2", "shortDescription2");
        List<FilmDto> filmsDto = List.of(filmDto1, filmDto2);

        when(kinopoiskClient.addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME)).thenReturn(filmsDto);
        Optional<List<FilmDto>> result = kinoService.addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME);

        assertTrue(result.isPresent());
        assertEquals(result.get().size(), 2);
        verify(kinopoiskClient, times(1)).addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME);
    }

    @Test
    void findFilmDtoById() {
        FilmDto filmDto1 = new FilmDto(1L, "kinopoiskHDId1", "nameRu1", 3000, 1.0, "slogan1", "description1", "shortDescription1");
        FilmDto filmDto2 = new FilmDto(2L, "kinopoiskHDId2", "nameRu2", 4000, 2.0, "slogan2", "description2", "shortDescription2");
        List<FilmDto> filmsDto = List.of(filmDto1, filmDto2);

        when(kinopoiskClient.addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME)).thenReturn(filmsDto);
        Optional<FilmDto> result = kinoService.findFilmDtoById(1, TypeCollections.CATASTROPHE_THEME, 1L);

        assertTrue(result.isPresent());
        assertEquals(filmDto1, result.get());
        verify(kinopoiskClient, times(1)).addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME);
    }

    @Test
    void findFilmDtoByNameRu() {
        FilmDto filmDto1 = new FilmDto(1L, "kinopoiskHDId1", "nameRu1", 3000, 1.0, "slogan1", "description1", "shortDescription1");
        FilmDto filmDto2 = new FilmDto(2L, "kinopoiskHDId2", "nameRu2", 4000, 2.0, "slogan2", "description2", "shortDescription2");
        List<FilmDto> filmsDto = List.of(filmDto1, filmDto2);

        when(kinopoiskClient.addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME)).thenReturn(filmsDto);
        Optional<FilmDto> result = kinoService.findFilmDtoByNameRu(1, TypeCollections.CATASTROPHE_THEME, "nameRu1");

        assertTrue(result.isPresent());
        assertEquals(filmDto1, result.get());
        verify(kinopoiskClient, times(1)).addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME);
    }

    @Test
    void findFilmsByRatingFrom() {
        FilmDto filmDto1 = new FilmDto(1L, "kinopoiskHDId1", "nameRu1", 3000, 1.0, "slogan1", "description1", "shortDescription1");
        FilmDto filmDto2 = new FilmDto(2L, "kinopoiskHDId2", "nameRu2", 4000, 2.0, "slogan2", "description2", "shortDescription2");
        List<FilmDto> filmsDto = List.of(filmDto1, filmDto2);

        when(kinopoiskClient.addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME)).thenReturn(filmsDto);
        Optional<List<FilmDto>> result = kinoService.findFilmsByRatingFrom(1, TypeCollections.CATASTROPHE_THEME, 2.0);

        assertTrue(result.isPresent());
        assertEquals(result.get().get(0), filmDto2);
        verify(kinopoiskClient, times(1)).addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME);
    }

    @Test
    void findFilmsByRatingToo() {
        FilmDto filmDto1 = new FilmDto(1L, "kinopoiskHDId1", "nameRu1", 3000, 1.0, "slogan1", "description1", "shortDescription1");
        FilmDto filmDto2 = new FilmDto(2L, "kinopoiskHDId2", "nameRu2", 4000, 2.0, "slogan2", "description2", "shortDescription2");
        List<FilmDto> filmsDto = List.of(filmDto1, filmDto2);

        when(kinopoiskClient.addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME)).thenReturn(filmsDto);
        Optional<List<FilmDto>> result = kinoService.findFilmsByRatingToo(1, TypeCollections.CATASTROPHE_THEME, 1.0);

        assertTrue(result.isPresent());
        assertEquals(result.get().get(0), filmDto1);
        verify(kinopoiskClient, times(1)).addListFilmsByType(1, TypeCollections.CATASTROPHE_THEME);
    }
}