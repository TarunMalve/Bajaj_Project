package com.bfhl.controller;

import com.bfhl.dto.RequestDTO;
import com.bfhl.dto.ResponseDTO;
import com.bfhl.service.BFHLService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class BFHLController {

    private final BFHLService bfhlService;

    public BFHLController(BFHLService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ResponseDTO> process(@Valid @RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(bfhlService.process(requestDTO));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
