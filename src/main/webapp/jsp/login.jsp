<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	    <style>
	     body{
            background: url("<%= basePath %>/img/1.jpg");
        }
        #login{
            position: relative;
            width: 300px;
            height: 500px;
            margin: 80px auto;
            /*border: 1px solid red;*/
            background-color: #f1f1f1;
        }
        img{
            width: 300px;
            height: 80px;
        }
        #name{
            position: relative;
            width: 260px;
            height: 32px;
            background: cornsilk url("img/login2.png") no-repeat ;
            border: 1px solid #cccccc;
            margin: 20px;
            padding-left: 5px;
        }
        #username{
            position: absolute;
            right: 10px;
            width: 200px;
            height: 30px;
            border: none;
            background-color: cornsilk;
            outline: none;
        }
        #psw{
            position: relative;
            width: 260px;
            height: 32px;
            background: cornsilk url("img/login2.png") no-repeat 0px -30px ;
            border: 1px solid #cccccc;
            margin: 20px 20px 40px 20px;
            padding-left: 5px;
        }
        #password{
            position: absolute;
            right: 10px;
            width: 200px;
            height: 30px;
            border: none;
            background-color: cornsilk;
            outline: none;
        }
        #psw .message{
            position: absolute;
            width: 260px;
            height: 30px;
            top: 40px;
            left: 70px;
            color: red;
            display: none;
        }
        .usertype{
            position: absolute;
            left: 94px;
            width: 300px;
        }
        .submit{
            position: absolute;
            width: 270px;
            height: 34px;
            left: 20px;
            bottom: 200px;
            background-color: cadetblue;
            color: #f1f1f1;
            border:0;
            outline: none;
            cursor: pointer;
            letter-spacing: 5px;
        }
        .forget{
            position: absolute;
            left: 20px;
            bottom: 170px;
            color: blue;
            font-size: 12px;
            cursor: pointer;
        }
    </style>
       <script src="js/jquery.min.js"></script>
    <script>
    	//获取路径
		function getContextPath(){   
		    var pathName = document.location.pathname;   
		    var index = pathName.substr(1).indexOf("/");   
		    var result = pathName.substr(0,index+1);   
		    return result;   
		}  
        window.onload = function () {
            var oUsername = document.getElementById('username');
            var oPassword = document.getElementById('password');
            var oForget = document.getElementsByTagName('span')[0];
            var oMessage = document.getElementById('psw').getElementsByTagName('label')[0];
            var oBtn = document.getElementById('btn');



            oUsername.focus();
            oForget.onclick = function () {
                alert("忘记就算了吧。");
            }

            oBtn.onclick = function () {
               
                $.ajax({
                    url: "login",    //请求的url地址
                    dataType: "json",   //返回格式为json
                    async: true, 
                    data: { "account": encodeURI(oUsername.value),"password":encodeURI(oPassword.value)},    //参数值
                    type: "POST",   //请求方式
                    beforeSend: function() {
                        //请求前的处理
                    },
                    success: function(data) {
                    
                        //请求成功时处理
                        if(data.code === '1'){
                            oMessage.style.display = 'none';
                            window.location.href = getContextPath()+"/html/movieList.html"
                        }
                        else {
                            oMessage.style.display = 'block';
                        }
                    },
                    complete: function() {
                        //请求完成的处理
                    },
                    error: function() {
                        //请求出错处理
                    }
                });
            }
        }
    </script>
  </head>
  
  <body>
     <div id="login">
        <!--<form action="#" method="post">-->
            <img src="img/loginback.png"/>
            <div id="name">
                <input type="text" id="username" name="username" placeholder="请输入用户名"/><br/>
            </div>
            <div id="psw">
                <input type="password" id="password" name="password" placeholder="请输入密码"/><br/>
                <label class="message">用户名或密码错误</label>
            </div>
            <input class="submit" type="submit" id="btn" value="登录"/>
            <span class="forget">忘记密码？</span>
        <!--</form>-->
    </div>
  </body>
</html>
