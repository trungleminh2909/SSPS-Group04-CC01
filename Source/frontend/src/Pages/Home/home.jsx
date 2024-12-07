import { useState } from 'react'
import { Link } from 'react-router-dom'
import './home.css'
import Footer from '../../Components/Footer/footer'
import NavBar from '../../Components/NavBar/navBar'

function Home() {

  return (
    <>
    <div className='home-page wrapper'>

      <NavBar></NavBar>

      <div className="home-hero">
        <h1 style={{ "textAlign": "center", "color": "#030391"}}>
          <strong>HỆ THỐNG IN THÔNG MINH</strong>
        </h1>
        <h2><strong>Dịch vụ có sẵn</strong></h2>
        <div className="home-services" style={{ gridAutoFlow: "column"}}>
          <a href='/Print/1' className="home-service-card">
            <h2 >In tài liệu</h2>
            <p>Cung cấp dịch vụ hỗ trợ sinh viên in tài liệu trong khuôn viên ĐHBK</p>
          </a >
          <a className="home-service-card">
            <h2 >Mua trang in</h2>
            <p>Mua thêm trang in ngoài những trang in được cung cấp sẵn</p>
          </a>              
          <a className="home-service-card">
            <h2 >Lịch sử</h2>
            <p>Xem lại các đơn in và giao dịch đã thực hiện <br/>&nbsp;</p>
          </a>
        </div>
        <br />
        <h2><strong>Về chúng tôi</strong></h2>
        <p><strong>Nhóm sinh viên Trường Đại học Bách Khoa - Đại học Quốc gia TPHCM</strong></p>
      </div>

      <Footer></Footer>
    </div>
    </>
  )
}

export default Home
