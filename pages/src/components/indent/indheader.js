import React, { Component } from 'react';
import './indheader.css'

class Indheader extends Component {

    constructor(props){
        super(props)
    }

    render() {
    return (
        <div className = "ind-header">
            <h1>订单信息</h1>
            <div>
                <p>共{this.props.goodsNum}件宝贝</p>
                <p>商品信息</p>
                <p>单价</p>
                <p>数量</p>
                <p>小计</p>
            </div>
        </div>
    );
  }
}

export default Indheader;