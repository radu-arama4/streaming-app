package com.streaming.streamingbackmvn.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllVideosDto {
  List<VideoDto> allVideos;
  int nrOfVideos;
}
