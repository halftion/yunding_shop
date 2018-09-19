import React, { Component } from 'react';
import './Commonleft.css'
import { Menu } from 'antd';
import $ from 'jquery';
import PropTypes from "prop-types";
import '../../config';

let active = 0;

/* 公共商品分类导航菜单V2.0，更新于2018.8.8 */
class Commonleft extends Component {
  
  static contextTypes = {
    router: PropTypes.object
  }

  constructor(props,context){
    super(props,context);
  }


    rendersideleft() {
        let arr = [];
        $.ajax({
            url : 'http://qiyubing.cn/yundingShop/api/platformCategory/list',
            type : 'GET',
            dataType : "json",
            async: false,
            success : (response) => {
                if (response.code === 200) {
                    for(let i = 0; i < response.data.length ;++i){
                      arr.push(<Menu.Item key={response.data[i].platformGoodsCategoryId} onClick={() => this.click(response.data[i].platformGoodsCategoryId,response.data[i].name)}>{response.data[i].name}</Menu.Item>);
                    }    
                }
            },
            error:(status) => {
                console.log(status);
            }
        });

        return arr;

    }

    click = (id,name) => {
      global.platform.id = id;
      global.platform.name = name;
      this.context.router.history.push("/platform");
      this.changeMenu();
      //global.platform.this.getdata(); 
      //console.log(global.platform);
    }

  changeMenu(){
    if(!active){
      $(".common-left-switch").addClass("common-left-switch-active");
      $(".side-menu-box").addClass("side-menu-box-active");
      active=(!active);
    }else{
      $(".common-left-switch").removeClass("common-left-switch-active");
      $(".side-menu-box").removeClass("side-menu-box-active");
      active=(!active);
    }
  }

  render() {
    return (
           <div className="common-side-left">
            <div className="common-left-switchbox">
              <i className="common-left-switch" onClick={this.changeMenu}>&#xe60a;</i>
            </div>
            <div className="side-menu-box">
              <Menu mode="vertical" className="side-menu">
              {this.rendersideleft()}
              </Menu>
            </div>
          </div>
      );
    }
  }
  
  export default Commonleft;