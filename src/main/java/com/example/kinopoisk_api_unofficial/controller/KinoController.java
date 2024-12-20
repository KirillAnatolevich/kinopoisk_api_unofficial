
package com.example.kinopoisk_api_unofficial.controller;

import com.example.kinopoisk_api_unofficial.dto.KinoDto;
import com.example.kinopoisk_api_unofficial.dto.TypeCollections;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.service.KinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/films")
public class KinoController {
    private final KinoService kinoService;
    @GetMapping("pageFilmsDTO")
    public Page<Film> pageFilmsFilterDTO(@RequestParam(defaultValue = "0.0") Double minRating,
                                         @RequestParam(defaultValue = "10.0") Double maxRating,
                                         @RequestParam(defaultValue = "1900") Integer minYear,
                                         @RequestParam(defaultValue = "2024") Integer maxYear,
                                         @RequestParam(required = false, defaultValue = "") String name,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size){
        return kinoService.pageFilmsFilterDTO(minRating, maxRating, minYear, maxYear, name, PageRequest.of(page, size));
    }
    @GetMapping("/pageFilms")
    public Page<Film> pageFilms(@RequestParam Double minRating,
                                @RequestParam Double maxRating,
                                @RequestParam Integer minYear,
                                @RequestParam Integer maxYear,
                                @RequestParam(required = false) String name,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "10") Integer size){
        return kinoService.pageFilms(minRating, maxRating, minYear, maxYear, name, PageRequest.of(page, size));
    }
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
    public ResponseEntity<KinoDto> addFilmDtoById(@RequestParam Long id){
        return kinoService.addFilmDtoById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/filmsByType")
    public ResponseEntity<List<KinoDto>> addListFilmsByType(
            @RequestParam Integer id,
            @RequestParam TypeCollections type){
        return kinoService.addListFilmsByType(id, type).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/filmsDtoById")
    public KinoDto findFilmDtoById(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) Long kinoId){
        return kinoService.findFilmDtoById(id, typeCollections, kinoId).get();
    }
    @PostMapping("/filmDtoByNameRu")
    public KinoDto findFilmDtoByNameRu(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) String nameFilm){
        return kinoService.findFilmDtoByNameRu(id, typeCollections, nameFilm).get();
    }
    @PostMapping("/filmsByRatingFrom")
    public List<KinoDto> findFilmsByRatingFrom(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) Double ratingFrom){
        return kinoService.findFilmsByRatingFrom(id, typeCollections, ratingFrom).get();
    }
    @PostMapping("/findFilmsByRatingToo")
    public List<KinoDto> findFilmsByRatingToo(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) TypeCollections typeCollections,
            @RequestParam(required = false) Double ratingTo){
        return kinoService.findFilmsByRatingToo(id, typeCollections, ratingTo).get();
    }
    //endregion
}
