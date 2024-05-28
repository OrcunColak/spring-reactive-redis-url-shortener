package com.colak.spring_reactive_redis_url_shortener.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ShortyConfig {

    @Value("${shorty.service.url}")
    private String serviceUrl;
}
