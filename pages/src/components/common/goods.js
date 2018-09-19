import React, { Component } from 'react';
import './goods.css';

class Goods extends Component {

  render() {
    return (
            <li>
                <div className="goods">
                <img src="http://i4.bvimg.com/657228/7a412e9d6a55167d.png" />
                <h2>耐克夏季款Nike Air Max Air Force Sneakers </h2>
                <p><i>4564</i>人付款</p>
                <span>&yen;<i>313.00</i></span>
                </div>
            </li>        
    );
  }
}

export default Goods;