import React, { Component } from 'react';
import { message } from 'antd';
import { Link } from 'react-router-dom';
import './newgoods.css';
import $ from 'jquery';

class Newgoods extends Component {

    constructor() {
        super();
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        $.get("http://qiyubing.cn/yundingShop/api/content/2", (response) => {
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

    renderNewGoods = () => {
        const { data } = this.state;
        //console.log(data);
        let hotSale = [];
        for(let i = 0;i < data.length &&  i < 4 ; ++i){
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
                <h1>新品首发</h1>
                <ul className="new-goods clearfix">
                    {this.renderNewGoods()}
                    <Link to="/search/新品"><div className="more">查看更多 <i>&nbsp;&#xe608;</i></div></Link>
                </ul>
        </div>
    );
  }
}

export default Newgoods;