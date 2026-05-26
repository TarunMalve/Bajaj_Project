package com.bfhl.exception;

import com.bfhl.config.BfhlProperties;
import com.bfhl.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final BfhlProperties bfhlProperties;

    public GlobalExceptionHandler(BfhlProperties bfhlProperties) {
        this.bfhlProperties = bfhlProperties;
    }

    @ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDTO> handleBadRequest(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildFailureResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleUnexpected(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildFailureResponse());
    }

    private ResponseDTO buildFailureResponse() {
        ResponseDTO response = new ResponseDTO();
        response.setSuccess(false);
        response.setUserId(bfhlProperties.getUserId());
        response.setEmail(bfhlProperties.getEmail());
        response.setRollNumber(bfhlProperties.getRollNumber());
        response.setSum("0");
        response.setConcatString("");
        return response;
    }
}
