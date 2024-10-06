package com.example.kinopoisk_api_unofficial.repository;

import com.example.kinopoisk_api_unofficial.dao.KinopoiskDao;
import com.example.kinopoisk_api_unofficial.dio.KinopoiskDto;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KinopoiskRepository {
    private KinopoiskDao kinopoiskDao = new KinopoiskDao();
    private List<KinopoiskDto> kinoList = new ArrayList<>();

    public void copyFilmsFromKinopoisk(int from, int to){
        if (from > 0 && to > 0 && from < to) {
            kinopoiskDao.generateHttpEntity();
            kinoList = IntStream.range(from, to)
                    .mapToObj(n -> kinopoiskDao.addFindByIdFilm(n))
                    .filter(n -> n.getKinopoiskId() != null)
                    .collect(Collectors.toList());
        }
    }
    public KinopoiskDto copyFilmsFromKinopoisk(int index){
        kinopoiskDao.generateHttpEntity();
        return kinopoiskDao.addFindByIdFilm(index);
    }
}
