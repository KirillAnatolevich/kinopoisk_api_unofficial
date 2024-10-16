package com.example.kinopoisk_api_unofficial.mupstruct;

import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MappingFulms {
    public Film enrichFilm(FilmDto kinopoisk){
        return new Film(
                kinopoisk.getKinopoiskId(),
                kinopoisk.getNameRu(),
                kinopoisk.getYear(),
                kinopoisk.getRatingKinopoisk(),
                kinopoisk.getSlogan() + "\n" +
                        kinopoisk.getDescription() + "\n" +
                        kinopoisk.getShortDescription());
    }

    public List<Film> enrichFilms(List<FilmDto> filmDtos){
        return filmDtos.stream().map(this::enrichFilm).collect(Collectors.toList());
    }
}
