import { useEffect, useState } from "react";
import API from "../api/api";
import TaskList from "../components/TaskList";

export default function TaskListPage() {
  const [tasks, setTasks] = useState([]);

  function loadTasks() {
    API.get("/tasks/list").then(res => setTasks(res.data));
  }

  useEffect(() => {
    loadTasks();
  }, []);

  function deleteTask(id) {
    API.delete(`/tasks/delete/${id}`).then(loadTasks);
  }

  function completeTask(id) {
    API.put(`/tasks/complete/${id}`).then(loadTasks);
  }

  return (
    <div className="container">
      <h2 className="page-title">All Tasks</h2>

      <TaskList
        tasks={tasks}
        onDelete={deleteTask}
        onComplete={completeTask}
      />
    </div>
  );
}
