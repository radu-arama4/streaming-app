package com.streaming.streamingbackmvn.services;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  void uploadVideo(MultipartFile video);

  String getVideo();

  String removeVideo();
}
