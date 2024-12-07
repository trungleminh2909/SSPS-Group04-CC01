import React from "react";
import { FaUser } from "react-icons/fa"; // Importing the user icon
import "./Info.css";

function Info() {
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
    <div className="user-info-container">
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
          <h2>{user.name}</h2>
          <p><strong>Mã số nhân viên:</strong> {user.employeeId}</p>
          <p><strong>Chức vụ:</strong> {user.position}</p>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Địa chỉ:</strong> {user.address}</p>
        </div>
      </div>

      {/* Nút đăng xuất */}
      <button className="logout-button">Đăng xuất</button>
    </div>
  );
}

export default Info;