import React, { Component } from 'react';
import { Affix,Button,Layout,Form,Input } from 'antd';
import './Commonhead.css';
import logo from './img/logo.png';
const { Header } = Layout;

class Commonhead extends Component {
    state = {
      collapsed: false,
    };
  
    onCollapse = (collapsed) => {
      console.log(collapsed);
      this.setState({ collapsed });
    }
    
    render() {
      return (
          <Affix>
            <Header
            style={{
              height : '60px',
              backgroundColor :'#fff',
            }}>
            <img src={logo} alt='云顶' style={{
              paddingLeft:'50px'
            }}/>
            <Form.Item style={{width:200,display:'inline-block',verticalAlign:'center',}}>
              <Input prefix={<Button icon="search" className='topbtn' type='submit' shape='circle' />} style={{
                marginTop:16,
                marginLeft:'360%',
                border:'0 0 1 0',
              }}
              className='topSearch' />
            </Form.Item>
            </Header>
          </Affix>
      );
    }
  }

export default Commonhead;