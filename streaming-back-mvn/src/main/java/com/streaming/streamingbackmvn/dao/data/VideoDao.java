package com.streaming.streamingbackmvn.dao.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VideoDao {

  @Id
  private String videoId;
  private String fileName;
}
