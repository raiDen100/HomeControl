package com.raiden.homecontrol.config;

import com.sourceforge.snap7.moka7.S7;
import com.sourceforge.snap7.moka7.S7Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Value("${plc.ipAddress}")
    private String ipAddress;

    @Value("${plc.rack}")
    private Integer rack;

    @Value("${plc.slot}")
    private Integer slot;

    @Bean
    public S7Client s7Client(){
        S7Client client = new S7Client();
        client.SetConnectionType(S7.OP);
        client.ConnectTo(ipAddress, rack, slot);
        return client;
    }
}
