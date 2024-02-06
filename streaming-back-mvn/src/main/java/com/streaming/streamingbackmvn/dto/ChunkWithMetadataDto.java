package com.streaming.streamingbackmvn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChunkWithMetadataDto {
  private byte[] chunk;
  private String contentType;
  private long fileSize;
}
