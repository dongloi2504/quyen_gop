package com.example.quyengopbackend.security.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler({ SignupFailedException.class, UserNotFoundException.class })  // Có thể bắt nhiều loại exception
        public ResponseEntity<String> handleExceptionA(Exception e) {
            return ResponseEntity.status(432).body(e.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleUnwantedException(Exception e) {
            // Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
            e.printStackTrace();  // Thực tế người ta dùng logger
            return ResponseEntity.status(500).body("Unknow error");
        }

}
