// import React, { useEffect, useState } from "react";
// import { getTask, getScheduleStatus } from "../api/api";

// const ViewTask = ({ id }) => {
//   const [task, setTask] = useState(null);
//   const [status, setStatus] = useState("");

//   useEffect(() => {
//     getTask(id).then(res => setTask(res.data));
//   }, [id]);

//   const loadStatus = async () => {
//     try {
//       const res = await getScheduleStatus(id);
//       setStatus(res.data);
//     } catch {
//       setStatus("Error fetching reminder status");
//     }
//   };

//   return task ? (
//     <div>
//       <h2>Task #{task.id}</h2>
//       <p><b>Title:</b> {task.title}</p>
//       <p><b>Description:</b> {task.description}</p>
//       <p><b>Due Date:</b> {task.dueDate}</p>
//       <p><b>Email:</b> {task.reminderEmail}</p>

//       <button onClick={loadStatus} className="btn-status">Check Reminder Status</button>

//       {status && <p style={{ marginTop: 10, color: "blue" }}>{status}</p>}
//     </div>
//   ) : (
//     <p>Loading...</p>
//   );
// };

// export default ViewTask;
