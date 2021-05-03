package com.example.component.controller;

import com.example.component.service.GreetingService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class DemoController {

  private final GreetingService service;

  DemoController(GreetingService service) {
    this.service = service;
  }

  @GetMapping("/hello")
  String hello(@RequestParam String name, @ModelAttribute("trackingId") String trackingId) {
    return service.hello(name, trackingId);
  }

}
