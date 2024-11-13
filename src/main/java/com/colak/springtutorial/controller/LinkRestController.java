package com.colak.springtutorial.controller;


import com.colak.springtutorial.model.LinkRequest;
import com.colak.springtutorial.model.LinkResponse;
import com.colak.springtutorial.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LinkRestController {

    private final LinkService linkService;


    // http://localhost:8080/api/v1/links
    @GetMapping("api/v1/links")
    public Flux<LinkResponse> getAllLinks() {
        return linkService.getAllLinks();
    }

    // http://localhost:8080/api/v1/1
    @GetMapping("api/v1/{id}")
    public Mono<LinkResponse> getLink(@PathVariable Long id) {
        return linkService.getLink(id);
    }

    @PostMapping("api/v1/add")
    public Mono<LinkResponse> addLink(@RequestBody LinkRequest linkRequest) {
        return linkService.addLink(linkRequest);
    }

    @PostMapping("api/v1/update/{id}")
    public Mono<Void> update(@PathVariable Long id, @RequestBody LinkRequest linkRequest) {
        return linkService.updateLink(id, linkRequest);
    }

    @DeleteMapping("api/v1/delete/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return linkService.deleteLink(id);
    }
}
