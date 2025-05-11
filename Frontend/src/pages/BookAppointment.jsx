import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { useEffect } from "react";

export default function BookAppointment() {
  const { id: doctorId } = useParams();
  const [doctor, setDoctor] = useState(null);
  const user = JSON.parse(localStorage.getItem("user") || "null");
  const u_id=user.user.id;
  const [formData, setFormData] = useState({
    age: "",
    appointmentDate: "",       // Matches Java field 'appointmentDate'
    appointmentTime: "",       // Matches Java field 'appointmentTime'
    gender: "",
    user_id: user.user.id,
    paymentStatus: "paid",     // Matches 'paymentStatus'
    status: "confirmed",       // Example default
  });
  useEffect(() => {
    const fetchDoctor = async () => {
      try {
        const response = await fetch(`http://localhost:8187/api/doctors/${doctorId}`);
        if (response.ok) {
          const data = await response.json();
          console.log(data);
          setDoctor(data);
        } else {
          console.error("Failed to fetch doctor");
        }
      } catch (error) {
        console.error("Error fetching doctor:", error);
      }
    };
  
    fetchDoctor();
  }, [doctorId]);
  
  
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    if (!doctor) {
      alert("Doctor information not loaded.");
      return;
    }
  
    const payload = {
      ...formData,
      doctor: doctor, // full doctor object
    };
  
    try {
      const response = await fetch(`http://localhost:8187/api/appointments/book/${u_id}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });
  
      if (response.ok) {
        alert("Appointment booked successfully!");
      } else {
        console.error("Booking failed:", await response.text());
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };
  

  return (
    <div className="max-w-xl mx-auto mt-10 bg-white shadow p-6 rounded-lg">
      <h2 className="text-2xl font-bold mb-4">Book Appointment</h2>
      <div className="space-y-4">
        <input
          type="number"
          name="age"
          placeholder="Age"
          value={formData.age}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-md"
        />
        <input
          type="date"
          name="appointmentDate"
          value={formData.appointmentDate}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-md"
        />
        <input
  type="time"
  name="appointmentTime"
  value={formData.appointmentTime}
  onChange={handleChange}
  className="w-full border px-4 py-2 rounded-md"
/>

        <select
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded-md"
        >
          <option value="">Select Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
        </select>
       
        <button
          onClick={handleSubmit}
          className="bg-green-600 text-white w-full py-2 rounded-md hover:bg-green-700"
        >
          Proceed to Payment
        </button>
      </div>
    </div>
  );
}
