import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './goodsDetails_head.css';
import { Button,message } from 'antd';
import $ from 'jquery';
import '../../../config';


export default class GoodsDetailshead extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            goods:[],
        }
    }


    componentDidMount(){
        $.ajax({
			url : `http://qiyubing.cn/yundingShop/api/goods/info/${this.props.id}`,
			type : 'GET',
			/* 获取商品详情 */
			success : (info) => {
				if(info.code === 200){
					let goodslist = info.data;
					this.setState({
						goods:goodslist,
                      });
				}else {
					message.error(info.message,2)
				}
			},
			error:() => {message.error("获取商品详情失败，请检查网络设置",2);}
        });
    }

    getdata = (goodsid) => {
        $.ajax({
			url : `http://qiyubing.cn/yundingShop/api/goods/info/${goodsid}`,
			type : 'GET',
			/* 获取商品详情 */
			success : (info) => {
				if(info.code === 200){
					let goodslist = info.data;
					this.setState({
						goods:goodslist,
                      });
                      this.props.history.push(`/goodsdetail/${goodsid}`);
				}else {
					message.error(info.message,2)
				}
			},
			error:() => {message.error("获取商品详情失败，请检查网络设置",2);}
        });
        
    }

    renderType = () => {
        const { goods } = this.state;
        let type = [];
        if(goods.linkGoodsList !== undefined){
            for(let i = 0;i < goods.linkGoodsList.length; ++i){
            type.push(
                <Button className="Goods-Details-right-click1" onClick={() => this.getdata(goods.linkGoodsList[i].goodsId)}>{goods.linkGoodsList[i].property}</Button>
            );
        }
    } 
        return type;
    }
    
    /* 添加商品 */
    addgoods = () => {
        const { goods } = this.state;
        let token = "Bearer "+sessionStorage.getItem("token");
        console.log(token);
        if(token === ("Bearer "+null) ){
            global.commonright.showModal();
            
        }else{
            let json = {
            "goodsId" : goods.goodsId,
            "name" : goods.name,
            "picture" : goods.picture,
            "price" : goods.price,
            "shopName" : goods.shopName,
            "shopId": goods.shopId
        };
		$.ajax({
			url : "http://qiyubing.cn/yundingShop/api/cart/",	//请求url
			type : "PUT",	//请求类型  post|get
			// data : "key=value&key1=value2",	//后台用 request.getParameter("key");
			dataType: "json",    //返回数据的 类型 text|json|html--
			contentType: "application/json;charset=utf-8",
			headers : {'Authorization':token},
			data:  JSON.stringify(json),
			success: (response) => {
				if(response.code === 200){
                    message.success(response.message,2);
                    this.getdata(goods.goodsId);
                    global.commonright.changesideright();
				}else{
					message.error(response.message,2);
				}
			},
			error:() => {message.error("更改失败，请检查网络设置",2);},
		});
        }
        
        
		
    }

    render() {
			return (
				<div className="Goods-Details-bigbox clearfix">										
					<div className="Goods-Details-left">
                        <div className="Goods-Details-image">
                            <div><img className="Goods-Details-img" src={this.state.goods.picture} alt="商品图片" /></div>
                        </div>
                    </div>
                    <div className="Goods-Details-right">
                        <div className="Goods-Details-right-center">
                            <h2 className="Goods-Details-right-title">{this.state.goods.name}</h2>
                            <i className="Goods-Details-right-price">云云价<span className="Goods-Details-right-num">￥{parseFloat(this.state.goods.price).toFixed(2)}</span></i>
                            <div>
                                <i className="Goods-Details-right-choice">选择规格</i>
                                <div className="Goods-Details-right-smallbox">
                                    { this.renderType() }
                                </div>
                            </div>
                            <p className="Goods-Details-right-sale">销量</p>
                            <i className="Goods-Details-right-sale-num">{this.state.goods.sales}</i>
                            <Button className="Goods-Details-right-add" onClick={this.addgoods}>加入购物袋</Button>                           
                        </div>
                    </div> 
                </div>
            	);
            }
 }
              