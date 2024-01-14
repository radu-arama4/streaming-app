package com.streaming.streamingbackmvn.dao.repository;

import com.streaming.streamingbackmvn.dao.data.VideoDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<VideoDao, String> {

}
