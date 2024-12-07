import { useState } from 'react'
import './printing_3.css'
import Footer from '../../Components/Footer/footer'
import NavBar from '../../Components/NavBar/navBar'


function PrintingPage3() {

    const checkSelectPrinter = (className) => {
        const collection = document.getElementsByClassName(className)
        let result = false;
        for (let i = 0; i < collection.length; i++) {
            if (collection[i].checked) result = true;
        }
        return result
    };

    return (
        <>
        <div className='wrapper'>
        <NavBar></NavBar>
        <br />

        <div className='printing-background-container'>
            <div className='title'><strong>CHỌN MÁY IN <i style={{ color: "black"}} class="bi bi-printer"></i></strong></div>

            <form action="">
                <div className='printing-setup-field'> 
                <br />
                    <div className='printing-printer'>
                        <div className='printing-printer-left'>
                            <h5>01 - Canon <span style={{ color: "green" }}>(Đang sẵn sàng)</span></h5>
                            <p>Tòa A4 - Cơ sở Lý Thường Kiệt</p>
                        </div>
                        <div className='printing-printer-right'>
                            <input type="radio" className='btn-check printing-printer-radio' name='printing-choose-printer' id='printing-printer-1' value="1" required/>
                            <label class="btn btn-outline-success" for="printing-printer-1">Chọn máy in 1</label>
                        </div>
                    </div>
                    <br />
                    <div className='printing-printer'>
                        <div className='printing-printer-left'>
                            <h5>02 - Panasonic <span style={{ color: "green" }}>(Đang sẵn sàng)</span></h5>
                            <p>Tòa B4 - Cơ sở Lý Thường Kiệt</p>
                        </div>
                        <div className='printing-printer-right'>
                            <input type="radio" className='btn-check printing-printer-radio' name='printing-choose-printer' value="2"  id='printing-printer-2'/>
                            <label class="btn btn-outline-success" for="printing-printer-2">Chọn máy in 2</label>
                        </div>
                    </div>

                    <br />
                    <div className='printing-printer'>
                        <div className='printing-printer-left'>
                            <h5>03 - HP <span style={{ color: "green" }}>(Đang sẵn sàng)</span></h5>
                            <p>Tòa B1 - Cơ sở Lý Thường Kiệt</p>
                        </div>
                        <div className='printing-printer-right'>
                            <input type="radio" className='btn-check printing-printer-radio' name='printing-choose-printer' value="3"  id='printing-printer-3'/>
                            <label class="btn btn-outline-success" for="printing-printer-3">Chọn máy in 3</label>
                        </div>
                    </div>

                <br />
            </div>

            <button
                className='printing-continue-btn'
                type='button'
                onClick={(e) => {
                    if (checkSelectPrinter("printing-printer-radio")) {
                        window.location.href = "/Print/4";
                    } else {
                        alert("Please select a printer!");
                    }
                }}
            >
                Tiếp tục
            </button>
            <button className='printing-cancel-btn'>Quay lại</button>
            </form>
        </div>
        <Footer imgSrc='../logobk.png'></Footer>
        </div>
        </>
    )

}

export default PrintingPage3
