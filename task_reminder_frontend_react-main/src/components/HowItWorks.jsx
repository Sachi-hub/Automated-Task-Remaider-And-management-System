// src/components/HowItWorks.jsx
import React from "react";

export default function HowItWorks() {
  const workflow = [
  
  ];

  return (
    <div className="how-it-works">
<h2 className="how-it-works">How It Works</h2>
      
      <div className="workflow-grid">
        {workflow.map((item, index) => (
          <div className="workflow-card" key={index}>
            <span className="step">{item.step}</span>
            <h4>{item.title}</h4>
            <p>{item.desc}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
