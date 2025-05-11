import React from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user") || "null");

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/login");
  };

  return (
    <nav className="bg-slate-900 shadow-md  flex justify-between items-center fixed top-0 left-0 w-full z-50">
  <div className="flex items-center space-x-3">
    <img src="logo.png" alt="Sensei Logo" className="h-20 w-20" />
  </div>

  <div className="space-x-4 flex items-center">
    <Link to="/" className="text-yellow-300 hover:text-yellow-600 font-extrabold">
      Home
    </Link>

    {user ? (
      <>
        <span className="text-gray-700 font-medium">Hi, {user.user.username}</span>
        <button
          onClick={handleLogout}
          className="text-red-600 hover:text-red-800 font-medium"
        >
          Logout
        </button>
      </>
    ) : (
      <>
        <Link to="/login" className="text-yellow-300 hover:text-yellow-600 font-extrabold">
          Login
        </Link>
        <Link to="/register" className="text-yellow-300 hover:text-yellow-600 font-extrabold">
          Register
        </Link>
      </>
    )}
  </div>
</nav>
  );
}
