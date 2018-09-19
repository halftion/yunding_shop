import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './SearchGoods.css';
import $ from 'jquery';
import { message } from 'antd';
import { Link } from 'react-router-dom';
import '../config';

export default class PlatformGoods extends Component {

	constructor(){
		super();
		this.state={
            data: [],
            name: "",
        }
        global.platform.this = this;
    }

    componentDidMount () {
        this.getdata();         
        window.scrollTo(0, 0);
	}
		 
    componentWillReceiveProps(){
       this.getdata();
	}

    getdata = () => {
        let id = global.platform.id;
        let name = global.platform.name;
        let url = 'http://qiyubing.cn/yundingShop/api/platformCategory/allGoods/sales/'+id;
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
        })
    }
		
	

	renderBanner() {
		let pic = "";
		$.ajax({
			url : 'http://qiyubing.cn/yundingShop/api/content/6',
			type : 'GET',
			async: false,
			success : function(response){
				if (response.code === 200) {
					pic = <Link to={response.data[0].url}> <img className="shopSearchHeader" src={response.data[0].pic} alt="" /></Link>;
				}
			},
			error:function(status){
				console.log(status);
			}
		});
		
		return pic;
	}

	renderSearchResult() {		
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



	render() {
			return (
				<div className="shopSearchGoods">
					<div className="shopSearchGoods-header">
						{this.renderBanner()}
					</div>

					<div className="shopSearchGoods-information">
						<h2 className="shopSearchGoods-information-title">{this.state.name}</h2>

						<p className="shopSearchGoods-information-next">分类商品如下：</p>
					</div>
					<div className="shopSearchGoods-information-goods">
						<ul className="shopSearchGoods-information-goodslist clearfix">
						{this.renderSearchResult()}		
						</ul>
					</div>
				</div>				
					);
			  	}
}
