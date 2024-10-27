package com.example.kinopoisk_api_unofficial.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmDto {
    Long kinopoiskId;
    String kinopoiskHDId;
    String nameRu;
    Integer year;
    Double ratingKinopoisk;
    String slogan;
    String description;
    String shortDescription;
}



























