package com.streaming.streamingbackmvn.dao.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Video")
@Getter
@Setter
public class VideoDao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer videoId;
  private String originalFileName;
  private String contentType;
  private String location;
  private Instant uploadTime;
}
