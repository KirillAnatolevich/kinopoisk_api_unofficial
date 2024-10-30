package com.example.kinopoisk_api_unofficial.mupstruct;

import com.example.kinopoisk_api_unofficial.dto.KinoDto;
import com.example.kinopoisk_api_unofficial.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
        //    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MappingFulms {

    MappingFulms INSTANCE = Mappers.getMapper(MappingFulms.class);

    @Mapping(source = "kinopoiskId", target = "filmId")
    @Mapping(source = "nameRu", target = "filmName")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "ratingKinopoisk", target = "rating")
    @Mapping(expression = "java(contactDescriptions(kinopoisk.getSlogan(), kinopoisk.getShortDescription(), kinopoisk.getDescription()))", target = "description")
    Film enrichFilm(KinoDto kinopoisk);

    List<Film> enrichFilms(List<KinoDto> kinoDtos);

    default String contactDescriptions(String slogan, String shortDescription, String description){
        return slogan + "\n" + shortDescription + "\n" + description;
    }
}
