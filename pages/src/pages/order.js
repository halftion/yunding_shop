import React, { Component } from 'react';
import '../components/indent/recinfo.css';
import Indheader from '../components/indent/indheader';
import { Form, Input,message, Button,Modal } from 'antd';
import moment from 'moment';
import { Link } from 'react-router-dom';
import './order.css';
import $ from 'jquery';
import { Base64 } from 'js-base64';
import '../config';

const {TextArea} = Input;
const FormItem = Form.Item;

let check = true;


class Order extends Component {

  constructor(props){
    super(props);
    this.state = {
      cart:[],
      remark:"",
      address:"",
      phoneNumber:"",
      consignee:"",
      visible:false,
      pay:"",
      tradeid:"",
      payinfo:[],
    };
  }

  componentWillMount(){
		let expirein = sessionStorage.getItem("expirein");
        let time = moment().unix()*1000;
        if((expirein - time) <= 1800000){
			    message.error("登录已过期，请重新登录",2);
			  this.props.history.push('/');
        } 
	}

  componentDidMount() {
    this.getdata();
    global.commonright.carthidden();
    window.scrollTo(0, 0);
  }

  /* 数据获取 */
  getdata = () => {
    let token = "Bearer "+sessionStorage.getItem("token");
    $.ajax({
			url : `http://qiyubing.cn/yundingShop/api/cart/`,
			type : 'GET',
			headers : {'Authorization':token},
			/* 获取购物车 */
			success : (info) => {
				if(info.code === 200){
					let datalist = info.data;
					this.setState({
            cart:datalist,
          });
         // console.log(this.state.cart)
				}else {
					message.error(info.message,2)
				}
      },
      error:() => {message.error("获取购物车失败，请检查网络设置",2);}
		});
  }

  /* 商品渲染2018.08.25 */
  cartrender = () => {
    const { cart } = this.state;
    let goods = [];
    for(let i = 0;i < cart.length ;++i){
      goods.push(
        <label className="goods-info" onClick={this.price} id={i}>
                <div className="checkbox" >
                  <input type="checkbox" id={"radio"+i} name={cart[i].goodsId}/>
                  <div></div>
                </div>
                <div>
                  <Link to={`/shop/${cart[i].shopId}`}>{cart[i].shopName} &nbsp;&gt;</Link>
                  <img src={cart[i].picture} />
                  <Link to={`/goodsdetail/${cart[i].goodsId}`} className="goodsname">{cart[i].name}</Link>
                  <p>{cart[i].property}</p>
                </div>
                <div id={"unitprice"+i}>¥{cart[i].price.toFixed(2)}</div>
                <div>
                  <i onClick={this.numreduce} id={i}>&#xe624;</i>
                  <input type="number" className="number-input" onChange={this.changegoodsnum} id={"number"+i} defaultValue="1" min="1" max={cart[i].stockNum}/>
                  <i onClick={this.numadd} id={i}>&#xe624;</i>
                </div>
                <div id={"price"+i}>¥{cart[i].price.toFixed(2)}</div>
            </label>
        );
      }
    return goods;
  }

  /* 收货信息填写控件2.0，2018.8.25 */
  changename = (event) => {
    let node = event.currentTarget;
    let temp=node.value;
    this.setState({
      consignee:temp,
    })
  }

  changephone = (event) => {
    let node = event.currentTarget;
    let temp = node.value;
    this.setState({
      phoneNumber:temp,
    })
  }

  changeadd = (event) => {
    let node = event.currentTarget;
    let temp= node.value;
    this.setState({
      address:temp,
    })
  }

  changeremark = (event) => {
    let node = event.currentTarget;
    let temp= node.value;
    this.setState({
      remark:temp,
    })
  }

  /* 订单结算控件，2018.8.13 */
  counter = () => {
      let sum = 0.00;
      let num = 0;
      for(let i=0;i<document.getElementsByClassName("goods-info").length;i++){
        if(document.getElementById("radio"+i).checked === true){
          let temp = document.getElementById("price"+i).innerHTML;
          let price = parseFloat(temp.substring(1));
          sum=sum+price;
          ++num;  
        }
      }
      document.getElementById("goodNum").innerHTML=num;
      document.getElementById("totalPrice").innerHTML = sum.toFixed(2);
    }

  price = (event) => {
    let node = event.currentTarget;
    let unitprice = document.getElementById("unitprice"+node.id).innerHTML;
    unitprice=unitprice.substring(1);
    let number = document.getElementById("number"+node.id).value;
    let price = parseFloat(parseFloat(unitprice)*number);
    document.getElementById("price"+node.id).innerHTML=`¥${price.toFixed(2)}`;
    this.counter();
  }

  numreduce = (event) => {
    let node = event.currentTarget;
    let temp = $(node).next().val();
    --temp;
    $(node).next().val(temp);
    let radio = document.getElementById("radio"+node.id);
    radio.checked = false;
  }

  changegoodsnum = (event) => {
    let node = event.currentTarget;
    let temp = $(node).val();
    let max = parseInt($(node).attr("max"));
    let min = parseInt($(node).attr("min"));
    if(temp > max){
      temp = max;
    }else if(temp < min){
      temp = min;
    }
    $(node).val(temp);
    this.counter();
  }

  numadd = (event) => {
    let node = event.currentTarget;
    let temp = $(node).prev().val();
    ++temp;
    $(node).prev().val(temp);
    let radio = document.getElementById("radio"+node.id);
    radio.checked = false;
  }

  checkall = () => {
    
    for(let j=0;j<document.getElementsByClassName("goods-info").length;j++){
      let radio = document.getElementById("radio"+j);
      radio.checked = check;
    }
    check=!check;
    //console.log(check);
    this.counter();
  }

  /* 删除购物车中商品2018.8.25 */
  removegoods = () => {
    const { cart } = this.state;
    let token = "Bearer "+sessionStorage.getItem("token");
    for(let i = 0;i < cart.length;++i){
      let radio = document.getElementById("radio"+i);
      if(radio.checked == true){
        $.ajax({
          url : `http://qiyubing.cn/yundingShop/api/cart/${cart[i].goodsId}`,
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
                error:() => {message.error("删除商品失败，请检查网络设置",2)}
            });
      }
    }
  }

  /* 提交订单2018.8.25 */
  submitOrder = () => {
    const { cart } = this.state;
    let token = "Bearer "+sessionStorage.getItem("token");
    let orderGoodsList = [];
    for(let i = 0;i < cart.length;++i){
      let radio = document.getElementById("radio"+i);
      if(radio.checked == true){
        let goodsNum = parseInt($("#number"+i).val());
        orderGoodsList.push(
          {
            "goodsId":cart[i].goodsId,
            "goodsNum":goodsNum,
          }
        );
      }
    }

    if(this.state.consignee){
      if(this.state.phoneNumber){
        if(this.state.address){
          let myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
          if(myreg.test(this.state.phoneNumber)){
            let json = {
            "orderGoodsList":orderGoodsList,
            "orderInfo":{
            "remark":this.state.remark,
            "address":this.state.address,
            "phoneNumber":this.state.phoneNumber,
            "consignee":this.state.consignee
            }
          };
      
          $.ajax({
            url : `http://qiyubing.cn/yundingShop/api/order/create`,
            type : 'POST',
            headers : {'Authorization':token},
            dataType: "json",    //返回数据的 类型 text|json|html--
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(json),
            /* 订单创建 */
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
          }else{
            message.warning("手机号格式不正确");
          }
          
        }else {
          message.error("请填写收货地址",2);
        }
      }else{
        message.error("请填写收货人联系方式",2);
      }
    }else {
      message.error("请填写收货人姓名",2);
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
            this.props.history.push("/personal");
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
        <div style={{margin:"0 auto",width:"70%",paddingTop:"60px"}}>
            <div className="recinfo">
              <h1>收货信息</h1>
              <Form layout="inline">
                  <FormItem className="rec-name" >
                      <p>收货人</p>
                      <Input placeholder="请您填写收货人姓名" onChange={this.changename} />
                      <p>手机号</p>
                      <Input placeholder="请您填写手机号码" onChange={this.changephone}/>
                  </FormItem>
                  <FormItem className="rec-add">
                      <p>收货地址</p>
                      <TextArea placeholder="请您填写收货人地址" autosize={{ minRows: 5, maxRows: 5 }} onChange={this.changeadd} />
                  </FormItem>
                  <FormItem className="rec-remark">
                      <p>买家备注</p>
                      <TextArea placeholder="请您填写备注信息" autosize={{ minRows: 5, maxRows: 5 }} onChange={this.changeremark} />
                  </FormItem>
                </Form>
            </div>
            <Indheader goodsNum={this.state.cart.length}/>
            {this.cartrender()}
            <div className="settle">
              <label >
                  <input type="checkbox" onClick={this.checkall}/>
                  <div></div>
                  <span>全选</span>  
              </label>
              <span onClick={this.removegoods}>删除</span>
              <div>
                <p>寄送至：<span>{this.state.address}</span> </p>
                <p>收货人： <span>{this.state.consignee} </span> <span>{this.state.phoneNumber}</span></p>
                <p>已选<i id="goodNum"> 0</i> 件商品，合计<span>¥&nbsp;<i id="totalPrice">0.00</i></span></p>
                <a onClick={this.submitOrder}>提交订单</a>
              </div>
            </div>
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
        </div> 
    );
  }
}

export default Order;