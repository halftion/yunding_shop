import React,{Component} from 'react';
import 'antd/dist/antd.css';
import './myheader.css';
import { Upload,message,Modal,Input,Button,Radio,DatePicker } from 'antd';
import $ from 'jquery';
import moment from 'moment';
const RadioGroup = Radio.Group;




class Myheader extends Component {

	constructor () {
		super();
		this.state = {
			personal:[],
			order:[],
			visible1: false,
			sex:0,
		}
	}

	componentDidMount () {
		
		let token = "Bearer "+sessionStorage.getItem("token");
		$.ajax({
			url : 'http://qiyubing.cn/yundingShop/api/user/info',
			type : 'GET',
			headers : {'Authorization':token},
			/* 侧边栏加载 */
			success : (info) => {
				if(info.code === 200){
					let datalist = info.data;
					this.setState({
						personal:datalist,
						sex:datalist.gender,
					});
				}	
			}
		});
		//console.log(token);   
	}

	/* 个人信息渲染 */

	renderMyhead () {
		const { personal } = this.state;
		let myhead = 
		<div>
			<Upload
				name="avatar"
				listType="picture-card"
				className="avatar-uploader"
				showUploadList={false}
				action={this.upload}
				beforeUpload={this.beforeUpload}
				
			>
				<img style={{width:150,height:150,borderRadius:75}} src={personal.avatar} id="avatar" alt="头像获取失败" />
			</Upload>
			<div className="myheader-first-bigbox">
				<div className="myheader-first-userInformation">
					<span className="myheader-first-userId">{personal.nickName}</span>
					<span className="myheader-first-setPassword" onClick={this.showModifier}>修改个人信息</span>
				</div>
				<div className="myheader-first-rightBox">
					<div>
						<p className="myheader-first-bindPhone">绑定手机：</p>
						<p>{personal.phoneNumber}</p>
					</div>
					<div>
						<p className="myheader-first-bindMailbox">经验值：</p>
						<p>{personal.xp}</p>
					</div>
				</div>
			</div>
		</div>;
		return myhead;

	} 

	renderModal() {
		const{ personal } = this.state;
		let personalInfo = <div>
								<div>
									<p>昵称：</p>
								<Input id="nickname" autocomplete="off" defaultValue={personal.nickName} />
								</div>
								<div>
									<p>性别：</p>
									<RadioGroup id="sex" onChange={this.onChange} value={this.state.sex} style={{width:"80%"}} >
										<Radio value={0}> 不公开 </Radio>
										<Radio value={1}> 男 </Radio>
										<Radio value={2}> 女</Radio>
									</RadioGroup>
								</div>
								<div>
									<p>生日：</p>
								<DatePicker id="birthday" placeholder="请选择日期" className="date-input" defaultValue={moment(personal.birthday)} />
								</div>
								<div>
									<p>手机：</p>
								<Input id="phone" autocomplete="off" disabled defaultValue={personal.phoneNumber}/>
								</div>
								<div>
									<p>邮箱：</p>
								<Input id="email" autocomplete="off" defaultValue={personal.email} />
								</div>
								
								<Button className="setPersonalInfo-btn" onClick={this.setPersonalInfo}>提交</Button>
							</div>
		;
		return personalInfo;
	}

	

	/* 修改个人信息 */

	showModifier = () => {
		this.setState({
			visible1: true
		  });
	}

	handleCancel = () => {
		this.setState({
		  visible1: false,
		});
	  }

	 beforeUpload(file) {
		const ispng = file.type === 'image/png';
		if (!ispng) {
		  message.error('头像暂时只支持png图片');
		}
		const isLt2M = file.size / 1024 / 1024 < 2;
		if (!isLt2M) {
		  message.error('请上传图片大小在2M以下的头像');
		}
		return ispng && isLt2M;
	  }

	  /* 修改头像 */
	  upload(file) {
		let formData = new FormData();
		formData.append("smfile",file);
		let avatarUrl;
		let token = "Bearer "+sessionStorage.getItem("token");
        $.ajax({
            type : "POST",
            contentType: false,
            processData: false,
            url : "https://sm.ms/api/upload",
            data : formData,
            success : (response) => {
                if(response.code === "success"){
                    {
						message.success("上传成功",10);
						avatarUrl = response.data.url;	
					}
				  }
				  
				  $.ajax({
					url : "http://qiyubing.cn/yundingShop/api/user/avatar",	//请求url
					type : "PUT",	//请求类型  post|get
					// data : "key=value&key1=value2",	//后台用 request.getParameter("key");
					dataType: "json",    //返回数据的 类型 text|json|html--
					contentType: "application/json;charset=utf-8",
					headers : {'Authorization':token},
					data:  JSON.stringify({'url':avatarUrl}),
					success: (response2) => {
						if(response2.code === 200){
							// message.success(response2.message,2);
							$("#avatar").attr('src',avatarUrl);
							global.commonright.changesideright();
						}else{
							message.error(response2.message,2);
						}
					},
					error:() => {message.error("数据库上传失败，请检查网络设置",2);},
				});
			}
		});
		
	}	

	/* 性别单选框 */
	onChange = (e) => {
		this.setState({
		  sex: e.target.value,
		});
	  }

	/* 提交更改 */
	setPersonalInfo = () => {
		let token = "Bearer "+sessionStorage.getItem("token");
		//birthday = moment.unix(birthday);
		let json = {
			"nickName" : $("#nickname").val(),
			"gender" : $("#sex input[type='radio']:checked").val(),
			"birthday" : $("#birthday input").val(),
			"phoneNumber" : this.state.personal.phoneNumber,
			"email" : $("#email").val()
		};
		$.ajax({
			url : "http://qiyubing.cn/yundingShop/api/user/info",	//请求url
			type : "PUT",	//请求类型  post|get
			// data : "key=value&key1=value2",	//后台用 request.getParameter("key");
			dataType: "json",    //返回数据的 类型 text|json|html--
			contentType: "application/json;charset=utf-8",
			headers : {'Authorization':token},
			data:  JSON.stringify(json),
			success: (response3) => {
				if(response3.code === 200){
					message.success(response3.message,2);
				}else{
					message.error(response3.message,2);
				}
			},
			error:() => {message.error("更改失败，请检查网络设置",2);},
		});
		this.setState({
			visible1:false,
		});
	}


	render() {
			return (
				<div className="myheader-background">
					<div className="myheader-first">
						<div><h1 className="myheader-first-title">账户信息</h1></div>
						{this.renderMyhead()}
					</div>	
					<Modal
						title="请设置个人信息"
						visible={this.state.visible1}
						onCancel={this.handleCancel}
						width="26%"
						maskClosable={true}
						footer={null}
						centered={true}
						wrapClassName="setPersonalInfo"
					>
						{this.renderModal()}
					</Modal>
				</div>
				);
			}
}
export default Myheader;
          