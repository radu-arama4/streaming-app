import logo from "./logo.svg";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./App.css";
import Home from "./views/Home";
import VideoPage from "./views/videoPlayer/Video";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Home />,
    },
    // other pages....
    {
      path: "/video",
      element: <VideoPage />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
