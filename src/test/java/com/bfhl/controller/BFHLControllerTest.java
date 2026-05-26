package com.bfhl.controller;

import com.bfhl.dto.RequestDTO;
import com.bfhl.dto.ResponseDTO;
import com.bfhl.service.BFHLService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BFHLController.class)
class BFHLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BFHLService bfhlService;

    @Test
    void shouldProcessBfhlEndpoint() throws Exception {
        RequestDTO request = new RequestDTO();
        request.setData(List.of("a", "1", "334", "4", "R", "$"));

        ResponseDTO response = new ResponseDTO();
        response.setSuccess(true);
        response.setUserId("tarun_malve_ddmmyyyy");
        response.setEmail("YOUR_EMAIL");
        response.setRollNumber("YOUR_ROLL_NUMBER");
        response.setOddNumbers(List.of("1"));
        response.setEvenNumbers(List.of("334", "4"));
        response.setAlphabets(List.of("A", "R"));
        response.setSpecialCharacters(List.of("$"));
        response.setSum("339");
        response.setConcatString("Ra");

        when(bfhlService.process(any(RequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    @Test
    void shouldReturnHealthStatus() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
