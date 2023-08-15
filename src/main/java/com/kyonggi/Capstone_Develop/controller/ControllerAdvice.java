package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.dto.ErrorResponse;
import com.kyonggi.Capstone_Develop.exception.CspopException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
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

        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorResponse(e.getErrorCode(), e.getShowMessage()));
    }
}
