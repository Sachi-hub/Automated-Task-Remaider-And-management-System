import { useState } from "react";
import API from "../api/api";
import { useNavigate } from "react-router-dom";

export default function AddTask() {
  const navigate = useNavigate();

  const [task, setTask] = useState({
    title: "",
    description: "",
    dueDate: "",
    reminderEmail: "",
    reminderMinutesBefore: 30
  });

  function handleChange(e) {
    setTask({ ...task, [e.target.name]: e.target.value });
  }

  function handleSubmit(e) {
    e.preventDefault();

    API.post("/tasks/add", task).then(() => {
      alert("Task created!");
      navigate("/");
    });
  }

  return (
    <div className="container mt-4">
      <h2>Add New Task</h2>

      <form onSubmit={handleSubmit}>
        <input name="title" className="form-control mt-2" placeholder="Title" onChange={handleChange} />
        <textarea name="description" className="form-control mt-2" placeholder="Description" onChange={handleChange} />
        <input name="dueDate" type="datetime-local" className="form-control mt-2" onChange={handleChange} />
        <input name="reminderEmail" className="form-control mt-2" placeholder="Reminder Email" onChange={handleChange} />
        <input name="reminderMinutesBefore" className="form-control mt-2" placeholder="Reminder Minutes" onChange={handleChange} />

        <button className="btn btn-primary mt-3">Save Task</button>
      </form>
    </div>
  );
}









