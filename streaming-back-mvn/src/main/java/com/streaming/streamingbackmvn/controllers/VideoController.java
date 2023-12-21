package com.streaming.streamingbackmvn.controllers;

import com.streaming.streamingbackmvn.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class VideoController {

  @Autowired
  VideoService videoService;

  @GetMapping("/video/{videoId}")
  public void getVideo(@PathVariable(value = "videoId") String videoId) {

  }

  @PostMapping("/video/")
  public void uploadVideo(@RequestBody MultipartFile file) {
    //TODO: Perform checking for the file format
    videoService.uploadVideo();

  }

}
