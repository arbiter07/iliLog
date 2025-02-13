package com.ililog.api;

import com.ililog.api.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class ILiLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ILiLogApplication.class, args);
    }

}
