package com.streaming.streamingbackmvn.services.impl;

import com.streaming.streamingbackmvn.dao.repository.VideoRepository;
import com.streaming.streamingbackmvn.dao.data.VideoDao;
import com.streaming.streamingbackmvn.services.VideoService;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

  @Autowired
  private VideoRepository videoRepository;

  @Autowired
  private Environment env;

  private String videoStorageLocation;

  @PostConstruct
  private void init() {
    videoStorageLocation = env.getProperty("video.storage.location",
        "src/main/resources/video_storage/");
  }

  @Override
  public void uploadVideo(MultipartFile video) {
    String systemVideoName = UUID.randomUUID().toString().replace("-", "");
    String path = videoStorageLocation + systemVideoName;
    File file = new File(path);
    log.info("Uploading video...");

    try {
      InputStream inputStream = video.getInputStream();
      Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

      VideoDao dbVideo = new VideoDao();
      String absolutePath = file.getAbsolutePath();
      dbVideo.setLocation(absolutePath);
      dbVideo.setOriginalFileName(video.getOriginalFilename());
      dbVideo.setUploadTime(Instant.now());

      videoRepository.save(dbVideo);
      log.info("New video stored on - " + file.getAbsoluteFile());
    } catch (IOException e) {
      log.error("Error while uploading the video - " + e.getMessage());
      throw new RuntimeException(e);
    }
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
