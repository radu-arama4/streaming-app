import { useLocation } from "react-router-dom";
import VideoPlayer from "./VideoPlayer";
import ReactPlayer from "react-player";

const VideoPage = ({ route, navigate }) => {
  const location = useLocation();
  const videoUrl = `http://localhost:8080/api/v1/video/${location.state.videoId}`;
  console.log("Streaming video from " + videoUrl);

  return (
    <div>
      <h2>Video Title: {location.state.videoId}</h2>
      <VideoPlayer videoUrl={videoUrl} />
    </div>
  );
};

export default VideoPage;
