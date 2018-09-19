import React, { Component } from 'react';
import {  Form, Input, Button,message } from 'antd';
import './sign.css';
import { Link } from 'react-router-dom';
import $ from 'jquery';
import '../../config';

const FormItem = Form.Item;

/* 公共登录/注册组件，改版时间2018.8.10*/
class SetPasswords extends Component {
    constructor() {
        super();
        () => {
            if(this.props.location.query === null){
                this.props.history.push("/404");
            }
        } 
    }

    signup = () => {
        let nickname = this.props.location.query.nickname;
        let phone = this.props.location.query.phone;
        let code = this.props.location.query.code;
        if( $("#password").val() === $("#passwordconfirm").val() ){
            $.ajax({
                url : "http://qiyubing.cn/yundingShop/api/account/register",	//请求url
                type : "POST",	//请求类型  post|get
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify({"loginName":phone,"nickName":nickname,"code":code,"password":$("#password").val()}),
                success:(submit) => {
                    if(submit.code === 200){
                        message.success(submit.message,2);
                        /*setTimeout(this.props.history.push("/login",2),2000)*/
                        sessionStorage.setItem("token",submit.data.token);
                        sessionStorage.setItem("expirein",submit.data.expireIn);
                        global.commonright.handleCancel();
                        global.commonright.changesideright();
                        this.props.history.push("/");
                    }else if(submit.code === 500){
                        message.warning(submit.message, 10);
                    }
                     
                }
                //回调函数 和 后台返回的 数据        
            });
        }else{
            message.warning("两次密码输入不一致",10);
        }
    }



    render() {
        return (

            <div>
                <div className="setpassword-dsc">
                    <p>亲爱的用户：</p>
                        <p>{this.props.location.query.nickname}</p>
                        <p>您好~请设置您的密码~</p>
                </div>
                <Form className="login" >
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
                            <Link to="/signup" style={{marginLeft:"10%"}}>上一步</Link>
                        </div>
                        <Button htmlType="button" className="login-button" block="true" hight="60px" onClick={this.signup} >
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

const SetPassword = Form.create()(SetPasswords);

export default SetPassword;