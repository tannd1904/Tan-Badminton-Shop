<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>N17DCCN150</title>
<style>
<%@ include file="/resources/css/user-login.css"%>
</style>
</head>
<body>
		<div class="login-container" style="background-color: #e3faff;">
	        <div id="login" class="login-box">
	            <div class="login-content">
	            <img alt="logo" src="${pageContext.request.contextPath}/resources/img/logo/TAN.png">
	                <h1>Đăng Nhập</h1>
	                <form class="login-form"  action="${pageContext.request.contextPath}/admin/login.htm" method="post">
	                    <div class="usern">
	                        <label for="uname"><b>Username</b></label>
	                        <input type="text" placeholder="Enter Username" name="user" required>
	                    </div>
	                    <div class="usern">
	                        <label for="psw"><b>Password</b></label>
	                        <input type="password" placeholder="Enter Password" name="pass" required>
	                    </div>
	                    <button >Login</button>
	                </form>
	                <h2>${tb}</h2>
	                <a href="${pageContext.request.contextPath}/home/index.htm"><b>Quay lại trang chủ</b></a>
	            </div>
	            
	        </div>
	        
		</div>
 	
</body>
</html>