import React, { Component } from 'react';
import {  Form, Input, Button,message } from 'antd';
import {Link} from 'react-router-dom';
import $ from 'jquery';
import './sign.css';

const FormItem = Form.Item;

/* 公共登录/注册组件，改版时间2018.8.15*/
class LogInForm extends Component {
    constructor(props) {
        super(props);
        
    }

    login = () => {
        //console.log('ajax start');
        $.ajax({
            
            url : "http://qiyubing.cn/yundingShop/api/account/login",	//请求url
            type : "POST",	//请求类型  post|get
            // data : "key=value&key1=value2",	//后台用 request.getParameter("key");
            dataType: "json",    //返回数据的 类型 text|json|html--
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify({
                "loginName": $("#logname").val(),
                "password": $("#password").val()
            }), 
            success:(verify) =>{
                if(verify.code === 200){
                    message.success(verify.message,2);
                    sessionStorage.setItem("token",verify.data.token);
                    sessionStorage.setItem("expirein",verify.data.expireIn);
                    global.commonright.changesideright();
                    global.commonright.handleCancel();
                    /*let token = sessionStorage.getItem("token");
                    alert(token);*/
                }else if(verify.code === 500){
                    message.warning(verify.message,2);
                }
                
            },
            error:() => { 
                message.error("登录失败，请检查网络设置");
            }
            
    });
    }


    render() {
        return (
                <div>
                <Form className="login" >
                    <p>登录名：</p>
                    <FormItem>
                        {this.props.form.getFieldDecorator('logname', {
                            rules: [{ required: true, message: '请输入手机号！' }],
                        })(
                            <Input id="logname" autocomplete="off" />
                        )}
                    </FormItem>
                    <p>密码：</p>
                    <FormItem>
                        {this.props.form.getFieldDecorator('password', {
                            rules: [{ required: true, message: '请输入你的密码' }],
                        })(
                            <Input type="password" id="password" autocomplete="off" />
                        )}
                    </FormItem>
                    <FormItem>
                        <div>
                            {/*<a className="phone-number-switch">手机验证码登录</a>
                            <a>忘记密码</a>*/}
                        </div>
                        <Button htmlType="submit" className="login-button" block="true" hight="60px" onClick={this.login} >
                            登录
                        </Button>
                        <div>
                            <p>没有账号？</p><Link to="/signup"><a className="signup-switch">注册</a></Link>
                        </div>
                    </FormItem>
                </Form>
                </div>
            
        );
    }
}

const LogIn = Form.create()(LogInForm);

export default LogIn;