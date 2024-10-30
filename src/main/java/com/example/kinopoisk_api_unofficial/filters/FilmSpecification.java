package com.example.kinopoisk_api_unofficial.filters;

import com.example.kinopoisk_api_unofficial.model.Film;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FilmSpecification {
    public static Specification<Film> ratingBitWin(Double min, Double max){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (min != null || max != null){
                return criteriaBuilder.between(root.get("rating"), min, max);
            }
            return criteriaBuilder.conjunction();
        });
    }
    public static Specification<Film> yearBitWin(Integer min, Integer max){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (min != null || max != null){
                return criteriaBuilder.between(root.get("year"), min, max);
            }
            return criteriaBuilder.conjunction();
        });
    }
    public static Specification<Film> nameBitWin(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (name != null){
                return criteriaBuilder.like(root.get("filmName"), "%" + name + "%");
            }
            return criteriaBuilder.conjunction();
        });
    }
}
