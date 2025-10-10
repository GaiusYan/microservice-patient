package com.yandev.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceConfig {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exeption) {
        Map<String, Object> response = new HashMap<>();
        StringWriter sw = new StringWriter();
        exeption.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();

        response.put("message", exeption.getMessage());
        response.put("stacktrace", stackTrace);
        response.put("trace", stackTrace);
        response.put("timestamp", System.currentTimeMillis());
        response.put("error", exeption.getClass().getName());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
