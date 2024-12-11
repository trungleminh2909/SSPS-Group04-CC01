import React from "react";
import { FaUser } from "react-icons/fa"; // Importing the user icon
import "./Info.css";
import Footer from "../../../Components/Footer/footer";
import NavBar from "../../../Components/NavBar/navBar";

function SPSOInfo() {
  // ví dụ
  const user = {
    name: 'Nguyễn Văn A',
    employeeId: 'NV12345',
    position: 'Nhân viên',
    email: 'nguyen.vana@example.com',
    address: 'Hà Nội, Việt Nam',
    role: 'SPSO' // Thêm role vào dữ liệu người dùng
  };

  return (
    <>
    <NavBar></NavBar>
    <div className="user-info-container">
      <br />
        <h1 className="info-title">HỒ SƠ NGƯỜI DÙNG</h1>
        <div className="user-info">
        {/* Cột chứa biểu tượng và role */}
        <div className="user-left">
          {/* Biểu tượng người dùng */}
          <div className="user-icon">
            <FaUser size={50} color="#fff" />
          </div>
          {/* Dòng "Role" */}
          <div className="user-role">
            <p>{user.role}</p>
          </div>
        </div>

        {/* Cột chứa thông tin người dùng */}
        <div className="user-right">
          <h2>{window.sessionStorage.getItem("staffName")}</h2>
          <p><strong>Mã số nhân viên:</strong> {window.sessionStorage.getItem("staffID")}</p>
          <p><strong>Chức vụ:</strong> {window.sessionStorage.getItem("staffEmail")}</p>
          <p><strong>Email:</strong> {window.sessionStorage.getItem("staffEmail")}</p>
          <p><strong>Địa chỉ:</strong> {window.sessionStorage.getItem("staffAddress")}</p>
        </div>

        {/* Cột chứa thông tin người dùng */}
        <div className="user-right">
          <h2></h2>
          <p></p>
          <p></p>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </p>
          <p><strong></strong></p>
        </div>
      </div>

      {/* Nút đăng xuất */}
      <button className="logout-button" onClick={() => {
        window.sessionStorage.setItem("studentID", "");
        window.sessionStorage.setItem("username", "");
        window.sessionStorage.setItem("role", "");
        window.sessionStorage.setItem("isLoggedIn", false)
        window.location.href = "/Home"
      }}>Đăng xuất</button>
    </div>
    <Footer></Footer>
    </>
  );
}

export default SPSOInfo;