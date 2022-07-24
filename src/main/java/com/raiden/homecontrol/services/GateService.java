package com.raiden.homecontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateService {

    @Autowired
    private final PlcService plcService;

    public GateService(PlcService plcService) {
        this.plcService = plcService;
    }

    public void openGate() {

        plcService.setDatabaseBit(20, 30, true);
        plcService.setDatabaseBit(20, 30, false);
    }
}
