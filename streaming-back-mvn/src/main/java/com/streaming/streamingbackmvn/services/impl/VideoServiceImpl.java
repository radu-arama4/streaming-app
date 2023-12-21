package com.streaming.streamingbackmvn.services.impl;

import com.streaming.streamingbackmvn.dao.VideoRepository;
import com.streaming.streamingbackmvn.services.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

  @Autowired
  VideoRepository videoRepository;

  @Override
  public void uploadVideo() {
    log.info("Uploading video...");
//        videoRepository.save();
  }

  @Override
  public String getVideo() {
    return null;
  }

  @Override
  public String removeVideo() {
    return null;
  }
}
