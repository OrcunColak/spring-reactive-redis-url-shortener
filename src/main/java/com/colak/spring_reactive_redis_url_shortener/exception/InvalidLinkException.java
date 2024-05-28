package com.colak.spring_reactive_redis_url_shortener.exception;


public class InvalidLinkException extends RuntimeException {

    public InvalidLinkException(String link) {
        super(String.format("Link [ %s ] is invalid", link));
    }
}
