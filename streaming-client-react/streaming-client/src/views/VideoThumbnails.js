import React from "react";

const VideoThumbnail = ({ video }) => {
  return (
    <div className="video-thumbnail">
      <img src={video.thumbnailUrl} alt={video.originalFileName} />
      <h2>{video.title}</h2>
    </div>
  );
};

export default VideoThumbnail;
