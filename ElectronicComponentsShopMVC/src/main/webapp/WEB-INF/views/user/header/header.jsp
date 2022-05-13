<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/tablib.jsp"%>

<div class="page-loader">
	<div class="loader">Loading...</div>
</div>

<style>

#ip {
  background: rgba(0,0,0,0.2);
  border: none;
  position: relative;
  display: block;
  outline: none;
  width: 260px;
  height: 20px;
  color:white;
  -webkit-box-shadow: 0 2px 10px 1px rgba(0,0,0,0.1);
  box-shadow: 0 2px 10px 1px rgba(0,0,0,0.5);
}
::-webkit-input-placeholder { color: #FFFAFA; font-style: italic;} 
:-moz-placeholder { color: #FFFAFA;  font-style: italic; }
::-moz-placeholder { color: #FFFAFA;  font-style: italic;}
:-ms-input-placeholder { color: #FFFAFA;  font-style: italic;}
<!-- --!>

.notification:hover {
  background: red;
}

.notification .badge {
  position: absolute;
  top: 8px;
  right: 3px;
  padding: 2px 4px;
  border-radius: 50%;
  background-color: red;
  font-size:12px; max-height:15px;
}
</style>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a style="left:-100px; position:relative" class="navbar-brand" href="home.htm">Essential Collection sports Shop</a>
			<div class="navbar-brand">
				<form action="search.htm" method="post">
					<input style="left:-50px; position:relative" id="ip" style="font-size:18px; color:#B4CDCD;" type="text" name="search" id="search"  placeholder="Search.."/>	
				</form>
			</div>	
		</div>
		
			  
	
		
		<div style="right:90px; position:fixed" class="collapse navbar-collapse" id="custom-collapse">
			<ul class="nav navbar-nav navbar-right">

				<!--  vể trang chủ bằng href -->
				<li class="dropdown"><a href="home.htm">Home</a></li>
				
				<!--  vể trang sản phẩm bằng href -->
				<li class="dropdown"><a class="dropdown-toggle"
					href="product.htm" data-toggle="dropdown">Product</a>
					<ul class="dropdown-menu">
						<c:forEach var="item" items="${ all_category }" begin="0"
							end="${ all_category.size() }" varStatus="loop">
							<li><a href="category/${ item.category_id }.htm">${ item.category_name }</a></li>
						</c:forEach>
					</ul></li>

				<!--  vể trang check out bằng href -->
				<!-- <li class="dropdown"><a href="checkout.htm">Checkout</a></li> -->

				<c:if
					test="${  not empty LoginInfo  && LoginInfo.role.role_id != 3}">
					<li class="dropdown"><a href="admin.htm">ADMIN</a></li>
				</c:if>

				
				<li class="dropdown">
					<a class="notification" href="cart.htm">
						Cart 
						<c:if test="${cartCount != 0}">
							<span class="badge">${cartCount}</span>
						</c:if>
					</a>
				</li>
				
				
				<!--  kiểm tra tồn tại của user đăng nhập -->
				<c:if test="${ empty LoginInfo }">
					<li><a href="login_register.htm">Login / Register</a></li>
				</c:if>

				<c:if test="${ not empty LoginInfo }">
					<c:if test="${ LoginInfo.role.role_id == 3 }">
						<%-- <li class="dropdown"><a href="user/profile/${LoginInfo.users_id}.htm">Welcome, ${ user.customer.khachhang_name }</a></li> --%>
						
						<li class="dropdown"><a class="dropdown-toggle"
						href="user/profile/${LoginInfo.users_id}.htm" data-toggle="dropdown">Welcome, ${ LoginInfo.customer.khachhang_name}</a>
						<ul class="dropdown-menu">
							<li><a href="user/profile/${LoginInfo.users_id}.htm">Profile</a></li>
							<li><a href="user/order/${LoginInfo.users_id}.htm">Order</a></li>
						</ul>
						</li>	
					</c:if>
					<c:if
						test="${ LoginInfo.role.role_id != 3 && LoginInfo.role.role_id != 1}">
						<li class="dropdown"><a class="dropdown-toggle"
						href="user/profile/${LoginInfo.users_id}.htm" data-toggle="dropdown">Welcome, ${ LoginInfo.emloyee.nhanvien_name}</a>
						<ul class="dropdown-menu">
							<li><a href="user/profile/${LoginInfo.users_id}.htm">Profile</a></li>
							<li><a href="user/order/${LoginInfo.users_id}.htm">Order</a></li>
						</ul>
						</li>
					</c:if>
				</c:if>

				<c:if test="${ not empty LoginInfo }">
					<li><a href="logout.htm">Logout</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<script type="text/javascript">

	</script>
</nav>