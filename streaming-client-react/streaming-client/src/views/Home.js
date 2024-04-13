import VideoThumbnail from "./VideoThumbnails";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Home(params) {
  const [existingVideos, setVideos] = useState([]);
  const navigate = useNavigate();

  const openVideo = (videoId) => {
    console.log("Displaying video with video ID " + videoId);
    navigate("/video", { state: { videoId: videoId } });
  };

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
          <VideoThumbnail
            key={video.videoId}
            video={video}
            onClick={openVideo}
          />
        ))}
      </div>
    </div>
  );
}
