package com.colak.spring_reactive_redis_url_shortener.exception;


public class LinkAlreadyExistsException extends RuntimeException {

    public LinkAlreadyExistsException(String link) {
        super(String.format("Link [ %s ] already exists", link));
    }
}
