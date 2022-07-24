package com.raiden.homecontrol.controller;

import com.raiden.homecontrol.services.BlindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/roleta")
public class BlindController {

    @Autowired
    private final BlindService blindService;

    public BlindController(BlindService blindService) {
        this.blindService = blindService;
    }


    @GetMapping("/open/{id}")
    public void open(@PathVariable String id){
        blindService.open(id);
    }

    @GetMapping("/open/{id}/{seconds}")
    public void openFor(@PathVariable String id, @PathVariable int seconds){
        blindService.openFor(id, seconds);
    }

    @GetMapping("/close/{id}")
    public void close(@PathVariable String id){
        blindService.close(id);
    }

    @GetMapping("/close/{id}/{seconds}")
    public void closeFor(@PathVariable String id, @PathVariable int seconds){
        blindService.closeFor(id, seconds);
    }

    @GetMapping
    public String getPositions(){

        return blindService.getPositions().toString();
    }
}
