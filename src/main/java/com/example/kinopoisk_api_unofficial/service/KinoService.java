package com.example.kinopoisk_api_unofficial.service;

import com.example.kinopoisk_api_unofficial.client.KinopoiskClient;
import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.filters.FilmSpecification;
import com.example.kinopoisk_api_unofficial.mupstruct.MappingFulms;
import com.example.kinopoisk_api_unofficial.my_exception.CheckingTheArgumentExceptions;
import com.example.kinopoisk_api_unofficial.repository.CrudRepositoryFilms;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KinoService {
    //private final Config config;
    private final CrudRepositoryFilms repositoryFilms;
    private final MappingFulms mappingFulmsImpl;
    private final KinopoiskClient kinopoiskClient;

    public Page<Film> pageByRaitingFilms(Double min, Double max, Pageable pageable){
        Specification<Film> specification = Specification.where(FilmSpecification.ratingBitWin(min, max));
        return repositoryFilms.findAll(specification, pageable);
    }

    public Page<Film> pageByYarFilms(Integer min, Integer max, Pageable pageable){
        Specification<Film> specification = Specification.where(FilmSpecification.yearBitWin(min, max));
        return repositoryFilms.findAll(specification, pageable);
    }

    public Page<Film> pageByNameFilms(String name, Pageable pageable){
        Specification<Film> specification = Specification.where(FilmSpecification.nameBitWin(name));
        return repositoryFilms.findAll(specification, pageable);
    }

    public List<Film> saveFilms(Integer id, TypeCollections type){
        return mappingFulmsImpl
                .enrichFilms(addListFilmsByType(id, type).get())
                .stream()
                .filter(film -> findByFilmName(film.getFilmName()) == null)
                .map(this::save).collect(Collectors.toList());
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
    public Film findByFilmName(String name){
        return repositoryFilms.findByFilmName(name);
    }
    public Film findByFilmId(Long id){
        return repositoryFilms.findByFilmId(id);
    }
    public List<Film> findByYearGreaterThan(Integer year){
        return repositoryFilms.findByYearGreaterThan(year);
    }
    public List<Film> findByRatingGreaterThan(Double rating){
        return repositoryFilms.findByRatingGreaterThan(rating);
    }
    public List<Film> findByRatingLessThanEqual(Double rating){
        return repositoryFilms.findByRatingLessThanEqual(rating);
    }
    //endregion
    //region KR
    public Optional<FilmDto> addFilmDtoById(Long id){
        if (id <= 0){
            throw new CheckingTheArgumentExceptions("ID не может быть отрицательным");
        }
        return Optional.ofNullable(kinopoiskClient.addFindByIdFilm(id));
    }
    public Optional<List<FilmDto>> addListFilmsByType(Integer id, TypeCollections typeCollections){
        if (id<0 || id> 10){
            throw new  CheckingTheArgumentExceptions().invalidNumber(1, 10);
        }
        Optional<List<FilmDto>> result = Optional.ofNullable(kinopoiskClient.addListFilmsByType(id, typeCollections));
        if (result.isPresent()){
            return result;
        }
        throw new  CheckingTheArgumentExceptions();
    }
    public Optional<FilmDto> findFilmDtoById(Integer id, TypeCollections typeCollections, Long kinoId){
        if (id<0 || id> 10){
            throw new  CheckingTheArgumentExceptions().invalidNumber(1, 10);
        }
        return addListFilmsByType(id, typeCollections).flatMap(dtos -> dtos.stream().filter(filmDto -> Objects.equals(filmDto.getKinopoiskId(), kinoId)).findAny());
    }
    public Optional<FilmDto> findFilmDtoByNameRu(Integer id, TypeCollections typeCollections, String nameFilm){
        if (id<0 || id> 10){
            throw new  CheckingTheArgumentExceptions().invalidNumber(1, 10);
        }
        return addListFilmsByType(id, typeCollections).map(dtos -> dtos.stream().filter(filmDto -> filmDto.getNameRu().equals(nameFilm)).findAny()).orElse(null);
    }

    public Optional<List<FilmDto>> findFilmsByRatingFrom(Integer id, TypeCollections typeCollections, Double ratingFrom){
        if (id<0 || id> 10){
            throw new  CheckingTheArgumentExceptions().invalidNumber(1, 10);
        }
        return Optional.of(addListFilmsByType(id, typeCollections).get().stream().filter(filmDto -> filmDto.getRatingKinopoisk() >= ratingFrom).toList());
    }
    public Optional<List<FilmDto>> findFilmsByRatingToo(Integer id, TypeCollections typeCollections, Double ratingTo){
        if (id<0 || id> 10){
            throw new  CheckingTheArgumentExceptions().invalidNumber(1, 10);
        }
        return Optional.of(addListFilmsByType(id, typeCollections).get().stream().filter(filmDto -> filmDto.getRatingKinopoisk() <= ratingTo).toList());
    }
    //endregion
}
