import React, { Component } from 'react';
import { Layout,Affix,Icon,Menu, Checkbox, Select, InputNumber, Button } from 'antd';
import './Commonright.css';
import commodity1 from './img/commodity1.png';
import commodity2 from './img/commodity2.png';
const {  Sider } = Layout;
const Option = Select.Option;

function changePrice(num,name) {
  var tempprice = parseFloat(document.getElementById('sidePrice'+name).title)*num;
  document.getElementById("sidePrice"+name).innerHTML=tempprice.toFixed(2);
}  

function onChange(value) {
  console.log('changed', value);
  changePrice(value,this.name);
  document.getElementById("CheckBox"+this.name).checked="";
}



class Commonright extends Component {

    state = {
      collapsed: true,
    }
  
    toggle = () => {
      this.setState({
        collapsed: !this.state.collapsed,
      });
    }
    
    render() {
      return (

            <Affix offsetTop={60} style={{overflow:'auto',width:'650px',height:'600px',backgroundColor:'#f5f5f5'}} className="sideRightAffix"> 
                  <Layout style={{backgroundColor:'#f5f5f5',width:"650px",height:"600px"}}>
                    <Sider collapsible='true' collapsed={this.state.collapsed} collapsedWidth='0' theme='light' trigger={null}
                    style={{backgroundColor:'#f5f5f5',marginLeft:'0px',overflow:'auto',position:'fixed'}} width={650}>
                      <Menu mode="inline" defaultSelectedKeys={['1']} id='commonMenu' className="commonRightMenu" >
                        <div className="sideShopping">
                          <h1>购物袋</h1>
                          <p>共两件宝贝</p>
                          <Icon type="setting" />
                        </div>
                        <Menu.Item key="1" style={{height:260}}>
                          <Checkbox className="sideShoppingBox" id="CheckBox1">
                            <a>毕舍服饰专营店 <Icon type="right" style={{paddingLeft:'20px'}} /></a>
                            <a><img src={commodity1} alt="商品图片" /></a>
                            <p>2018春夏男装 上衣  牛仔套衫 <br />Ecole De Paris</p>
                              <Select defaultValue="blue XL">
                                <Option value="blue XL">纽扣蓝色男款 XL</Option>
                                <Option value="blue L">纽扣蓝色男款 L</Option>
                              </Select>
                              <div className="sideshopnumber">
                                X<InputNumber min={1} max={10} defaultValue={3} onChange={onChange} name="1" />
                              </div>
                              <span className="sidePrice">&yen;<i id="sidePrice1" title="1800.00">5400.00</i></span>    
                          </Checkbox>
                        </Menu.Item>
                        <Menu.Item key="2" style={{height:260}}>
                        <Checkbox className="sideShoppingBox" id="CheckBox2">
                            <a>NIKE官方旗舰店<Icon type="right" style={{paddingLeft:'20px'}} /></a>
                            <a><img src={commodity2} alt="商品图片" /></a>
                            <p>Nike/耐克男鞋  NIKE TANJU 透气网面轻便休闲运动鞋  812 54-01</p>
                              <Select defaultValue="gray 42">
                              <Option value="gray 42">灰/白 42码</Option>
                                <Option value="gray 40">灰/白 40码</Option>
                              </Select>
                              <div className="sideshopnumber">
                                X<InputNumber min={1} max={10} defaultValue={1} onChange={onChange} name="2" />
                              </div>
                              <span className="sidePrice" >&yen;<i id="sidePrice2" title="339.00">339.00</i></span>      
                          </Checkbox>
                        </Menu.Item>
                        <Menu.Item key="3" style={{height:260}}>
                        <Checkbox className="sideShoppingBox" id="CheckBox3">
                          <a>NIKE官方旗舰店 <Icon type="right" style={{paddingLeft:'20px'}} /></a>
                          <a><img src={commodity2} alt="商品图片" /></a>
                          <p>Nike/耐克男鞋  NIKE TANJU 透气网面轻便休闲运动鞋  812 54-01</p>
                            <Select defaultValue="gray 42">
                              <Option value="gray 42">灰/白 42码</Option>
                              <Option value="gray 40">灰/白 40码</Option>
                            </Select>
                            <div className="sideshopnumber">
                              X<InputNumber min={1} max={10} defaultValue={3} onChange={onChange} name="3" />
                            </div>
                            <span className="sidePrice">&yen; <i id="sidePrice3" title="539.00">1617.00</i></span>        
                        </Checkbox>
                        </Menu.Item>
                        <Menu.Item key="3" style={{height:80}}>
                        </Menu.Item>
                        <div className="sideRightShopBottom">
                          <Checkbox className="sideRightShopCheckAll"><span className="checkAll">全选</span></Checkbox>
                          <p>合计：<span>&yen;<i id="AllPrice"></i></span></p>
                          <Button className="send">结算（<i>0</i>）</Button>
                        </div>
                      </Menu>
                    </Sider>
                    <Sider theme='light' width='60' style={{textAlign:'center',position:'fixed',right:'0'}}>
                      <Icon type="shopping-cart"  onClick={this.toggle} className="siderrighticon" />
                    </Sider>
                  </Layout> 
              </Affix>
      );
    }
  }
  
  export default Commonright;