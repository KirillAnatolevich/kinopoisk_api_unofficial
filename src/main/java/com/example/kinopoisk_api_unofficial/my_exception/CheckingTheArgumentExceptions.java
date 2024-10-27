package com.example.kinopoisk_api_unofficial.my_exception;



public class CheckingTheArgumentExceptions extends IllegalArgumentException{
    public CheckingTheArgumentExceptions() {
    }

    public CheckingTheArgumentExceptions(String s) {
        super(s);
    }

    public IllegalArgumentException typeMismatch(String type){
        return new IllegalArgumentException(String.format("Аргумент не соответствует типу данных %s", type));
    }

    public IllegalArgumentException invalidNumber(int prefix, int suffix){
        return new IllegalArgumentException(String.format("Не валидное число в аргументе, одо должно быть от-%d и до-%d", prefix, suffix));
    }
}