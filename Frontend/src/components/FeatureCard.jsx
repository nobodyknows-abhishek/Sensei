import React from "react";

const FeatureCard = ({ icon, title, text }) => {
  return (
    <div className="bg-white border rounded-xl shadow-sm p-6 text-center">
      <div className="mb-4 flex justify-center">{icon}</div>
      <h3 className="font-semibold text-lg mb-1">{title}</h3>
      <p className="text-sm text-gray-500">{text}</p>
    </div>
  );
};

export default FeatureCard;