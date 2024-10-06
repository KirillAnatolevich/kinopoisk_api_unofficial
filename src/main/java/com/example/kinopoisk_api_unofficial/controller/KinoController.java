package com.example.kinopoisk_api_unofficial.controller;

import com.example.kinopoisk_api_unofficial.dio.KinopoiskDto;
import com.example.kinopoisk_api_unofficial.model.Film;
import com.example.kinopoisk_api_unofficial.repository.KinopoiskRepository;
import com.example.kinopoisk_api_unofficial.service.KinoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Data
public class KinoController {
    private final KinoService kinoService;

    @PostMapping("/api/v2/films/{index}")
    public KinopoiskDto addKino(@PathVariable Integer index){
        return kinoService.addKino(index);
    }

    @PostMapping("/api/v2/films/{from}/{to}")
    public KinopoiskDto addKino(@PathVariable Integer from, @PathVariable Integer to){
        return kinoService.addKino(from, to);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Film> findByName(@PathVariable String name){
        Optional<Film> film = kinoService.findByFilmName(name);
        return film.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/v2/films/sortyar")
    public List<Film> sortFilmByYar(){
        return kinoService.sortFilmsByYear();
    }

    @GetMapping("/api/v2/films/findall")
    public List<Film> findAll(){
        return kinoService.findAll();
    }
}
