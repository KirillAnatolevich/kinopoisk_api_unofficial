package com.example.kinopoisk_api_unofficial.my_exception;

import lombok.Getter;
import org.springframework.stereotype.Component;



public class MyExceptions extends IllegalAccessException{
    public MyExceptions(String s) {
        super(s);
    }


}
