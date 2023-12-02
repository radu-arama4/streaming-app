package com.streaming.streamingbackmvn.controllers;

import com.streaming.streamingbackmvn.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("api/v1/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/{videoId}")
    public void getVideo(@PathVariable(value = "videoId") String videoId){

    }

    @PostMapping
    public void uploadVideo(@RequestBody MultipartFile file){
        //TODO: Perform checking for the file format
        videoService.uploadVideo();

    }

}
