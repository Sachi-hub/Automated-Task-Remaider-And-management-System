import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import API, { reminderStatus } from "../api/api";

export default function TaskView() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [task, setTask] = useState(null);
  const [status, setStatus] = useState("");

  useEffect(() => {
    API.get(`/tasks/${id}`).then(res => setTask(res.data));
  }, [id]);

  async function checkStatus() {
    const res = await reminderStatus(id);
    setStatus(res.data);
  }

  if (!task) return <p className="text-center mt-4">Loading...</p>;

  return (
    <div className="container mt-4">
      <h2 className="page-title">Task Details</h2>

      {/* TABLE */}
      <table className="table table-bordered table-striped task-view-table">
        <tbody>
          <tr>
            <th>Title</th>
            <td>{task.title}</td>
          </tr>
          <tr>
            <th>Description</th>
            <td>{task.description}</td>
          </tr>
          <tr>
            <th>Due Date</th>
            <td>{task.dueDate}</td>
          </tr>
          <tr>
            <th>Status</th>
            <td>
              <span
                className={`badge ${
                  task.completed ? "bg-success" : "bg-warning text-dark"
                }`}
              >
                {task.completed ? "Completed" : "Pending"}
              </span>
            </td>
          </tr>
        </tbody>
      </table>

      {/* BUTTONS */}
      <div className="d-flex gap-3 mt-3">
        <button className="btn btn-primary" onClick={checkStatus}>
          🔔 Check Reminder Status
        </button>

        <button
          className="btn btn-secondary"
          onClick={() => navigate("/tasks")}
        >
          ⬅ Back to Tasks
        </button>
      </div>

      {/* STATUS MESSAGE */}
      {status && (
        <div className="alert alert-info mt-4">
          <strong>Reminder Status:</strong> {status}
        </div>
      )}
    </div>
  );
}

// import { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
// import API, { reminderStatus } from "../api/api";

// export default function TaskView() {
//   const { id } = useParams();
//   const [task, setTask] = useState(null);
//   const [status, setStatus] = useState("");

//   useEffect(() => {
//     API.get(`/tasks/${id}`).then(res => setTask(res.data));
//   }, [id]);

//   async function checkStatus() {
//     const res = await reminderStatus(id);
//     setStatus(res.data);
//   }

//   if (!task) return <p>Loading...</p>;

//   return (
//     <div className="container mt-4">
//       <h2>Task Details</h2>

//       <p><strong>Title:</strong> {task.title}</p>
//       <p><strong>Description:</strong> {task.description}</p>
//       <p><strong>Due:</strong> {task.dueDate}</p>
//       <p><strong>Status:</strong> {task.completed ? "Completed" : "Pending"}</p>

//       <button className="btn btn-info mt-3" onClick={checkStatus}>
//         Check Reminder Status
//       </button>

//       {status && <p className="mt-3 alert alert-info">{status}</p>}
//     </div>
//   );
// }
