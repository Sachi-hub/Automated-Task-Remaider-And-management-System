// import { useEffect, useState } from "react";
// import API from "../api/api";

// export default function Overview() {
//   const [stats, setStats] = useState({ total: 0, completed: 0, pending: 0 });

//   useEffect(() => {
//     API.get("/reports/overview").then((res) => setStats(res.data));
//   }, []);

//   function downloadCsv() {
//     API.post("/reports/export", {}, { responseType: "blob" })
//       .then((res) => {
//         const url = window.URL.createObjectURL(new Blob([res.data]));
//         const link = document.createElement("a");
//         link.href = url;
//         link.download = "tasks_report.csv";
//         link.click();
//       });
//   }

//   return (
//     <div className="container mt-4">
//       <h2>Overview</h2>

//       <div className="alert alert-info mt-3">
//         <p><strong>Total:</strong> {stats.total}</p>
//         <p><strong>Completed:</strong> {stats.completed}</p>
//         <p><strong>Pending:</strong> {stats.pending}</p>
//       </div>

//       <button className="btn btn-success" onClick={downloadCsv}>
//         Download CSV
//       </button>
//     </div>
//   );
// }
import { useEffect, useState } from "react";
import API from "../api/api";
import { Bar, Pie } from "react-chartjs-2";
import {
  Chart as ChartJS,
  BarElement,
  ArcElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend
} from "chart.js";

import "../css/Overview.css";

ChartJS.register(
  BarElement,
  ArcElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend
);

export default function Overview() {
  const [stats, setStats] = useState({
    total: 0,
    completed: 0,
    pending: 0
  });

  useEffect(() => {
    API.get("/reports/overview").then((res) => setStats(res.data));
  }, []);

  function downloadCsv() {
    API.post("/reports/export", {}, { responseType: "blob" }).then((res) => {
      const url = window.URL.createObjectURL(new Blob([res.data]));
      const link = document.createElement("a");
      link.href = url;
      link.download = "tasks_report.csv";
      link.click();
    });
  }

  const barData = {
    labels: ["Total", "Completed", "Pending"],
    datasets: [
      {
        label: "Tasks",
        data: [stats.total, stats.completed, stats.pending],
        backgroundColor: ["#2563eb", "#16a34a", "#dc2626"]
      }
    ]
  };

  const pieData = {
    labels: ["Completed", "Pending"],
    datasets: [
      {
        data: [stats.completed, stats.pending],
        backgroundColor: ["#16a34a", "#dc2626"]
      }
    ]
  };

  return (
    <div className="container">
      <h2 className="page-title">Overview Dashboard</h2>

      {/* SUMMARY TABLE */}
      <table className="overview-table">
        <thead>
          <tr>
            <th>Total Tasks</th>
            <th>Completed</th>
            <th>Pending</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{stats.total}</td>
            <td>{stats.completed}</td>
            <td>{stats.pending}</td>
          </tr>
        </tbody>
      </table>

      {/* CARDS */}
      <div className="overview-cards">
        <div className="card total">
          <h3>Total Tasks</h3>
          <span>{stats.total}</span>
        </div>
        <div className="card completed">
          <h3>Completed</h3>
          <span>{stats.completed}</span>
        </div>
        <div className="card pending">
          <h3>Pending</h3>
          <span>{stats.pending}</span>
        </div>
      </div>

      {/* CHARTS */}
      <div className="charts">
        <div className="chart-box">
          <h3>Tasks Bar Chart</h3>
          <Bar data={barData} />
        </div>

        <div className="chart-box">
          <h3>Completion Pie Chart</h3>
          <Pie data={pieData} />
        </div>
      </div>

      <button className="btn btn-primary mt-4" onClick={downloadCsv}>
        Download CSV
      </button>
    </div>
  );
}
