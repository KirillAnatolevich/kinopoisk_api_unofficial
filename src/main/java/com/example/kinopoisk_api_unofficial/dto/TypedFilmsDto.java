package com.example.kinopoisk_api_unofficial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypedFilmsDto {
    private List<KinoDto> items;
}
