import React, { useState } from 'react';
import { FaUser, FaBell } from 'react-icons/fa'; // Importing icons from react-icons
import './navBar.css'; // Importing the CSS file for styling

function Notification(notifications) {
  return notifications > 0 ? <span className="notification-badge">{notifications}</span> : <></>
}

function LoginButton({ isLoggedIn, notifications }) {
  console.log(isLoggedIn);
  if (isLoggedIn == "true") {
    console.log("True!!!");
    return (
      <>
        <a href="/Login">
          <FaUser className="login-icon" />
        </a>
        <div className="bell-container">
          <FaBell className="bell-icon" />
          {Notification(notifications)}
        </div>
      </>
    );
  } else {
    console.log("False!!!");
    return <a href="/Login">Đăng nhập</a>;
  }
}

function NavBar() {
  // State for notifications
  const [notifications, setNotifications] = useState(5); // Example: 5 notifications
  const isLoggedIn = localStorage.getItem("isLoggedIn")
  // console.log(isLoggedIn)


  return (
  <>
    <nav className="navbar">
      {/* Logo */}
      <div className="nav-logo">
        <img src="/logobk.png" alt="Logo" className="navbar-logo" />
      </div>

      {/* Navigation links */}
      <ul className="nav-links">
        <li className="nav-item"><a href="/">Trang chủ</a></li>
        <li className="nav-item"><a href="/about">In tài liệu</a></li>
        <li className="nav-item"><a href="/services">Mua trang in</a></li>
        <li className="nav-item"><a href="/contact">Lịch sử</a></li>
      </ul>
    
    <div className="nav-extra">
    <LoginButton isLoggedIn={isLoggedIn} notifications={notifications} />
    </div>
  </nav>
  </>
  );
}

export default NavBar;