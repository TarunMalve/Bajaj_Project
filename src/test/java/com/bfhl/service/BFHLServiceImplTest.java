package com.bfhl.service;

import com.bfhl.config.BfhlProperties;
import com.bfhl.dto.RequestDTO;
import com.bfhl.dto.ResponseDTO;
import com.bfhl.service.impl.BFHLServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BFHLServiceImplTest {

    private BFHLServiceImpl service;

    @BeforeEach
    void setUp() {
        BfhlProperties properties = new BfhlProperties();
        properties.setUserId("tarun_malve_ddmmyyyy");
        properties.setEmail("YOUR_EMAIL");
        properties.setRollNumber("YOUR_ROLL_NUMBER");
        service = new BFHLServiceImpl(properties);
    }

    @Test
    void shouldExtractOddNumbers() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("1", "2", "3", "4"));

        ResponseDTO response = service.process(request);

        assertEquals(List.of("1", "3"), response.getOddNumbers());
    }

    @Test
    void shouldExtractEvenNumbers() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("10", "11", "22"));

        ResponseDTO response = service.process(request);

        assertEquals(List.of("10", "22"), response.getEvenNumbers());
    }

    @Test
    void shouldConvertAlphabetsToUppercase() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("a", "bCd"));

        ResponseDTO response = service.process(request);

        assertEquals(List.of("A", "BCD"), response.getAlphabets());
    }

    @Test
    void shouldExtractSpecialCharacters() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("$", "@", "A1"));

        ResponseDTO response = service.process(request);

        assertEquals(List.of("$", "@", "A1"), response.getSpecialCharacters());
    }

    @Test
    void shouldCalculateSumAsString() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("1", "334", "4"));

        ResponseDTO response = service.process(request);

        assertEquals("339", response.getSum());
    }

    @Test
    void shouldGenerateConcatString() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("A", "ABCD", "DOE"));

        ResponseDTO response = service.process(request);

        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    void shouldTreatMixedAlphanumericAsSpecialCharacter() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("A1", "XYZ", "22"));

        ResponseDTO response = service.process(request);

        assertEquals(List.of("A1"), response.getSpecialCharacters());
        assertEquals(List.of("XYZ"), response.getAlphabets());
        assertEquals(List.of("22"), response.getEvenNumbers());
    }

    @Test
    void shouldReturnEmptyConcatStringWhenNoAlphabetsPresent() {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("1", "2", "$"));

        ResponseDTO response = service.process(request);

        assertEquals("", response.getConcatString());
    }
}
