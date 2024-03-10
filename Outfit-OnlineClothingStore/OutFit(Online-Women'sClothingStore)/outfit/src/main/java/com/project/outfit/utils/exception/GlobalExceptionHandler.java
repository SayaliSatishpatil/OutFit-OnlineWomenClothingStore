package com.project.outfit.utils.exception;

import com.project.outfit.utils.response.ErrorResponse;
import com.project.outfit.utils.response.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(OutfitException.class)
    public ResponseEntity<ErrorResponse> outfitException(final OutfitException exception){
        log.error("global exception: ", exception);
        return new ResponseEntity<>(exception.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleException(final Exception exception) {
        // Log the exception
        log.error("Unhandled exception occurred", exception);

        // Return a 500 Internal Server Error response with the stack trace
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericResponse.error("Internal Server Error. Please contact support."));
    }
}
