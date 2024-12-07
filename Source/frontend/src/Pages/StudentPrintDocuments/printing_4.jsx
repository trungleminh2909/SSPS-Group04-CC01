import { useState } from 'react'
import './printing_4.css'
import Footer from '../../Components/Footer/footer'
import NavBar from '../../Components/NavBar/navBar'


function PrintingPage4() {
    return (
        <>
        <div className='wrapper'>
        <NavBar></NavBar>
        <br />
        <div className='title'><strong>THÔNG TIN ĐƠN IN <i style={{ color: "black"}} class="bi bi-file-earmark"></i></strong></div>
        <div className='title' style={{ fontSize: "20px", color: "red", padding: 0}}>Vui lòng kiểm tra trước khi xác nhận </div>

        <button className='printing-continue-btn'><a href="/Print/3">Tiếp tục</a></button> {/*SEND DATA*/}
        <button className='printing-cancel-btn'>Quay lại</button>
        
        <Footer imgSrc='../logobk.png'></Footer>
        </div>
        </>
    )

}

export default PrintingPage4
