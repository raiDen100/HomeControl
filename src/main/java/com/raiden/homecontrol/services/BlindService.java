package com.raiden.homecontrol.services;

import com.raiden.homecontrol.models.Blind;
import com.raiden.homecontrol.exceptions.BlindNotAvailableException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlindService {

    @Autowired
    private final PlcService plcService;

    private final List<Blind> blinds = new ArrayList<>();

    public BlindService(PlcService plcService) {
        this.plcService = plcService;
    }

    public void open(String id) {
        Blind blind = blinds.stream()
                .filter(blind1 -> blind1.getId().equals(id))
                .findFirst()
                .orElseThrow();

        if (!blind.isAvailable())
            throw new BlindNotAvailableException();

        timeoutBlind(blind, 20_000);
        plcService.setMerker(blind.getPositionOpen(), blind.getBitOpen(), true);
    }

    public void openFor(String id, int seconds) {
        Blind blind = blinds.stream()
                .filter(blind1 -> blind1.getId().equals(id))
                .findFirst()
                .orElseThrow();

        if (!blind.isAvailable())
            throw new BlindNotAvailableException();

        blind.setAvailable(false);
        plcService.setMerker(blind.getPositionOpen(), blind.getBitOpen(), true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                blind.setAvailable(true);
                plcService.setMerker(blind.getPositionClose(), blind.getBitClose(), true);
            }
        }, seconds * 1000L);
    }

    public void close(String id) {
        Blind blind = blinds.stream()
                .filter(blind1 -> blind1.getId().equals(id))
                .findFirst()
                .orElseThrow();

        if (!blind.isAvailable())
            throw new BlindNotAvailableException();

        timeoutBlind(blind, 20_000);
        plcService.setMerker(blind.getPositionClose(), blind.getBitClose(), true);
    }

    public void closeFor(String id, int seconds) {
        Blind blind = blinds.stream()
                .filter(blind1 -> blind1.getId().equals(id))
                .findFirst()
                .orElseThrow();

        if (!blind.isAvailable())
            throw new BlindNotAvailableException();

        blind.setAvailable(false);
        plcService.setMerker(blind.getPositionClose(), blind.getBitClose(), true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                blind.setAvailable(true);
                plcService.setMerker(blind.getPositionOpen(), blind.getBitOpen(), true);

            }
        }, seconds * 1000L);
    }

    public JSONObject getPositions() {
        return plcService.getPositions();
    }

    public void addBlinds(List<Blind> blinds){
        this.blinds.addAll(blinds);
    }

    private void timeoutBlind(Blind blind, long time){
        blind.setAvailable(false);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                blind.setAvailable(true);
            }
        }, time);
    }
}
