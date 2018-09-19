import React, { Component } from 'react';
import {  Form, Input, Button,message } from 'antd';
import { Link } from 'react-router-dom';
import './sign.css';
import $ from 'jquery';

const FormItem = Form.Item;

/* 公共登录/注册组件，改版时间2018.8.10*/
class SignUpForm extends Component {
    constructor() {
        super();
        this.state = {
            verify: [],
            occupy: [],
            submit: [],
            disabled:false
        }
    }

    
    
    /* 发送验证码 */
    getCode = () =>{
        let phone = $("#phoneNumber").val();
        var myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/; 
        if(myreg.test(phone)){
            
                let url = `http://qiyubing.cn/yundingShop/api/verificationCode/send/${phone}`;
                $.ajax({
                    
                    url : url,	//请求url
                    type : "GET",	//请求类型  post|get
                    // data : "key=value&key1=value2",	//后台用 request.getParameter("key");
                    dataType: "json",    //返回数据的 类型 text|json|html--
                    //data:{"pic":$("#pic").val(),"title":$("#title").val()},
                    
                    success:verify =>{
                        if(verify.code === 200){
                            message.success(verify.message,10);
                        }else if(verify.code === 500){
                            message.warning(verify.message,10);
                        }
                        /* 计时器，提交后60秒内无法再次申请验证码： */
                        let time = 60;
                        let timer = setInterval(() => {
                            if (time === 0){
                            let temp = $("#getCode");
                            let item = `<span>获取短信验证码</span>`;
                            temp.html(item);
                            temp.attr({"disabled":false});
                            clearInterval(timer); 
                            }else{
                            /*alert(time);*/
                            let temp = $("#getCode");
                            temp.attr({"disabled":"disabled"});
                            let item = `<span>(${time}s)后再次获取验证码</span>`;
                            temp.html(item);
                            --time;
                        }},1000);
                    },
                    error:() => {
                        message.error("请求失败，请检查网络",10);
                    }
                    
                    //回调函数 和 后台返回的 数据        
                    });
                
        }else{
            message.error("手机号格式错误",2)
        }
        

    }

    

    /* 校验用户名是否可用： */
    checknickname = () => {
        let myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/; 
        let nickname = $("#phoneNumber").val();
        if(myreg.test(nickname)){
        let url2 = `http://qiyubing.cn/yundingShop/api/account/checkLoginName/${nickname}`;
                $.ajax({
                    
                    url : url2,	//请求url
                    type : "GET",	//请求类型  post|get
                    // data : "key=value&key1=value2",	//后台用 request.getParameter("key");
                    dataType: "json",    //返回数据的 类型 text|json|html--
                    //data:{"pic":$("#pic").val(),"title":$("#title").val()},
                    
                    success:occupy =>{
                        if(occupy.code === 500){
                            message.warning(occupy.message, 10);
                        }else if(occupy.code === 200){
                            message.success("该手机号可用",2);
                        }
                        
                    },
                    error:() => {alert("校验失败，请检查网络设置")}
                    //回调函数 和 后台返回的 数据
                             
                    });
                   
            }
    }

    nextstep = () => {
        let code = $(".code").val();
        let phone = $("#phoneNumber").val();
        let data = {
            nickname:$("#nickName").val(),
            phone:phone,
            code:code
        }
        if(data.nickname.length >= 3 ){
            let url = `http://qiyubing.cn/yundingShop/api/verificationCode/check/${phone}/${code}`
            $.ajax({
                url : url,	//请求url
                type : "GET",	//请求类型  post|get
                // data : "key=value&key1=value2",	//后台用 request.getParameter("key");
                dataType: "json",    //返回数据的 类型 text|json|html--
                //data:{"loginName":$("#phoneNumber").val(),"password":$("#password").val()},
                success:(submit) => {
                    if(submit.code === 200){
                        message.success(submit.message,2);
                        setTimeout(this.props.history.push({pathname:'/setpassword',query:data}),2000);
                    }else if(submit.code === 500){
                        message.warning(submit.message, 10);
                    }
                    
                }
                    //回调函数 和 后台返回的 数据        
                });
            }else{
                message.warning("用户名长度必须大于两位",2);
            }
        
    }


    render() {
        return (
                <div className="sign-up">
                <Form className="login" >
                    <p>昵称</p>
                    <FormItem>
                        {this.props.form.getFieldDecorator('nickName', {
                            rules: [{ required: true, message: '请输入昵称！' }],
                        })(
                            <Input autocomplete="off" id="nickName" />
                        )}
                    </FormItem>
                    <p>手机号</p>
                    <FormItem>
                        {this.props.form.getFieldDecorator('phoneNumber', {
                            rules: [{ required: true, message: '请输入你的手机号' }],
                        })(
                            <Input autocomplete="off" id="phoneNumber" onChange={this.checknickname}/>
                        )}
                    </FormItem>
                    <p>验证码</p>
                    <FormItem>
                        {this.props.form.getFieldDecorator('codeNumber', {
                            rules: [{ required: true, message: '验证码错误' }],
                        })(
                            <Input autocomplete="off" className="code" addonAfter={<Button onClick={this.getCode} id="getCode">获取短信验证码</Button>}/>
                        )}
                        
                    </FormItem>
                    <FormItem>
                        <div className="switch">
                            <p>注册即代表同意<a className="protocol">《云顶服务协议》</a></p>
                        </div>
                        <Button htmlType="submit" className="login-button" block="true" hight="60px"  onClick={this.nextstep}>
                            下一步
                        </Button>
                        <div className="switch">
                            <p>已有账号？</p><Link to="/login"><a className="signup-switch"  >登录</a></Link>
                        </div>
                    </FormItem>
                </Form>
                </div>
            
        );
    }
}

const SignUp = Form.create()(SignUpForm);

export default SignUp;