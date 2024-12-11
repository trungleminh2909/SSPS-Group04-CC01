import React from 'react';
import './Acclist.css';

const Acclist = () => {
  // Danh sách tài khoản mẫu
  const accounts = [
    { id: 1, name: 'Nguyễn Văn A', email: 'nguyenvana@mail.com', role: 'Sinh viên' },
    { id: 2, name: 'Trần Thị B', email: 'tranthib@mail.com', role: 'SPSO' },
    { id: 3, name: 'Lê Quang C', email: 'lequangc@mail.com', role: 'Admin' }
  ];

  return (
    <div className="acclist-container">
      <h1>Danh Sách Tài Khoản</h1>
      <table className="acclist-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Họ và tên</th>
            <th>Địa chỉ mail</th>
            <th>Chức vụ</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {accounts.map((account) => (
            <tr key={account.id}>
              <td>{account.id}</td>
              <td>{account.name}</td>
              <td>{account.email}</td>
              <td>{account.role}</td>
              <td>
                <button className="view-btn">Xem chi tiết</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="add-account-btn">
        <button>Thêm tài khoản</button>
      </div>
    </div>
  );
};

export default Acclist;
