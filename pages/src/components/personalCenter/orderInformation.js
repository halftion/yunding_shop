import React,{Component} from 'react';
import './orderInformation.css';
import  { Button,Checkbox,message,Modal } from 'antd';
import { Route,Switch,Link } from 'react-router-dom';
import $ from 'jquery';
import Unpaid from './unpaid';
import Unsigned from './unsigned';
import Unevaluated from './unevaluated';
import Allorder from './allorder';
import { Base64 } from 'js-base64';
import '../../config';

export default class OrderInformation extends Component {
	
	constructor(){
		super();
		this.state = {
			check:false,
			pay:true,
			orderInfo:[],
			sorderGoodsList:[],
			visible:false,
            payment:"",
            payinfo:[],
		}
	}

	componentDidMount() {
		let token = "Bearer "+sessionStorage.getItem("token");
		$.ajax({
			url : 'http://qiyubing.cn/yundingShop/api/order/user',
			type : 'GET',
			headers : {'Authorization':token},
			/* 侧边栏加载 */
			success : (info) => {
				if(info.code === 200){
					let datalist = info.data;
					this.setState({
						orderInfo:datalist.orderInfo,
						sorderGoodsList:datalist.orderGoodsList,
					});
				}else{
					message.error(info.message,2);
				}
			}
		});
	}

	

	/* 全选： */
	checkall = () => {
		let check = this.state.check;
		if(!check){
			for(let i = 0;i < $(".order-secondbox").length; ++i){
				let radio = $(".order-circle2 input")[i];
				radio.checked = !check;
				let subradio = $(".ant-checkbox")[i+1];
				$(subradio).addClass("ant-checkbox-checked");
				//console.log(this.state.check);
				this.setState ({
					check:true,
				});
			}
		}else {
			for(let i = 0;i < $(".order-secondbox").length; ++i){
				let radio = $(".order-circle2 input")[i];
				radio.checked = !check;
				let subradio = $(".ant-checkbox")[i+1];
				$(subradio).removeClass("ant-checkbox-checked");
				//console.log(this.state.check);
				this.setState ({
					check:false,
				});
			}
		}
		
	}

	/* “合并付款”按钮状态修改 */

	changepay = () => {
		this.setState({
			pay:false
		})
	}
	payforbidden = () =>{
		this.setState({
			pay:true
		})
	}

	/* 支付全部 */
    paytogether = () => {
        
        let order = [];
        for(let i = 1;i < $(":checkbox").length; ++i){
            if($(":checkbox")[i].checked == true){
                order.push($(":checkbox")[i].name);
            }
        }
		//console.log(order);
		if (order.length !== 0){
			$.ajax({
            url : `http://qiyubing.cn/yundingShop/api/order/createTrade`,
            type : 'POST',
            dataType: "json",    //返回数据的 类型 text|json|html--
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(order),
            /* 支付 */
            success : (info) => {
              if(info.code === 200){
                let payinfo = info.data;
                let pay = Base64.decode(info.data.html);
                //console.log(pay);
                this.setState({
                  visible:true,
                  payment:pay,
                  tradeid:payinfo.tradeId,
                  payinfo:payinfo,
                });
                setInterval(this.checkPayState,1000);      
              }else{
                message.error(info.message,2);
              }
                  },
                  error:() => {message.error("支付信息获取失败，请检查网络设置",2)}
              });
		}else {
			message.warning("请先选择商品",2);
		}
		
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
                global.unpaid.getdata();
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

	
	render() {
			return (
					<div className="order-bigbox" style={{margin:'auto',width:'100%'}}>
						<div className="order-firstbox">
							<div>
								<Link to="/personal" className="order-goods" onClick={this.payforbidden}>所有订单</Link>
								<Link to="/personal/unpaid" className="order-pay" onClick={this.changepay}>待付款<span className="order-num">({global.order.unpaid})</span></Link>
								<Link to="/personal/unsigned" className="order-receive" onClick={this.payforbidden}>待收货<span className="order-num">({global.order.unsign})</span></Link>
								<Link to="/personal/unvaluated" className="order-assess" onClick={this.payforbidden}>待评价<span className="order-num">({global.order.unevaluated})</span></Link>
							</div>
							<div className="order-firtbox-smallnav">
								<Checkbox className="order-circle" onChange={this.checkall} />
								<p className="order-choice">全选</p>
								<Button className="order-all" disabled={this.state.pay} onClick={this.paytogether}>合并付款</Button>
								<ul className="order-nav">
									<li className="order-shop">商品</li>
									<li className="order-number">数量</li>
									<li className="order-money">金额</li>
									<li className="order-state">交易状态</li>
									<li className="order-operation">交易操作</li>
								</ul>
							</div>
						</div>
						<Switch>
							<Route path="/personal/unpaid" component={Unpaid}  />
							<Route path="/personal/unsigned" component={Unsigned} />
							<Route path="/personal/unvaluated" component={Unevaluated} />
							<Route path="/personal" component={Allorder} />
						</Switch>
						<Modal
							title="快捷支付"
							visible={this.state.visible}
							onCancel={this.handleCancel}
							width="400px"
							maskClosable={true}
							footer={null}
							centered={true}
							className="pay"
							>
							<p>交易号：{this.state.payinfo.tradeId}</p>
							{/* <p>买家昵称：小云云</p> */}
							<div className="pay-window">
								<div></div>
								<div></div>
								<iframe className="ant-pay" scrolling="no" srcdoc={this.state.payment}></iframe>
								<div></div>
								<div></div>
							</div>
							<p className="price">¥<span>{this.state.payinfo.totalPrice}</span></p>
							<div className="pay-dis"><span>&#xe611;</span><p>打开手机支付宝扫一扫付款</p></div>
							<div className="pay-footer clearfix">
								<p><span>&#xe631;</span>云顶商城智能支付保护已开启</p>
								<a>帮助</a>
							</div>
						</Modal>
					</div>
					
					);
			  	}
}