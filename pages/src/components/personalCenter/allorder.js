import React,{Component} from 'react';
import './orderInformation.css';
import  { Button,Checkbox,message,Input,Modal } from 'antd';
import { Link } from 'react-router-dom'; 
import moment from 'moment';
import $ from 'jquery';
import { Base64 } from 'js-base64';
import '../../config';
const { TextArea } = Input;

export default class Allorder extends Component {
    
    constructor() {
        super();
        this.state = {
            data:[],
            order:{
	            goodsId : "",
	            orderId : "",
            },
            visible2: false,
            visible3: false,
            pay:"",
            payinfo:[],
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
    
    /* 订单签收 */
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
                    this.getdata();
                    //console.log(this.state.data);
				}else{
					message.error(info.message,2);
				}
            },
            error:() => {message.error("获取订单信息失败，请检查网络设置",2)}
        });
    }

    /* 订单获取 */
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
    
    /* 订单删除 */
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
                    global.commonright.changesideright();                   
				}else{
					message.error(info.message,2);
				}
            },
            error:() => {message.error("删除订单失败，请检查网络设置",2)}
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
                    this.setState({
                        visible2: false,
                      });
				}else{
					message.error(response.message,2);
				}
			},
			error:() => {message.error("更改失败，请检查网络设置",2);},
		});
    }

    /* 支付弹窗 */
    handleCancel2 = () => {
        this.setState({
        visible3:false
        })
    }

    /* 订单支付 */

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
                  visible3:true,
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
        if(this.state.visible3 === true){
        $.ajax({
            url : `http://qiyubing.cn/yundingShop/api/order/checkPay/${this.state.tradeid}`,
            type : "GET",
            success:(response) => {
            if(response.code === 200){
                message.success("支付成功",2);
                this.setState({
                visible3:false,
                });
                this.getdata();
            }
            },
            error:() => {message.error("获取支付信息失败，请检查网络设置",2);}
        })
        }
    }
    
    render(){
        const { data } =this.state;
        let orders = [];
        let unpaidnumber = 0;
        let unsignnumber = 0;
        let unevaluatednumber = 0;
        if (data.length === 0) {
            orders.push(
                <div className = "not-found">
	 						<img src="https://i.loli.net/2018/08/27/5b83e1f8c4628.png" />
	 						<div>
	 							<h1>没有订单</h1>
	 							<p>订单列表空空如也</p>
	 						</div>
	 					</div>
            )
            return orders;
        }
        for(let i = 0; i < data.length; ++i){
            if(data[i].orderInfo.state === 0){
                ++unpaidnumber;
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                orders.push(
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
                            <p className="order-secondbox-objectmail">等待买家付款</p>
                            <Link to={`/orderdetail/${data[i].orderInfo.orderId}`} className="order-secondbox-orderdetail">订单详情</Link>
                        </div>
                        <Button className="order-confirm2" onClick={() => this.pay(data[i].orderInfo.orderId)}>立即付款</Button>
                    </div>
                </div>
                );
                if(data[i].orderGoodsList.length > 1){
                    for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                         orders.push(
                            <div className="order-secondbox2">
                                <div className="order-secondbox-items">
                                    <img className="order-img order-imgs" src={data[i].orderGoodsList[j].goodsPic} />
                                    <div className="order-secondbox-items1">
                                        <Link to={`/goodsdetail/${data[i].orderGoodsList[j].goodsId}`} className="order-information">{data[i].orderGoodsList[j].goodsName}</Link>								
                                    </div>
                                    <div className="order-secondbox-items2"><p>X{data[i].orderGoodsList[j].goodsNum}</p></div>
                                </div>
                            </div>
                        );
                    }   
                }
               
            }else if(data[i].orderInfo.state === 1){
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                ++unsignnumber;
                orders.push(
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
                        <Button className="order-confirm2" onClick={() => this.sign(data[i].orderInfo.orderId)} >确认收货</Button>
                    </div>
                </div>
                );
                if(data[i].orderGoodsList.length > 1){
                    for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                         orders.push(
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
                ++unsignnumber;
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                orders.push(
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
                        <Button className="order-confirm2" onClick={() => this.sign(data[i].orderInfo.orderId)} >确认收货</Button>
                    </div>
                </div>
                );
                if(data[i].orderGoodsList.length > 1){
                    for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                         orders.push(
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
               
            } else if(data[i].orderInfo.state === 3){
                let date = moment(data[i].orderInfo.createdAt).format("YYYY-MM-DD");
                let num = 0;
                for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                if(data[i].orderGoodsList[j].state === 0){++num;}
                }
                if(num){
                    ++unevaluatednumber;
                    if(data[i].orderGoodsList[0].state === 0){
                    orders.push(
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
                    orders.push(
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
                                orders.push(
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
                                orders.push(
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
                                    
                                </div>);

                            }       
                        }   
                    }
                }else {
                    orders.push(
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
                    if(data[i].orderGoodsList.length > 1){
                        for(let j = 1;j < data[i].orderGoodsList.length;++j ){
                            orders.push(
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
                                    
                                </div>);
                        }


                    }
                }   
            }
            
        }
        orders.push(
            <Modal
				title="商品评价"
				visible={this.state.visible2}
				onCancel={this.handleCancel}
				width="26%"
				maskClosable={true}
				footer={null}
				centered={true}
				wrapClassName="setPersonalInfo"
				>
				<TextArea placeholder="您的评价就是对我们最大的支持！" id="apprise" autosize={{ minRows: 5 }} style={{marginBottom:20}}/>		
                <Button style={{width:"100%"}} onClick={() => this.apprise(this.state.order)}>提交评价</Button>
			</Modal>
        );
        orders.push(
            <Modal
            title="快捷支付"
            visible={this.state.visible3}
            onCancel={this.handleCancel2}
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
          </Modal>
        );
        global.order.unpaid = unpaidnumber;
        global.order.unsign = unsignnumber;
        global.order.unevaluated = unevaluatednumber;
        return orders; 
    }
        
    
}