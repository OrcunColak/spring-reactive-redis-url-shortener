package com.colak.springtutorial.service;

import com.colak.springtutorial.exception.InvalidLinkException;
import com.colak.springtutorial.model.LinkRequest;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final UrlValidator urlValidator;

    public ValidationService() {
        this.urlValidator = new UrlValidator();
    }

    public void validateLink(LinkRequest linkRequest) {
        final String link = linkRequest.getLink();
        if (!urlValidator.isValid(link)) {
            throw new InvalidLinkException(link);
        }
    }
}
