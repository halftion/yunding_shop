import React,{Component} from 'react';
import './orderInformation.css';
import  { Button,Checkbox,message } from 'antd';
import { Link } from 'react-router-dom'; 
import moment from 'moment';
import $ from 'jquery';
import '../../config';

export default class Unsigned extends Component {
    
    constructor() {
        super();
        this.state = {
            data:[],
        }
    }

    componentDidMount() {
		let token = "Bearer "+sessionStorage.getItem("token");
		$.ajax({
			url : 'http://qiyubing.cn/yundingShop/api/order/user',
			type : 'GET',
			headers : {'Authorization':token},
			/* 订单获取 */
			success : (info) => {
				if(info.code === 200){
					let datalist = info.data;
					this.setState({
                        data:datalist,
                    });
                    //console.log(this.state.data);
				}else{
					message.error(info.message,2);
				}
            },
            error:() => {message.error("获取订单信息失败，请检查网络设置",2)}
        });
        
    }
    
    sign(orderid) {
        let token = "Bearer "+sessionStorage.getItem("token");
        $.ajax({
            url : `http://qiyubing.cn/yundingShop/api/order/receive/${orderid}`,
			type : 'PUT',
			headers : {'Authorization':token},
			/* 订单获取 */
			success : (info) => {
                if(info.code === 200){
                    message.success(info.message,2);
                    //console.log(this.state.data);
				}else{
					message.error(info.message,2);
				}
            },
            error:() => {message.error("获取订单信息失败，请检查网络设置",2)}
        });
    }

    getdata() {
        let token = "Bearer "+sessionStorage.getItem("token");
		$.ajax({
			url : 'http://qiyubing.cn/yundingShop/api/order/user',
			type : 'GET',
			headers : {'Authorization':token},
			/* 订单获取 */
			success : (info) => {
				if(info.code === 200){
					let datalist = info.data;
					this.setState({
                        data:datalist,
                    });
                    //console.log(this.state.data);
				}else{
					message.error(info.message,2);
				}
            },
            error:() => {message.error("获取订单信息失败，请检查网络设置",2)}
        });
        
    }
    
    
    deleteOrder(orderid) {
        let token = "Bearer "+sessionStorage.getItem("token");
        
        $.ajax({
			url : `http://qiyubing.cn/yundingShop/api/order/${orderid}`,
			type : 'DELETE',
			headers : {'Authorization':token},
			/* 订单删除 */
			success : (info) => {
				if(info.code === 200){
                    message.success(info.message,2);
                    this.getdata();                    
				}else{
					message.error(info.message,2);
				}
            },
            error:() => {message.error("删除订单失败，请检查网络设置",2)}
        });

    }
    
    render(){
        const { data } =this.state;
        let unsigned = [];
        let number = 0;
        if (data.length === 0) {
            unsigned.push(
                <div className = "not-found">
	 						<img src="https://i.loli.net/2018/08/27/5b83e1f8c4628.png" />
	 						<div>
	 							<h1>没有订单</h1>
	 							<p>订单列表空空如也</p>
	 						</div>
	 					</div>
            )
            return unsigned;
        }
        for(let i = 0; i < data.length; ++i){
            if(data[i].orderInfo.state === 1){
                ++number;
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                unsigned.push(
                <div className="order-secondbox">
                    <div className="order-secondbox-top">
                        <p className="order-shopname">{data[i].orderInfo.shopName}</p>
                        <p className="order-data">{date}</p>
                        <p className="order-ordernum">订单号：{data[i].orderInfo.orderId}</p>
                        <p className="order-err" title="删除订单" onClick={() => this.deleteOrder(data[i].orderInfo.orderId)}>X</p>
                    </div>
                    <div className="order-secondbox-items">
                        <Checkbox className="order-circle2" />
                        <img className="order-img" src={data[i].orderGoodsList[0].goodsPic} ></img>
                        <div className="order-secondbox-items1">
                            <Link to={`/goodsdetail/${data[i].orderGoodsList[0].goodsId}`} className="order-information">{data[i].orderGoodsList[0].goodsName}</Link>							
                        </div>
                        <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[0].goodsNum}</p></div>
                        <div className="order-secondbox-items3">
                            <p className="order-secondbox-price">￥{data[i].orderGoodsList[0].totalPrice}</p>			
                        </div>
                        <div className="order-secondbox-items4">
                            <p className="order-secondbox-objectmail">等待卖家发货</p>
                            <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                        </div>
                        <Button className="order-confirm2" onClick={() => this.sign(data[i].orderInfo.orderId)}>确认收货</Button>
                    </div>
                </div>
                );
                if(data[i].orderGoodsList.length > 1){
                    for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                         unsigned.push(
                            <div className="order-secondbox2">
                                <div className="order-secondbox-items">
                                    <img className="order-img order-imgs" src={data[i].orderGoodsList[j].goodsPic} />
                                    <div className="order-secondbox-items1">
                                        <Link to={`/goodsdetail/${data[i].orderGoodsList[j].goodsId}`} className="order-information">{data[i].orderGoodsList[j].goodsName}</Link>								
                                    </div>
                                    <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[j].goodsNum}</p></div>
                                </div>
                            </div>
                        )
                    }   
                }
               
            }else if(data[i].orderInfo.state === 2){
                ++number;
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                unsigned.push(
                <div className="order-secondbox">
                    <div className="order-secondbox-top">
                        <p className="order-shopname">{data[i].orderInfo.shopName}</p>
                        <p className="order-data">{date}</p>
                        <p className="order-ordernum">订单号：{data[i].orderInfo.orderId}</p>
                        <p className="order-err" title="删除订单" onClick={() => this.deleteOrder(data[i].orderInfo.orderId)}>X</p>
                    </div>
                    <div className="order-secondbox-items">
                        <Checkbox className="order-circle2" />
                        <img className="order-img" src={data[i].orderGoodsList[0].goodsPic} ></img>
                        <div className="order-secondbox-items1">
                            <Link to={`/goodsdetail/${data[i].orderGoodsList[0].goodsId}`} className="order-information">{data[i].orderGoodsList[0].goodsName}</Link>							
                        </div>
                        <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[0].goodsNum}</p></div>
                        <div className="order-secondbox-items3">
                            <p className="order-secondbox-price">￥{data[i].orderGoodsList[0].totalPrice}</p>			
                        </div>
                        <div className="order-secondbox-items4">
                            <p className="order-secondbox-objectmail">等待买家收货</p>
                            <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                        </div>
                        <Button className="order-confirm2" onClick={() => this.sign(data[i].orderInfo.orderId)} id={data[i].orderInfo.orderId}>确认收货</Button>
                    </div>
                </div>
                );
                if(data[i].orderGoodsList.length > 1){
                    for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                         unsigned.push(
                            <div className="order-secondbox2">
                                <div className="order-secondbox-items">
                                    <img className="order-img order-imgs" src={data[i].orderGoodsList[j].goodsPic} />
                                    <div className="order-secondbox-items1">
                                        <Link to={`/goodsdetail/${data[i].orderGoodsList[j].goodsId}`} className="order-information">{data[i].orderGoodsList[j].goodsName}</Link>								
                                    </div>
                                    <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[j].goodsNum}</p></div>
                                </div>
                            </div>
                        )
                    }   
                };
               
            } 
            global.order.unsign = number; 
        }    
        return unsigned; 
    }
        
    
}