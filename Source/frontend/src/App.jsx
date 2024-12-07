import { useState } from 'react'
import { BrowserRouter as Router, Route, Routes, useLocation } from "react-router-dom";
import './App.css'
import Footer from './Components/Footer/footer'
import NavBar from './Components/NavBar/navBar'
import Home from "./Pages/Home/home";
import Login from "./Pages/Login/login";
import PrintingPage1 from "./Pages/StudentPrintDocuments/printing_1";
import PrintingPage2 from "./Pages/StudentPrintDocuments/printing_2";
import PrintingPage3 from "./Pages/StudentPrintDocuments/printing_3";
import PrintingPage4 from "./Pages/StudentPrintDocuments/printing_4";
import PrintingPage5 from "./Pages/StudentPrintDocuments/printing_5";


function App() {
  localStorage.removeItem("isLoggedIn")
  localStorage.setItem("isLoggedIn", false)
  return (
    <Router>
      <div className="App">
        <div className="content">
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route path="/Home" element={<Home />} />
            <Route path="/Login" element={<Login />} />
            <Route path="/Print/1" element={<PrintingPage1 />} />
            <Route path="/Print/2" element={<PrintingPage2 />} />
            <Route path="/Print/3" element={<PrintingPage3 />} />
            <Route path="/Print/4" element={<PrintingPage4 />} />
            <Route path="/Print/5" element={<PrintingPage5 />} />
          </Routes>
        </div>
      </div>
    </Router>
  )
}

export default App
