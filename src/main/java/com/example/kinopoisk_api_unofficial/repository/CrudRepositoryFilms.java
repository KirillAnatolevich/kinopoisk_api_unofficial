package com.example.kinopoisk_api_unofficial.repository;

import com.example.kinopoisk_api_unofficial.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRepositoryFilms extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {
    Film findByFilmName(String FilmName);
    Film findByFilmId(Long id);
    List<Film> findByYearGreaterThan(Integer year);
    List<Film> findByRatingGreaterThan(Double rating);
    List<Film> findByRatingLessThanEqual(Double rating);
//    Просто напоминание что можно так
//    @Query(value = "SELECT * FROM film ORDER BY year", nativeQuery = true)
//    List<Film> sortFilmByYear();
}
