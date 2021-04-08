<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>N17DCCN150</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.js"></script>
<script type="text/javascript" src=" ${pageContext.request.contextPath}/resources/js/slider.js"></script>
<script type="text/javascript" src=" ${pageContext.request.contextPath}/resources/js/sub.js"></script>
<base href="${pageContext.servletContext.contextPath }" />
<style>
<%@ include file="/resources/css/reset.css"%>
<%@ include file="/resources/css/index.css"%>
<%@ include file="/resources/css/user-login.css"%>
*[id$=errors]{
	color:red;
	font-style: italic;
}
</style>
</head>
<body>
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
                                <c:forEach var="sz" items="${prode}">
                                	<c:if test="${sz.product.id==a.idSanPham && sz.size == a.size}">
                                		<input type="number" name="sl${a.idSanPham}" min="0" max="${sz.quanlity}" value="${a.soLuong}" >
                                	</c:if>
                                </c:forEach>
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
					      		<a href="${pageContext.request.contextPath}/home/login.htm">Đăng nhập</a>
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
		

	<div class="login-container">
        <div id="login" class="login-box">
            <div class="login-content">
                <h1>Quên mật khẩu</h1>
                <form class="login-form"  action="${pageContext.request.contextPath}/home/forget-pass.htm" method="post">
                    <div class="usern">
                        <label for="uname"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" required>
                    </div>
                    <button >Gửi mail</button>
                </form>
                <h4>${tinnhan}</h4>
                <h2>${tb}</h2>
                <div class="login-bottom">
                     <button><a  href="${pageContext.request.contextPath}/home/login.htm"> Đăng nhập</a></button>
                    <button><a  href="${pageContext.request.contextPath}/home/sign-up.htm"> Đăng kí</a></button>
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
    
    	
</script>
</html>