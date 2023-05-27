package com.vacika.projectstartertemplate.configuration.rest.handler;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex) {
        ex.printStackTrace();
        return ResponseHandler.error(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDenied(
            RuntimeException ex) {
        ex.printStackTrace();
        return ResponseHandler.error(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(
            RuntimeException ex) {
        ex.printStackTrace();
        return ResponseHandler.error(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<Object> handleNoSuchElementException(
            RuntimeException ex) {
        ex.printStackTrace();
        return ResponseHandler.error(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FileUploadException.class})
    protected ResponseEntity<Object> handleFileUploadException(
            RuntimeException ex) {
        ex.printStackTrace();
        return ResponseHandler.error(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        exception.printStackTrace();
        return ResponseHandler.error("Entity not found", exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataContraintViolated(DataIntegrityViolationException exception) {
        exception.printStackTrace();
        return ResponseHandler.error("Constraint violation", "Data violates DB constraint(s). Please re-check your input data", HttpStatus.BAD_REQUEST);
    }

    // if field validation failed
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        String errors = "Validation failed for following field(s): " + ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));
        return ResponseHandler.error("Validation error. Check 'msg' field for details.", errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> defaultHandler(Exception exception) {
        exception.printStackTrace();
        return ResponseHandler.error(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}