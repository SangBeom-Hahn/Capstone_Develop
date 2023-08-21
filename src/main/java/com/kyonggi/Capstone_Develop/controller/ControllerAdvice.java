package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.dto.ErrorResponse;
import com.kyonggi.Capstone_Develop.exception.CspopException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleInvalidRequest(
            final BindingResult bindingResult,
            final HttpServletRequest request,
            final MethodArgumentNotValidException e
    ) throws IOException {
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        final FieldError mainError = fieldErrors.get(0);
        final String[] errorInfo = Objects
                .requireNonNull(mainError.getDefaultMessage())
                .split(":");
        
        log.error("HandledException: {} {} statusCode={} errMessage={}\n",
                request.getMethod(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                errorInfo[0]
        );
        
        log.debug("Error StackTrace: ", e);
        
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("0", errorInfo[0]));
    }
    
    @ExceptionHandler({CspopException.class})
    public ResponseEntity<ErrorResponse> handleCspopException(
            final CspopException e,
            final HttpServletRequest request
    ) throws IOException {
        log.error("HandleException: {} {} statusCode={} errMessage={}\n",
                request.getMethod(),
                request.getRequestURI(),
                e.getHttpStatus().value(),
                e.getMessage()
        );
        log.debug("Error StackTrace: ", e);

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getShowMessage()));
    }
}
