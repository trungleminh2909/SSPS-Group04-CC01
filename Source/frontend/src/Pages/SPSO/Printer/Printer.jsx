import React, { useState } from "react";
import './Printer.css';
import Footer from "../../../Components/Footer/footer";
import NavBar from "../../../Components/NavBar/navBar";

function Printer() {
  // Dữ liệu các máy in mẫu
  const [printers, setPrinters] = useState([
    { id: 'P001', brand: 'Canon', location: 'Tòa A4', status: 'active' },
    { id: 'P002', brand: 'HP', location: 'Tòa B2', status: 'inactive' },
    { id: 'P003', brand: 'Epson', location: 'Tòa B4', status: 'active' },
  ]);

  // Dữ liệu cho form thêm máy in
  const [showAddPrinterForm, setShowAddPrinterForm] = useState(false);
  const [newPrinter, setNewPrinter] = useState({
    brand: '',
    location: '',
    status: 'active',  // Mặc định là 'active'
  });

  // Hàm để dừng máy in
  const stopPrinter = (id) => {
    const updatedPrinters = printers.map((printer) => 
      printer.id === id ? { ...printer, status: 'inactive' } : printer
    );
    setPrinters(updatedPrinters);
  };

  // Hàm để khôi phục máy in trở lại trạng thái 'active'
  const activatePrinter = (id) => {
    const updatedPrinters = printers.map((printer) => 
      printer.id === id ? { ...printer, status: 'active' } : printer
    );
    setPrinters(updatedPrinters);
  };

  // Hàm để thêm máy in mới
  const addPrinter = () => {
    const newPrinterData = { ...newPrinter, id: `P00${printers.length + 1}` };
    setPrinters([...printers, newPrinterData]);
    setShowAddPrinterForm(false);  // Đóng form sau khi thêm máy in
    setNewPrinter({ brand: '', location: '', status: 'active' });  // Reset form
  };

  // Hàm để lọc máy in theo trạng thái
  const filterPrinters = (status) => {
    if (status === 'all') return printers;
    return printers.filter((printer) => printer.status === status);
  };

  return (
    <>
    <NavBar></NavBar>
    <div className="printer-container">
      <div className="printer-sidebar">
        <div className="filter-option">
          <button onClick={() => setPrinters(filterPrinters('all'))}>Tất cả máy in</button>
        </div>
        <div className="filter-option">
          <button onClick={() => setPrinters(filterPrinters('active'))}>Đang hoạt động</button>
        </div>
        <div className="filter-option">
          <button onClick={() => setPrinters(filterPrinters('inactive'))}>Không hoạt động</button>
        </div>
        <div className="filter-option">
          <button onClick={() => setShowAddPrinterForm(true)}>Thêm máy in</button>
        </div>
      </div>

      <div className="printer-list">
        <h2>Danh sách máy in</h2>
        <table>
          <thead>
            <tr>
              <th>Mã số</th>
              <th>Hiệu</th>
              <th>Địa điểm</th>
              <th>Trạng thái</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            {printers.map((printer) => (
              <tr key={printer.id}>
                <td>{printer.id}</td>
                <td>{printer.brand}</td>
                <td>{printer.location}</td>
                <td>{printer.status === 'active' ? 'Đang hoạt động' : 'Không hoạt động'}</td>
                <td>
                  {printer.status === 'active' ? (
                    <button onClick={() => stopPrinter(printer.id)}>Dừng</button>
                  ) : (
                    <button onClick={() => activatePrinter(printer.id)}>Hoạt động</button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Form thêm máy in */}
      {showAddPrinterForm && (
        <div className="add-printer-form">
          <h2>Thêm máy in mới</h2>
          <label>
            Tên máy in:
            <input 
              type="text" 
              value={newPrinter.brand} 
              onChange={(e) => setNewPrinter({ ...newPrinter, brand: e.target.value })} 
            />
          </label>
          <label>
            Vị trí:
            <input 
              type="text" 
              value={newPrinter.location} 
              onChange={(e) => setNewPrinter({ ...newPrinter, location: e.target.value })} 
            />
          </label>
          <label>
            Trạng thái:
            <select 
              value={newPrinter.status} 
              onChange={(e) => setNewPrinter({ ...newPrinter, status: e.target.value })}
            >
              <option value="active">Hoạt động</option>
              <option value="inactive">Tạm dừng</option>
            </select>
          </label>
          <button onClick={addPrinter}>Thêm máy in</button>
          <button onClick={() => setShowAddPrinterForm(false)}>Hủy</button>
        </div>
      )}
    </div>
    <Footer></Footer>
    </>
  );
}

export default Printer;
