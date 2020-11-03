package com.example.async.springbootdemorestasync.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Value("${integration.connection-timeout}")
    private int connectionTimeout;

    @Bean
    public RestTemplate configRestTemplate(RestTemplateBuilder builder){
        return builder
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(connectionTimeout))
                .build();
    }
}
