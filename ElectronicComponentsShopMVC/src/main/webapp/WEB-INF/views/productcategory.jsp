<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">

<%@include file="/WEB-INF/views/user/header/head.jsp"%>

<body data-spy="scroll" data-target=".onpage-navigation"
	data-offset="60">
	<main>

		<%@include file="/WEB-INF/views/user/header/header.jsp"%>

		<div class="main">
			<section class="module module-video bg-dark-30"
				data-background="assets/images/Screenshotyjgh.png">
				<div class="container">

					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h2 class="module-title font-alt">Products About ${ category.category_name }
							</h2>
						</div>
					</div>
					<div class="video-player"
						data-property="{videoURL:'https://www.youtube.com/watch?v=672TY8K2PKk', containment:'.module-video', startAt:3, mute:true, autoPlay:true, loop:true, opacity:1, showControls:false, showYTLogo:false, vol:25}"></div>
				</div>
			</section>
			<br>
			<hr class="divider-w">
			<section class="module-small">
				<div class="container">
					<div class="row multi-columns-row">
						<c:if test="${ all_product.size() == 0}">
							<div class="col-sm-6 col-md-3 col-lg-3">
								<h4>Không có sản phẩm</h4>
							</div>
						</c:if>
						<c:if test="${ all_product.size() != 0}">
							<c:forEach var="item" items="${ all_product }" begin="0"
								end="${ all_product.size() }" varStatus="loop">
								<div class="col-sm-6 col-md-3 col-lg-3">
									<div class="shop-item">
										<div class="shop-item-image">
											<img src="assets/images/shop/${item.product_images}"
												alt="Accessories Pack" />
											<div class="shop-item-detail">
												<a class="btn btn-round btn-b"  href="addToCart/${ item.product_id }.htm"><span class="icon-basket">Add
														To Cart</span></a>
											</div>
										</div>
										<h4 class="shop-item-title font-alt">
											<a style="font-size:18px;" href="product/${ item.product_id }.htm">${ item.product_name }</a>
										</h4>
										<c:if test="${ item.product_discount > 0 }">
											<del ><span style="font-size:16px;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.product_price}"  /> VNĐ</span> </del>   
											 <span style="font-size:16px;"><fmt:formatNumber  type = "number" maxFractionDigits = "3" value = "${ item.product_price - item.product_discount}"  /> VNĐ</span>
										</c:if>
										<c:if test="${ item.product_discount == 0 }">
											<span style="font-size:16px;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.product_price}" /> VNĐ </span>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="pagination font-alt">
								<!-- <a href="#"><i class="fa fa-angle-left"></i></a> -->
								<c:forEach var="item" begin="1" end="${ pagecategory }">
									<c:choose>
										<c:when test="${ item == 1 }">
											<a class="active"
												href="pages/${ category.category_id }/${ item }.htm">${ item }</a>
										</c:when>
										<c:otherwise>
											<a href="pages/${ category.category_id }/${ item }.htm">${ item }</a>
										</c:otherwise>
									</c:choose>
									<!-- <a href="#"><i class="fa fa-angle-right"></i></a> -->
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</section>
			<%@include file="/WEB-INF/views/user/footer/footer.jsp"%>

		</div>
		<div class="scroll-up">
			<a href="#totop"><i class="fa fa-angle-double-up"></i></a>
		</div>
	</main>

	<%@include file="/WEB-INF/views/user/js/js.jsp"%>

</body>
</html>