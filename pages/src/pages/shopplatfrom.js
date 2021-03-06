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
      super(props,context);
		this.state={
            data: [],
            name: "",
            shop:[],
            search:"",
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
        let id = global.shopplatform.id;
        let name = global.shopplatform.name;
        let shopid = this.props.match.params.shopid;
        let url = `http://qiyubing.cn/yundingShop/api/shopCategory/allGoods/sales/${shopid}/${id}`;
        $.get(url,  (response) => {
            if (response.code === 200) {
                   let dataList = response.data;					
                   this.setState({
                       data: dataList,
                   });
            } else {
                message.error(response.message,10);
           }
        });
        this.setState({
            name:name,
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

	renderSearchResult() {		
	    const { data } = this.state;
		//console.log(data);
	    let noResult = <div className = "not-found">
	 						<img src="https://i.loli.net/2018/08/27/5b83e1f8c4628.png" />
	 						<div>
	 							<h1>????????????</h1>
	 							<p>????????????????????????</p>
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
					<p><i>{data[i].sales}</i>?????????</p>
					<span>&yen;<i>{data[i].price}</i></span>
				</div>
			</li></Link>);	
		}
			return arr;
        }
        
        /* ????????????????????? */
        click = (id,name) => {
            global.shopplatform.id = id;
            global.shopplatform.name = name;
            this.getdata();
            //console.log(global.shopplatform);
        }

        /* ???????????? */
        searchs = () =>{
            let temp = $(".shop-search-items1-input1").val();
            this.setState({
              search: temp
            });     
          }

        /* ??????????????? */
        shopsearch = () => {
            global.shopsearch = this.state.search;
            this.context.router.history.push(`/shop/${this.props.match.params.shopid}/shopsearch`);
        }

	render() {
			return (
			<div>
                <div className="shop-search-items1 platform">
                        <input className="shop-search-items1-input1" onChange={this.searchs}></input>
                        <Link to={`/search/${this.state.search}`} className="shop-search-items1-input2">?????????</Link>
                        <button className="shop-search-items1-input3" onClick={this.shopsearch}>?????????</button>
                        <Link to={`/shop/${this.props.match.params.shopid}`} className="shop-search-items1-title1">CTA</Link>
                        <Link to={`/shop/${this.props.match.params.shopid}`} className="shop-search-items1-title2">???????????????????????????</Link>

                         <div className="shop-search-items1-nav">
                             {this.renderBanner()}
                         </div> 
                         
                    </div>
                <div className="shopSearchGoods">
					<div className="shopSearchGoods-information">
						<h2 className="shopSearchGoods-information-title">{this.state.name}</h2>
						<p className="shopSearchGoods-information-next">?????????????????????</p>
					</div>
					<div className="shopSearchGoods-information-goods">
						<ul className="shopSearchGoods-information-goodslist clearfix">
						{this.renderSearchResult()}		
						</ul>
					</div>
				</div>
            </div>	
									
					);
			  	}
}
