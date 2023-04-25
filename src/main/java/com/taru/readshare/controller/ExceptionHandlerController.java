package com.taru.readshare.controller;

import com.taru.readshare.exceptions.ErrorMessage;
import com.taru.readshare.exceptions.LinkNotFoundException;
import com.taru.readshare.exceptions.TeamNotFoundException;
import com.taru.readshare.exceptions.UserNotFoundException;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {LinkNotFoundException.class, UserNotFoundException.class, TeamNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(@Nonnull RuntimeException ex){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorMessage> illegalArgumentException(@Nonnull HttpMessageNotReadableException ex){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
