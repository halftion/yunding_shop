import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './goodsDetails-comment.css';
import { Avatar,message } from 'antd';
import { Link } from 'react-router-dom';
import $ from 'jquery';
import moment from 'moment';




export default class GoodsDetailscomment extends Component {
    
    constructor(props){
        super(props);
        this.state = {
            evaluate:[],
        }
    }

    componentDidMount(){
        let goodsid = this.props.match.params.id;
        this.getdata(goodsid);
    }

    getdata = (goodsid) => {
        $.ajax({
			url : `http://qiyubing.cn/yundingShop/api/goods/comment/${goodsid}`,
			type : 'GET',
			/* 获取商品评价 */
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

    renderevaluate = () => {
        const { evaluate } = this.state;
        let noResult = <div className = "not-found">
	 						<img className="detailnotfound" src="https://i.loli.net/2018/08/29/5b86095333bb3.png" />
	 						<div>
	 							<h1>很抱歉</h1>
	 							<p>还没有评价哦</p>
	 						</div> 
	 					</div>;
	 	if (evaluate.length <= 0) {
			return noResult;
	 	}


        let eva = [];
        if(evaluate[0] !== undefined){
            for(let i = 0;i < evaluate.length;++i){
                eva.push(
                    <div className="Goods-Details-comment-secondbox">
                        <Avatar className="Goods-Details-comment-head" src={evaluate[i].avatar} alt="头像" />
                        <p className="Goods-Details-comment-head-nickname">{evaluate[i].nickName}</p>
                        <p className="Goods-Details-comments">{evaluate[i].content}</p>
                        <div>
                            <i className="Goods-Details-comments-data">{moment(evaluate[i].updateAt).format('YYYY-MM-DD HH:mm:ss')}</i>
                        </div>
                    </div>
                )
            };
        }
        return eva;
    }
    
    render() {
			return (
				<div className="Goods-Details-comment">	

                    <div className="Goods-Details-comment-bodybox">
                        <div className="Goods-Details-comment-firstbox">
                        <div className="Goods-Details-comment-firstsmall">
                            <Link to={`/goodsdetail/${ this.props.match.params.id }/dis`} className="Goods-Details-comment-title2">商品详情</Link>
                            <Link to={`/goodsdetail/${ this.props.match.params.id }/eva`} className="Goods-Details-comment-title1">评价<i>{this.state.evaluate.length}</i></Link>
                        </div>
                            {this.renderevaluate()}


                            {/*<div className="Goods-Details-comment-center">100%<i className="Goods-Details-comment-center-words">&nbsp;&nbsp;好评度</i></div>
                            <div className="Goods-Details-comment-bottom-nav">
                                <i className="Goods-Details-comment-bottom-nav allcomment">全部(3326)</i>
                                <i className="Goods-Details-comment-bottom-nav goodcomment">好评(3326)</i>
                                <i className="Goods-Details-comment-bottom-nav justcomment">中评(0)</i>
                                <i className="Goods-Details-comment-bottom-nav badcomment">差评(0)</i>
                                <i className="Goods-Details-comment-bottom-nav catchcomment">追评(0)</i>
                            </div>
                        </div>
                        <div className="Goods-Details-comment-secondbox">
                            <Avatar className="Goods-Details-comment-head" src={commentHeader} alt="头像" />
                            <p className="Goods-Details-comment-head-nickname">J******Z</p>
                            <p className="Goods-Details-comments">我觉得郭聪聪很nb</p>
                            <div>
                                <i className="Goods-Details-comments-data">2018-08-04</i>
                                <i className="Goods-Details-comments-time">09:08:57</i>
                            </div>
                        </div>
                        <div className="Goods-Details-comment-secondbox">
                            <Avatar className="Goods-Details-comment-head" src={commentHeader} alt="头像" />
                            <p className="Goods-Details-comment-head-nickname">J******Z</p>
                            <p className="Goods-Details-comments">我觉得郭聪聪很nb</p>
                            <div>
                                <i className="Goods-Details-comments-data">2018-08-04</i>
                                <i className="Goods-Details-comments-time">09:08:57</i>
                            </div>
                        </div>
                        <div className="Goods-Details-comment-secondbox">
                            <Avatar className="Goods-Details-comment-head" src={commentHeader} alt="头像" />
                            <p className="Goods-Details-comment-head-nickname">J******Z</p>
                            <p className="Goods-Details-comments">我觉得郭聪聪很nb</p>
                            <div>
                                <i className="Goods-Details-comments-data">2018-08-04</i>
                                <i className="Goods-Details-comments-time">09:08:57</i>
                            </div>*/}
                        </div>
                    </div>                   				
				</div>
					);
			  	}
}
