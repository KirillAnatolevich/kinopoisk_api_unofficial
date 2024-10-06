package com.example.kinopoisk_api_unofficial.repository;

import com.example.kinopoisk_api_unofficial.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRepositoryFilms extends JpaRepository<Film, Long>{
    Optional<Film> findByFilmName(String FilmName);
    @Query(value = "SELECT * FROM film ORDER BY year", nativeQuery = true)
    List<Film> sortFilmByYear();
}
