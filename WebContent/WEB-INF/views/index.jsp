<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>N17DCCN150</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.js"></script>
<script type="text/javascript" src=" ${pageContext.request.contextPath}/resources/js/slider.js"></script>
<script type="text/javascript" src=" ${pageContext.request.contextPath}/resources/js/sub.js"></script>
<base href="${pageContext.servletContext.contextPath }" />
<style>
<%@ include file="/resources/css/reset.css"%>
<%@ include file="/resources/css/index.css"%>
<%@ include file="/resources/css/home.css"%>
<%@ include file="/resources/css/list-pro.css"%>
<%@ include file="/resources/css/pro-del.css"%>
<%@ include file="/resources/css/checkout.css"%>
</style>
</head>
<body>
	
	<c:if test="#{flag==0 }">
		document.getElementById('id01').style.display='block
	</c:if>
	
	<div class="icon-bar">
		<a onclick="openCart()" class="carticon"><i class="fas fa-shopping-cart"></i></a>
		<a href="#" class="facebook"><i class="fa fa-facebook"></i></a> 
	    <a href="#" class="twitter"><i class="fa fa-twitter"></i></a> 
	    <a href="#" class="google"><i class="fa fa-google"></i></a> 
	    <a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a>
	    <a href="#" class="youtube"><i class="fa fa-youtube"></i></a> 
	</div>
	
	<div id="mySidebar" class="sidebar">
	  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
	  <a href="#">About</a>
	  <a href="#">Services</a>
	  <a href="#">Clients</a>
	  <a href="#">Contact</a>
	</div>
	
	<div id="main">
	  <button class="openbtn" onclick="openNav()">☰</button>  
	</div>
	
	<div id="myCart" class="cartslide" style="background-color: #4ac4f1;">
		<a href="javascript:void(0)" class="closebtn" onclick="closeCart()"><i class="far fa-times-circle "></i></a>
		      <h1>Giỏ Hàng</h1>
		      <div class="right-cont">
                	<br>
					<c:forEach var="a" items="${cart}">
					<div class="item-cart ">
						<a class="del" href="${pageContext.request.contextPath}/home/deletecart.htm?idSanPham=${a.idSanPham}"><i class="far fa-times-circle "></i></a>
                        <img src="${pageContext.request.contextPath}/resources/img/pro/${a.image}" alt=" ">
                        <div class="item-cart1 ">
                            <a class="pr"  href="${pageContext.request.contextPath}/detail/${a.idSanPham}.htm">${a.tenSanPham}</a>
                            <div class="sl-tt ">
                                <select   name="size${a.idSanPham}" id="size" class="size">
		                        <c:forEach var="sz" items="${prode}">
		                       		<c:if test="${sz.product.id==a.idSanPham}">
		                       		<c:choose>
		                       			<c:when test="${a.size==sz.size}">
		                       				<option selected="selected" value="${sz.size}">${sz.size}</option>
		                       			</c:when>
		                       			<c:otherwise><option value="${sz.size}">${sz.size}</option></c:otherwise>
		                       		</c:choose>
		                       		</c:if>
		                       </c:forEach>   
                                </select >
                                <h4><fmt:formatNumber pattern="###,### VND" value="${a.giaSanPham*a.soLuong}" type="currency" /></h4>                     
                            </div>
                        </div>
                    </div>
					</c:forEach>
                    <div class="sum ">
                        <div class="s-t ">
                            <h4><b>Thành Tiền:</b></h4>
                            <h1><fmt:formatNumber pattern="###,### VND" value="${tongTien}" type="currency" /></h1> 
                        </div>
                    </div>
                    <div class="btn">
                    	<a href="${pageContext.request.contextPath}/home/order.htm">Thanh toán</a>
                    </div>
                </div>
            </div>
	
	<div id="id01" class="modal">
		  <form class="modal-content animate" action="${pageContext.request.contextPath}/home/log-in.htm" method="post">
		    <div class="imgcontainer">
		      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
		      <img src="${pageContext.request.contextPath}/resources/img/logo/TAN.png" alt="Avatar" class="avatar">
		    </div>
		
		    <div class="container">
		      <label for="uname"><b>Username</b></label>
		      <input type="text" placeholder="Enter Username" name="uname" required>
		
		      <label for="psw"><b>Password</b></label>
		      <input type="password" placeholder="Enter Password" name="psw" required>
		        
		      <button type="submit">Login</button>
		      <label>
		        <input type="checkbox" checked="checked" name="remember"> Remember me
		      </label>
		      <h2>${tb }</h2>
		    </div>
		
		    <div class="container" style="background-color:#f1f1f1">
		      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
		      <span class="psw">Forgot <a href="#">password?</a></span>
		    </div>
		  </form>
		</div>
		

	<header>
		<div class="container">
			<div class="logo">
				<a href=""><img
					src="${pageContext.request.contextPath}/resources/img/logo/tanshop-logo.png" alt="Logo Shop"></a>
			</div>
			<div class="contener1">
					<ul class="navbar">
										
						<c:forEach var="menu1" items="${menu}">
							<c:if test="${menu1.parentid==null}">
								<li>
									<a href="${pageContext.request.contextPath}/${menu1.link}">${menu1.name}</a>
									<div class="sub-menu">
										<div class="sub-menu1">
											<ul>
												<c:forEach var="menu2" items="${menu}">
													<c:if test="${menu2.parentid==menu1.id}">
														<li><a href="${pageContext.request.contextPath}/list/${menu2.link}.htm">${menu2.name }</a></li>
													</c:if>
												</c:forEach>
											</ul>
										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
                <div class="navbar-right">
                	<form action="${pageContext.request.contextPath}/list/search.htm">
                		<div class="search">
	                        <input class="ip-search" name="search_string" placeholder="Search..."/>
	                        <button><i class="fas fa-search icon-search"></i></button>
	                    </div>
                	</form>
                     <div class="dropdown">
					    <button class="dropbtn">Chào, ${username }
					    </button>
					    <div class="dropdown-content">
					      <c:choose>
					      	<c:when test="${username=='Guest' }">
					      		<a onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Đăng nhập</a>
					      		<a href="${pageContext.request.contextPath}/home/sign-up.htm">Đăng kí</a>
					      		
					      	</c:when>
					      	<c:otherwise>
					      		<a href="${pageContext.request.contextPath}/home/logout.htm"> Đăng xuất</a>
					      	</c:otherwise>
					      </c:choose>
					    </div>
					</div> 
                </div>
			</div>
		</div>
		
		<div id="user" class="user-box">
            <span onclick="document.getElementById('user').style.display='none'" class="close" title="Close Modal">&times;</span>
            <div class="user-content">
                <div class="content-top">
                    <i class="fas fa-user-circle"></i>
                    <a>${username}</a>
                </div>
                <div class="content-bottom">
                    <c:choose>
                    	<c:when test="${username=='Guest'}">
                    		<button > <a href="${pageContext.request.contextPath}/home/login.htm"> Đăng nhập</a> </button>
                    		<button > <a href="${pageContext.request.contextPath}/home/sign-up.htm"> Đăng kí</a> </button>
                    	</c:when>
                    	<c:otherwise><button > <a href="${pageContext.request.contextPath}/home/logout.htm"> Đăng xuất</a> </button></c:otherwise>
                    </c:choose>
                </div>
            </div> 
        </div>
	</header>
	
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
	
	<div class="slider">
        <div class=" owl-carousel owl-theme ">
			<c:forEach var = "ban" items="${banner}">
				<c:if test="${ban.status== 1}">
				<div class="item">
                   <div class="item-con">
                    <img src="${pageContext.request.contextPath}/resources/img/banner/${ban.link}">
                  </div>
            	</div>
				</c:if>
			</c:forEach>
        </div>
    </div>
	<br>
	
	<div class="new-pro">
        <a href="${pageContext.request.contextPath}/list/vot-cau-long.htm">
            <h1>Vợt cầu lông</h1>
        </a>
        <div class="container-pro">
        	<c:forEach var="pn" items="${vot}">
        	<div class="item-pro">
                <div class="sale">
                    <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm"><img src="${pageContext.request.contextPath}/resources/img/pro/${pn.img1}" alt=""></a>
                    <c:if test="${pn.sale!=0}">
                    	<h1>-${pn.sale}%</h1>
                    </c:if>
                    <c:if test="${pn.status!=1 }">
                    	<h1>Liên hệ</h1>
                    </c:if>
                </div>
                <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm">${pn.name}</a>
                <h3>${pn.coler}</h3>
                <div style="margin-top: 5px" class="price">
                    <c:if test="${pn.sale==0}">
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price}" type="currency" /></h3>
                    </c:if>
                    <c:if test="${pn.sale!=0}">
                    	<del><h3><fmt:formatNumber pattern="###,###" value="${pn.price}" type="currency" /> </h3></del>
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price - (pn.price*pn.sale)/100}" type="currency" /></h3>
                    </c:if>
                </div>
                <c:if test="${pn.status!=1 }">
                	<div class="bt-ab">
                    	<button disabled="disabled">Mua ngay</button>
                    	<button disabled="disabled">Thêm</button>
                	</div>
                </c:if>
                <c:if test="${pn.status==1 }">
                	<div class="bt-ab">
	                    <a class="bt-mua" href="${pageContext.request.contextPath}/home/shopping-now.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Mua ngay</a>
	                    <a class="bt-add" href="${pageContext.request.contextPath}/home/shopping.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Thêm</a>
	                </div>
                </c:if>
            </div>
        	</c:forEach>
        </div>
    </div>
	<br>
	
	<div class="new-pro">
        <a href="${pageContext.request.contextPath}/list/giay-cau-long.htm">
            <h1>Giày cầu lông</h1>
        </a>
        <div class="container-pro">
        	<c:forEach var="pn" items="${giay}">
        	<div class="item-pro">
                <div class="sale">
                    <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm"><img src="${pageContext.request.contextPath}/resources/img/pro/${pn.img1}" alt=""></a>
                    <c:if test="${pn.sale!=0}">
                    	<h1>-${pn.sale}%</h1>
                    </c:if>
                    <c:if test="${pn.status!=1 }">
                    	<h1>Liên hệ</h1>
                    </c:if>
                </div>
                <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm">${pn.name}</a>
                <h3>${pn.coler}</h3>
                <div style="margin-top: 5px" class="price">
                    <c:if test="${pn.sale==0}">
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price}" type="currency" /></h3>
                    </c:if>
                    <c:if test="${pn.sale!=0}">
                    	<del><h3><fmt:formatNumber pattern="###,###" value="${pn.price}" type="currency" /> </h3></del>
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price - (pn.price*pn.sale)/100}" type="currency" /></h3>
                    </c:if>
                </div>
                <c:if test="${pn.status!=1 }">
                	<div class="bt-ab">
						<button disabled="disabled">Mua ngay</button>
                    	<button disabled="disabled">Thêm</button>
                	</div>
                </c:if>
                <c:if test="${pn.status==1 }">
                	<div class="bt-ab">
	                    <a class="bt-mua" href="${pageContext.request.contextPath}/home/shopping-now.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Mua ngay</a>
	                    <a class="bt-add" href="${pageContext.request.contextPath}/home/shopping.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Thêm</a>
	                </div>
                </c:if>
            </div>
        	</c:forEach>
        </div>
    </div>
	<br>
	
	<div class="new-pro">
        <a href="${pageContext.request.contextPath}/list/ao-quan-cau-long.htm">
            <h1>Áo quần cầu lông</h1>
        </a>
        <div class="container-pro">
        	<c:forEach var="pn" items="${aoquan}">
        	<div class="item-pro">
                <div class="sale">
                    <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm"><img src="${pageContext.request.contextPath}/resources/img/pro/${pn.img1}" alt=""></a>
                    <c:if test="${pn.sale!=0}">
                    	<h1>-${pn.sale}%</h1>
                    </c:if>
                    <c:if test="${pn.status!=1 }">
                    	<h1>Liên hệ</h1>
                    </c:if>
                </div>
                <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm">${pn.name}</a>
                <h3>${pn.coler}</h3>
                <div style="margin-top: 5px" class="price">
                    <c:if test="${pn.sale==0}">
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price}" type="currency" /></h3>
                    </c:if>
                    <c:if test="${pn.sale!=0}">
                    	<del><h3><fmt:formatNumber pattern="###,###" value="${pn.price}" type="currency" /> </h3></del>
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price - (pn.price*pn.sale)/100}" type="currency" /></h3>
                    </c:if>
                </div>
                <c:if test="${pn.status!=1 }">
                	<div class="bt-ab">
                    	<button disabled="disabled">Mua ngay</button>
                    	<button disabled="disabled">Thêm</button>
                	</div>
                </c:if>
                <c:if test="${pn.status==1 }">
                	<div class="bt-ab">
	                    <a class="bt-mua" href="${pageContext.request.contextPath}/home/shopping-now.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Mua ngay</a>
	                    <a class="bt-add" href="${pageContext.request.contextPath}/home/shopping.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Thêm</a>
	                </div>
                </c:if>
            </div>
        	</c:forEach>
        </div>
    </div>
    <br>
    
    <div class="new-pro">
        <a href="${pageContext.request.contextPath}/list/phu-kien.htm">
            <h1>Phụ kiện cầu lông</h1>
        </a>
        <div class="container-pro">
        	<c:forEach var="pn" items="${phukien}">
        	<div class="item-pro">
                <div class="sale">
                    <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm"><img src="${pageContext.request.contextPath}/resources/img/pro/${pn.img1}" alt=""></a>
                    <c:if test="${pn.sale!=0}">
                    	<h1>-${pn.sale}%</h1>
                    </c:if>
                    <c:if test="${pn.status!=1 }">
                    	<h1>Liên hệ</h1>
                    </c:if>
                </div>
                <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm">${pn.name}</a>
                <h3>${pn.coler}</h3>
                <div style="margin-top: 5px" class="price">
                    <c:if test="${pn.sale==0}">
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price}" type="currency" /></h3>
                    </c:if>
                    <c:if test="${pn.sale!=0}">
                    	<del><h3><fmt:formatNumber pattern="###,###" value="${pn.price}" type="currency" /> </h3></del>
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price - (pn.price*pn.sale)/100}" type="currency" /></h3>
                    </c:if>
                </div>
                <c:if test="${pn.status!=1 }">
                	<div class="bt-ab">
                    	<button disabled="disabled">Mua ngay</button>
                    	<button disabled="disabled">Thêm</button>
                	</div>
                </c:if>
                <c:if test="${pn.status==1 }">
                	<div class="bt-ab">
	                    <a class="bt-mua" href="${pageContext.request.contextPath}/home/shopping-now.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Mua ngay</a>
	                    <a class="bt-add" href="${pageContext.request.contextPath}/home/shopping.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Thêm</a>
	                </div>
                </c:if>
                </div>
        	</c:forEach>
        </div>
    </div>
	<br>
	
	<div class="new-pro">
        <a href="">
            <h1>Sản phẩm mới</h1>
        </a>
        <div class="container-pro">
        	<c:forEach var="pn" items="${newproduct}">
        	<div class="item-pro">
                <div class="sale">
                    <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm"><img src="${pageContext.request.contextPath}/resources/img/pro/${pn.img1}" alt=""></a>
                    <c:if test="${pn.sale!=0}">
                    	<h1>-${pn.sale}%</h1>
                    </c:if>
                    <c:if test="${pn.status!=1 }">
                    	<h1>Liên hệ</h1>
                    </c:if>
                </div>
                <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm">${pn.name}</a>
                <h3>${pn.coler}</h3>
                <div style="margin-top: 5px" class="price">
                    <c:if test="${pn.sale==0}">
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price}" type="currency" /></h3>
                    </c:if>
                    <c:if test="${pn.sale!=0}">
                    	<del><h3><fmt:formatNumber pattern="###,###" value="${pn.price}" type="currency" /> </h3></del>
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price - (pn.price*pn.sale)/100}" type="currency" /></h3>
                    </c:if>
                </div>
                <c:if test="${pn.status!=1 }">
                	<div class="bt-ab">
                    	<button disabled="disabled">Mua ngay</button>
                    	<button disabled="disabled">Thêm</button>
                	</div>
                </c:if>
                <c:if test="${pn.status==1 }">
                	<div class="bt-ab">
	                    <a class="bt-mua" href="${pageContext.request.contextPath}/home/shopping-now.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Mua ngay</a>
	                    <a class="bt-add" href="${pageContext.request.contextPath}/home/shopping.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Thêm</a>
	                </div>
                </c:if>
            </div>
        	</c:forEach>
        </div>
    </div>
    <br>
    <div class="new-pro">
        <a href="">
            <h1>Sản phẩm bán chạy nhất</h1>
        </a>
        <div class="container-pro">
        	<c:forEach var="pn" items="${bestproduct}">
        	<div class="item-pro">
                <div class="sale">
                    <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm"><img src="${pageContext.request.contextPath}/resources/img/pro/${pn.img1}" alt=""></a>
                    <c:if test="${pn.sale!=0}">
                    	<h1>-${pn.sale}%</h1>
                    </c:if>
                    <c:if test="${pn.status!=1 }">
                    	<h1>Liên hệ</h1>
                    </c:if>
                </div>
                <a href="${pageContext.request.contextPath}/detail/${pn.id}.htm">${pn.name}</a>
                <h3>${pn.coler}</h3>
                <div style="margin-top: 5px" class="price">
                    <c:if test="${pn.sale==0}">
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price}" type="currency" /></h3>
                    </c:if>
                    <c:if test="${pn.sale!=0}">
                    	<del><h3><fmt:formatNumber pattern="###,###" value="${pn.price}" type="currency" /> </h3></del>
                    	<h3><fmt:formatNumber pattern="###,### VND" value="${pn.price - (pn.price*pn.sale)/100}" type="currency" /></h3>
                    </c:if>
                </div>
                <c:if test="${pn.status!=1 }">
                	<div class="bt-ab">
                    	<button disabled="disabled">Mua ngay</button>
                    	<button disabled="disabled">Thêm</button>
                	</div>
                </c:if>
                <c:if test="${pn.status==1 }">
                	<div class="bt-ab">
	                    <a class="bt-mua" href="${pageContext.request.contextPath}/home/shopping-now.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Mua ngay</a>
	                    <a class="bt-add" href="${pageContext.request.contextPath}/home/shopping.htm?id=${pn.id }&name=${pn.name }&coler=${pn.coler}&gia=${pn.price }&image=${pn.img1}">Thêm</a>
	                </div>
                </c:if>
            </div>
        	</c:forEach>
        </div>
    </div>
	<div class="contents">
	        <div class="container-cont">
	            <div class="slidercont">
	                <div class="owl-carousel owl-theme">
						<c:forEach var="sub" items="${subbanner}">
							<c:if test="${sub.status==2}">
								<div class="item-cont">
			                        <img src="${pageContext.request.contextPath}/resources/img/banner/${sub.link}">
			                        <a href="${pageContext.request.contextPath}/list/${sub.type}.htm">${sub.name}</a>
			                        <p>${sub.content}</p>
	                    		</div>
							</c:if>
						</c:forEach>
	                </div>
	            </div>
	        </div>
	        <div class="container-cont">
	            <div class="slidercont">
	                <div class="owl-carousel owl-theme">
						<c:forEach var="sub" items="${subbanner}">
							<c:if test="${sub.status==3}">
								<div class="item-cont">
			                        <img src="${pageContext.request.contextPath}/resources/img/banner/${sub.link}">
			                        <a href="${pageContext.request.contextPath}/list/${sub.type}.htm">${sub.name}</a>
			                        <p>${sub.content}</p>
	                    		</div>
							</c:if>
						</c:forEach>
	                </div>
	            </div>
	        </div>
    </div>
     
	
    <footer>
        <div id="about" class="footer-cont">
            <img src="${pageContext.request.contextPath}/resources/img/logo/logo-footer.png" alt="">
            <ul>
                Sản phẩm
                <li><a href="${pageContext.request.contextPath}/list/vot-cau-long.htm">Vợt cầu lông</a></li>
                <li><a href="${pageContext.request.contextPath}/list/giay-cau-long.htm">Giày cầu lông</a></li>
                <li><a href="${pageContext.request.contextPath}/list/ao-quan-cau-long.htm">Áo quần cầu lông</a></li>
                <li><a href="${pageContext.request.contextPath}/list/phu-kien.htm">Phụ kiện cầu lông</a></li>
                <li><a href="${pageContext.request.contextPath}/list/sale.htm">Sale-off</a></li>
            </ul>
            <ul>
                Liên hệ
                <li><a href="">Email : tannd194@gmail.com</a></li>
                <li><a href="">Hotline : 0389211236</a></li>
            </ul>
            <ul>
                Hỗ Trợ
                <li><a href="">FAQs</a></li>
                <li><a href="">Chính sách chung</a></li>
                <li><a href="">Tra cứu đơn hàng</a></li>
            </ul>
        </div>
        <h1>Copyright &copy 2020 TanShop. All rights reserved.</h1>
    </footer>
</body>

<script>

	//Get the button
	var mybutton = document.getElementById("myBtn");
	
	// When the user scrolls down 20px from the top of the document, show the button
	window.onscroll = function() {scrollFunction()};
	
	function scrollFunction() {
	  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	    mybutton.style.display = "block";
	  } else {
	    mybutton.style.display = "none";
	  }
	}
	
	// When the user clicks on the button, scroll to the top of the document
	function topFunction() {
	  document.body.scrollTop = 0;
	  document.documentElement.scrollTop = 0;
	}
	
	
	
    // Get the modal
    var modalus = document.getElementById('user');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modalus) {
            modalus.style.display = "none";
        }
    }
    var modallg = document.getElementById('login');
    window.onclick = function(event) {
        if (event.target == modallg) {
            modallg.style.display = "none";
        }
		
    }
    var modalsu = document.getElementById('sign-up');
    window.onclick = function(event) {
        if (event.target == modalsu) {
            modalsu.style.display = "none";
        }
    }
    var modalsu = document.getElementById('cart1');
    window.onclick = function(event) {
        if (event.target == modalsu) {
            modalsu.style.display = "inline-block";
        }
    }
    
    function openNav() {
    	  document.getElementById("mySidebar").style.width = "250px";
    	  document.getElementById("main").style.marginLeft = "250px";
    	}

    	function closeNav() {
    	  document.getElementById("mySidebar").style.width = "0";
    	  document.getElementById("main").style.marginLeft= "0";
    	}
    	var modalNav = document.getElementById('mySidebar');
    	 // When the user clicks anywhere outside of the modal, close it
    	    window.onclick = function(event) {
    	        if (event.target == modalNav) {
    	        	closeNav();
    	        }
    	    }
    	
    	
   	function openCart() {
   	  document.getElementById("myCart").style.width = "800px";
   	  document.getElementById("btnCart").style.marginRight = "800px";
   	}

   	function closeCart() {
   	  document.getElementById("myCart").style.width = "0";
   	  document.getElementById("btnCart").style.marginRight = "0";
   	}
   	
   	var modal = document.getElementById('myCart');
 // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
        	closeCart();
        }
    }
    
 // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    	
</script>
</html>