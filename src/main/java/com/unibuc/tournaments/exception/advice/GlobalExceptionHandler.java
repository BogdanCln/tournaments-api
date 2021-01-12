package com.unibuc.tournaments.exception.advice;

import com.unibuc.tournaments.exception.game.GameNotCreatedException;
import com.unibuc.tournaments.exception.game.GameNotFoundException;
import com.unibuc.tournaments.exception.team.TeamNotCreatedException;
import com.unibuc.tournaments.exception.team.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity<String> handle(GameNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }

    @ExceptionHandler({GameNotCreatedException.class})
    public ResponseEntity<String> handle(GameNotCreatedException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }

    @ExceptionHandler({TeamNotFoundException.class})
    public ResponseEntity<String> handle(TeamNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }

    @ExceptionHandler({TeamNotCreatedException.class})
    public ResponseEntity<String> handle(TeamNotCreatedException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage() + " at " + LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body("Invalid value : " + Objects.requireNonNull(e.getFieldError()).getRejectedValue() +
                        " for field " + e.getFieldError().getField() +
                        " with message " + e.getFieldError().getDefaultMessage());
    }

}
