package com.streaming.streamingbackmvn.dao;

import com.streaming.streamingbackmvn.dao.data.VideoDao;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<VideoDao, String> {

}
