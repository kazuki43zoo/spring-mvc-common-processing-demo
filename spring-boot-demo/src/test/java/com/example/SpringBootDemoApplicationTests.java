package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootDemoApplicationTests {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void normal() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("X-Tracking-Id", "abc");
    ResponseEntity<String> response = restTemplate.exchange("/hello?name={name}", HttpMethod.GET,
        new HttpEntity<>(null, headers), String.class, "Kazuki");
    Assertions.assertEquals("Hello Kazuki! tracing with abc", response.getBody());
  }

  @Test
  void systemError() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("X-Tracking-Id", "abc");
    ResponseEntity<String> response = restTemplate.exchange("/hello?name={name}", HttpMethod.GET,
        new HttpEntity<>(null, headers), String.class, "system-error");
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    Assertions.assertEquals("System Error!!", response.getBody());
  }

}
