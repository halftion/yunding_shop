import React, { Component } from 'react';
import { message } from 'antd';
import { Link } from 'react-router-dom';
import './qualitygoods.css';
import $ from 'jquery';

class Qualitygoods extends Component {

    constructor(props) {
        super(props);
        this.state = {
            goods: [],
            platform:[],
            suit:[],
        }
    }

    componentWillMount() {
        $.get("http://qiyubing.cn/yundingShop/api/content/3", (response) => {
            if (response.code === 200) {
                let datalist = response.data;
                this.setState({
                    goods:datalist,
                })    
            } else {
                message.error(response.message,10);
            }
        });

        $.get("http://qiyubing.cn/yundingShop/api/content/4", (response) => {
            if (response.code === 200) {
                let datalist = response.data;
                this.setState({
                    platform:datalist,
                })    
            } else {
                message.error(response.message,10);
            }
        });

        $.get("http://qiyubing.cn/yundingShop/api/content/5", (response) => {
            if (response.code === 200) {
                //console.log(response);
                let datalist = response.data;
                this.setState({
                    suit:datalist,
                })    
            } else {
                message.error(response.message,10);
            }
        });
    }

    renderQualityGoods() {
        const { goods } = this.state;
        let qualityGoods = [];
        for(let i = 0;i < goods.length && i < 2; ++i){
            qualityGoods.push(<Link to={goods[i].url} >
                <div className = "goods">
                    <img src = {goods[i].pic} />
                    <h2>{goods[i].title1}</h2>
                    <p>{goods[i].title2}</p>
                    <span>{goods[i].title3}</span>
                </div>
            </Link>
            );
        }
        return qualityGoods;
    }

    renderPlatform () {
        const { platform } = this.state;
        let platformGoods = [];
        for(let i = 0;i < platform.length && i < 2; ++i){
            platformGoods.push(<Link to={platform[i].url} >
                <div>
                    <h2>{platform[i].title1}</h2>
                    <div>
                        <p>{platform[i].title2}</p>
                        <p>{platform[i].title3}</p>
                        <img src = {platform[i].pic} />
                    </div>    
                </div>
            </Link>
            );
        }
        return platformGoods;
    }

    renderSuitbox () {
        const { suit } = this.state;
        let suitbox = [];
        //console.log(suit);
        if(suit.length !== 0){
        //console.log(suit);
        suitbox.push(<li className="suitbox"><Link to={suit[0].url} >
            <div>
                <h2>{suit[0].title1}</h2>
                <div>
                    <p>{suit[0].title2}</p>
                    <p>{suit[0].title3}</p>
                    <img src = {suit[0].pic} />
                </div>    
            </div>
            </Link>
            <Link to={'/search/精品'}>
                <p>更多精品 <i>&nbsp;&#xe608;</i></p>
            </Link></li>);  
        }
        //console.log(suitbox);
        return suitbox;
}

  render() {
    return (
            <div className="home-list">
                <h1>精选好物</h1>
                <ul className="quality-goods clearfix">
                    <li>
                        {this.renderQualityGoods()}
                    </li>
                    <li className="goods-platformGoodsCateGory">
                       {this.renderPlatform()}
                    </li>
                    {this.renderSuitbox()}  
                </ul>
            </div>
    );
  }
}

export default Qualitygoods;