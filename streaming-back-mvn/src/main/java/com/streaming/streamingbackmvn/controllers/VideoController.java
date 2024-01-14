package com.streaming.streamingbackmvn.controllers;

import com.streaming.streamingbackmvn.services.VideoFormatCheckerService;
import com.streaming.streamingbackmvn.services.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class VideoController {

  @Autowired
  VideoService videoService;

  @Autowired
  VideoFormatCheckerService videoFormatCheckerService;

  @GetMapping("/video/{videoId}")
  public void getVideo(@PathVariable(value = "videoId") String videoId) {

  }

  @PostMapping("/video")
  public void uploadVideo(@RequestParam("video") MultipartFile video) {
    if (!videoFormatCheckerService.isVideoValid(video)) {
      log.warn("Video format not valid!");
      return;
    }

    videoService.uploadVideo(video);
  }

}
