
import { motion } from "framer-motion";
import FeatureBalls from "../components/FeatureBalls";
import HowItWorks from "../components/HowItWorks";

export default function Home() {
  return (
    <div className="container">
      {/* HERO */}
      <motion.div
        className="hero"
        initial={{ opacity: 0, y: -40 }}
        animate={{ opacity: 1, y: 0 }}
      >
        <h1>MyWays</h1>
        <p className="how-it-works">Automated Task Reminder & Tracking System</p>
      </motion.div>

      <FeatureBalls />
      <HowItWorks />
    </div>
  );
}
