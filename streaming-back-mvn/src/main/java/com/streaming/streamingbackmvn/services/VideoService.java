package com.streaming.streamingbackmvn.services;

import com.streaming.streamingbackmvn.dto.ChunkWithMetadataDto;
import com.streaming.streamingbackmvn.dto.Range;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

  void uploadVideo(MultipartFile video);

  ChunkWithMetadataDto getVideo(String videoId, Range range);

  String removeVideo();
}
