package com.colak.spring_reactive_redis_url_shortener.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LinkRequest {

    @NotEmpty
    private String link;
}
