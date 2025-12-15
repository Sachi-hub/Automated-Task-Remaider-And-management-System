import { useEffect, useState } from "react";
import API from "../api/api";
import TaskList from "../components/TaskList";

export default function Home() {
  const [tasks, setTasks] = useState([]);

  function loadTasks() {
    API.get("/tasks/list").then(res => setTasks(res.data));
  }

  useEffect(() => { loadTasks(); }, []);

  function deleteTask(id) {
    API.delete(`/tasks/delete/${id}`).then(() => {
      alert("Deleted!");
      loadTasks();
    });
  }

  function completeTask(id) {
    API.put(`/tasks/complete/${id}`).then(() => {
      alert("Marked completed!");
      loadTasks();
    });
  }

  return (
    <div className="container mt-3">
      <h2>All Tasks</h2>
      <TaskList tasks={tasks} onDelete={deleteTask} onComplete={completeTask} />
    </div>
  );
}
