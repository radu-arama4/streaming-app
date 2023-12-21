package com.streaming.streamingbackmvn.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class PingController {

  private static final String DEFAULT_MESSAGE = "Alive!";

  @PostMapping("/ping")
  public String pingServer() {
    log.info("Ping request received!");
    return DEFAULT_MESSAGE;
  }

}
