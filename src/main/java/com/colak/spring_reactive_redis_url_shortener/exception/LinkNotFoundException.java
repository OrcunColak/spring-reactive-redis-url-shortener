package com.colak.spring_reactive_redis_url_shortener.exception;


public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException(String linkKey) {
        super(String.format("Link not found for [ %s ] link key", linkKey));
    }
}
