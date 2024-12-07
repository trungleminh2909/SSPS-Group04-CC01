import { useState } from "react";
import "./printing_1.css";
import Footer from "../../Components/Footer/footer";
import NavBar from "../../Components/NavBar/navBar";

function PrintingPage1() {
  const [fileList, setFileList] = useState([]);
  const [uploadStatus, setUploadStatus] = useState("");

  const handleFileChange = (event) => {
    const files = Array.from(event.target.files); // Convert FileList to an array
    setFileList(files);
  };

  const handleSubmit = async () => {
    if (fileList.length === 0) {
      setUploadStatus("Hãy chọn ít nhất 1 file để tải lên!!!");
      return;
    }

    // Prepare FormData for POST request
    const formData = new FormData();
    fileList.forEach((file) => {
      formData.append("files", file); // "files" is the key in the backend to accept files
    });

    try {
      // Make the POST request
      const response = await fetch("http://localhost:8080/upload", {
        method: "POST",
        body: formData,
      });

      if (response.ok) {
        setUploadStatus("Files uploaded successfully!");
      } else {
        setUploadStatus("Failed to upload files. Please try again.");
      }
    } catch (error) {
      console.error("Error uploading files:", error);
      setUploadStatus("An error occurred. Please try again.");
    }
  };

  return (
    <>
      <div className="wrapper">
        <NavBar></NavBar>
        <br />
        <div className="printing-background-container">
          <div className="title">
            <strong>
              TẢI LÊN TỆP CẦN IN{" "}
              <i style={{ color: "black" }} className="bi bi-file-earmark-text"></i>
            </strong>
          </div>
          <div className="upload-file-field">
            <i
              style={{ color: "black", fontSize: "50px" }}
              className="bi bi-folder-symlink-fill"
            ></i>
            <p>Kéo, thả tệp cần in tại đây</p>
            <p>Hoặc</p>
            <input type="file" multiple onChange={handleFileChange} />
          </div>
          <p>Chỉ hỗ trợ file có định dạng {/* danh sách các định dạng */}</p>

          {/* Display the uploaded file names */}
          <div className="uploaded-files">
            <h6>Danh sách tệp đã tải lên:</h6>
            {fileList.length === 0 ? (
              <p>Chưa có tệp nào được tải lên.</p>
            ) : (
              <ul>
                {fileList.map((file, index) => (
                  <li key={index}>File: {file.name}</li>
                ))}
              </ul>
            )}
          </div>

          {/* Display upload status */}
          {uploadStatus && <p className="upload-status" style={{  }}>{uploadStatus}</p>}

          <button
            className="printing-continue-btn"
            type="button"
            onClick={handleSubmit}
          >
            <a href="/Print/2">Tiếp tục</a> 
            {/* Send data */}
          </button>
          <button className="printing-cancel-btn" type="button">
            Huỷ
          </button>
        </div>
        <Footer imgSrc="../logobk.png"></Footer>
      </div>
    </>
  );
}

export default PrintingPage1;
