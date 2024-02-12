package com.streaming.streamingbackmvn.services.impl;

import com.streaming.streamingbackmvn.services.VideoFormatCheckerService;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class VideoFormatCheckerServiceImpl implements VideoFormatCheckerService {

  private static final String ALLOWED_VIDEO_FORMATS_PROP = "allowed.video.formats";

  private String[] allowedVideoFormats = new String[]{};

  @Autowired
  private Environment env;

  @PostConstruct
  private void init() {
    // TODO: move to dedicated config file
    String rawAllowedVideoFormats = env.getProperty(ALLOWED_VIDEO_FORMATS_PROP);
    if (rawAllowedVideoFormats != null) {
      allowedVideoFormats = rawAllowedVideoFormats.split(",");
    }
  }

  @Override
  public boolean isVideoValid(MultipartFile video) {
    if (allowedVideoFormats.length == 0) {
      log.warn("No pre configured allowed video formats! Validating any format.");
      return true;
    }

    return Arrays.stream(allowedVideoFormats)
        .anyMatch(allowedFormat -> allowedFormat.equals(video.getContentType()));
  }
}
