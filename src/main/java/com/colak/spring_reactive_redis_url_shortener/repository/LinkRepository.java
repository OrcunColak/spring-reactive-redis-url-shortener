package com.colak.spring_reactive_redis_url_shortener.repository;

import com.colak.spring_reactive_redis_url_shortener.jpa.LinkEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LinkRepository extends ReactiveCrudRepository<LinkEntity, Long> {

    Mono<LinkEntity> findByLinkKey(String linkKey);
    Mono<LinkEntity> findByLink(String link);
}
