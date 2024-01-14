package com.streaming.streamingbackmvn.services;

import org.springframework.web.multipart.MultipartFile;

public interface VideoFormatCheckerService {

  boolean isVideoValid(MultipartFile video);

}
