import React, { Component } from 'react';
import { Layout, Mention, Button } from 'antd';
import {Link} from 'react-router-dom';
import './Commonhead.css';
const { toString,contentState } = Mention;

/* 公共头部文件2.0，改版时间：2018.08.07 */
class Commonhead extends Component {
    
    constructor(){
      super();
      this.state = {
        search:"",
      }
    }

    searchs = (contentState) =>{
      let temp = toString(contentState);
      this.setState({
        search: temp
      });     
    }
    
    render() {
      return (
          <Layout className="common-header">
            <Link to="/"><i className="header-logo">YUNDING</i></Link>{/* logo待替换 */}
            <Mention placeholder="搜索" className="head-search" onChange={this.searchs}/>
            <Button className="head-search-button"><Link to={`/search/${this.state.search}`}><i>&#xe60c;</i></Link></Button>
          </Layout>
      );
    }
  }

export default Commonhead;