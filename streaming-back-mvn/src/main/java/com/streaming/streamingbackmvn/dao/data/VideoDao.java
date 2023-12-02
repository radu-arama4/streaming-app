package com.streaming.streamingbackmvn.dao.data;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
public class VideoDao {
    @Id
    private String videoId;
    private String fileName;
}
