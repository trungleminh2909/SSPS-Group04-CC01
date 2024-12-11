import React, { useState } from "react";
import './Setting.css';
import Footer from '../../../Components/Footer/footer'
import NavBar from '../../../Components/NavBar/navBar'

function Setting() {
  const [pageNumber, setPageNumber] = useState('');
  const [dateAdded, setDateAdded] = useState('');
  const [fileFormats, setFileFormats] = useState({
    pdf: false,
    docx: false,
    doc: false,
    png: false,
    iepg: false,
    jpg: false,
    txt: false,
  });

  // Hàm xử lý lưu cài đặt
  const handleSaveSettings = () => {
    console.log('Cài đặt đã lưu:', {
      pageNumber,
      dateAdded,
      fileFormats,
    });
    alert('Cài đặt đã được lưu');
  };

  // Hàm cập nhật trạng thái các định dạng tệp
  const handleFormatChange = (event) => {
    const { name, checked } = event.target;
    setFileFormats((prevState) => ({
      ...prevState,
      [name]: checked,
    }));
  };

  return (
    <>
    <NavBar></NavBar>
    <div className="settings-container">
      <div className="settings-form">
        <h2>Thiết lập hệ thống</h2>
        
        {/* Số trang mặc định */}
        <div className="form-group">
          <label>Số trang mặc định:</label>
          <input
            type="number"
            value={pageNumber}
            onChange={(e) => setPageNumber(e.target.value)}
            placeholder="Nhập số trang"
          />
        </div>

        {/* Ngày thêm trang mặc định */}
        <div className="form-group">
          <label>Ngày thêm trang mặc định:</label>
          <input
            type="text"
            value={dateAdded}
            onChange={(e) => setDateAdded(e.target.value)}
            placeholder="DD/MM/YYYY"
          />
        </div>

        {/* Định dạng tệp in */}
        <div className="form-group">
          <label>Định dạng tệp in được chấp nhận:</label>
          <div className="file-formats">
            <label>
              <input
                type="checkbox"
                name="pdf"
                checked={fileFormats.pdf}
                onChange={handleFormatChange}
              /> PDF
            </label>
            <label>
              <input
                type="checkbox"
                name="docx"
                checked={fileFormats.docx}
                onChange={handleFormatChange}
              /> DOCX
            </label>
            <label>
              <input
                type="checkbox"
                name="doc"
                checked={fileFormats.doc}
                onChange={handleFormatChange}
              /> DOC
            </label>
            <label>
              <input
                type="checkbox"
                name="png"
                checked={fileFormats.png}
                onChange={handleFormatChange}
              /> PNG
            </label>
            <label>
              <input
                type="checkbox"
                name="iepg"
                checked={fileFormats.iepg}
                onChange={handleFormatChange}
              /> IEPG
            </label>
            <label>
              <input
                type="checkbox"
                name="jpg"
                checked={fileFormats.jpg}
                onChange={handleFormatChange}
              /> JPG
            </label>
            <label>
              <input
                type="checkbox"
                name="txt"
                checked={fileFormats.txt}
                onChange={handleFormatChange}
              /> TXT
            </label>
          </div>
        </div>

        {/* Nút Lưu */}
        <button className="save-button" onClick={handleSaveSettings}>
          Lưu
        </button>
      </div>
    </div>
    <Footer></Footer>
    </>
  );
}

export default Setting;
