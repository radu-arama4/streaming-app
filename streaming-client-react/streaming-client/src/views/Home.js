import VideoThumbnail from "./VideoThumbnails";
import { useState, useEffect } from "react";

export default function Home(params) {
  // TODO: Fetch from backend

  const [existingVideos, setVideos] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/api/v1/video/all")
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setVideos(data.allVideos);
      });
  }, []);

  return (
    <div className="homepage">
      <div className="video-grid">
        {existingVideos.map((video) => (
          <VideoThumbnail key={video.videoId} video={video} />
        ))}
      </div>
    </div>
  );
}
