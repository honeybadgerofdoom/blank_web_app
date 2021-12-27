package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiTest extends Request {

    private final transient Logger log = LoggerFactory.getLogger(ApiTest.class);

    private boolean success;

    @Override
    public void buildResponse() {
        success = true;
        log.trace("buildResponse -> {}", this);
    }

}
