package ua.iot.kovalyk.mysqlspringlab.controller;

import ua.iot.kovalyk.mysqlspringlab.exception.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String generalHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    /*@ResponseBody
    @ExceptionHandler(PersonsExistForCityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String personsExistForCityHandler(PersonsExistForCityException ex) {
        return ex.getMessage();
    }*/
}
