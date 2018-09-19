import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './goodsDetails_modelshow.css';
import { Link } from 'react-router-dom';
import $ from 'jquery';
import { message } from 'antd';


export default class GoodsDetailsmodelshow extends Component {
    
    constructor(props){
        super(props);
        this.state = {
            evaluate:[],
            goods:[],
        }
    }

    componentDidMount(){
        let goodsid = this.props.match.params.id;
        this.getdata(goodsid);
        this.getgoods(goodsid);
    }

    getdata = (goodsid) => {
        $.ajax({
			url : `http://qiyubing.cn/yundingShop/api/goods/comment/${goodsid}`,
			type : 'GET',
			/* 获取商品评论 */
			success : (info) => {
				if(info.code === 200){
					let evalist = info.data;
					this.setState({
						evaluate:evalist,
                      });
				}else {
                    message.error(info.message,2)	 	
				}
			},
			error:() => {message.error("获取评论失败，请检查网络设置",2);}
        });
        
    }


    getgoods = (goodsid) => {
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
                    //this.props.history.push(`/goodsdetail/${goodsid}`);
                    //console.log(goodslist);
                    // console.log(this.state.cart)
				}else {
                     message.error(info.message,2)
				}
			},
			error:() => {message.error("获取商品详情失败，请检查网络设置",2);}
        });
        
    }

    renderSearchDetails = () => {
        let noResult;
        if(this.state.goods.introduction === undefined){
            noResult = <div className = "not-found">
                                <img className="detailnotfound" src="https://i.loli.net/2018/08/29/5b8608cfadb2a.png" />
                                <div>
                                    <h1>很抱歉</h1>
                                    <p>这里空空的哦</p>
                                </div>
                            </div>;                    
        }else {           
            noResult = <img className="Goods-Details-secondbox" src={this.state.goods.introduction} alt=""></img>           
        }
        return noResult;
    }



    render() {
			return (
				<div className="Goods-Details-modelshow">	
                									
                    <div className="Goods-Details-bodybox">
                        <div className="Goods-Details-firstbox">
                            <Link to={`/goodsdetail/${ this.props.match.params.id }/dis`} className="Goods-Details-firstbox-title1">商品详情</Link>
                            <Link to={`/goodsdetail/${ this.props.match.params.id }/eva`} className="Goods-Details-firstbox-title2">评价<i>{this.state.evaluate.length}</i></Link>
                        </div>

                         <div>
                             {this.renderSearchDetails()}
                            {/* <img className="Goods-Details-secondbox" src={this.state.goods.introduction} alt=""></img> */}
                        </div>
                             

                    </div>                   				
				</div>
					);
			  	}
}
