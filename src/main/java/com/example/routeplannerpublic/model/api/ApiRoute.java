package com.example.routeplannerpublic.model.api;

import lombok.Data;

import java.util.List;

@Data
public class ApiRoute {
    private List<Leg> legs;
}
