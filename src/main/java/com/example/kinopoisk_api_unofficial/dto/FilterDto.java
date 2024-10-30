package com.example.kinopoisk_api_unofficial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
    private Double minRaring;
    private Double maxRating;
    private Integer minYear;
    private Integer maxYear;
    private String name;
}
