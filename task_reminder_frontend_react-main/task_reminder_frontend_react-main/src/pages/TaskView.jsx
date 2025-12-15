//  import { useEffect, useState } from "react";

// import { useParams } from "react-router-dom";
// import API from "../api/api";

// export default function TaskView() {
//   const { id } = useParams();
//   const [task, setTask] = useState(null);

//   useEffect(() => {
//     API.get(`/tasks/${id}`).then(res => setTask(res.data));
//   }, [id]);

//   if (!task) return <p>Loading...</p>;

//   return (
//     <div className="container mt-4">
//       <h2>Task Details</h2>
//       <p><strong>Title:</strong> {task.title}</p>
//       <p><strong>Description:</strong> {task.description}</p>
//       <p><strong>Due:</strong> {task.dueDate}</p>
//       <p><strong>Status:</strong> {task.completed ? "Completed" : "Pending"}</p>
//     </div>
//   );
// }










import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import API, { reminderStatus } from "../api/api";

export default function TaskView() {
  const { id } = useParams();
  const [task, setTask] = useState(null);
  const [status, setStatus] = useState("");

  useEffect(() => {
    API.get(`/tasks/${id}`).then(res => setTask(res.data));
  }, [id]);

  async function checkStatus() {
    const res = await reminderStatus(id);
    setStatus(res.data);
  }

  if (!task) return <p>Loading...</p>;

  return (
    <div className="container mt-4">
      <h2>Task Details</h2>

      <p><strong>Title:</strong> {task.title}</p>
      <p><strong>Description:</strong> {task.description}</p>
      <p><strong>Due:</strong> {task.dueDate}</p>
      <p><strong>Status:</strong> {task.completed ? "Completed" : "Pending"}</p>

      <button className="btn btn-info mt-3" onClick={checkStatus}>
        Check Reminder Status
      </button>

      {status && <p className="mt-3 alert alert-info">{status}</p>}
    </div>
  );
}
