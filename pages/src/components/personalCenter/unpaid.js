import React,{Component} from 'react';
import './orderInformation.css';
import  { Button,Checkbox,message,Modal } from 'antd';
import { Link } from 'react-router-dom'; 
import moment from 'moment';
import $ from 'jquery';
import { Base64 } from 'js-base64';
import '../../config';

export default class Unpaid extends Component {
    
    constructor() {
        super();
        this.state = {
            data:[],
            visible:false,
            pay:"",
            payinfo:[],
        }
        global.unpaid = this;
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

    /* 支付 */
    pay = (orderid) => {
        let json = [
            orderid
        ];
        $.ajax({
            url : `http://qiyubing.cn/yundingShop/api/order/createTrade`,
            type : 'POST',
            dataType: "json",    //返回数据的 类型 text|json|html--
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(json),
            /* 支付 */
            success : (info) => {
              if(info.code === 200){
                message.success("订单创建成功",2);
                global.commonright.changesideright();
                this.getdata();
                let payinfo = info.data;
                let pay = Base64.decode(info.data.html);
                //console.log(pay);
                this.setState({
                  visible:true,
                  pay:pay,
                  tradeid:payinfo.tradeId,
                  payinfo:payinfo,
                });
                setInterval(this.checkPayState,1000);      
              }else{
                message.error(info.message,2);
              }
                  },
                  error:() => {message.error("订单提交失败，请检查网络设置",2)}
              });
    }

    /* 支付状态查询 */
    checkPayState = () => {
        if(this.state.visible === true){
        $.ajax({
            url : `http://qiyubing.cn/yundingShop/api/order/checkPay/${this.state.tradeid}`,
            type : "GET",
            success:(response) => {
            if(response.code === 200){
                message.success("支付成功",2);
                this.setState({
                visible:false,
                });
                this.getdata();
            }
            },
            error:() => {message.error("获取支付信息失败，请检查网络设置",2);}
        })
        }
    }

    

    /* 支付弹窗 */
    handleCancel = () => {
        this.setState({
        visible:false
        })
    }

    
    render(){
        const { data } =this.state;
        let unpaid = [];
        let number = 0;
        if (data.length === 0) {
            unpaid.push(
                <div className = "not-found">
	 						<img src="https://i.loli.net/2018/08/27/5b83e1f8c4628.png" />
	 						<div>
	 							<h1>没有订单</h1>
	 							<p>订单列表空空如也</p>
	 						</div>
	 					</div>
            )
            return unpaid;
        }
        for(let i = 0; i < data.length; ++i){
            if(data[i].orderInfo.state === 0){
                ++number;
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                unpaid.push(
                <div className="order-secondbox">
                    <div className="order-secondbox-top">
                        <p className="order-shopname">{data[i].orderInfo.shopName}</p>
                        <p className="order-data">{date}</p>
                        <p className="order-ordernum">订单号：{data[i].orderInfo.orderId}</p>
                        <p className="order-err" title="删除订单" onClick={() => this.deleteOrder(data[i].orderInfo.orderId)}>X</p>
                    </div>
                    <div className="order-secondbox-items">
                        <Checkbox className="order-circle2" name={data[i].orderInfo.orderId}/>
                        <img className="order-img" src={data[i].orderGoodsList[0].goodsPic} ></img>
                        <div className="order-secondbox-items1">
                            <Link to={`/goodsdetail/${data[i].orderGoodsList[0].goodsId}`} className="order-information">{data[i].orderGoodsList[0].goodsName}</Link>							
                        </div>
                        <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[0].goodsNum}</p></div>
                        <div className="order-secondbox-items3">
                            <p className="order-secondbox-price">￥{data[i].orderGoodsList[0].totalPrice}</p>			
                        </div>
                        <div className="order-secondbox-items4">
                            <p className="order-secondbox-objectmail">等待买家付款</p>
                            <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                        </div>
                        <Button className="order-confirm2" onClick={() => this.pay(data[i].orderInfo.orderId)}>立即付款</Button>
                    </div>
                </div>
                );
                if(data[i].orderGoodsList.length > 1){
                    for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                         unpaid.push(
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
               
            } 
            global.order.unpaid = number;  
        }
        unpaid.push(<Modal
            title="快捷支付"
            visible={this.state.visible}
            onCancel={this.handleCancel}
            width="400px"
            maskClosable={true}
            footer={null}
            centered={true}
            className="pay"
            destroyOnClose={true}
            >
              <p>交易号：{this.state.payinfo.tradeId}</p>
              {/* <p>买家昵称：小云云</p> */}
              <div className="pay-window">
                <div></div>
                <div></div>
                <iframe className="ant-pay" scrolling="no" srcdoc={this.state.pay}></iframe>
                <div></div>
                <div></div>
              </div>
              <p className="price">¥<span>{this.state.payinfo.totalPrice}</span></p>
              <div className="pay-dis"><span>&#xe611;</span><p>打开手机支付宝扫一扫付款</p></div>
              <div className="pay-footer clearfix">
                <p><span>&#xe631;</span>云顶商城智能支付保护已开启</p>
                <a>帮助</a>
              </div>
          </Modal>);
        return unpaid; 
    }
}