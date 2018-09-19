import React, { Component } from 'react';
import { Link } from 'react-router-dom';


class Notfound extends Component {

  constructor(){
    super();
    this.state = {
      pic1:"https://i.loli.net/2018/08/29/5b8572b0152a5.png",
      pic2:"https://i.loli.net/2018/08/29/5b8572b015802.png",
      pic3:"https://i.loli.net/2018/08/29/5b8572b0205f9.png",
    }
  }

  render404(){
    let num = parseInt((Math.random()*10)%3);
    let pic = "";
    if(num === 0){
      pic = <img src={this.state.pic1} style={{marginTop:200,paddingBottom:200,marginLeft:"15%"}}/>;
    }else if(num === 1){
      pic = <img src={this.state.pic2} style={{marginTop:200,paddingBottom:200,marginLeft:"15%"}}/>;
    }else{
      pic = <img src={this.state.pic3} style={{marginTop:200,paddingBottom:200,marginLeft:"15%"}}/>
    }
    return pic;
  }

  render() {
    return (
        <div style={{width:"70%",margin:"0 auto",paddingTop:"60px",textAlign:"center"}}>
          {this.render404()}
          <div style={{
              display:"inline-block",
              width:"40%",
              marginTop:240,
              marginLeft:40,
              verticalAlign:"top",
              textAlign:"left",
              }}>
              <h1 style={{fontSize:30}}>这个网页打不开了&nbsp;&nbsp;！</h1>
                <p style={{
                    display:"block",
                    fontSize:14,
                    }}>错误代码：0x800c00008</p>
                <Link to="/" style={{
                    display:"block",
                    width:"120px",
                    height:"40px",

                    marginTop:"120px",

                    backgroundColor:"#000",

                    borderRadius:"5px",

                    fontFamily:"iconfont",
                    color:"#fff",
                    fontSize:"18px",
                    textAlign:"center",
                    lineHeight:"40px",

                    cursor:"pointer",

                    userSelect:"none",
                    }}><span style={{fontSize:"20px"}}>&#xe621; </span> 返回首页</Link>
          </div>
        </div>
    );
  }
}

export default Notfound;