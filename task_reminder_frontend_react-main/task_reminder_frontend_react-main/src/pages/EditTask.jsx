// import { useParams, useEffect, useState } from "react";
// import API from "../api/api";
// import { useNavigate } from "react-router-dom";

// export default function EditTask() {
//   const { id } = useParams();
//   const navigate = useNavigate();
//   const [task, setTask] = useState(null);

//   useEffect(() => {
//     API.get(`/tasks/${id}`).then(res => setTask(res.data));
//   }, [id]);

//   function handleChange(e) {
//     setTask({ ...task, [e.target.name]: e.target.value });
//   }

//   function handleSubmit(e) {
//     e.preventDefault();
//     API.put(`/tasks/update/${id}`, task).then(() => {
//       alert("Updated!");
//       navigate("/");
//     });
//   }

//   if (!task) return <p>Loading...</p>;

//   return (
//     <div className="container mt-4">
//       <h2>Edit Task</h2>
//       <form onSubmit={handleSubmit}>
//         <input name="title" value={task.title} className="form-control mt-2" onChange={handleChange} />
//         <textarea name="description" value={task.description} className="form-control mt-2" onChange={handleChange} />
//         <input name="dueDate" type="datetime-local" value={task.dueDate} className="form-control mt-2" onChange={handleChange} />
//         <button className="btn btn-primary mt-3">Save</button>
//       </form>
//     </div>
//   );
// }
import {  useEffect, useState } from "react";
import { useNavigate,useParams } from "react-router-dom";

import API from "../api/api";

export default function EditTask() {
  const { id } = useParams();   // ✔ Works now!
  const navigate = useNavigate();

  const [task, setTask] = useState(null);

  useEffect(() => {
    API.get(`/tasks/${id}`).then(res => setTask(res.data));
    
  }, [id]);

  if (!task) return <p>Loading...</p>;

  function handleChange(e) {
    setTask({ ...task, [e.target.name]: e.target.value });
  }

  function handleSubmit(e) {
    e.preventDefault();
    API.put(`tasks/update`, task).then(() => {
   // API.put(`/tasks/update/${id}`, task).then(() => {
      alert("Task updated!");
      navigate("/");
    });
  }

  return (
    <div className="container mt-4">
      <h2>Edit Task</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="title"
          value={task.title}
          className="form-control mt-2"
          onChange={handleChange}
        />

        <textarea
          name="description"
          value={task.description}
          className="form-control mt-2"
          onChange={handleChange}
        />

        <input
          name="dueDate"
          type="datetime-local"
          value={task.dueDate}
          className="form-control mt-2"
          onChange={handleChange}
        />

        <button className="btn btn-primary mt-3">Save</button>
      </form>
    </div>
  );
}
