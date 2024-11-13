package com.colak.springtutorial.service;

import com.colak.springtutorial.config.ShortyConfig;
import com.colak.springtutorial.exception.LinkAlreadyExistsException;
import com.colak.springtutorial.exception.LinkNotFoundException;
import com.colak.springtutorial.jpa.LinkEntity;
import com.colak.springtutorial.model.LinkRequest;
import com.colak.springtutorial.model.LinkResponse;
import com.colak.springtutorial.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LinkService {
    private static final String KEY = "link";

    private final ShortyConfig shortyConfig;
    private final ReactiveHashOperations<String, Long, LinkResponse> hashOperations;
    private final LinkRepository linkRepository;
    private final ValidationService validationService;


    public Flux<LinkResponse> getAllLinks() {
        return linkRepository.findAll().map(this::toLinkResponse);
    }

    public Mono<LinkResponse> getLink(Long id) {
        return hashOperations.get(KEY, id).switchIfEmpty(this.getFromDatabaseAndCache(id));
    }

    public Mono<LinkResponse> addLink(LinkRequest linkRequest) {
        validationService.validateLink(linkRequest);
        return linkRepository
                .findByLink(linkRequest.getLink())
                .map(
                        linkEntity -> {
                            if (Objects.nonNull(linkEntity)) {
                                throw new LinkAlreadyExistsException(linkEntity.getLink());
                            }
                            return linkEntity;
                        })
                .switchIfEmpty(linkRepository.save(toLinkEntity(linkRequest)))
                .map(
                        linkEntity -> {
                            final LinkResponse linkResponse = toLinkResponse(linkEntity);
                            this.hashOperations
                                    .put(KEY, linkEntity.getId(), linkResponse)
                                    .thenReturn(linkResponse);
                            return linkResponse;
                        });
    }

    public Mono<Void> updateLink(Long id, LinkRequest linkRequest) {
        validationService.validateLink(linkRequest);
        return this.linkRepository
                .findById(id)
                .flatMap(
                        link -> {
                            link.setLink(linkRequest.getLink());
                            return linkRepository.save(link);
                        })
                .then(hashOperations.remove(KEY, id))
                .then();
    }

    public Mono<RedirectView> redirectToLink(String linkKey) {
        return linkRepository
                .findByLinkKey(linkKey)
                .switchIfEmpty(Mono.error(new LinkNotFoundException(linkKey)))
                .doOnNext(c -> hashOperations.remove(KEY, c.getId()))
                .flatMap(
                        link -> {
                            link.setClickCount(link.getClickCount() + 1);
                            hashOperations.remove(KEY, link.getId());
                            return linkRepository
                                    .save(link)
                                    .map(updatedLink -> new RedirectView(updatedLink.getLink()));
                        });
    }

    public Mono<Void> deleteLink(Long id) {
        return this.linkRepository.deleteById(id).then(hashOperations.remove(KEY, id)).then();
    }

    private Mono<LinkResponse> getFromDatabaseAndCache(Long id) {
        return linkRepository
                .findById(id)
                .map(this::toLinkResponse)
                .flatMap(link -> this.hashOperations.put(KEY, id, link).thenReturn(link));
    }

    private LinkEntity toLinkEntity(LinkRequest linkRequest) {
        return new LinkEntity()
                .setClickCount(0L)
                .setLink(linkRequest.getLink())
                .setLinkKey(RandomStringUtils.randomAlphanumeric(6));
    }

    private LinkResponse toLinkResponse(LinkEntity linkEntity) {
        return new LinkResponse()
                .setId(linkEntity.getId())
                .setShortLink(shortyConfig.getServiceUrl() + linkEntity.getLinkKey())
                .setLink(linkEntity.getLink())
                .setClickCount(linkEntity.getClickCount());
    }
}
