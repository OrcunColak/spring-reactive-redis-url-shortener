package com.colak.spring_reactive_redis_url_shortener.controller;


import com.colak.spring_reactive_redis_url_shortener.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping("favicon.ico")
    public ResponseEntity<ClassPathResource> favicon() {
        ClassPathResource imgFile = new ClassPathResource("static/favicon.ico");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/x-icon");

        return new ResponseEntity<>(imgFile, headers, HttpStatus.OK);
    }

    @GetMapping("/{linkKey}")
    public Mono<RedirectView> redirect(@PathVariable String linkKey) {
        return linkService.redirectToLink(linkKey);
    }
}
