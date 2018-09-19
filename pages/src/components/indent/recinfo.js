import React, { Component } from 'react';
import { Form, Input } from 'antd';
import './recinfo.css'
const {TextArea} = Input;
const FormItem = Form.Item;

/* 2018.8.13：为了减少控件间传值，此页面已被搬移到主订单页面中 */

/* 收货信息填写组件 2018.8.12 */
class Recinfo extends Component {

  render() {
    return (
        <div className="recinfo">
            <h1>收货信息</h1>
            <Form layout="inline">
                <FormItem className="rec-name" >
                    <p>收货人</p>
                    <Input placeholder="请您填写收货人姓名" />
                    <p>手机号</p>
                    <Input placeholder="请您填写手机号码"/>
                </FormItem>
                
                <FormItem className="rec-add">
                    <p>收货地址</p>
                    <TextArea placeholder="请您填写收货人地址" autosize={{ minRows: 5, maxRows: 5 }} />
                </FormItem>
            </Form>
        </div>
    );
  }
}

export default Recinfo;