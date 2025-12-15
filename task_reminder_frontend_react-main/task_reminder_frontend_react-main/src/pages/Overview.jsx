import { useEffect, useState } from "react";
import API from "../api/api";

export default function Overview() {
  const [stats, setStats] = useState({ total: 0, completed: 0, pending: 0 });

  useEffect(() => {
    API.get("/reports/overview").then((res) => setStats(res.data));
  }, []);

  function downloadCsv() {
    API.post("/reports/export", {}, { responseType: "blob" })
      .then((res) => {
        const url = window.URL.createObjectURL(new Blob([res.data]));
        const link = document.createElement("a");
        link.href = url;
        link.download = "tasks_report.csv";
        link.click();
      });
  }

  return (
    <div className="container mt-4">
      <h2>Overview</h2>

      <div className="alert alert-info mt-3">
        <p><strong>Total:</strong> {stats.total}</p>
        <p><strong>Completed:</strong> {stats.completed}</p>
        <p><strong>Pending:</strong> {stats.pending}</p>
      </div>

      <button className="btn btn-success" onClick={downloadCsv}>
        Download CSV
      </button>
    </div>
  );
}
