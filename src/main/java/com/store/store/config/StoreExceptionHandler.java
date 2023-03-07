package com.store.store.config;

import com.store.store.dto.ErrorResponse;
import com.store.store.exception.NullEntityReferenceException;
import com.store.store.exception.UnacceptableParameterValueException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class StoreExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NullEntityReferenceException.class, UnacceptableParameterValueException.class})
    ResponseEntity<ErrorResponse> handleNullEntityReferenceException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler({NoSuchElementException.class, EntityNotFoundException.class, UsernameNotFoundException.class, IllegalArgumentException.class})
    ResponseEntity<?> handleNoSuchElementException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }
}
