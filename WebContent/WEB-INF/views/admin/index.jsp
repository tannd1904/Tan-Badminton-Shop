<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>N17DCCN150</title>
<base href="${pageContext.servletContext.contextPath }" />
<style>
<%@ include file="/resources/css/reset.css"%>
<%@ include file="/resources/css/manager-pr.css"%>
</style>
</head>
<body onload="myFunc()">
	    <div class="header">
        <div class="container-header">
            <div class="left-header">
                <img src=" ${pageContext.request.contextPath}/resources/img/logo/tanshop-logo.png" alt="">
            </div>
            <div class="right-header">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/index.htm">Quản lý sản phẩm</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/donhang.htm">Quản lý đơn hàng</a></li>
                </ul>
            </div>
            <div class="right-header" style="float: right;">
            	<ul>
            		<li> <a href="${pageContext.request.contextPath}/admin/logout.htm">Đăng xuất</a> </li>
            	</ul>
            </div>
        </div>
    </div>
    <div class="main">
    	
    	<div id="myModal" class="modal">
		  <!-- Modal content -->
		  <div class="modal-content">
		    <span class="close">&times;</span>
		    <h2><b>${tb}</b></h2>
		  </div>
		</div>
    
        <div class="main-left">
        	<br>
            <form class="add-pr" action="${pageContext.request.contextPath}/admin/gr-product.htm"  method="post">
            <h1>Thêm Nhóm Sản phẩm</h1>
            	<div class="input-fm">
                    <label for="id"><b>ID nhóm sản phẩm :</b></label>
                    <input name ="id">
                </div>
                <div class="input-fm">
                    <label for="name"><b>Tên nhóm sản phẩm :</b></label>
                    <input name ="name">
                </div>
                <div class="input-fm">
                    <label for="content"><b>Nội dung nhóm sản phẩm :</b></label>
                    <input name ="content">
                </div>
                
                <div class = "type">  	
                	<input type="radio" name = "brands" value="0">Vợt
					<input type="radio" name = "brands" value="1">Giày
					<input type="radio" name = "brands" value="2">Áo quần
					<input type="radio" name = "brands" value="3">Phụ kiện
				</div>
				<div class="bt">
					<button>Thêm nhóm SP</button>	
				</div>
            </form>
            <form  class="add-pr" action="${pageContext.request.contextPath}/admin/product.htm" method="POST" enctype="multipart/form-data">
            	<h1>Thêm sản phẩm</h1>
			        <div class="input-fm">
	                    <label for="grid"><b>Thuộc nhóm sản phẩm :</b></label>
	                    <select style="width: 310px;height: 30px" name = "grid" >
	                       <c:forEach var="sz" items="${grpr}">
	                       		<option value="${sz.id}">${sz.name}</option>
	                       </c:forEach>
	                    </select>
	                </div>
		            <div class="input-fm">
		            	<label><b>ID sản phẩm :</b></label>
		            	<input name="id">
		            </div>
		            <div class="input-fm">
		            	<label><b>Tên sản phẩm :</b></label>
		            	<input name="name">
		            </div>
		            <div class="input-fm">
		            	<label><b>Giới thiệu sản phẩm :</b></label>
		            	<textarea rows="10" cols="15" name="content"></textarea>
		            </div>
		            <div class="input-fm">
		            	<label><b>Màu sản phẩm :</b></label>
		            	<input name="coler">
		            </div>
		            <div class="input-fm">
		            	<label><b>Giá sản phẩm :</b></label>
		            	<input  name="p" value="0">
		            </div>
		            <div class="input-fm">
		            	<label><b>Khuyến mãi :</b></label>
		            	<input  name="s" value="0">
		            </div>		

		            <div class="input-fm">
		            	<label><b>Ảnh SP 1 :</b></label>
		            	<input type="file" name="img1"/><br>
		            	<img  alt="" src="img/${prod.img1}">
		            </div>
		            <div class="input-fm">
		            	<label><b>Ảnh SP 2 :</b></label>
		            	<input type="file" name="img2"/><br>
		            	<img  alt="" src="img/${prod.img2}">
		            </div>
		            <div class="input-fm">
		            	<label><b>Ảnh SP 3 :</b></label>
		            	<input type="file" name="img3"/><br>
		            	<img  alt="" src="img/${prod.img3}">
		            </div>         
		            <div class="bt">
					<button>Thêm SP</button>	
				</div>
            </form>
            <form class="add-pr" action="${pageContext.request.contextPath}/admin/productde.htm"  method="post">
            <h1>Thêm Chi Tiết Sản phẩm</h1>
            		<div class="input-fm">
	                    <label for="prid"><b>Sản phẩm :</b></label>
	                    <select style="width: 310px;height: 30px" name = "prid" >
	                       <c:forEach var="sz" items="${prr}">
	                       		<option value="${sz.id}">${sz.id} -	${sz.name}</option>
	                       </c:forEach>
	                    </select>
	                </div>
                <div class="input-fm">
                    <label for="size"><b>Loại :</b></label>
                    <input name ="size">
                </div>
                <div class="input-fm">
                    <label for="quanlity"><b>Số lượng :</b></label>
                    <input name ="quanlity">
                </div>
				<div class="bt">
					<button>Thêm chi tiết</button>	
				</div>
            </form>
        </div>
		
        <div class="main-right">
            <table>
                <tr>
                	<th>Ảnh SP</th>
                    <th>Mã SP</th>
                    <th>Tên SP</th>
                    <th>Nhóm SP</th>
                    <th>Màu</th>
                    <th>Giá</th>
                    <th>Sale</th>
                    <th>Loại</th>
                    <th>SL</th>
                    <th>Trạng thái</th>
                    <th></th>
                </tr>
                <c:forEach var="a" items="${listpr}">
                		<tr style="align-items: center;">              	
                		<td> <img style="width: 80px;height: 80px" alt="" src="${pageContext.request.contextPath}/resources/img/pro/${a.product.img1}"></td>
                		<td>${a.product.id}</td>
                		<td>${a.product.name}</td>
                		<td>${a.product.groupProduct.name}</td>
                		<td>${a.product.coler}</td>
                		<td>${a.product.price}</td>
                		<td>${a.product.sale}</td>
                		<td>${a.size}</td>
                		<td>${a.quanlity}</td>
                		<c:if test="${a.product.status==0}">
                			<td>Hết Hàng</td>
                		</c:if>
                		<c:if test="${a.product.status==1}">
                			<td>Đang bán</td>
                		</c:if>
                		<td><a title="Sửa sản phẩm" href="${pageContext.request.contextPath}/admin/editpr.htm?idSanPham=${a.id}&img1=${a.product.img1}&img2=${a.product.img2}&img3=${a.product.img3}" >Sửa</a> 
                		<br>
                		<br>
                		<a href="${pageContext.request.contextPath}/admin/deletepr.htm?idSanPham=${a.id}" >Xóa Chi tiết</a> 
                		<br>
                		<br>
                		<a  href="${pageContext.request.contextPath}/admin/deletepr-all.htm?idSanPham=${a.id}">Xóa SP</a></td>		
                </tr>
                </c:forEach>
                <c:forEach var="a" items="${prr}">
                <c:if test="${a.status == 0 }">
                	<tr style="align-items: center;">              	
                		<td> 
                			<img style="width: 80px;height: 80px" alt="" src="${pageContext.request.contextPath}/resources/img/pro/${a.img1}"></td>
                		<td>${a.id}</td>
                		<td>${a.name}</td>
                		<td>${a.groupProduct.name}</td>
                		<td>${a.coler}</td>
                		<td>${a.price}</td>
                		<td>${a.sale}</td>
                		<td>Không</td>
                		<td>Không</td>
                		<td>Hết hàng</td>
                		<td> 
                		<br>
                		<br>
                		<a  href="${pageContext.request.contextPath}/admin/deletepr-all.htm?idSanPham=${a.id}">Xóa SP</a></td>
                </c:if>
                	
                </c:forEach>
            </table>
        </div>
		
    </div>
<script>
	
	// Get the modal
	var modal = document.getElementById("myModal");
	
	function myFunc() {
		modal.style.display = "block";
		
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
		
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		  modal.style.display = "none";
		}
		
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		  if (event.target == modal) {
		    modal.style.display = "none";
		  }
		}
	}
</script>    
</body>
</html>