package com.example.routeplannerpublic.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Step {
    private String distance;
    private String instructions;
}