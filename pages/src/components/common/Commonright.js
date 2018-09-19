import React, { Component } from 'react';
import './Commonright.css';
import $ from 'jquery';
import { Switch, Route,Link } from 'react-router-dom';
import { Modal,message } from 'antd';
import moment from 'moment';
import LogIn from './login';
import SignUp from './signup';
import SetPassword from './setpassword';
import ReterievePassword from './reterievepassword';
import PropTypes from "prop-types";
import '../../config';

/* 公共购物车V2.0，更新于2018.8.8 */

/* 公共购物车V2.1，更新于2018.8.18 */

/* 公共购物车V3.0，更新于2018.8.28 */
class Commonright extends Component {

  static contextTypes = {
      router: PropTypes.object
    }

  constructor(props,context){
    super(props,context);
    global.commonright = this;
    this.state = {
      visible:false,
      userinfo:[],
      shopcart:[],
      shoppingActive:false,
    }
  }

  

  componentDidMount() {
    this.changesideright();

    $('.shopping-cart').mouseover(
      () => {
        let top = $(document).scrollTop();
        $(document).on('scroll.unable',() => { $(document).scrollTop(top);})
      }
    );

    $('.shopping-cart').mouseout(() => {$(document).unbind("scroll.unable");});

    
  }

  /* 获取用户数据 */
  getdata = () => {
    let token = "Bearer "+sessionStorage.getItem("token");
    $.ajax({
      url : 'http://qiyubing.cn/yundingShop/api/user/info',
      type : 'GET',
      headers : {'Authorization':token},
      /* 侧边栏加载 */
      success : (response) => {
        if(response.code === 200){
        let userinfo = response.data;
        this.setState({
          userinfo:userinfo,
        });
        this.getcart();
      }else {
        message.error(response.message,2);
      }
      },
      error:() => {message.error("个人信息加载失败，请检查网络设置",2);}
    });
  }
  /* 获取购物车 */
  getcart = () => {
    let token = "Bearer "+sessionStorage.getItem("token");
    $.ajax ({
      url : 'http://qiyubing.cn/yundingShop/api/cart/',
      type : 'GET',
      headers : {'Authorization':token},
      success : (response) => {
        if(response.code === 200){
          let shopcart = response.data;
          this.setState({
            shopcart:shopcart,
          },()=>{
            setTimeout(()=>{
             $(".product").removeClass("product-unable");
              $(".product").addClass("product-active"); 
            },200)
            
          });
        }else {
          message.error(response.message,2);
        }
      },
      error:() => {message.error("购物车加载失败，请检查网络设置",2);}
    })
  }


  /* 侧边栏渲染 */
  rendersideright = () => {
    let user = "";
    const { userinfo,shopcart } = this.state;
    if(userinfo.length !== 0){
      user = <div className="side-shopping-cart-iconbox">
        <Link to="/personal"><img src={userinfo.avatar} title="个人中心" /></Link>
        {shopcart.data !== undefined?<i id="carticon" title="购物袋中没有商品" onClick={this.changeShoppingCart}>&#xe60b;</i>:<i id="carticon" title={`购物袋中有${shopcart.length}件商品`} onClick={this.changeShoppingCart}><span>{shopcart.length}</span>&#xe60b;</i>}
        <i id="carticon" title="退出登录" onClick={this.exit}>&#xe608;</i>
      </div>
        ;
    }else {
      user = <div className="side-shopping-cart-iconbox">
        <img src="https://i.loli.net/2018/08/16/5b7526bd35d39.png" title="注册/登录" onClick={this.showModal} />
        <i onClick={this.showModal} title="请先登录">&#xe60b;</i>
      </div>
      ;
    }
    return user;
  }

  /* 购物车渲染 */
  rendercart = () => {
    let cart = [];
    const { userinfo,shopcart } = this.state;
    if(userinfo.length !== 0){
      if(shopcart.length === 0){
        cart.push(
          <div className="no-product">
            <img src="https://i.loli.net/2018/08/28/5b8532a76047a.png" />
            <p>购物车这么空，是小熊熊不可爱了吗......</p>
            <Link to="/">去首页逛逛</Link>
          </div>
        )
      }else{
         for(let i = 0;i < shopcart.length; ++i){
          cart.push(
          <div class="product product-unable" >
            <img src={shopcart[i].picture} />
            <Link to={`/goodsdetail/${shopcart[i].goodsId}`}>{shopcart[i].name}</Link>
            <span>¥<i>{parseFloat(shopcart[i].price).toFixed(2)}</i></span>
            <i class="delete-product" onClick={() => this.removegoods(shopcart[i].goodsId,i)}>&#xe60d;</i>
          </div>
        ) 
      }
      } 
    }
    return cart;
  }

  /* 刷新侧边栏 */
  changesideright = () => {
    let expirein = sessionStorage.getItem("expirein");
    let time = moment().unix()*1000;
    if((expirein - time) >= 1800000){
      this.getdata();
    }else{
      message.error("未登录或登录已过期，请重新登录");
    }
    
  }

  /* 改变侧边栏回收状态 */
  changeShoppingCart = () => {
    if(!this.state.shoppingActive){
      $(".side-shopping-cart").addClass("side-shopping-cart-active");
      $(".side-shopping-cart-iconbox").addClass("side-shopping-cart-iconbox-active");
      let shoppingactive = this.state.shoppingActive;
      this.setState({
        shoppingActive:!shoppingactive,
      });
    }else{
      $(".side-shopping-cart").removeClass("side-shopping-cart-active");
      $(".side-shopping-cart-iconbox").removeClass("side-shopping-cart-iconbox-active");
      let shoppingactive = this.state.shoppingActive;
      this.setState({
        shoppingActive:!shoppingactive,
      });
    }
    
  }

  /* 更改modal状态 */
  handleCancel = () => {
    //console.log(e);
    this.setState({
      visible: false,
    });
  }

  showModal = () => {
    this.setState({
      visible: true,
    });
 }

 /* 删除购物车中商品2018.8.25 */
 removegoods = (goodsid,i) => {
  let token = "Bearer "+sessionStorage.getItem("token");  
  $(".product").eq(i).removeClass("product-active");
  $(".product").eq(i).addClass("product-unable"); 
    $.ajax({
      url : `http://qiyubing.cn/yundingShop/api/cart/${goodsid}`,
      type : 'DELETE',
      headers : {'Authorization':token},
      /* 订单删除 */
      success : (info) => {
        if(info.code === 200){
          message.success(info.message,2);
          this.changesideright();                   
        }else{
          message.error(info.message,2);
        }
      },
      error:() => {message.error("删除商品失败，请检查网络设置",2)}  
      });
  }

  /* 收回购物车 */
  carthidden = () => {
    $(".side-shopping-cart").removeClass("side-shopping-cart-active");
      $(".side-shopping-cart-iconbox").removeClass("side-shopping-cart-iconbox-active");
  }

    /* 退出登录 */
    exit = () => {
      sessionStorage.removeItem("token");
      sessionStorage.removeItem("expirein");
      message.success("退出成功",2);
      this.setState({
        visible:false,
        userinfo:[],
        shopcart:[],
        shoppingActive:false,
      });
     this.carthidden();
      this.context.router.history.push('/');
    }
  
    render() {
      return (
        <div>
          {/* 购物车： */}
          <div className="side-shopping-cart">
            <div className="side-shopping-cart-title">
              <h1>购物袋</h1>
              <p>共<span>{this.state.shopcart?this.state.shopcart.length:""}</span>件宝贝</p>
            </div>
            {/* 购物车管理控件，可替换： */}
            <div className="shopping-cart">
              {this.rendercart()}
            </div>
            {/* 结算/删除模块： */}
            <div className="side-shopping-cart-handle">
              {/*<p>合计：<i>&yen;<span>3139.10</span></i></p>*/}
              <Link to="/order">去结算</Link>
            </div>
          </div>
          {/* 侧边菜单，用于调用购物车与登录/个人中心组件： */}
            {this.rendersideright()}
            <Modal
                title="YUNDING"
                visible={this.state.visible}
                onOk={this.handleOk}
                onCancel={this.handleCancel}
                width="26%"
                maskClosable={true}
                footer={null}
                centered={true}
                wrapClassName="sign"
                destroyOnClose={true}
            >
              <Switch>
                
                <Route path="/signup" component={SignUp}/>
                <Route path="/setpassword" component={SetPassword} />
                <Route place-="/login" component={LogIn}/>
                <Route place-="/reterievepassword" component={ReterievePassword} />
              </Switch>
            </Modal>
        </div>
      );
    }
  }
  
  export default Commonright;