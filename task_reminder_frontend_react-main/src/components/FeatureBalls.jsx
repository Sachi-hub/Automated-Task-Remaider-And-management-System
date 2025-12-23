import { motion } from "framer-motion";
import "../App.css";

export default function FeatureBalls() {
  const features = [
    "Task Creation",
    "Email Reminder",
    "Status Tracking",
    "CSV Export",
    "Dashboard View",
    "Automation"
  ];

  return (
    <div className="feature-ball-container">
      {features.map((f, i) => (
        <motion.div
          key={i}
          className="feature-ball"
          whileHover={{ scale: 1.15 }}
          initial={{ opacity: 0, y: 40 }}
          whileInView={{ opacity: 1, y: 0 }}
          transition={{ delay: i * 0.1 }}
        >
          {f}
        </motion.div>
      ))}
    </div>
  );
}
