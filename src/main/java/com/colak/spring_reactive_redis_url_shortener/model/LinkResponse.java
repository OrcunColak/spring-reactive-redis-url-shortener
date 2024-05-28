package com.colak.spring_reactive_redis_url_shortener.model;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LinkResponse {

    private Long id;
    private String shortLink;
    private String link;
    private Long clickCount;
}
