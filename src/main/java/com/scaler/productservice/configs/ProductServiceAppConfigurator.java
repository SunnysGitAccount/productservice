package com.scaler.productservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ProductServiceAppConfigurator {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
}
