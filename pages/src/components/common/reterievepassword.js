import React, { Component } from 'react';
import { Form, Input, Button } from 'antd';
import { Link } from 'react-router-dom';
import './sign.css';

const FormItem = Form.Item;

/* 公共登录/注册组件，改版时间2018.8.10*/
class ReterievePasswords extends Component {
    constructor() {
        super();
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    }

    render() {
        return (

            <div>
                <div className="resetpassword-dsc">
                    <h1>找回密码：</h1>
                        <p>验证码将直接发送到您的注册手机</p>
                </div>
                <Form  onSubmit={this.handleSubmit} className="login" >
                    <p>密码</p>
                    <FormItem>
                        {this.props.form.getFieldDecorator('password', {
                            rules: [{ required: true, message: '请输入您的密码！' }],
                        })(
                            <Input type="password" autocomplete="off"/>
                        )}
                    </FormItem>
                    <p>确认密码</p>
                    <FormItem>
                        {this.props.form.getFieldDecorator('passwordconfirm', {
                            rules: [{ required: true, message: '请确认您的密码' }],
                        })(
                            <Input type="password" autocomplete="off"/>
                        )}
                    </FormItem>
                    <FormItem>
                        <div className="switch">
                            <p>注册即代表同意<a className="protocol">《云顶服务协议》</a></p>
                        </div>
                        <Button htmlType="submit" className="login-button" block="true" hight="60px" >
                            注册
                        </Button>
                        <div className="switch">
                            <p>已有账号？</p><Link to="/login"><a className="signup-switch">登录</a></Link>
                        </div>
                    </FormItem>
                </Form>
            </div>
            
        );
    }
}

const ReterievePassword = Form.create()(ReterievePasswords);

export default ReterievePassword;