package com.raiden.homecontrol.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeteoService {

    @Autowired
    private final PlcService plcService;

    public MeteoService(PlcService plcService) {
        this.plcService = plcService;
    }

    public JSONObject getMeteoData() {
        JSONObject json = new JSONObject();
        json.put("temperature", plcService.getFloatAt(10, 222));
        json.put("feelsLike", plcService.getFloatAt(100, 42));
        json.put("wind", plcService.getFloatAt(100, 24));
        json.put("windMax", plcService.getFloatAt(100, 28));
        json.put("windDirection", plcService.getWordAt(100, 22));
        json.put("humidity", plcService.getFloatAt(10, 112));
        json.put("pressure", plcService.getFloatAt(10, 144));

        return json;
    }
}
