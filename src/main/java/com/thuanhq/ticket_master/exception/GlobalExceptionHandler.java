package com.thuanhq.ticket_master.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.thuanhq.ticket_master.dto.response.APIResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<APIResponse> exceptionHandler(Exception exception) {
        APIResponse response = new APIResponse();

        response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        response.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<APIResponse> exceptionApplicationHandler(ApplicationException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        APIResponse response = new APIResponse();

        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> exceptionValidationHandler(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_MESSAGE_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }

        APIResponse response = new APIResponse();

        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
