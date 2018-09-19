import React,{Component} from 'react';
import 'antd/dist/antd.css';
import { message } from 'antd';
import Myheader from '../components/personalCenter/myheader';
import OrderInformation from '../components/personalCenter/orderInformation';
import moment from 'moment';


class PersonalCenter extends Component {
	constructor(){
		super()
	}
	
	componentDidMount(){
		window.scrollTo(0, 0);
	}

	componentWillMount(){
		let expirein = sessionStorage.getItem("expirein");
        let time = moment().unix()*1000;
        //console.log(expirein);
        //console.log(time);
        if((expirein - time) <= 1800000){
			message.error("登录已过期，请重新登录",2);
			this.props.history.push('/');
        } 
	}
	
	render() {
			return (
				<div style={{margin:'auto',width:'70%'}}>
					<Myheader />
					<OrderInformation />
				</div>
					);
			  	}
}
export default PersonalCenter;