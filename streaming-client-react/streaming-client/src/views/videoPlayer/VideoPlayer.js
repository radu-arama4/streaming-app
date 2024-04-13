import React, { useState, useRef, useEffect } from "react";
import ReactPlayer from "react-player";

const VideoPlayer = ({ videoUrl }) => {
  const [playing, setPlaying] = useState(false);
  const [range, setRange] = useState({ start: 0, end: 1024 * 1024 }); // Initial range for the first chunk

  useEffect(() => {
    const fetchVideoChunk = async (start, end) => {
      const headers = {
        Range: `bytes=${start}-${end}`,
      };

      const response = await fetch(videoUrl, { headers });
      const videoBlob = await response.blob();

      const arrayBuffer = await videoBlob.arrayBuffer();
      const videoUrl = URL.createObjectURL(new Blob([arrayBuffer]));

      setRange({ start: end + 1, end: end + 1024 * 1024 }); // Next chunk range
      setPlaying(true); // Start playing the video
      return videoUrl;
    };

    // Fetch initial video chunk
    fetchVideoChunk(range.start, range.end);

    return () => {
      setPlaying(false); // Pause the video when component unmounts
    };
  }, [videoUrl, range]);

  const handleProgress = (progress) => {
    const playedSeconds = progress.playedSeconds;
    const contentLength = range.end - range.start + 1;
    const fraction = playedSeconds / progress.loaded;
    const newStart = Math.floor(range.start + contentLength * fraction);
    const newEnd = Math.min(newStart + 1024 * 1024, range.end); // Fetch next chunk after 1MB

    if (newStart >= range.end) {
      fetchVideoChunk(newStart, newEnd);
    }
  };

  return (
    <ReactPlayer
      url={videoUrl}
      playing={playing}
      controls
      width="100%"
      height="auto"
      onProgress={() => {
        handleProgress();
      }}
    />
  );
};

export default VideoPlayer;
