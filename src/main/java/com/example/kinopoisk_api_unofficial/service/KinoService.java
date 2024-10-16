package com.example.kinopoisk_api_unofficial.service;

import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.mupstruct.MappingFulms;
import com.example.kinopoisk_api_unofficial.repository.CrudRepositoryFilms;
import com.example.kinopoisk_api_unofficial.repository.KinopoiskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class KinoService {
    private final CrudRepositoryFilms repositoryFilms;
    private final KinopoiskRepository kinopoiskRepository;
    private final MappingFulms mappingFulms;


    public Optional<List<Film>> saveFilms(Integer id, TypeCollections type){
        return Optional.of(mappingFulms
                .enrichFilms(addListFilmsByType(id, type).get())
                .stream()
                .filter(film -> findByFilmName(film.getFilmName()).isEmpty())
                .map(this::save).collect(Collectors.toList()));
    }

    //region CR
    public Film save(Film film){
        return repositoryFilms.save(film);
    }
    public List<Film> findAll(){
        return repositoryFilms.findAll();
    }
    public Optional<Film> findById(Long id){
        return repositoryFilms.findById(id);
    }
    public Optional<Film> findByFilmName(String name){
        return repositoryFilms.findByFilmName(name);
    }
    public Optional<Film> findByFilmId(Long id){
        return repositoryFilms.findByFilmId(id);
    }
    public Optional<List<Film>> findByYearGreaterThan(Integer year){
        return repositoryFilms.findByYearGreaterThan(year);
    }
    public Optional<List<Film>> findByRatingGreaterThan(Double rating){
        return repositoryFilms.findByRatingGreaterThan(rating);
    }
    public Optional<List<Film>> findByRatingLessThanEqual(Double rating){
        return repositoryFilms.findByRatingLessThanEqual(rating);
    }
    //endregion
    //region KR
    public Optional<FilmDto> addFilmDtoById(Long id){
        return kinopoiskRepository.addFilmDtoById(id);
    }

    public Optional<List<FilmDto>> addListFilmsByType(Integer id, TypeCollections typeCollections){
        return kinopoiskRepository.addListFilmsByType(id, typeCollections);
    }

    public Optional<FilmDto> findFilmDtoById(Integer id, TypeCollections typeCollections, Long kinoId){
        return kinopoiskRepository.findFilmDtoById(id, typeCollections, kinoId);
    }
    public Optional<FilmDto> findFilmDtoByNameRu(Integer id, TypeCollections typeCollections, String nameFilm){
        return kinopoiskRepository.findFilmDtoByNameRu(id, typeCollections, nameFilm);
    }
    public Optional<List<FilmDto>> findFilmsByRatingFrom(Integer id, TypeCollections typeCollections, Double ratingFrom){
        return kinopoiskRepository.findFilmsByRatingFrom(id, typeCollections, ratingFrom);
    }
    public Optional<List<FilmDto>> findFilmsByRatingToo(Integer id, TypeCollections typeCollections, Double ratingTo){
        return kinopoiskRepository.findFilmsByRatingToo(id, typeCollections, ratingTo);
    }

    //endregion

//    public List<FilmDto> getKinopoisk(int id, TypeCollections collections){
//        return kinopoiskClient.addListFilmsByType(id, collections);
//    }

//    public List<Film> sortFilmsByYear(){
//        return repositoryFilms.sortFilmByYear();
//    }


//    public FilmDto addKino(int index){
//        FilmDto kinopoiskDto = kinopoiskClient.addFindByIdFilm(index);
//        if (findByFilmName(kinopoiskDto.getNameRu()).isEmpty()) {
//                save(mappingFulms.enrichFilm(kinopoiskDto));
//        }
//        return kinopoiskDto;
//    }
//
//    public String addKino(int from, TypeCollections collections){
//        for (FilmDto item: kinopoiskClient.addListFilmsByType(from, collections)) {
//            if (findByFilmName(item.getNameRu()).isEmpty()) {
//                save(mappingFulms.enrichFilm(item));
//            }
//        }
//        return "addFilms";
//    }
}
