import React, { Component } from 'react';
import './home.css';
// import { Carousel } from 'antd';
import HotSale from '../components/home/hotsale';
import Newgoods from '../components/home/newgoods';
import Qualitygoods from '../components/home/qualitygoods';
import Banner from '../components/home/banner';
import '../components/common/goods.css'



class Home extends Component {
    
    componentDidMount(){
        window.scrollTo(0, 0);
    }
    
    
    render() {
      return (
          <div style={{position:'relative',backgroundColor:'#f5f5f5'}}>
            <Banner />
            <Qualitygoods />
            <HotSale />
            <Newgoods />
            
          </div>
      );
    }
  }
  
  export default Home;