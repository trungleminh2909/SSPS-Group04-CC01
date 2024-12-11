import React, { useState } from "react";
import './AddAccount.css';

function AddAccount() {
  // State để lưu thông tin người dùng
  const [accountType, setAccountType] = useState("student");
  const [formData, setFormData] = useState({
    id: '',
    name: '',
    password: '',
    email: '',
    address: '',
    avatar: '',
    pageNumber: '',
    spsId: ''
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleAccountTypeChange = (e) => {
    setAccountType(e.target.value);
    setFormData({
      id: '',
      name: '',
      password: '',
      email: '',
      address: '',
      avatar: '',
      pageNumber: '',
      spsId: ''
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Tạo tài khoản mới:", formData);
    alert("Tài khoản đã được tạo!");
  };

  return (
    <div className="add-account-container">
      <h1>Thêm Tài Khoản Mới</h1>

      {/* Chọn loại tài khoản */}
      <div className="account-type-selector">
        <label>
          <input
            type="radio"
            name="accountType"
            value="student"
            checked={accountType === "student"}
            onChange={handleAccountTypeChange}
          />
          Sinh viên
        </label>
        <label>
          <input
            type="radio"
            name="accountType"
            value="spsso"
            checked={accountType === "spsso"}
            onChange={handleAccountTypeChange}
          />
          SPSO
        </label>
        <label>
          <input
            type="radio"
            name="accountType"
            value="admin"
            checked={accountType === "admin"}
            onChange={handleAccountTypeChange}
          />
          Admin
        </label>
      </div>

      <form onSubmit={handleSubmit} className="account-form">
        {/* Form cho Sinh viên */}
        {accountType === "student" && (
          <>
            <label>
              ID:
              <input
                type="text"
                name="id"
                value={formData.id}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Họ tên:
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Mật khẩu:
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Email:
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Địa chỉ:
              <input
                type="text"
                name="address"
                value={formData.address}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Avatar:
              <input
                type="text"
                name="avatar"
                value={formData.avatar}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Số trang in:
              <input
                type="number"
                name="pageNumber"
                value={formData.pageNumber}
                onChange={handleInputChange}
                required
              />
            </label>
          </>
        )}

        {/* Form cho SPSO */}
        {accountType === "spsso" && (
          <>
            <label>
              SPSO ID:
              <input
                type="text"
                name="spsId"
                value={formData.spsId}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Họ tên:
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Mật khẩu:
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Email:
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Địa chỉ:
              <input
                type="text"
                name="address"
                value={formData.address}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Avatar:
              <input
                type="text"
                name="avatar"
                value={formData.avatar}
                onChange={handleInputChange}
                required
              />
            </label>
          </>
        )}

        {/* Form cho Admin */}
        {accountType === "admin" && (
          <>
            <label>
              ID:
              <input
                type="text"
                name="id"
                value={formData.id}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Họ tên:
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Mật khẩu:
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Email:
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Địa chỉ:
              <input
                type="text"
                name="address"
                value={formData.address}
                onChange={handleInputChange}
                required
              />
            </label>
            <label>
              Avatar:
              <input
                type="text"
                name="avatar"
                value={formData.avatar}
                onChange={handleInputChange}
                required
              />
            </label>
          </>
        )}

        <button type="submit" className="save-button">Lưu</button>
      </form>
    </div>
  );
}

export default AddAccount;
