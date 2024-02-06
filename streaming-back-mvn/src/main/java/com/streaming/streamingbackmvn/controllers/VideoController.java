package com.streaming.streamingbackmvn.controllers;

import com.streaming.streamingbackmvn.dto.ChunkWithMetadataDto;
import com.streaming.streamingbackmvn.dto.Range;
import com.streaming.streamingbackmvn.services.VideoFormatCheckerService;
import com.streaming.streamingbackmvn.services.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpHeaders.*;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class VideoController {

  @Autowired
  private VideoService videoService;

  @Autowired
  private VideoFormatCheckerService videoFormatCheckerService;

  private static final int DEFAULT_CHUNK_SIZE = 3145728; // TODO: add config

  @GetMapping("/video/{videoId}")
  public ResponseEntity<byte[]> getVideo(
      @RequestHeader(value = HttpHeaders.RANGE, required = false) String range,
      @PathVariable(value = "videoId") String videoId) {
    Range parsedRange = Range.parseHttpRangeString(range, DEFAULT_CHUNK_SIZE);
    ChunkWithMetadataDto chunkWithMetadataDto = videoService.getVideo(videoId, parsedRange);
    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
        .header(CONTENT_TYPE, chunkWithMetadataDto.getContentType())
        .header(ACCEPT_RANGES, "bytes")
        .header(CONTENT_LENGTH,
            calculateContentLengthHeader(parsedRange, chunkWithMetadataDto.getFileSize()))
        .header(CONTENT_RANGE,
            constructContentRangeHeader(parsedRange, chunkWithMetadataDto.getFileSize()))
        .body(chunkWithMetadataDto.getChunk());
  }

  private String calculateContentLengthHeader(Range range, long fileSize) {
    return String.valueOf(range.getRangeEnd(fileSize) - range.getRangeStart() + 1);
  }

  private String constructContentRangeHeader(Range range, long fileSize) {
    return "bytes " + range.getRangeStart() + "-" + range.getRangeEnd(fileSize) + "/" + fileSize;
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
