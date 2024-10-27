package com.example.kinopoisk_api_unofficial.repository;

import com.example.kinopoisk_api_unofficial.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRepositoryFilms extends JpaRepository<Film, Long>{
    Optional<Film> findByFilmName(String FilmName);
    Optional<Film> findByFilmId(Long id);
    Optional<List<Film>> findByYearGreaterThan(Integer year);
    Optional<List<Film>> findByRatingGreaterThan(Double rating);
    Optional<List<Film>> findByRatingLessThanEqual(Double rating);
//    Просто напоминание что можно так
//    @Query(value = "SELECT * FROM film ORDER BY year", nativeQuery = true)
//    List<Film> sortFilmByYear();
}
