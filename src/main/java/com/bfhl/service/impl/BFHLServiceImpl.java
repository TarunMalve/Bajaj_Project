package com.bfhl.service.impl;

import com.bfhl.config.BfhlProperties;
import com.bfhl.dto.RequestDTO;
import com.bfhl.dto.ResponseDTO;
import com.bfhl.exception.BadRequestException;
import com.bfhl.service.BFHLService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class BFHLServiceImpl implements BFHLService {

    private final BfhlProperties bfhlProperties;

    public BFHLServiceImpl(BfhlProperties bfhlProperties) {
        this.bfhlProperties = bfhlProperties;
    }

    @Override
    public ResponseDTO process(RequestDTO requestDTO) {
        if (requestDTO == null || requestDTO.getData() == null) {
            throw new BadRequestException("Invalid request payload");
        }

        ResponseDTO response = initSuccessResponse();
        StringBuilder alphaCombined = new StringBuilder();
        BigInteger sum = BigInteger.ZERO;

        for (String item : requestDTO.getData()) {
            if (item == null || item.isBlank()) {
                continue;
            }

            if (isNumeric(item)) {
                BigInteger numeric = new BigInteger(item);
                sum = sum.add(numeric);
                if (numeric.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                    response.getEvenNumbers().add(item);
                } else {
                    response.getOddNumbers().add(item);
                }
                continue;
            }

            if (isAlphabetic(item)) {
                response.getAlphabets().add(item.toUpperCase());
                alphaCombined.append(item);
                continue;
            }

            response.getSpecialCharacters().add(item);
        }

        response.setSum(sum.toString());
        response.setConcatString(generateAlternatingCapsReversed(alphaCombined.toString()));
        return response;
    }

    private ResponseDTO initSuccessResponse() {
        ResponseDTO response = new ResponseDTO();
        response.setSuccess(true);
        response.setUserId(bfhlProperties.getUserId());
        response.setEmail(bfhlProperties.getEmail());
        response.setRollNumber(bfhlProperties.getRollNumber());
        response.setSum("0");
        response.setConcatString("");
        return response;
    }

    private boolean isNumeric(String value) {
        return value.matches("^-?\\d+$");
    }

    private boolean isAlphabetic(String value) {
        return value.matches("^[A-Za-z]+$");
    }

    private String generateAlternatingCapsReversed(String value) {
        if (value.isEmpty()) {
            return "";
        }

        String reversed = new StringBuilder(value).reverse().toString();
        StringBuilder transformed = new StringBuilder();

        for (int i = 0; i < reversed.length(); i++) {
            char current = reversed.charAt(i);
            transformed.append(i % 2 == 0 ? Character.toUpperCase(current) : Character.toLowerCase(current));
        }

        return transformed.toString();
    }
}
