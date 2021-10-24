package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class LoginRequest extends Request {
    private String serverName;
    private final transient Logger log = LoggerFactory.getLogger(LoginRequest.class);
    private ArrayList<String> features;

    @Override
    public void buildResponse() {
        serverName = "Team-ReaKt";
        features = new ArrayList<>();
        features.add("login");
        log.trace("buildResponse -> {}", this);
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public LoginRequest() {
        this.requestType = "login";
    }

    public String getServerName() {
        return serverName;
    }

    public boolean validFeature(String feature){
        return features.contains(feature);
    }
}