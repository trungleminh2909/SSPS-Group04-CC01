import { useState } from 'react'
import './printing_5.css'
import Footer from '../../Components/Footer/footer'
import NavBar from '../../Components/NavBar/navBar'


function PrintingPage5() {
    return (
        <>
        <div className='wrapper'>
        <NavBar></NavBar>
        <br />
        <i style={{ color: "green",  }} class="bi bi-check-circle"></i>
        <div className='title'><strong>ĐÃ TẠO ĐƠN IN THÀNH CÔNG <i style={{ color: "black"}} class="bi bi-printer"></i></strong></div>
        <div className='title' style={{ fontSize: "20px", color: "red", padding: 0}}>Vui lòng kiểm tra trước khi xác nhận </div>

        <button className='printing-continue-btn'><a href="/Print/3">Tiếp tục</a></button> {/*SEND DATA*/}
        <button className='printing-cancel-btn'>Quay lại</button>
        
        <Footer imgSrc='../logobk.png'></Footer>
        </div>
        </>
    )

}

export default PrintingPage5
