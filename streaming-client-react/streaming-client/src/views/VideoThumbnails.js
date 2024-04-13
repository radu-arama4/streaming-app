import React from "react";

const VideoThumbnail = ({ video, onClick }) => {
  return (
    <div className="video-thumbnail" onClick={() => onClick(video.videoId)}>
      <img src={video.thumbnailUrl} alt={video.originalFileName} />
      <h2>{video.title}</h2>
    </div>
  );
};

export default VideoThumbnail;
