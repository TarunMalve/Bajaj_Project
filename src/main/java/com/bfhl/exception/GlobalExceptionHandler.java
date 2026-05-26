package com.bfhl.exception;

import com.bfhl.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String USER_ID = "tarun_malve_ddmmyyyy";
    private static final String EMAIL = "YOUR_EMAIL";
    private static final String ROLL_NUMBER = "YOUR_ROLL_NUMBER";

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
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setSum("0");
        response.setConcatString("");
        return response;
    }
}
