package com.streaming.streamingbackmvn.services.impl;

import com.streaming.streamingbackmvn.dao.data.VideoDao;
import com.streaming.streamingbackmvn.dao.repository.VideoRepository;
import com.streaming.streamingbackmvn.dto.ChunkWithMetadataDto;
import com.streaming.streamingbackmvn.dto.Range;
import com.streaming.streamingbackmvn.services.VideoService;
import com.streaming.streamingbackmvn.services.util.FileChunkUtil;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Optional;
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
      dbVideo.setContentType(video.getContentType());
      dbVideo.setUploadTime(Instant.now());

      videoRepository.save(dbVideo);
      log.info("New video stored on - " + file.getAbsoluteFile());
    } catch (IOException e) {
      log.error("Error while uploading the video - " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public ChunkWithMetadataDto getVideo(String videoId, Range range) {
    Optional<VideoDao> video = videoRepository.findById(videoId);

    if (video.isPresent()) {
      VideoDao videoDao = video.get();
      String absoluteVideoLocation = videoDao.getLocation();
      File existingVideo = new File(absoluteVideoLocation);
      try {
        InputStream inputStream = new FileInputStream(existingVideo);
        int chunkSize = FileChunkUtil.calculateChunkSize(range, existingVideo.length());
        byte[] chunkBytes = inputStream.readNBytes(chunkSize);

        ChunkWithMetadataDto chunkWithMetadataDto = ChunkWithMetadataDto.builder().build();
        chunkWithMetadataDto.setChunk(chunkBytes);
        chunkWithMetadataDto.setContentType(videoDao.getContentType());
        chunkWithMetadataDto.setFileSize(existingVideo.length());

        return chunkWithMetadataDto;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    log.warn("No video was found for given ID");
    return ChunkWithMetadataDto.builder().build();
  }

  @Override
  public String removeVideo() {
    return null;
  }
}
