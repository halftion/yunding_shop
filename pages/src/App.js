import React, { Component } from 'react';
import { Layout } from 'antd';
import './App.css';
import { Switch, Route } from 'react-router-dom';
import Commonhead from './components/common/Commonhead';
import Commonleft from './components/common/Commonleft';
import Commonright from './components/common/Commonright';
import Home from './pages/home';
import SearchGoods from './pages/SearchGoods';
import Notfound from './components/common/Notfound';
import Commonfooter from './components/common/Commonfooter';
import GoodsDetails from './pages/goodsDetails';
import Shop from './pages/shop';
import PersonalCenter from './pages/personalCenter';
import Order from './pages/order';
import OrderDetails from './pages/orderDetails';
import PlatformGoods from './pages/platformGoods';
import ShopPlatform from './pages/shopplatfrom';
import Shopsearch from './pages/shopsearch';

const {  Content } = Layout;

class App extends Component {

  render() {
    return (
        <div>
          <Commonhead />
          <Commonleft />
          <Content style={{width:'100%',minWidth:1200,backgroundColor:'#f5f5f5'}}>
            <Switch>
              <Route exact path="/"  component={Home} />
              <Route exact path="/signup"  component={Home} />
              <Route exact path="/login"  component={Home} />
              <Route exact path="/setpassword"  component={Home} />
              <Route path="/goodsdetail/:id" component={GoodsDetails} />
              <Route path="/search/:searchname" component={SearchGoods} />
              <Route exact path="/shop/:shopid" component={Shop} />
              <Route path="/shop/:shopid/shopplantform" component={ShopPlatform} />
              <Route path="/shop/:shopid/shopsearch" component={Shopsearch} />
              <Route path="/personal" component={PersonalCenter} />
              <Route path="/order" component={Order} />
              <Route path="/orderdetail/:orderid" component={OrderDetails} />
              <Route path="/platform" component={PlatformGoods} /> 
              <Route component={Notfound} />
            </Switch>
            <Commonfooter />
          </Content>
          <Commonright />
        </div>
    );
  }
}

export default App;