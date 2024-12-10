import React, { useState } from "react";
import './History.css';
import Footer from "../../Components/Footer/footer";
import NavBar from "../../Components/NavBar/navBar";

function History() {
  // Mẫu dữ liệu
  const printHistoryData = [
    {
      printId: 'P001',
      documentName: 'Tài liệu A',
      printDate: '01/12/2024',
      status: 'Đã hoàn thành',
      notes: ''
    },
    {
      printId: 'P002',
      documentName: 'Tài liệu B',
      printDate: '03/12/2024',
      status: 'Đang in',
      notes: 'Chưa hoàn thành'
    },
    {
      printId: 'P003',
      documentName: 'Tài liệu C',
      printDate: '05/12/2024',
      status: 'Đã hoàn thành',
      notes: ''
    },
  ];

  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [filteredHistory, setFilteredHistory] = useState(printHistoryData);

  const handleFilter = () => {
    const filteredData = printHistoryData.filter((item) => {
      const printDate = new Date(item.printDate.split('/').reverse().join('-'));
      const start = new Date(startDate.split('/').reverse().join('-'));
      const end = new Date(endDate.split('/').reverse().join('-'));

      return printDate >= start && printDate <= end;
    });
    setFilteredHistory(filteredData);
  };

  return (
    <>
    <NavBar></NavBar>
    <div className="history-container">
      <h1 className="history-title">LỊCH SỬ IN</h1>

      {/* Form chọn ngày */}
      <div className="date-filter">
        <input 
          type="text" 
          placeholder="Từ ngày (DD/MM/YYYY)" 
          value={startDate} 
          onChange={(e) => setStartDate(e.target.value)} 
        />
        <input 
          type="text" 
          placeholder="Đến ngày (DD/MM/YYYY)" 
          value={endDate} 
          onChange={(e) => setEndDate(e.target.value)} 
        />
        <button onClick={handleFilter}>Lọc</button>
      </div>

      {/* Bảng lịch sử in */}
      <table className="history-table">
        <thead>
          <tr>
            <th>Mã đơn in</th>
            <th>Tên tài liệu</th>
            <th>Ngày thực hiện</th>
            <th>Trạng thái</th>
            <th>Ghi chú</th>
          </tr>
        </thead>
        <tbody>
          {filteredHistory.map((item, index) => (
            <tr key={index}>
              <td>{item.printId}</td>
              <td>{item.documentName}</td>
              <td>{item.printDate}</td>
              <td>{item.status}</td>
              <td>{item.notes}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    <Footer></Footer>
    </>
  );
}

export default History;