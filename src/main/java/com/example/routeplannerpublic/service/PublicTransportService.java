package com.example.routeplannerpublic.service;

import com.example.routeplannerpublic.model.api.ApiResponse;
import com.example.routeplannerpublic.model.api.ApiRoute;
import com.example.routeplannerpublic.model.api.Leg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.routeplannerpublic.model.Route;
import com.example.routeplannerpublic.model.Step;

import java.net.URI;
import java.util.List;
@Service
public class PublicTransportService {
    @Value("${api.url}")
    private String url;
    @Value("${api.key}")
    private String key;
    private RestTemplate restTemplate = new RestTemplate();

    public List<Route> getRoutes(String destination, String origin) {
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("key", key)
                .queryParam("mode", "transit")
                .queryParam("destination", destination)
                .queryParam("origin", origin)
                .build()
                .toUri();
        ApiResponse response = restTemplate.getForObject(uri, ApiResponse.class);
        List<ApiRoute> routes = response.getRoutes();
        uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("key", key)
                .queryParam("mode", "transit")
                .queryParam("destination", origin)
                .queryParam("origin", destination)
                .build()
                .toUri();
        response = restTemplate.getForObject(uri, ApiResponse.class);
        routes.addAll(response.getRoutes());
        return routes.stream()
                .map(this::toRoute)
                .toList();
    }
    private Route toRoute(ApiRoute apiRoute){
        Route route = new Route();
        Leg leg = apiRoute.getLegs().get(0);
        List<Step> steps = leg.getSteps().stream()
                .map(step -> new Step(step.getDistance().get("text"), clearHtml(step.getHtml_instructions())))
                .toList();
        route.setSteps(steps);
        route.setDuration(leg.getDuration().get("text"));
        route.setArrivalTime(leg.getArrivalTime().get("text"));
        route.setDepartureTime(leg.getDepartureTime().get("text"));
        return route;
    }
    private String clearHtml(String string){
        return string.replace("<b>", "").replace("</b>", "");
    }
}

