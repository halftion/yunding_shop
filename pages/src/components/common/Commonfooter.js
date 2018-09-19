import React, { Component } from 'react';
import './Commonfooter.css';
class Commonfooter extends Component {

  render() {
    return (
        <div className="footer">
            <div className="footer-item">
                <ul>
                    <li>
                        <span>&#xe618;</span>
                        <p>正版保证</p>
                    </li>
                    <li>
                        <span>&#xe625;</span>
                        <p>物流透明</p>
                    </li>
                    <li>
                        <span>&#xe61e;</span>
                        <p>实物实拍</p>
                    </li>
                    <li>
                        <span>&#xe61b;</span>
                        <p>全品包邮</p>
                    </li>
                </ul>
                <div className="footer-message">
                    <div className="message">
                        <img src="https://i.loli.net/2018/08/28/5b8547014fea7.png" alt=""></img>
                        <p><span>QQ：3120716436</span><span>邮编：030600</span></p>
                        <p>周一至周六9:00-21:30</p>
                        <p>山西省晋中市榆次区乌金山镇大学街209号</p>
                    </div>
                    <div className="qr">
                        <img src="https://i.loli.net/2018/08/23/5b7e77f0d8f3a.png" />
                        <p>手机扫描下载云顶APP</p>
                    </div>
                    <div className="qr">
                        <img src="https://i.loli.net/2018/08/23/5b7e77f0e4654.png" />
                        <p>手机扫描查看网页版</p>
                    </div>
                </div>
            </div>
            <div className="footer-menu">
                <ul>
                    <li><a href="#">云顶商城</a></li>
                    <li><a target="_blank" href="###">云顶书院官网</a></li>
                    {/* <li><a target="_blank" href="https://www.taobao.com">淘宝网</a></li> */}
                    {/* <li><a target="_blank" href="https://www.jd.com">京东商城</a></li> */}
                    {/* <li><a target="_blank" href="https://www.suning.com">苏宁易购</a></li> */}
                </ul>
                <ul>
                    <li>© 云顶书院 版权所有</li>
                    <li><a href="#">手机版</a></li>
                    <li><a href="#">关于我们</a></li>
                    <li>本站由云顶电商项目组建设</li>
                </ul>
            </div>
        </div>
    );
  }
}

export default Commonfooter;