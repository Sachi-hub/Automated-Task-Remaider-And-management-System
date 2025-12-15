// App.js
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import AddTask from "./pages/AddTask";
import Overview from "./pages/Overview";
import TaskView from "./pages/TaskView";
import EditTask from "./pages/EditTask";
import "./App.css"; // <-- make sure this line is present

export default function App() {
  return (
    <BrowserRouter>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add" element={<AddTask />} />
        <Route path="/overview" element={<Overview />} />
        <Route path="/task/:id" element={<TaskView />} />
        <Route path="/edit/:id" element={<EditTask />} />
      </Routes>
    </BrowserRouter>
  );
}
