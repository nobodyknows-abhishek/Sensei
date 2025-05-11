import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import toast, { Toaster } from "react-hot-toast";

export default function RegisterPage() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();

    const newUser = {
      username,
      email,
      password,
    };

    try {
        console.log(newUser);
      const response = await axios.post(
        "http://localhost:8187/api/auth/register", 
        newUser, 
        { headers: { "Content-Type": "application/json" } }
      );
      toast.success("Registration successful! Please log in.");
      console.log(response.data);
      // If registration is successful, redirect to the login page

      navigate("/login");
    } catch (err) {
      console.error(err);
      setError("Username or Email already exists");
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-6 border border-gray-300 rounded-lg">
      <h2 className="text-2xl font-semibold text-center">Register</h2>
      {error && <div className="text-red-600 mb-4">{error}</div>}
      <form onSubmit={handleRegister}>
        <div className="mb-2">
          <label htmlFor="username" className="block text-sm font-medium text-gray-700">Username</label>
          <input
            type="text"
            id="username"
            name="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="w-full p-2 border border-gray-300 rounded mt-1"
            required
          />
        </div>
        <div className="mb-3.5">
          <label htmlFor="email" className="block text-sm font-medium text-gray-700">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full p-2 border border-gray-300 rounded mt-1"
            required
          />
        </div>
        <div className="mb-4">
          <label htmlFor="password" className="block text-sm font-medium text-gray-700">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full p-2 border border-gray-300 rounded mt-1"
            required
          />
        </div>
        <button type="submit" className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700">
          Register
        </button>
        <div className=" text-center">
          <span className="text-sm">Already have an account? </span>
          <a href="/register" className="text-blue-600 hover:underline">Log in</a>
        </div>
      </form>
    </div>
  );
}
