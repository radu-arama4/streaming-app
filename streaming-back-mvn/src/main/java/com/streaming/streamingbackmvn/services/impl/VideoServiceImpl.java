package com.streaming.streamingbackmvn.services.impl;

import com.streaming.streamingbackmvn.dao.VideoRepository;
import com.streaming.streamingbackmvn.services.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class VideoServiceImpl implements VideoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    VideoRepository videoRepository;

    @Override
    public void uploadVideo() {
        logger.info("Uploading video...");
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
