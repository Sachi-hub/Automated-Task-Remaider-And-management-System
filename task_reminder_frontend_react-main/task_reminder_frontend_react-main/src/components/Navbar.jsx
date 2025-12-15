import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="navbar navbar-dark bg-dark px-3">
      <Link className="navbar-brand" to="/">Task Reminder</Link>

      <div>
        <Link className="btn btn-outline-light me-2" to="/add">Add Task</Link>
        <Link className="btn btn-outline-light" to="/overview">Overview</Link>
      </div>
    </nav>
  );
}
