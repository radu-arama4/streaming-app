package com.streaming.streamingbackmvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
// Temporary exclude spring security autoconfiguration
public class StreamingBackMvnApplication {

  public static void main(String[] args) {
    SpringApplication.run(StreamingBackMvnApplication.class, args);
  }

}
