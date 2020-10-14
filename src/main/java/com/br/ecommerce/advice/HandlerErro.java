package com.br.ecommerce.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerErro {

    private final Logger log = LoggerFactory.getLogger(HandlerErro.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handle (MethodArgumentNotValidException methodArgumentNotValidException) {
        log.info("Tratando {}", methodArgumentNotValidException);

        List<String> errors = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().map(
                e -> e.getDefaultMessage()).collect(Collectors.toList());

        HashMap<String , List<String>> response = new HashMap<>();

        response.put("erros", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

