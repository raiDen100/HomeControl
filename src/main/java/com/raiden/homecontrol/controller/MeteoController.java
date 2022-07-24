package com.raiden.homecontrol.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raiden.homecontrol.services.MeteoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeteoController {

    @Autowired
    private final MeteoService meteoService;

    public MeteoController(MeteoService meteoService) {
        this.meteoService = meteoService;
    }

    @GetMapping("/meteo")
    public String getMeteoData(){
        return meteoService.getMeteoData().toString();
    }
}
