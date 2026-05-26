package com.bfhl.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class RequestDTO {

    @NotNull(message = "data must not be null")
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
