package com.colak.springtutorial.model;


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
