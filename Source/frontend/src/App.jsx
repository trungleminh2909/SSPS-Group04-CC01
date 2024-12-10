import { useState } from 'react'
import { BrowserRouter as Router, Route, Routes, useLocation } from "react-router-dom";
import './App.css'
import Home from "./Pages/Home/home";
import Info from "./Pages/StudentInfo/Info";
import PaymentHistory from "./Pages/StudentHistory/PaymentHistory";
import PrintHistory from "./Pages/StudentHistory/PrintHistory";
import Login from "./Pages/Login/login";
import PrintingPage1 from "./Pages/StudentPrintDocuments/printing_1";
import PrintingPage2 from "./Pages/StudentPrintDocuments/printing_2";
import PrintingPage3 from "./Pages/StudentPrintDocuments/printing_3";
import PrintingPage4 from "./Pages/StudentPrintDocuments/printing_4";
import PrintingPage5 from "./Pages/StudentPrintDocuments/printing_5";
// import PrintingPage from "./Pages/StudentPrintDocuments/printing";
import BuyPages1 from "./Pages/StudentBuyPages/buypage_1";
import BuyPages2 from "./Pages/StudentBuyPages/buypage_2";
import BuyPages3 from "./Pages/StudentBuyPages/buypage_3";

function App() {
  localStorage.removeItem("isLoggedIn")
  localStorage.setItem("isLoggedIn", false)
  return (
    <Router>
      <div className="App">
        <div className="content">
          <Routes>
            <Route exact path="/" element={<Home/>} />
            <Route path="/Home" element={<Home/>} />
            <Route path="/Login" element={<Login />} />
            <Route path="/Student/Info" element={<Info />} />
            {/* <Route path="/Student/Print" element={<PrintingPage />} /> */}
            <Route path="/Student/Print/1" element={<PrintingPage1 />} />
            <Route path="/Student/Print/2" element={<PrintingPage2 />} />
            <Route path="/Student/Print/3" element={<PrintingPage3 />} />
            <Route path="/Student/Print/4" element={<PrintingPage4 />} />
            <Route path="/Student/Print/5" element={<PrintingPage5 />} />
            <Route path="/Student/History" element={<PrintHistory />} />
            <Route path="/Student/History/Payment" element={<PaymentHistory />} />
            <Route path="/Student/BuyPages/1" element={<BuyPages1 />} />
            <Route path="/Student/BuyPages/2" element={<BuyPages2 />} />
            <Route path="/Student/BuyPages/3" element={<BuyPages3 />} />
          </Routes>
        </div>
      </div>
    </Router>
  )
}

export default App
