package com.example.kinopoisk_api_unofficial.service;

import com.example.kinopoisk_api_unofficial.dio.KinopoiskDto;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.repository.KinopoiskRepository;
import com.example.kinopoisk_api_unofficial.repository.CrudRepositoryFilms;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
@RequiredArgsConstructor
@Data
public class KinoService {
    private final CrudRepositoryFilms repositoryFilms;
    private final KinopoiskRepository kinopoiskRepository;

    public Film save(Film film){
        return repositoryFilms.save(film);
    }


    public List<Film> sortFilmsByYear(){
        return repositoryFilms.sortFilmByYear();
    }

    public List<Film> findAll(){
        return repositoryFilms.findAll();
    }

    public KinopoiskDto addKino(int index){
        KinopoiskDto kinopoiskDto = kinopoiskRepository.copyFilmsFromKinopoisk(index);
        if (findByFilmName(kinopoiskDto.getNameRu()).isEmpty()) {
                save(enrichFilm(kinopoiskDto));
        }
        return kinopoiskDto;
    }
    public KinopoiskDto addKino(int from, int to){
        kinopoiskRepository.copyFilmsFromKinopoisk(from, to);
        for (KinopoiskDto item: kinopoiskRepository.getKinoList()) {
            if (findByFilmName(item.getNameRu()).isEmpty()) {
                save(enrichFilm(item));
            }
        }
        return kinopoiskRepository.getKinoList().get(0);
    }

    public Optional<Film> findByFilmName(String name){
        return repositoryFilms.findByFilmName(name);
    }

    private Film enrichFilm(KinopoiskDto kinopoisk){
        return new Film(
                kinopoisk.getKinopoiskId(),
                kinopoisk.getNameRu(),
                kinopoisk.getYear(),
                kinopoisk.getRatingKinopoisk(),
                kinopoisk.getSlogan() + "\n" +
                        kinopoisk.getDescription() + "\n" +
                        kinopoisk.getShortDescription());
    }


}
