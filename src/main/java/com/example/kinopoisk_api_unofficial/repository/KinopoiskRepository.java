package com.example.kinopoisk_api_unofficial.repository;


import com.example.kinopoisk_api_unofficial.client.KinopoiskClient;
import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.my_exception.MyExceptions;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Getter
public class KinopoiskRepository {

    @Autowired
    private KinopoiskClient kinopoiskClient;



    public Optional<FilmDto> addFilmDtoById(Long id){
        return Optional.ofNullable(kinopoiskClient.addFindByIdFilm(id));

    }

    public Optional<List<FilmDto>> addListFilmsByType(Integer id, TypeCollections typeCollections){
        return Optional.ofNullable(kinopoiskClient.addListFilmsByType(id, typeCollections));
    }

    public Optional<FilmDto> findFilmDtoById(Integer id, TypeCollections typeCollections, Long kinoId){
        return addListFilmsByType(id, typeCollections).flatMap(dtos -> dtos.stream().filter(filmDto -> Objects.equals(filmDto.getKinopoiskId(), kinoId)).findAny());
    }

    public Optional<FilmDto> findFilmDtoByNameRu(Integer id, TypeCollections typeCollections, String nameFilm){
        return addListFilmsByType(id, typeCollections).map(dtos -> dtos.stream().filter(filmDto -> filmDto.getNameRu().equals(nameFilm)).findAny()).orElse(null);
    }

    public Optional<List<FilmDto>> findFilmsByRatingFrom(Integer id, TypeCollections typeCollections, Double ratingFrom){
        return Optional.of(addListFilmsByType(id, typeCollections).get().stream().filter(filmDto -> filmDto.getRatingKinopoisk() >= ratingFrom).toList());
    }

    public Optional<List<FilmDto>> findFilmsByRatingToo(Integer id, TypeCollections typeCollections, Double ratingTo){
        return Optional.of(addListFilmsByType(id, typeCollections).get().stream().filter(filmDto -> filmDto.getRatingKinopoisk() <= ratingTo).toList());
    }
}
