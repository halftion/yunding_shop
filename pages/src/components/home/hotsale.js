import React, { Component } from 'react';
import './hotsale.css'
import { message } from 'antd';
import { Link } from 'react-router-dom';
import $ from 'jquery';

class HotSale extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        $.get("http://qiyubing.cn/yundingShop/api/content/1", (response) => {
            if (response.code === 200) {
                let datalist = response.data;
                this.setState({
                    data:datalist,
                })    
            } else {
                message.error(response.message,10);
            }
        });
    }

    renderHotSale = () => {
        const { data } = this.state;
        //console.log(data);
        let hotSale = [];
        for(let i = 0;i < data.length &&  i < 8 ; ++i){
            hotSale.push(<Link to={data[i].url} >
                            <li>
                                <div className = "goods">
                                    <img src = {data[i].pic} />
                                    <h2>{data[i].title1}</h2>
                                    <p>{data[i].title2}</p>
                                    <span>{data[i].title3}</span>
                                </div>
                            </li>
                            </Link>);
        }
        //console.log(hotSale);
        return hotSale; 
    }

  render() {
    return (
        <div className="home-list">
        <h1>热卖商品</h1>
        <ul className="hot-sale-list clearfix">
           {this. renderHotSale()}
        </ul>
    </div>


    );
  }
}

export default HotSale;