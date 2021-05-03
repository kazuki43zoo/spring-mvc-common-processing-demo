package com.example.component.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

  private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

  public String hello(String name, String trackingId) {
    logger.debug("Hi {}.", name);
    if ("system-error".equals(name)) {
      throw new IllegalArgumentException("Name is invalid.");
    }
    return "Hello " + name + "! tracing with " + trackingId;
  }

}