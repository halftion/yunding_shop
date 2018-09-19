import React, { Component } from 'react';
import $ from 'jquery';
import { Carousel } from 'antd';
import { Link } from 'react-router-dom';

class Banner extends Component {

    constructor(props) {
        super(props);
    }

        renderBanner() {

            let arr = [];
    
            $.ajax({
                url : 'http://qiyubing.cn/yundingShop/api/content/0',
                type : 'GET',
                async: false,
                success : function(response){
                    if (response.code === 200) {
                        $.each(response.data, function (index, content) {
                            arr.push(<Link to={content.url}><div><img src={content.pic}/></div></Link>);
                            // arr.push(<div><img src="http://qiyubing.cn/yundingShop/static/upload/content/banner2.png"/></div>);
                        });
                    }
                },
                error:function(status){
                    console.log(status);
                }
            });
    
            return arr;
    
        }
    
    

     /* banner = () => {

        const _this = this;
                    $.get("http://qiyubing.cn/yundingShop/api/content/0", function (response) {
                        if (response.code === 200) {
                            $.each(response.data, function (index, content) {
                                 html += `<div key=${index}><img src="${content.pic}" /></div>`;
                                $(".banner").append(html);
                                _this.setState({
                                    html : html
                                });
                                alert(html);
                            });
                        } else {
                            alert(response.message);
                        }
                        alert(html);
                    });


      }*/
     
          
          
    /*componentWillMount(){
        this.banner();
      }*/
      
  render() {
    return (
        <Carousel className="banner" autoplay="true" >
            {this. renderBanner()}
            {/*    <div><img src="http://i4.bvimg.com/657228/150ff806666b9a8c.png" /></div>
                <div><img src="http://i4.bvimg.com/657228/150ff806666b9a8c.png" /></div>
                <div><img src="http://i4.bvimg.com/657228/150ff806666b9a8c.png" /></div>*/}
        </Carousel>

    );
  }
}

export default Banner;