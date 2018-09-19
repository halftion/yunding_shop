import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './SearchGoods.css';
import './shop.css';
import $ from 'jquery';
import { message } from 'antd';
import { Link } from 'react-router-dom';
import PropTypes from "prop-types";
import '../config';

export default class ShopPlatform extends Component {
	static contextTypes = {
		router: PropTypes.object
	  }
  
	constructor(props,context){
        super(props,context)
        this.state={
            data: [],
            name: "",
            shop:[],
            search:"",
            searchList:0,
        }
        global.shopplatform.this = this;
    }

    componentDidMount () {
        this.getdata();         
        window.scrollTo(0, 0);
    }

    componentWillReceiveProps(){
        this.getdata();
     } 
    
    getdata = () => {
        let shopid = this.props.match.params.shopid;
        let searchname = global.shopsearch;
        let url = `http://qiyubing.cn/yundingShop/api/search/shop/${shopid}/${searchname}`;
        $.get(url,  (response) => {
            if (response.code === 200) {
                //搜索商品的总数
                let search = response.data.length;
                this.setState({
                    searchList: search,
                });

               let dataList = response.data;					
                this.setState({
                    data: dataList,
               });

        } else {
            message.error(response.message,10);
                }
            });
        this.getshopNav();
    }

    getshopNav = () => {
        $.ajax({
          url : `http://qiyubing.cn/yundingShop/api/shopCategory/list/${ this.props.match.params.shopid }`,
          type : 'GET',
          success : (Response) => {
              if(Response.code === 200){
                  let datalist = Response.data;
                  this.setState({
                  shop : datalist,
              });
          }   
        }
    });
    }
		

	renderBanner() {
        const { shop } = this.state;
        let nav = [];
        for(let i = 0;i < shop.length ;++i){
            nav.push(                   
                    <i className="nav " onClick={() => this.click(shop[i].shopGoodsCategoryId,shop[i].name)}>{shop[i].name}</i>                                                          
            );
        }
        return nav;
	}

	renderSearch() {		
	    const { data } = this.state;
		//console.log(data);
	    let noResult = <div className = "not-found">
	 						<img src="https://i.loli.net/2018/08/27/5b83e1f8c4628.png" />
	 						<div>
	 							<h1>没有结果</h1>
	 							<p>没有找到相关商品</p>
	 						</div>
	 					</div>;
	 	if (data.length <= 0) {
			return noResult;
	 	}
		let arr = [];
		for(let i = 0; i < data.length; i++) {
			arr.push(<Link to={`/goodsdetail/${data[i].goodsId}`}>
			<li>
				<div className = "goods">
					<img src={data[i].picture} />
					<h2 className="goodsInformation">{data[i].name}</h2>
					<p><i>{data[i].sales}</i>人付款</p>
					<span>&yen;<i>{data[i].price}</i></span>
				</div>
			</li></Link>);	
		}
			return arr;
        }
        

    /* 搜索缓存 */
    searchs = () =>{
        let temp = $(".shop-search-items1-input1").val();
        this.setState({
            search: temp
        });     
        }
    /* 店内搜索 */
    shopsearch = () => {
        global.shopsearch = this.state.search;
        this.getdata();
    }    

    /* 分类跳转 */
    click = (id,name) => {
        global.shopplatform.id = id;
        global.shopplatform.name = name;
        this.context.router.history.push(`/shop/${this.props.match.params.shopid}/shopplantform`);
        //console.log(global.shopplatform);
    }


	render() {
			return (
			<div>
                <div className="shop-search-items1 platform">
                        <input className="shop-search-items1-input1" onChange={this.searchs} defaultValue={global.shopsearch}></input>
                        <Link to={`/search/${this.state.search}`} className="shop-search-items1-input2">搜全站</Link>
                        <button className="shop-search-items1-input3" onClick={this.shopsearch}>搜本店</button>
                        <Link to={`/shop/${this.props.match.params.shopid}`} className="shop-search-items1-title1">CTA</Link>
                        <Link to={`/shop/${this.props.match.params.shopid}`} className="shop-search-items1-title2">云顶书院官方旗舰店</Link>

                         <div className="shop-search-items1-nav">
                             {this.renderBanner()}
                         </div> 
                         
                    </div>
                <div className="shopSearchGoods">
					<div className="shopSearchGoods-information">
                    <h2 className="shopSearchGoods-information-title">共<i style={{width:"44px",display:"inline-block",textAlign:"center"}}>{this.state.searchList}</i>件商品</h2>
						<p className="shopSearchGoods-information-next">搜索结果如下：</p>
					</div>
					<div className="shopSearchGoods-information-goods">
						<ul className="shopSearchGoods-information-goodslist clearfix">
						{this.renderSearch()}		
						</ul>
					</div>
				</div>
            </div>	
									
					);
			  	}
}
