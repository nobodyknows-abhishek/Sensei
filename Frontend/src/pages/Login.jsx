import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
  
    const user = { username, password };
  
    try {
      const response = await axios.post("http://localhost:8187/api/auth/login", user);
  
      // Assuming backend returns something like { id: 5, username: "john", ... }
      const userData = response.data;
  
      // Save to localStorage
      localStorage.setItem("user", JSON.stringify(userData));
  
      navigate("/");
    } catch (err) {
      console.error(err);
      setError("Invalid username or password");
    }
  };
  
  return (
    <div className="max-w-md mx-auto mt-10 p-6 border border-gray-300 rounded-lg">
      <h2 className="text-2xl font-semibold text-center mb-4">Login</h2>
      {error && <div className="text-red-600 mb-4">{error}</div>}
      <form onSubmit={handleLogin}>
        <div className="mb-4">
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
          Login
        </button>
        <div className="mt-4 mb-10 text-center">
          <span className="text-sm">Don't have an account? </span>
          <a href="/register" className="text-blue-600 hover:underline mb-24">Register</a>
        </div>
      </form>
      
    </div>
  );
}
