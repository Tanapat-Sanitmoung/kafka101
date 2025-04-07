package com.example.kafka_apache.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicationEmailException.class)
    public ProblemDetail handleDuplicationEmailException(DuplicationEmailException errorAttributes) {

        log.error("DuplicationEmailException handled");

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Duplicate email address");
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex, WebRequest request)
    {
        log.error("Exception handled");

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Unexpected error");
    }
}
