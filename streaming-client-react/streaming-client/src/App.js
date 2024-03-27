import logo from "./logo.svg";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./App.css";
import Home from "./views/Home";
import Video from "./views/Video";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Home />,
    },
    // other pages....
    {
      path: "/video",
      element: <Video />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
