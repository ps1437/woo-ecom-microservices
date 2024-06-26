package com.syscho.wocom.carts.config;

import com.syscho.wocom.carts.CartNotFoundException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandlerAdvice {
    @ExceptionHandler(CartNotFoundException.class)
    ProblemDetail handleNotFoundException(CartNotFoundException e) {
        log.error("Error handleNotFoundException : {}", e.getMessage(), e);
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleException(Exception e) {
        log.error("Error handleException : {}", e.getMessage(), e);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @ExceptionHandler(DataAccessException.class)
    public ProblemDetail handleDatabaseException(DataAccessException ex) {
        log.error("Error handleDatabaseException : {}", ex.getMessage(), ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Database Error occurred");
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleValidationExceptions(Exception ex) {
        log.error("Error handleValidationExceptions : {}", ex.getMessage(), ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ProblemDetail handleRequestNotPermitted(RequestNotPermitted ex, HttpServletRequest request) {
        log.warn("Request to path '{}' is blocked due to rate-limiting. {}",
                request.getRequestURI(), ex.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.TOO_MANY_REQUESTS, "Too many requests");
    }
}