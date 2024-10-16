package com.example.kinopoisk_api_unofficial.my_exception;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



public class ExceptionsToMethodArguments {
    public String checkingNumber(){
        return String.valueOf(new MyExceptions("either negative or too large is pure in the method argument"));
    }
}
