package com.example.kinopoisk_api_unofficial.dio;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Setter
public class KinopoiskDto {
    private String kinopoiskId, kinopoiskHDId, nameRu, year, ratingKinopoisk, slogan, description, shortDescription;
}
