import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './SearchGoods.css';
import $ from 'jquery';
import { message } from 'antd';
import { Link } from 'react-router-dom';

export default class SearchGoods  extends Component {

		constructor(props) {
			super(props);
			this.state={
				searchList: null,
				data: [],
				shop: [],
			}
		}
		

				renderBanner() {

				let pic = "";
		
				$.ajax({
					url : 'http://qiyubing.cn/yundingShop/api/content/6',
					type : 'GET',
					async: false,
					success : function(response){
						if (response.code === 200) {
							pic = <Link to={response.data[0].url} > <img className="shopSearchHeader" src={response.data[0].pic} alt="" /></Link>;
						}
					},
					error:function(status){
						console.log(status);
					}
				});
		
				return pic;
		
			}

		renderShopHead() {
			const { shop } = this.state;
			let shoplist = [];
			for(let i = 0; i < shop.length; i++){
				shoplist.push(
				<div className="shopSearchGoods-information-goodsHeader">
					<div>
						<img src={shop[i].logo} alt=""></img>
					</div>
					<h2>{shop[i].name}</h2>
					<Link to={`/shop/${shop[i].shopId}`}><button>进店看看</button></Link>
				</div>
				)					
			}
			return shoplist;
		}


		renderSearchResult() {		
		const {data} = this.state;
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

		let arr = new Array();
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


	 searchButton = () => {
		let url = 'http://qiyubing.cn/yundingShop/api/search/platform/'+$(".shopSearchGoods-information-input").val();
		url = encodeURI(url);
	 	$.get(url,  (response) => {
	 		if (response.code === 200) {
	 				//搜索商品的总数
	 				let search = response.data.goods.length;
	 				this.setState({
	 					searchList: search,
					 });

					let dataList = response.data.goods;					
	 				this.setState({
	 					data: dataList,
					});
						
					let shoparr = response.data.shop;					
	 				this.setState({
	 					shop: shoparr,
					});


	 		} else {
	 			message.error(response.message,10);
	 				}
	 			});
	 		
			}	
			


			 componentDidMount () {
				let searchName = this.props.match.params.searchname;
				// console.log(searchName);
				let url = 'http://qiyubing.cn/yundingShop/api/search/platform/'+searchName;
				// console.log(url);		
				url = encodeURI(url);
				 $.get(url,  (response) => {
					 if (response.code === 200) {
							 //搜索商品的总数
							let search = response.data.goods.length;
							this.setState({
								 searchList: search,
							});
		
							let dataList = response.data.goods;					
							this.setState({
								data: dataList,
							});
							
							let shoparr = response.data.shop;					
							this.setState({
								shop: shoparr,
							});

					 } else {
						 message.error(response.message,10);
					}
				 });
				 window.scrollTo(0, 0);
			 }
			


			getSearchData(event,name) {
				event.preventDefault();//阻止页面提交刷新
			}		 





	render() {
			return (
				<div className="shopSearchGoods">
					<div className="shopSearchGoods-header">
						{this.renderBanner()}
					</div>

					<div className="shopSearchGoods-information clearfix">
						<h2 className="shopSearchGoods-information-title">共<i style={{width:"44px",display:"inline-block",textAlign:"center"}}>{this.state.searchList}</i>件商品</h2>

						<form onSubmit={(e) => this.getSearchData(e,this.state.searchkey)} style={{display:"inline-block",float:"right",width:"40%"}} className="clearfix"> 
				
							<button className="shopSearchGoods-information-search" onClick={this.searchButton}>
									<i>&#xe60c;</i>
							</button>
							<input className="shopSearchGoods-information-input" defaultValue={this.props.match.params.searchname}></input>
						</form> 

						<p className="shopSearchGoods-information-next">本次搜索结果如下：</p>
					</div>


					<div className="shopSearchGoods-information-goods">

						{this.renderShopHead()}
						<ul className="shopSearchGoods-information-goodslist clearfix">
						{this.renderSearchResult()}	
							
						</ul>
					</div>
				</div>				
					);
			  	}
}
