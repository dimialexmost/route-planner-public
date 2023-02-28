package com.example.routeplannerpublic.controller;

import com.example.routeplannerpublic.model.Route;
import com.example.routeplannerpublic.service.PublicTransportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor
@RestController
public class RouteController {
    private PublicTransportService publicTransportService;

    @GetMapping("/public")
    public List<Route> getPublicTransport(@RequestParam String origin, @RequestParam String destination){

        return publicTransportService.getRoutes(destination, origin);
    }
}
