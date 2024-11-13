package com.colak.springtutorial.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LinkRequest {

    @NotEmpty
    private String link;
}
