import React,{Component} from 'react';
import 'antd/dist/antd.css';
import { Switch,Route } from 'react-router-dom';
import GoodsDetailshead from '../components/goodsDetails/goodsDetails_head/goodsDetails_head';
import GoodsDetailsmodelshow from '../components/goodsDetails/goodsDetails_modelshow/goodsDetails_modelshow';
import GoodsDetailscomment from '../components/goodsDetails/goodsDetails-comment/goodsDetails-comment';


export default class GoodsDetails extends Component {
	
	constructor(props){
		super(props)
		this.state = {
			goodsid:this.props.match.params.id,
		}
	}

	componentDidMount(){
		window.scrollTo(0, 0);
	}
	
	render() {
			return (
				<div>
                    <GoodsDetailshead id={this.state.goodsid} history ={this.props.history} />
					<Switch>	
						<Route path="/goodsdetail/:id/eva" component={GoodsDetailscomment} />
						<Route place="/goodsdetail/:id/dis" component={GoodsDetailsmodelshow} />
					</Switch>
                </div>
					);
			  	}
}
