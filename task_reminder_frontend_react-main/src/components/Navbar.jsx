
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="navbar">
      <div className="logo">MyWays</div>

      <div>
        <Link to="/" className="nav-link">Home</Link>
        <Link to="/add" className="nav-link">Add Task</Link>
        <Link to="/overview" className="nav-link">Overview</Link>
        {/* <Link to="/login" className="btn btn-primary">Login</Link> */}
        <Link to="/tasks">TasksList</Link>

      </div>
    </nav>
  );
}
