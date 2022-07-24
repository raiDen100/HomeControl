package com.raiden.homecontrol;

import com.raiden.homecontrol.models.Blind;
import com.raiden.homecontrol.services.BlindService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HomeControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeControlApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(BlindService blindService){
        return (args) -> {
            List<Blind> blinds = new ArrayList<>();

            blinds.add(new Blind(221, 0, 222, 4, "r1"));
            blinds.add(new Blind(221, 1, 222, 5, "r2"));
            blinds.add(new Blind(221, 2, 222, 6, "r3"));
            blinds.add(new Blind(221, 3, 222, 7, "r4"));
            blinds.add(new Blind(221, 4, 223, 0, "r5"));
            blinds.add(new Blind(221, 5, 223, 1, "r6"));
            blinds.add(new Blind(221, 6, 223, 2, "r7"));
            blinds.add(new Blind(221, 7, 223, 3, "r8"));
            blinds.add(new Blind(222, 0, 223, 4, "r9"));
            blinds.add(new Blind(222, 1, 223, 5, "r10"));
            blinds.add(new Blind(222, 2, 223, 6, "r11"));
            blinds.add(new Blind(222, 3, 223, 7, "r12"));

            blindService.addBlinds(blinds);
        };
    }

}
