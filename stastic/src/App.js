import React, { Component } from 'react';
import { Layout } from 'antd';
import './App.css';
import Commonhead from './common/Commonhead';
import Commonleft from './common/Commonleft';
import Commonright from './common/Commonright';
const {  Content } = Layout;

class App extends Component {

  render() {
    return (
        <div>
        <Layout>
          <Commonhead />
        </Layout>
        <Layout hasSider='true'>
          <Commonleft />
          <Content style={{height:'2000px',backgroundColor:'#f5f5f5'}}></Content>
          <Commonright />
        </Layout>
        </div>
    );
  }
}

export default App;