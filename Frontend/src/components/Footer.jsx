import { HeartIcon } from "lucide-react";
import React from "react";

const Footer = () => {
  return (
    <footer style={footerStyle}>
      <div style={textStyle}>
        
        <br />
        <span>© {new Date().getFullYear()} Sensei. All rights reserved.</span>
      </div>
      <div style={infoStyle}>
        <span>Developed by Abhishek Verma</span>
        <br />
        <span>For any inquiries, contact: support@Sensei.com</span>
      </div>
    </footer>
  );
};

const footerStyle = {
  backgroundColor: "#333",
  color: "#fff",
  textAlign: "center",
  bottom: 0,
  width: "100%",
};

const textStyle = {
  fontSize: "16px",
  fontWeight: "bold",
  marginTop: "29px",
};

const infoStyle = {
  fontSize: "14px",
  
};

export default Footer;
