import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './orderDetails.css';
import  { Button,message,Modal,Input } from 'antd';
import $ from 'jquery';
import { Link } from 'react-router-dom';
import moment from 'moment';
const { TextArea } = Input;



export default class OrderDetails extends Component {
	
	constructor(props){
		super(props);
		this.state = {
			order:[],
			goods:[],
			price:0,
			ordereva:{
	            goodsId : "",
	            orderId : "",
			},
			visible: false,
		}
	}

	componentDidMount(){
		this.getdata();
		window.scrollTo(0, 0);
	}

	getdata = () => {
		let token = "Bearer "+sessionStorage.getItem("token");
    	$.ajax({
			url : `http://qiyubing.cn/yundingShop/api/order/${this.props.match.params.orderid}`,
			type : 'GET',
			headers : {'Authorization':token},
			/* 获取订单详情 */
			success : (info) => {
				if(info.code === 200){
					let orderinfo = info.data.orderInfo;
					let ordergoods = info.data.orderGoodsList;
					this.setState({
						order:orderinfo,
						goods:ordergoods,
          			});
         		// console.log(this.state.cart)
				}else {
					message.error(info.message,2)
				}
			},
			error:() => {message.error("获取订单详情失败，请检查网络设置",2);}
		});
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

	/* 订单渲染 */
	renderOrder = () => {
		const { order } = this.state;
		let orderlist = [];
		orderlist.push(
			<div className="order-details-box2-one">
					<h3 className="order-details-title2">订单概况</h3>
					<span className="order-details-title2-underline"></span>
					<span className="order-details-readdress">收货地址：</span>
					<p className="order-details-address">{order.consignee}，{order.phoneNumber}，{order.address}</p>
					<div>
						<span className="order-details-sendway">运送方式：</span>
						<p className="order-details-way">快递</p>
					</div>
					<div>
						<span className="order-details-sendway">买家备注：</span>
						<p className="order-details-way">{order.remark}</p>
					</div>
			</div>
		);
		orderlist.push(
			<div className="order-details-box2-two">
				<p className="order-details-sell">店铺信息：</p>
				<p className="order-details-nickname">店铺名称：<Link to={`/shop/${order.shopId}`} className="order-details-name">云顶官方商城</Link></p>
			</div>
		);
		return orderlist;
	}

	/* 商品渲染 */
	rendergoods = () => {
		const { goods,order } = this.state;
		let goodlist = [];
		
		if(order.state === 0){
			goodlist.push(
				<div>
					<div className="order-details-secondbox-items1">
						<h1>商品</h1>
						<img className="order-details-img" src={goods[0].goodsPic} alt='' />
						<Link to={`/goodsdetail/${goods[0].goodsId}`} className="order-details-information">{goods[0].goodsName}</Link>										
					</div>
					<div className="order-details-secondbox-items2">
						<p>数量</p>
						<p>X{goods[0].goodsNum}</p>
					</div>
					<div className="order-details-secondbox-items3">
						<p>单价</p>
						<p className="order-details-secondbox-price">￥{goods[0].unitPrice}</p>											
					</div>
					<div className="order-details-secondbox-items4">
						<p>状态</p>
						<Button className="order-details-confirm">立即付款</Button>
					</div>
				</div>
			);
			for(let i = 1;i < goods.length;++i){
				goodlist.push(
					<div >
						<div className="order-details-secondbox-items1">
							<img className="order-details-img" src={goods[i].goodsPic} alt='' />
							<Link to={`/goodsdetail/${goods[i].goodsId}`} className="order-details-information">{goods[i].goodsName}</Link>										
						</div>
					<div className="order-details-secondbox-items2">
						<p>X{goods[i].goodsNum}</p>
						</div>
							<div className="order-details-secondbox-items3">
							<p className="order-details-secondbox-price">￥{goods[i].unitPrice}</p>											
						</div>
					</div>
				)
			}
		}else if(order.state === 1 ||order.state === 2){
			goodlist.push(
				<div>
					<div className="order-details-secondbox-items1">
						<h1>商品</h1>
						<img className="order-details-img" src={goods[0].goodsPic} alt='' />
						<Link to={`/goodsdetail/${goods[0].goodsId}`} className="order-details-information">{goods[0].goodsName}</Link>										
					</div>
					<div className="order-details-secondbox-items2">
						<p>数量</p>
						<p>X{goods[0].goodsNum}</p>
					</div>
					<div className="order-details-secondbox-items3">
						<p>单价</p>
						<p className="order-details-secondbox-price">￥{goods[0].unitPrice}</p>											
					</div>
					<div className="order-details-secondbox-items4">
						<p>状态</p>
						<Button className="order-details-confirm" onClick={() => this.sign(goods[0].orderId)}>确认收货</Button>
					</div>
				</div>
			);
			for(let i = 1;i < goods.length;++i){
				goodlist.push(
					<div >
						<div className="order-details-secondbox-items1">
							<img className="order-details-img" src={goods[i].goodsPic} alt='' />
							<Link to={`/goodsdetail/${goods[i].goodsId}`} className="order-details-information">{goods[i].goodsName}</Link>										
						</div>
					<div className="order-details-secondbox-items2">
						<p>X{goods[i].goodsNum}</p>
						</div>
							<div className="order-details-secondbox-items3">
							<p className="order-details-secondbox-price">￥{goods[i].unitPrice}</p>											
						</div>
					</div>
				)
			}
		}else if(order.state === 3){
			goodlist.push(
				<div>
					<div className="order-details-secondbox-items1">
						<h1>商品</h1>
						<img className="order-details-img" src={goods[0].goodsPic} alt='' />
						<Link to={`/goodsdetail/${goods[0].goodsId}`} className="order-details-information">{goods[0].goodsName}</Link>										
					</div>
					<div className="order-details-secondbox-items2">
						<p>数量</p>
						<p>X{goods[0].goodsNum}</p>
					</div>
					<div className="order-details-secondbox-items3">
						<p>单价</p>
						<p className="order-details-secondbox-price">￥{goods[0].unitPrice}</p>											
					</div>
					<div className="order-details-secondbox-items4">
						<p>状态</p>
						{goods[0].state?<Button className="order-details-confirm" disabled>评价完成</Button>:<Button className="order-details-confirm" onClick={() => this.appriseShow(goods[0].goodsId,goods[0].orderId)} >立即评价</Button>}
					</div>
				</div>
			);
			for(let i = 1;i < goods.length;++i){
				goodlist.push(
					<div >
						<div className="order-details-secondbox-items1">
							<img className="order-details-img" src={goods[i].goodsPic} alt='' />
							<Link to={`/goodsdetail/${goods[i].goodsId}`} className="order-details-information">{goods[i].goodsName}</Link>										
						</div>
						<div className="order-details-secondbox-items2">
							<p>X{goods[i].goodsNum}</p>
						</div>
						<div className="order-details-secondbox-items3">
							<p className="order-details-secondbox-price">￥{goods[i].unitPrice}</p>											
						</div>
						<div className="order-details-secondbox-items4">
							{goods[i].state?<Button className="order-details-confirm" disabled>评价完成</Button>:<Button className="order-details-confirm" onClick={() => this.appriseShow(goods[i].goodsId,goods[i].orderId)} >立即评价</Button>}
						</div>
					</div>
				)
			}
		}
		return goodlist;
	}

	/* modal渲染 */
	rendermodal = () => {
		const { order } = this.state;
		let modal = [];
		if(order.state === 3){
			modal.push(
				<Modal
					title="商品评价"
					visible={this.state.visible}
					onCancel={this.handleCancel}
					width="26%"
					maskClosable={true}
					footer={null}
					centered={true}
					wrapClassName="setPersonalInfo"
				>
					<TextArea placeholder="您的评价就是对我们最大的支持！" id="apprise" autosize={{ minRows: 5 }} style={{marginBottom:20}}/>		
                	<Button style={{width:"100%"}} onClick={() => this.apprise(this.state.ordereva)}>提交评价</Button>
				</Modal>
			)
		}
		return modal;
	}

	renderexpress = () => {
		const { order } = this.state;
		let express = [];
		if(order.state === 2|| order.state === 3){
			express.push(
				<div className="Order-details-box3">
						<h3 className="order-details-title2">快递信息</h3>
						<span className="order-details-title2-underline"></span>
						
						<div>
							<span className="order-details-sendway1">物流公司：</span>
							<p className="order-details-way">{order.expressCompany}</p>
						</div>
						<div>
							<span className="order-details-sendway">运单号码：</span>
							<p className="order-details-way">{order.trackingNum}</p>
						</div>
					</div>
			)
		}
		return express;
	}


	/* 签收 */
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
	
	/* 评价 */

	handleCancel = () => {
		this.setState({
		  visible: false,
		});
      }
      
    appriseShow = (goodsId,orderId) => {
        this.setState({
            visible: true,
            ordereva:{
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
					this.setState({
						visible:false,
					})
				}else{
					message.error(response.message,2);
				}
			},
			error:() => {message.error("更改失败，请检查网络设置",2);},
		});
    }

	render() {
			return (
				<div className="order-details">										
					<div className="order-details-box1">
                        <h2 className="order-details-title">订单详情</h2>
                    </div>
                    <div className="order-details-box2">
						{this.renderOrder()}
						<div className="order-details-box2-three">
							<p className="order-details-orderitems">订单信息：</p>
							<span className="order-details-order-num">订单编号：<p className="order-details-num">{this.state.order.orderId}</p></span>
							<span className="order-details-trade-num">支付宝交易号：<p className="order-details-trade">{this.state.order.alipayNum ? this.state.order.alipayNum:"未支付，暂无交易号"}</p></span>
							<div><span className="order-details-crate-time">创建时间：<p className="order-details-time">{moment(this.state.order.createdAt).format("YYYY-MM-DD HH:mm:ss")}</p></span></div>

							
							<div className="order-details-secondbox">
								<div className="order-details-secondbox-items">																		
									{this.rendergoods()}	
								</div>	
							</div>
							<div className="order-details-sum">
								<p>订单总额：<span className="order-details-allprices">{parseFloat(this.state.order.totalPrice).toFixed(2)}</span> 元</p>
							</div>
						</div>
                    </div>
                    {this.renderexpress()}
                    {/*<div className="Order-details-box4">
						<div className="Order-details-follow">
							<h3 className="order-details-title2">订单跟踪</h3>
							<span className="order-details-follow-underline"></span>
						</div>
						</div>*/}
					{ this.rendermodal() }			
				</div>
					);
			  	}
}

