import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './shop.css';
import $ from 'jquery';
import { Link } from 'react-router-dom';
import PropTypes from "prop-types";
import { Carousel } from 'antd';
import highQuality from '../components/image/highQuality.png';
import items3_Main from '../components/image/items3_Main.png';



export default class Shopsearch extends Component {
	static contextTypes = {
		router: PropTypes.object
	  }
  
	constructor(props,context){
		super(props,context)
        this.state = {
            turn: [],
            list: [],
            arr: [],
            items: [],
            bigpic: [],
            data: [],
            search:"",
        };
    }
    //店铺nav
    shopNav = () => {
        $.ajax({
          url : `http://qiyubing.cn/yundingShop/api/shopCategory/list/${ this.props.match.params.shopid }`,
          type : 'GET',
          success : (Response) => {
              if(Response.code === 200){
                  let datalist = Response.data;
                  this.setState({
                  data : datalist,
              });
          }   
        }
    });
    }
    //店铺nav渲染
    shopNavRender = () => {
        const { data } = this.state;
        let nav = [];
        for(let i = 0;i < data.length ;++i){
            nav.push(                   
                <span onClick={() => this.click(data[i].shopGoodsCategoryId,data[i].name)} className="nav ">{data[i].name}</span>                                                          
            );
        }
        return nav;
    }

    //get轮播
    shopTurn = () => {
          $.ajax({
            url : `http://qiyubing.cn/yundingShop/api/shopContent/${ this.props.match.params.shopid }/0`,
            type : 'GET',
            success : (Response) => {
                if(Response.code === 200){
                    let datalist = Response.data;
					this.setState({
                    turn : datalist,
                });
            }   
          }
      });
    }

    //轮播渲染
    shopRender = () => {
        const { turn } = this.state;
        let shop = [];
        for(let i = 0;i < turn.length ;++i){
            shop.push(                   
                <div>
                    <Link to={shop[i] === undefined? turn[i].url : "/" }><img className="shop-search-items2-image" src={turn[i].pic} alt=""></img></Link>
                </div>                                               
            );
        }
        return shop;
    }

    //get精选入口方图
    shopSquarePic = () => {
        $.ajax({
          url : `http://qiyubing.cn/yundingShop/api/shopContent/${ this.props.match.params.shopid }/1`,
          type : 'GET',
          success : (Response) => {
              if(Response.code === 200){
                  let datalist = Response.data;
                  this.setState({
                  list : datalist,
              });
          }   
        }
    });
    }

    //get精选入口长图
    shopLongPic = () => {
        $.ajax({
        url : `http://qiyubing.cn/yundingShop/api/shopContent/${ this.props.match.params.shopid }/2`,
        type : 'GET',
        success : (Response) => {
            if(Response.code === 200){
                let datalist = Response.data;
                this.setState({
                arr : datalist,
            });
        }   
        }
    });
    }

    //get类目入口圆图
    shopCirclePic = () => {
        $.ajax({
        url : `http://qiyubing.cn/yundingShop/api/shopContent/${ this.props.match.params.shopid }/3`,
        type : 'GET',
        success : (Response) => {
            if(Response.code === 200){
                let datalist = Response.data;
                this.setState({
                items : datalist,
            });
        }   
        }
    });
    }

      //get热售入口背景
      shopHotPic = () => {
        $.ajax({
        url : `http://qiyubing.cn/yundingShop/api/shopContent/${ this.props.match.params.shopid }/4`,
        type : 'GET',
        success : (Response) => {
            if(Response.code === 200){
                let datalist = Response.data;
                this.setState({
                bigpic : datalist,
            });
        }   
        }
    });
    }


    componentDidMount() {
        this.shopNav();
        this.shopTurn();
        this.shopSquarePic();
        this.shopLongPic();
        this.shopCirclePic();
        this.shopHotPic();
        window.scrollTo(0, 0);
    }

    /* 分类跳转 */
    click = (id,name) => {
        global.shopplatform.id = id;
        global.shopplatform.name = name;
        this.context.router.history.push(`/shop/${this.props.match.params.shopid}/shopplantform`);
        //console.log(global.shopplatform);
    }

    /* 搜索缓存 */
    searchs = () =>{
        let temp = $(".shop-search-items1-input1").val();
        this.setState({
          search: temp
        });     
      }

    /* 店铺搜索 */
    shopsearch = () => {
        global.shopsearch = this.state.search;
        this.context.router.history.push(`/shop/${this.props.match.params.shopid}/shopsearch`);
    }

	render() {
			return (
                <div className="shop-search-bigbox">
                    <div className="shop-search-items1">
                        <input className="shop-search-items1-input1"  onChange={this.searchs}></input>
                        <Link to={`/search/${this.state.search}` } className="shop-search-items1-input2" onChange={this.searchs}>搜全站</Link>
                        <button className="shop-search-items1-input3"  onClick={this.shopsearch}>搜本店</button>
                        <h1 className="shop-search-items1-title1">CTA</h1>
                        <h1 className="shop-search-items1-title2">云顶书院官方旗舰店</h1>

                         <div className="shop-search-items1-nav">
                             {this.shopNavRender()}
                         </div> 
                         
                    </div>

                    <div className="shop-search-items2">
                        {/* <a className="prev" href="javascript:;">&lt;</a> */}
		                {/* <a className="next" href="javascript:;">&gt;</a> */}
                        <Carousel autoplay="true">
                            {/* <div><img className="shop-search-items2-image" src={shopBanner} alt=""></img></div>
                            <div><img className="shop-search-items2-image" src={one} alt=""></img></div>
                            <div><img className="shop-search-items2-image" src={two} alt=""></img></div>
                            <div><img className="shop-search-items2-image" src={three} alt=""></img></div> */}
                            {this.shopRender()}
                        </Carousel>
                        
                    </div>


                     <div className="shop-search-items3">
                        <img className="shop-search-items3-image" src={highQuality} alt=""></img>
                        <div className="shop-search-items3-mainbox">
                            <h3 className="shop-search-items3-title1">精选入口</h3>
                            <p className="shop-search-items3-title2">High quality entrance</p>
                            <div className="shop-search-items3-mainImgBack">
                                <img className="shop-search-items3-mainImg" src={items3_Main} alt=""></img>
                                <i>---Beautiful yunding</i>
                                <span>云顶书院,太原理工大学创新组织...</span>
                                <p>云顶书院,创新组织,</p>


                                <Link to={this.state.list[0] === undefined? "/": this.state.list[0].url}>
                                    <div className="shop-search-items3-subImg">
                                        <img src={this.state.list[0] === undefined? null: this.state.list[0].pic} alt=""></img>
                                        <div className="shop-search-items3-subIntro">
                                            <h4>{this.state.list[0] === undefined? null: this.state.list[0].title1}</h4>
                                            <h5>{this.state.list[0] === undefined? null: this.state.list[0].title2}</h5>
                                        </div>
                                    </div>
                                </Link> 
                                
                                <Link to={this.state.list[1] === undefined? "/": this.state.list[1].url}>
                                    <div className="shop-search-items3-subImg2">
                                        <img src={this.state.list[1] === undefined? null: this.state.list[1].pic} alt=""></img>
                                        <div className="shop-search-items3-subIntro">
                                            <h4>{this.state.list[1] === undefined? null: this.state.list[1].title1}</h4>
                                            <h5>{this.state.list[1] === undefined? null: this.state.list[1].title2}</h5>
                                        </div>
                                    </div>
                                </Link>

                                <Link to={this.state.list[2] === undefined? "/": this.state.list[2].url}>
                                    <div className="shop-search-items3-subImg3">                                   
                                        <div className="hexagon">
                                            <img src={this.state.list[2] === undefined? null: this.state.list[2].pic} alt=""></img>
                                            <div className="left"></div>
                                            <div className="right"></div>                                        
                                            <div className="mask">
                                                <p>{this.state.list[2] === undefined? null: this.state.list[2].title1}</p>
                                                <span>{this.state.list[2] === undefined? null: this.state.list[2].title2}</span>
                                            </div>
                                        </div>                                    
                                    </div>
                                </Link>

                                <Link to={this.state.list[3] === undefined? "/": this.state.list[3].url}>
                                    <div className="shop-search-items3-subImg4">
                                        <img src={this.state.list[3] === undefined? null: this.state.list[3].pic} alt=""></img>
                                        <div className="shop-search-items3-subIntro2">
                                            <p>{this.state.list[3] === undefined? null: this.state.list[3].title1}</p>
                                            <h6>{this.state.list[3] === undefined? null: this.state.list[3].title2}</h6>
                                        </div>    
                                    </div>
                                </Link>

                                <Link to={this.state.list[4] === undefined? "/": this.state.list[4].url}>
                                    <div className="shop-search-items3-subImg5">
                                        <img src={this.state.list[4] === undefined? null: this.state.list[4].pic} alt=""></img>
                                        <div className="shop-search-items3-subIntro2">
                                            <p>{this.state.list[4] === undefined? null: this.state.list[4].title1}</p>
                                            <h6>{this.state.list[4] === undefined? null: this.state.list[4].title2}</h6>
                                        </div>    
                                    </div>
                                </Link>

                                <Link to={this.state.list[5] === undefined? "/": this.state.list[5].url}>
                                    <div className="shop-search-items3-subImg6">                                   
                                        <div className="hexagon">
                                            <img src={this.state.list[5] === undefined? null: this.state.list[5].pic} alt=""></img>
                                            <div className="left"></div>
                                            <div className="right"></div>                                        
                                            <div className="mask">
                                                <p>{this.state.list[5] === undefined? null: this.state.list[5].title1}</p>
                                                <span>{this.state.list[5] === undefined? null: this.state.list[5].title2}</span>
                                            </div>
                                        </div>                                    
                                    </div>
                                </Link>


                                <Link to={this.state.arr[0] === undefined? "/": this.state.arr[0].url}>
                                    <div className="shop-search-items3-subImg7">                                   
                                        <img src={this.state.arr[0] === undefined? null: this.state.arr[0].pic} alt=""></img>
                                        <div className="shop-search-items3-subIntro3">
                                            <p>{this.state.arr[0] === undefined? null: this.state.arr[0].title1}</p>
                                            <h6>{this.state.arr[0] === undefined? null: this.state.arr[0].title2}</h6>
                                        </div>                                    
                                    </div>
                                </Link>

                            </div>
                        </div>
                    </div>


                    <div className="shop-search-items4">
                            <h3 className="shop-search-items4-title1">类目入口</h3>
                            <p className="shop-search-items4-title2">High quality entrance</p>
                            <div className="shop-search-items4-center">

                            <Link to={this.state.items[0] === undefined? "/": this.state.items[0].url}>
                                <div className="shop-search-items4-circle">
                                    <img src={this.state.items[0] === undefined? null: this.state.items[0].pic} alt=""></img>
                                    <div className="shop-search-items4-squre">
                                        <p>{this.state.items[0] === undefined? null: this.state.items[0].title1}</p>
                                    </div> 
                                </div>
                            </Link>

                             <Link to={this.state.items[1] === undefined? "/": this.state.items[1].url}>
                                <div className="shop-search-items4-circle">
                                    <img src={this.state.items[1] === undefined? null: this.state.items[1].pic} alt=""></img>
                                    <div className="shop-search-items4-squre">
                                        <p>{this.state.items[1] === undefined? null: this.state.items[1].title1}</p>
                                    </div>
                                </div>
                            </Link>
                            
                            <Link to={this.state.items[2] === undefined? "/": this.state.items[2].url}>
                                <div className="shop-search-items4-circle">
                                <img src={this.state.items[2] === undefined? null: this.state.items[2].pic} alt=""></img>
                                    <div className="shop-search-items4-squre">
                                        <p>{this.state.items[2] === undefined? null: this.state.items[2].title1}</p>
                                    </div>
                                </div>
                            </Link>

                            <Link to={this.state.items[3] === undefined? "/": this.state.items[3].url}>
                                <div className="shop-search-items4-circle">
                                <img src={this.state.items[3] === undefined? null: this.state.items[3].pic} alt=""></img>
                                    <div className="shop-search-items4-squre">
                                        <p>{this.state.items[3] === undefined? null: this.state.items[3].title1}</p>
                                    </div>
                                </div>
                            </Link>   

                            <div className="shop-search-items4-allcute">
                                <div className="shop-search-items4-cute">
                                    <img src={this.state.items[4] === undefined? null: this.state.items[4].pic} alt=""></img>
                                    <p>{this.state.items[4] === undefined? null: this.state.items[4].title1}</p>
                                    <Link to={this.state.items[4] === undefined? "/": this.state.items[4].url}>
                                        <button>点击查看</button>
                                    </Link>
                                </div>

                                <div className="shop-search-items4-cute">
                                    <img src={this.state.items[5] === undefined? null: this.state.items[5].pic} alt=""></img>
                                    <p>{this.state.items[5] === undefined? null: this.state.items[5].title1}</p>
                                    <Link to={this.state.items[5] === undefined? "/": this.state.items[5].url}>
                                        <button>点击查看</button>
                                    </Link>
                                </div>
                                <div className="shop-search-items4-cute">
                                    <img src={this.state.items[6] === undefined? null: this.state.items[6].pic} alt=""></img>
                                    <p>{this.state.items[6] === undefined? null: this.state.items[6].title1}</p>
                                    <Link to={this.state.items[6] === undefined? "/": this.state.items[6].url}>
                                        <button>点击查看</button>
                                    </Link>
                                </div>
                                <div className="shop-search-items4-cute">
                                    <img src={this.state.items[7] === undefined? null: this.state.items[7].pic} alt=""></img>
                                    <p>{this.state.items[7] === undefined? null: this.state.items[7].title1}</p>
                                    <Link to={this.state.items[7] === undefined? "/": this.state.items[7].url}>
                                        <button>点击查看</button>
                                    </Link>
                                </div>
                            </div>

                            </div>
                    </div> 

                    
                    <div className="shop-search-items5">
                        <img className="shop-search-items5-image" src={this.state.bigpic[0] === undefined? null: this.state.bigpic[0].pic} alt=""></img>
                        <div className="shop-search-items5-circle">
                            <h2>{this.state.bigpic[0] === undefined? null: this.state.bigpic[0].title1}</h2>
                            <p>{this.state.bigpic[0] === undefined? null: this.state.bigpic[0].title2}</p>
                            <Link to={this.state.bigpic[0] === undefined? "/": this.state.bigpic[0].url}>
                                <button>{this.state.bigpic[0] === undefined? null: this.state.bigpic[0].title3}</button>
                            </Link>
                        </div>
                    </div>


                </div>
            );
        }
}