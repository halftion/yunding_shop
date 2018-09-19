import React,{Component} from 'react';
import './orderInformation.css';
import  { Button,Checkbox,message,Modal,Input } from 'antd';
import { Link } from 'react-router-dom'; 
import moment from 'moment';
import $ from 'jquery';
import '../../config';
const { TextArea } = Input;

export default class Unevaluated extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            data:[],
            order:{
	            goodsId : "",
	            orderId : "",
            },
            visible2: false,
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

    /* 评价订单modal */
    handleCancel = () => {
		this.setState({
		  visible2: false,
		});
      }
      
    appriseShow = (goodsId,orderId) => {
        this.setState({
            visible2: true,
            order:{
	            goodsId : goodsId,
	            orderId : orderId,
            },

        })
    }

    apprise = (order) => {
        let token = "Bearer "+sessionStorage.getItem("token");
		let json = {
            "goodsId" : order.goodsId,
            "orderId" : order.orderId,
            "content" : $("#apprise").val()
        };
		$.ajax({
			url : "http://qiyubing.cn/yundingShop/api/goods/comment",	//请求url
			type : "PUT",	//请求类型  post|get
			// data : "key=value&key1=value2",	//后台用 request.getParameter("key");
			dataType: "json",    //返回数据的 类型 text|json|html--
			contentType: "application/json;charset=utf-8",
			headers : {'Authorization':token},
			data:  JSON.stringify(json),
			success: (response) => {
				if(response.code === 200){
                    message.success(response.message,2);
                    this.getdata();
				}else{
					message.error(response.message,2);
				}
			},
			error:() => {message.error("更改失败，请检查网络设置",2);},
		});
    }
    
    /* 删除订单 */
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
        let unevaluated = [];
        let number = 0;
        if (data.length === 0) {
            unevaluated.push(
                <div className = "not-found">
	 						<img src="https://i.loli.net/2018/08/27/5b83e1f8c4628.png" />
	 						<div>
	 							<h1>没有订单</h1>
	 							<p>订单列表空空如也</p>
	 						</div>
	 					</div>
            )
            return unevaluated;
        }
        for(let i = 0; i < data.length; ++i){
            
            if(data[i].orderInfo.state === 3){
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                let num = 0;
                for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                if(data[i].orderGoodsList[j].state === 0){++num;}
                }
                if(num){
                    ++number;
                    if(data[i].orderGoodsList[0].state === 0){
                    unevaluated.push(
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
                                <p className="order-secondbox-objectmail">已收货</p>
                                <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                            </div>
                            <Button className="order-confirm2" onClick={() => this.appriseShow(data[i].orderGoodsList[0].goodsId,data[i].orderInfo.orderId)}  >立即评价</Button>
                        </div>
                    </div>
                );
                }else{
                    unevaluated.push(
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
                                    <p className="order-secondbox-objectmail">已评价</p>
                                    <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                                </div>
                                <Button className="order-confirm2" disabled>评价完成</Button>
                            </div>
                        </div>
                    );
                    }
                    if(data[i].orderGoodsList.length > 1){
                        for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                            if(data[i].orderGoodsList[j].state === 0){
                                unevaluated.push(
                                <div className="order-secondbox2">
                                    <div className="order-secondbox-items">
                                        <img className="order-img order-imgs" src={data[i].orderGoodsList[j].goodsPic} />
                                        <div className="order-secondbox-items1">
                                            <Link to={`/goodsdetail/${data[i].orderGoodsList[j].goodsId}`} className="order-information">{data[i].orderGoodsList[j].goodsName}</Link>								
                                        </div>
                                        <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[j].goodsNum}</p></div>
                                        <div className="order-secondbox-items3">
                                            <p className="order-secondbox-price"></p>			
                                        </div>
                                        <div className="order-secondbox-items4">
                                            <p className="order-secondbox-objectmail">已收货</p>
                                            <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                                        </div>
                                        <Button className="order-confirm2" onClick={() => this.appriseShow(data[i].orderGoodsList[j].goodsId,data[i].orderInfo.orderId)} >立即评价</Button>
                                    </div>
                                    
                                </div>
                            );
                            }else {
                                <div className="order-secondbox2">
                                    <div className="order-secondbox-items">
                                        <img className="order-img order-imgs" src={data[i].orderGoodsList[j].goodsPic} />
                                        <div className="order-secondbox-items1">
                                            <Link to={`/goodsdetail/${data[i].orderGoodsList[j].goodsId}`} className="order-information">{data[i].orderGoodsList[j].goodsName}</Link>
                                            								
                                        </div>
                                        <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[j].goodsNum}</p></div>
                                        <div className="order-secondbox-items3">
                                            <p className="order-secondbox-price"></p>			
                                        </div>
                                        <div className="order-secondbox-items4">
                                            <p className="order-secondbox-objectmail">已评价</p>
                                            <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                                        </div>
                                        <Button className="order-confirm2" disabled>评价完成</Button>
                                    </div>
                                    
                                </div>
                            }       
                        }   
                    }
                }   
            }
            global.order.unevaluated = number;    
        }   
        unevaluated.push(
            <Modal
				title="商品评价"
				visible={this.state.visible2}
				onCancel={this.handleCancel}
				width="26%"
				maskClosable={true}
				footer={null}
				centered={true}
                wrapClassName="setPersonalInfo"
                destroyOnClose={true}
				>
				<TextArea placeholder="您的评价就是对我们最大的支持！" id="apprise" autosize={{ minRows: 5 }} style={{marginBottom:20}}/>		
                <Button style={{width:"100%"}} onClick={() => this.apprise(this.state.order)}>提交评价</Button>
			</Modal>
        );
        
        return unevaluated; 
    }
        
    
}