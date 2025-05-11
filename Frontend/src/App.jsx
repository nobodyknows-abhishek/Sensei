import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Toaster } from 'react-hot-toast';

import Home from './pages/Home';
import Navbar from './components/Navbar';
import LoginPage from './pages/Login';
import RegisterPage from './pages/Register';
import BookAppointment from './pages/BookAppointment';
import Footer from './components/Footer';


/**
 * Note about React Router warnings:
 * 
 * These warnings are for future React Router v7 changes:
 * - React Router will begin wrapping state updates in React.startTransition in v7
 * - Relative route resolution within Splat routes is changing in v7
 * 
 * These can be safely ignored for now and will be addressed when upgrading to v7
 */

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-blue-200">
       <Navbar  />
        <main className="container mx-auto px-4 py-8">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/book-appointment/:id" element={<BookAppointment />} />
            {/* Add more routes as needed */}
          </Routes>
        </main>
        <Footer/>
        <Toaster position="bottom-right" />
      </div>
    </Router>
  );
}

export default App;