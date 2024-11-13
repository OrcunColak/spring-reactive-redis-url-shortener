package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.LinkEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LinkRepository extends ReactiveCrudRepository<LinkEntity, Long> {

    Mono<LinkEntity> findByLinkKey(String linkKey);
    Mono<LinkEntity> findByLink(String link);
}
