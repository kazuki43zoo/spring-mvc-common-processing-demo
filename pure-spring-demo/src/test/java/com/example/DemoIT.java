package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class DemoIT {

  @Test
  void normal() {
    RestTemplate restTemplate = new RestTemplate();

    RequestEntity<Void> request =
        RequestEntity.get("http://localhost:8080/pure-spring-demo/hello?name={name}", "Kazuki ")
            .header("X-Tracking-Id", "abc")
            .build();
    ResponseEntity<String> response = restTemplate.exchange(request, String.class);
    Assertions.assertEquals("Hello Kazuki! tracing with abc", response.getBody());
  }

  @Test
  void systemError() {
    RestTemplate restTemplate = new RestTemplate();

    RequestEntity<Void> request =
        RequestEntity.get("http://localhost:8080/pure-spring-demo/hello?name={name}", "system-error")
            .header("X-Tracking-Id", "def")
            .build();
    try {

      restTemplate.exchange(request, String.class);
      Assertions.fail();
    } catch (HttpStatusCodeException e) {
      Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatusCode());
      Assertions.assertEquals("System Error!!", e.getResponseBodyAsString());
    }
  }

}
