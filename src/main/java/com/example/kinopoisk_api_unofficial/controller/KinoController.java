package com.example.kinopoisk_api_unofficial.controller;

import com.example.kinopoisk_api_unofficial.dto.FilmDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.service.KinoService;
import com.example.kinopoisk_api_unofficial.mupstruct.MappingFulms;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/films")
public class KinoController {
    private final KinoService kinoService;

    //region ADD BD
    @PostMapping("/saveByType")
    public Optional<List<Film>> saveFilms(
            @RequestParam Integer id,
            @RequestParam TypeCollections type){
        return kinoService.saveFilms(id, type);
    }
    //endregion

    //region GET BD
    @GetMapping("/all")
    public Optional<List<Film>> findAll(){
        return Optional.of(kinoService.findAll());
    }
    @GetMapping("/ByFilmName")
    public Optional<Film> findByFilmName(@RequestParam String name){
        return kinoService.findByFilmName(name);
    }
    @GetMapping("/ById")
    public Optional<Film> findById(@RequestParam Long id){
        return kinoService.findById(id);
    }
    @GetMapping("/ByFilmId")
    public Optional<Film> findByFilmId(@RequestParam Long id){
        return kinoService.findByFilmId(id);
    }
    @GetMapping("/YearGreaterThan")
    public Optional<List<Film>> findByYearGreaterThan(@RequestParam Integer year){
        return kinoService.findByYearGreaterThan(year);
    }
    @GetMapping("/ByRatingGreaterThan")
    public Optional<List<Film>> findByRatingGreaterThan(@RequestParam Double rating){
        return kinoService.findByRatingGreaterThan(rating);
    }
    @GetMapping("/ByRatingLessThanEqual")
    public Optional<List<Film>> findByRatingLessThanEqual(@RequestParam Double rating){
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

//    @GetMapping("api/v2/film/{id}/{collections}")
//    public FilmDto getFilms(@PathVariable Integer id, @PathVariable TypeCollections collections){// @RequestParam(required = false) Integer year
//        return kinopoiskClient.addListFilmsByType(id, collections).get(0);
//    }

//    @PostMapping("/api/v2/film")
//    public FilmDto addFilm(@PathVariable Integer index){
//        return kinoService.addKino(index);
//    }
//
//    @PostMapping("/api/v2/films")
//    public String addFilms(@PathVariable Integer psge, @PathVariable TypeCollections collections){
//        return kinoService.addKino(psge, collections);
//    }
//
////    @GetMapping("/year")
////    public List<Film> getFilms(@RequestParam(required = false) String year){
////        return kinoService.getFilms(year);
////    }
//
////    @GetMapping("/{name}")
////    public ResponseEntity<Film> findByName(@PathVariable String name){
////        Optional<Film> film = kinoService.findByFilmName(name);
////        return film.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
////    }
//
//    @GetMapping("/api/v2/films")
//    public List<Film> sortFilmByYar(){
//        return kinoService.sortFilmsByYear();
//    }
}
