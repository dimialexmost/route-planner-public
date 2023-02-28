package com.example.routeplannerpublic.model;
import lombok.Data;

import java.util.List;

@Data
public class Route {
    private List<Step> steps;
    private String duration;
    private String arrivalTime;
    private String departureTime;
}