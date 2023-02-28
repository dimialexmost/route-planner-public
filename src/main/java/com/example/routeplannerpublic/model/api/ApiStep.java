package com.example.routeplannerpublic.model.api;

import lombok.Data;

import java.util.Map;

@Data
public class ApiStep {

    private Map<String, String> distance;
    private String html_instructions;
}
