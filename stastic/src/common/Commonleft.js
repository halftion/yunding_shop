import React, { Component } from 'react';
import { Layout, Affix,Icon,Menu } from 'antd';
import './Commonleft.css'
const {  Sider } = Layout;


class Commonleft extends Component {
  
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
            <Affix offsetTop={60}> 
                <Layout style={{backgroundColor:'#f5f5f5'}}>
                  <Sider theme='light' width='60' style={{textAlign:'center'}}>
                    <Icon
                      className="sidelefticon"
                      type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
                      onClick={this.toggle}
                    />
                  </Sider>
                  <Sider collapsible='true' collapsed={this.state.collapsed} collapsedWidth='0' theme='light' trigger={null}
                  style={{backgroundColor:'#fff'}} width={600}>
                    <Menu mode="inline" defaultSelectedKeys={['1']} id='commonMenu' className='commonLeftMenu' >
                      <Menu.Item key="1">
                      <span>帽子</span>
                      </Menu.Item>
                      <Menu.Item key="2">
                        <span>眼镜</span>
                      </Menu.Item>
                      <Menu.Item key="3">
                        <span>上衣</span>
                      </Menu.Item>
                      <Menu.Item key="4">
                        <span>衬衫</span>
                      </Menu.Item>
                      <Menu.Item key="5">
                        <span>裤子</span>
                      </Menu.Item>
                      <Menu.Item key="6">
                        <span>裙子</span>
                      </Menu.Item>
                      <Menu.Item key="7">
                        <span>鞋</span>
                      </Menu.Item>
                    </Menu>
                  </Sider>
                </Layout>
              
            </Affix>
      );
    }
  }
  
  export default Commonleft;