// import { Link } from "react-router-dom";
// import API from "../api/api";
// export default function TaskList({ tasks, onDelete, onComplete }) {
//   return (
//     <table className="table table-bordered mt-3">
//       <thead className="table-dark">
//         <tr>
//           <th>ID</th>
//           <th>Title</th>
//           <th>Due</th>
//           <th>Status</th>
//           <th>Actions</th>
//         </tr>
//       </thead>

//       <tbody>
//         {tasks.map((t) => (
//           <tr key={t.id}>
//             <td>{t.id}</td>
//             <td>{t.title}</td>
//             <td>{t.dueDate}</td>
//             <td>{t.completed ? "Completed" : "Pending"}</td>

//             <td>
//               <Link to={`/task/${t.id}`} className="btn btn-sm btn-primary me-2">View</Link>
//               <Link to={`/edit/${t.id}`} className="btn btn-sm btn-warning me-2">Edit</Link>
//               {!t.completed &&
//                 <button className="btn btn-sm btn-success me-2" onClick={() => onComplete(t.id)}>Complete</button>
//               }
//               <button className="btn btn-sm btn-danger" onClick={() => onDelete(t.id)}>Delete</button>
//             </td>
//           </tr>
//         ))}
//       </tbody>
//     </table>
//   );
// }















import { Link } from "react-router-dom";
import { scheduleReminder } from "../api/api";

export default function TaskList({ tasks, onDelete, onComplete }) {

  async function handleSchedule(id) {
    try {
      const res = await scheduleReminder(id);
      alert(res.data);
    } catch (e) {
      alert("Failed to schedule reminder");
    }
  }

  return (
    <table className="table table-bordered mt-3">
      <thead className="table-dark">
        <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Due</th>
          <th>Status</th>
          <th>Actions</th>
          <th>Reminder</th> {/* NEW COLUMN */}
        </tr>
      </thead>

      <tbody>
        {tasks.map((t) => (
          <tr key={t.id}>
            <td>{t.id}</td>
            <td>{t.title}</td>
            <td>{t.dueDate}</td>
            <td>{t.completed ? "Completed" : "Pending"}</td>

            <td>
              <Link to={`/task/${t.id}`} className="btn btn-sm btn-primary me-2">View</Link>
              <Link to={`/edit/${t.id}`} className="btn btn-sm btn-warning me-2">Edit</Link>

              {!t.completed &&
                <button className="btn btn-sm btn-success me-2"
                        onClick={() => onComplete(t.id)}>
                  Complete
                </button>
              }

              <button className="btn btn-sm btn-danger"
                      onClick={() => onDelete(t.id)}>
                Delete
              </button>
            </td>

            {/* ⭐ NEW BUTTON */}
            <td>
              <button
                className="btn btn-sm btn-info"
                onClick={() => handleSchedule(t.id)}
              >
                Schedule
              </button>
            </td>

          </tr>
        ))}
      </tbody>
    </table>
  );
}

