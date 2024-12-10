import { useState } from 'react'
import './printing.css'
import Footer from '../../Components/Footer/footer'
import NavBar from '../../Components/NavBar/navBar'


function PrintingPage3() {

    const handleSubmit = () => {
        const selectedPrinter = document.querySelector('input[name="printing-choose-printer"]:checked')
        if (!selectedPrinter) {
            alert("Hãy chọn 1 máy in!")
        } else {
            window.location.href = '/Student/Print/4'
        }
    }

    return (
        <>
        <NavBar></NavBar>
        <div className='wrapper'>
        <br />
        <div className='printing-background-container'>
            <div className='title'><strong>CHỌN MÁY IN <i style={{ color: "black"}} className="bi bi-printer"></i></strong></div>
            <br />
                <div className="printing-form-container">
                    <div className='printing-printer'>
                        <div className='printing-printer-left'>
                            <h5>01 - Canon <span style={{ color: "green" }}>(Đang sẵn sàng)</span></h5>
                            <p>Tòa A4 - Cơ sở Lý Thường Kiệt</p>
                        </div>
                        <div className='printing-printer-right'>
                            <input type="radio" className='btn-check printing-printer-radio' name='printing-choose-printer' id='printing-printer-1' value="1" required/>
                            <label className="btn btn-outline-success" htmlFor="printing-printer-1">Chọn máy in 1</label>
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
                            <label className="btn btn-outline-success" htmlFor="printing-printer-2">Chọn máy in 2</label>
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
                            <label className="btn btn-outline-success" htmlFor="printing-printer-3">Chọn máy in 3</label>
                        </div>
                    </div>
                </div>
            <br /><br /> <br />
            <button className='continue-btn' type='button' onClick={handleSubmit}> Tiếp tục </button>
            <button className='cancel-btn' onClick={() => {window.location.href = '/Student/Print/2'}}>Quay lại</button>
            </div>
        </div>
        <Footer></Footer>
        </>
    )

}

export default PrintingPage3
