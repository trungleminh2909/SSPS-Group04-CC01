import { useState } from 'react'
import './printing_2.css'
import Footer from '../../Components/Footer/footer'
import NavBar from '../../Components/NavBar/navBar'


function PrintingPage2() {
    return (
        <>
        <div className='wrapper'>
        <NavBar></NavBar>
        <br />

        <div className='printing-background-container'>
            <div className='title'><strong>ĐỊNH DẠNG IN <i style={{ color: "black"}} class="bi bi-file-earmark-text"></i></strong></div>

            <div className='printing-setup-field'>
            <ul>

                <form action="" className='printing-setup'>

                    <div className="printing-label-option">
                        <label htmlFor="print-page-size">Khổ giấy</label><br />
                        <label className='left-label' htmlFor="print-page-per-sheet">In 1 mặt/In 2 mặt</label><br />
                        <label className='left-label' htmlFor="print-page-color">In trắng đen/In màu</label>  <br />
                        <label className='left-label' htmlFor="print-pages">Trang cần in</label><br /><br /><br /><br />
                        <label className='left-label' htmlFor="print-copy">Số bản in</label><br />
                    </div>
                    <div className="printing-input-option">
                        <select name="page-size" id="print-page-size">
                                        <option value="A3">A3</option>
                                        <option value="A4">A4</option>
                        </select><br />
                        <select name="page-per-sheet" id="print-page-per-sheet">
                                    <option value="1">In 1 mặt</option>
                                    <option value="2">In 2 mặt</option>
                        </select><br />
                        <select name="page-color" id="print-page-color">
                                <option value="1">In trắng đen</option>
                                <option value="2">In màu</option>
                        </select><br />
                        <input name='pages' type='radio' value="1" id='pages-all' /> <label htmlFor="pages-all">In toàn bộ</label><br />
                        <input name='pages' type='radio' value="2" id='pages-odd' /> <label htmlFor="pages-odd">In trang lẻ</label><br />
                        <input name='pages' type='radio' value="3" id='pages-even' /> <label htmlFor="pages-even">In trang chẵn</label><br />
                        <input name='pages' type='radio' value="4" id='pages-custom' /> <label htmlFor="pages-custom">Tuỳ chọn <input type="text" id='print-custom-pages' placeholder='eg. 1-5, 8, 11-13' /></label>
                        <br />
                        <input type="number" name="print-copy" id="print-copy" placeholder='1'/>
                    </div>
                </form>    
            </ul>
            </div>
        
            <button className='printing-continue-btn'><a href="/Print/3">Tiếp tục</a></button> {/*SEND DATA*/}
            <button className='printing-cancel-btn'>Quay lại</button>
        </div>
        <Footer imgSrc='../logobk.png'></Footer>
        </div>
        </>
    )

}

export default PrintingPage2
