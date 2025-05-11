import React, { useEffect, useState } from "react";
import { Calendar, Clock, Search, Star, MapPin } from "lucide-react";
import FeatureCard from "../components/FeatureCard";
import { useNavigate } from "react-router-dom";

// inside the component

export default function LandingPage() {
  const [doctors, setDoctors] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8187/api/doctors")
      .then((res) => res.json())
      .then((data) => setDoctors(data))
      .catch((err) => console.error("Failed to fetch doctors:", err));
  }, []);

  return (
    <div className="bg-blue-200 min-h-screen px-8 py-16">
      {/* Hero Section */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-10 items-center mb-20">
        <div>
          <h1 className="text-4xl md:text-5xl font-bold mb-4">
            Book Your Doctor’s Appointment Effortlessly
          </h1>
          <p className="text-neutral-700 mb-6 font-semibold">
            Find the right doctor and book an appointment in seconds. Fast,
            easy, and convenient healthcare scheduling.
          </p>
          <div className="flex gap-4">
            <button className="bg-teal-500 text-white px-5 py-2 rounded-md hover:bg-gray-900 transition">
              Book Appointment
            </button>
            <button className="border border-stone-950 px-5 py-2 rounded-md hover:bg-gray-100 transition">
              View Doctors
            </button>
          </div>
        </div>
        <div className="flex justify-center">
          <div className="w-100 h-100  rounded-lg flex items-center justify-center">
            <img
              src="https://imgs.search.brave.com/kN1E_iuGdXYwbuhWRjrn8YR3mtc4C3lblHnGddO9pLM/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly90My5m/dGNkbi5uZXQvanBn/LzAxLzg3LzY4LzI0/LzM2MF9GXzE4NzY4/MjQwMl85Zldtakg3/cXFhZXZaVjNzRk9Y/b0lqUm5LT1J4QlBS/Qi5qcGc"
              alt=""
            />
          </div>
        </div>
      </div>

      {/* Features Section */}
      <div className="text-center mb-12">
        <h2 className="text-3xl font-bold mb-2">Find Your Doctor</h2>
        <p className="text-gray-600 font-semibold">
          Search for specialists in your area and book an appointment instantly.
        </p>
      </div>
      <div className="grid md:grid-cols-3 gap-6 max-w-4xl mx-auto mb-20">
        <FeatureCard
          icon={<Search className="h-8 w-8 text-gray-500" />}
          title="Find Doctor"
          text="Search by specialty, name, or condition"
        />
        <FeatureCard
          icon={<Calendar className="h-8 w-8 text-gray-500" />}
          title="Book Visit"
          text="Select a convenient time slot"
        />
        <FeatureCard
          icon={<Clock className="h-8 w-8 text-gray-500" />}
          title="Get Care"
          text="Visit in-person or via video call"
        />
      </div>

      {/* Top Doctors Section (Fetched from backend) */}
      <div className="grid md:grid-cols-3 gap-6 mb-10">
        {doctors.map((doc) => (
          <div
            key={doc.id}
            className="border rounded-lg shadow-sm overflow-hidden"
          >
            <div className="h-40 bg-gray-200 flex items-center justify-center">
              <span className="text-gray-400">Image</span>
            </div>
            <div className="p-4">
              <h3 className="font-bold">{doc.name}</h3>
              <p className="text-sm text-gray-600">{doc.specialization}</p>
              <div className="flex items-center text-sm text-gray-600 mt-1">
                <Star className="h-4 w-4 text-yellow-500 mr-1" />
                {doc.rating} ({doc.reviews} reviews)
              </div>
              <p className="text-sm text-gray-600 mt-1 flex items-center">
                <MapPin className="h-4 w-4 mr-1" />
                {doc.location}
              </p>
              <p className="text-green-600 text-sm mt-1">{doc.available}</p>
              <button
                onClick={() => navigate(`/book-appointment/${doc.id}`)}
                className="mt-4 bg-black text-white px-4 py-2 rounded-md hover:bg-gray-900"
              >
                Book Now
              </button>
            </div>
          </div>
        ))}
      </div>

      <div className="text-center mb-20">
        <button className="border px-4 py-2 rounded-md hover:bg-gray-100">
          View All Specialists
        </button>
      </div>

      {/* Appointment Booking Form */}
      <div className="grid md:grid-cols-2 gap-10">
        <div>
          <h2 className="text-2xl font-bold mb-2">
            Book Your Next Appointment
          </h2>
          <p className="text-gray-600 mb-6">
            Find available time slots and book instantly. No phone calls needed.
          </p>
          <div className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <select className="border px-4 py-2 rounded-md w-full">
                <option>Select specialty</option>
                <option>Cardiology</option>
                <option>Dermatology</option>
                <option>Neurology</option>
              </select>
              <input
                type="text"
                placeholder="Enter ZIP code"
                className="border px-4 py-2 rounded-md w-full"
              />
            </div>
            <input
              type="text"
              placeholder="dd-mm-yyyy"
              className="border px-4 py-2 rounded-md w-full"
            />
            <input
              type="text"
              placeholder="Enter insurance provider"
              className="border px-4 py-2 rounded-md w-full"
            />
            <button className="bg-black text-white w-full py-2 rounded-md hover:bg-gray-900">
              Search Available Doctors
            </button>
          </div>
        </div>
        <div className="h-1000 rounded-lg flex items-center justify-center">
          <img
            src="https://imgs.search.brave.com/AaOesCIRXBSbLZ3Ka-jJNr4zKIMozr1xAmjVmEKnsX0/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9idXJz/dC5zaG9waWZ5Y2Ru/LmNvbS9waG90b3Mv/ZG9jdG9ycy1vZmZp/Y2UuanBnP3dpZHRo/PTEwMDAmZm9ybWF0/PXBqcGcmZXhpZj0w/JmlwdGM9MA"
            alt=""
            classname="w-auto h-auto"
          />
        </div>
      </div>
    </div>
  );
}
