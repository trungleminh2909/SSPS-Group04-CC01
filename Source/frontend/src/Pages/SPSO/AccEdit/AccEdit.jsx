import React from "react";
import { FaUser } from "react-icons/fa"; // Importing the user icon
import "./AccEdit.css";

function AccEdit() {
  // ví dụ
  const user = {
    name: 'Nguyễn Văn A',
    employeeId: '1951001',
    email: 'nguyen.vana@example.com',
    address: 'TP. HCM, Việt Nam',
    phone: '0123456789',
    role: 'Sinh viên' // Thêm role vào dữ liệu người dùng
  };

  return (
    <div className="user-info-container">
        <h1 className="info-title">THÔNG TIN TÀI KHOẢN</h1>
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
          <p><strong>ID:</strong> {user.employeeId}</p>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Địa chỉ:</strong> {user.address}</p>
          <p><strong>Số điện thoại:</strong> {user.phone}</p>
        </div>
      </div>

      {/* Nút chỉnh sửa */}
      <button className="logout-button">Chỉnh sửa</button>
    </div>
  );
}

export default AccEdit;
