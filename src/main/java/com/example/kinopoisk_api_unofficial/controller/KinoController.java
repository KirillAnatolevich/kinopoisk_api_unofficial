
package com.example.kinopoisk_api_unofficial.controller;

import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.service.KinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/films")
public class KinoController {
    private final KinoService kinoService;
    @GetMapping("/pageByRaitingFilms")
    public Page<Film> pageByRaitingFilms(@RequestParam Double min,
                                         @RequestParam Double max,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size){
        return kinoService.pageByRaitingFilms(min, max, PageRequest.of(page, size));
    }
    @GetMapping("/pageByAgeFilms")
    public Page<Film> pageByYarFilms(@RequestParam Integer min,
                                    @RequestParam Integer max,
                                    @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size){
        return kinoService.pageByYarFilms(min, max, PageRequest.of(page, size));
    }
    @GetMapping("/pageByNameFilms")
    public Page<Film> pageByNameFilms(@RequestParam(required = false) String name,
                                    @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size){
        return kinoService.pageByNameFilms(name, PageRequest.of(page, size));
    }

    //region ADD BD
    @PostMapping("/saveByType")
    public List<Film> saveFilms(
            @RequestParam Integer id,
            @RequestParam TypeCollections type){
        return kinoService.saveFilms(id, type);
    }

    //endregion

    //region GET BD
    @GetMapping("/all")
    public List<Film> findAll(){
        return kinoService.findAll();//.orElseThrow(() ->  new MyExceptions("bkjbkbkbjkbvk;jbh"))
    }
    @GetMapping("/ByFilmName")
    public Film findByFilmName(@RequestParam String name){
        return kinoService.findByFilmName(name);
    }
    @GetMapping("/ById")
    public Optional<Film> findById(@RequestParam Long id){
        return kinoService.findById(id);
    }
    @GetMapping("/ByFilmId")
    public Film findByFilmId(@RequestParam Long id){
        return kinoService.findByFilmId(id);
    }
    @GetMapping("/YearGreaterThan")
    public List<Film> findByYearGreaterThan(@RequestParam Integer year){
        return kinoService.findByYearGreaterThan(year);
    }
    @GetMapping("/ByRatingGreaterThan")
    public List<Film> findByRatingGreaterThan(@RequestParam Double rating){
        return kinoService.findByRatingGreaterThan(rating);
    }
    @GetMapping("/ByRatingLessThanEqual")
    public List<Film> findByRatingLessThanEqual(@RequestParam Double rating){
        return kinoService.findByRatingLessThanEqual(rating);
    }
    //endregion

    //region POST REST API
    @PostMapping("/filmDtoById")
    public Optional<FilmDto> addFilmDtoById(@RequestParam Long id){
        return kinoService.addFilmDtoById(id);
    }
    @PostMapping("/filmsByType")
    public Optional<List<FilmDto>> addListFilmsByType(
            @RequestParam Integer id,
            @RequestParam TypeCollections type){
        return kinoService.addListFilmsByType(id, type);
    }

    @PostMapping("/filmsDtoById")
    public Optional<FilmDto> findFilmDtoById(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) Long kinoId){
        return kinoService.findFilmDtoById(id, typeCollections, kinoId);
    }
    @PostMapping("/filmDtoByNameRu")
    public Optional<FilmDto> findFilmDtoByNameRu(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) String nameFilm){
        return kinoService.findFilmDtoByNameRu(id, typeCollections, nameFilm);
    }
    @PostMapping("/filmsByRatingFrom")
    public Optional<List<FilmDto>> findFilmsByRatingFrom(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) Double ratingFrom){
        return kinoService.findFilmsByRatingFrom(id, typeCollections, ratingFrom);
    }
    @PostMapping("/findFilmsByRatingToo")
    public Optional<List<FilmDto>> findFilmsByRatingToo(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) Double ratingTo){
        return kinoService.findFilmsByRatingToo(id, typeCollections, ratingTo);
    }
    //endregion
}
