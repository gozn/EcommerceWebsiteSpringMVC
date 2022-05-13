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
			<section class="module">
				<div class="container">
					<div class="row">
						<form action="addToCart.htm" method="post">
							<div class="col-sm-6 mb-sm-40">
								<a class="gallery"
									href="assets/images/shop/${productsingle.product_images }"><img
									src="assets/images/shop/${productsingle.product_images }"
									alt="Single Product Image" /></a>
							</div>
							<div class="col-sm-6">
								<div class="row">
									<input style="display: none" type="text" id="product_id"
										name="product_id" value="${productsingle.product_id}">
									<div class="col-sm-12">
										<h1 class="product-title font-alt">${productsingle.product_name } </h1>
									</div>
								</div>

								<div class="row mb-20">
									<div class="col-sm-12">
										<div class="price font-alt">
											<c:if test="${ productsingle.product_discount > 0 }">
												<span class="amount"><del>
														<fmt:formatNumber type="number" maxFractionDigits="3"
															value="${productsingle.product_price}" />
														VND</del> 
														<fmt:formatNumber type="number" maxFractionDigits="3"
														value="${ productsingle.product_price - productsingle.product_discount}" />
													VND</span>
													</c:if>
											<c:if test="${ productsingle.product_discount == 0 }">
												<span class="amount"><fmt:formatNumber type="number"
														maxFractionDigits="3"
														value="${productsingle.product_price}" /> VND</span>
											</c:if>
										</div>
									</div>
								</div>
								<div class="row mb-20">
									<div class="col-sm-12">
										<div style="font-size: 14px;" class="description">
											<p>${productsingle.product_detail }</p>
											
											<h3>
												Quantity remaining: 
												<c:if test="${productsingle.product_quantity == 0 }">
													<span style="color:red"><strong>Out of stock!</strong></span>
												</c:if>
												<c:if test="${productsingle.product_quantity > 0 }">
													${productsingle.product_quantity}
												</c:if>
											</h3>
										</div>
									</div>
								</div>
								<c:if test="${productsingle.product_quantity > 0}">
								<div class="row mb-20">
									<div class="col-sm-4 mb-sm-20">
										<input style="font-size: 18px;" class="form-control" type="number"
											name="quantity" value="1" max="${productsingle.product_quantity}" min="1" 
											title="Số lượng mua không được vượt quá số sản phẩm còn lại!"
											required="required" />
									</div>
									<div class="col-sm-8">
										<div>
											<button type="submit"
												class="btn btn-lg btn-block btn-round btn-b">Add to
												cart</button>
										</div>
										<%-- <a class="btn btn-lg btn-block btn-round btn-b" href="product/${item.product_id}.htm">Add
										To Cart</a>	 --%>
									</div>
								</div>
								</c:if>
								<div class="row mb-20">
									<div class="col-sm-12">
										<div style="font-size: 18px;" class="product_meta">
											Categories:<a  href="category/${productsingle.categoryID.category_id }.htm">
												${productsingle.categoryID.category_name } </a>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="row mt-70">
						<div class="col-sm-12">
							<ul class="nav nav-tabs font-alt" role="tablist">
								<li class="active"><a data-toggle="tab">Related
										Products</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active">
									<div class="row multi-columns-row">
										<c:forEach var="item" items="${ all_product }" begin="0"
											end="3" varStatus="loop">
											<div class="col-sm-6 col-md-3 col-lg-3">
												<div class="shop-item">
													<div class="shop-item-image">
														<img src="assets/images/shop/${ item.product_images }"
															alt="Accessories Pack" />
														<div class="shop-item-detail">
															<a class="btn btn-round btn-b"><span
																class="icon-basket">Add To Cart</span></a>
														</div>
													</div>
													<h4 class="shop-item-title font-alt">
														<a style="font-size: 18px;" href="product/${item.product_id }.htm">${ item.product_name }</a>
													</h4>
													<c:if test="${ item.product_discount > 0 }">
														<del style="font-size: 14px;">
															<fmt:formatNumber type="number" maxFractionDigits="3"
																value="${item.product_price}" />
															VND
														</del>

														<span style="font-size: 16px;"><fmt:formatNumber type="number" maxFractionDigits="3"
															value="${ item.product_price - item.product_discount}" />
															VND</span>
													</c:if>
													<c:if test="${ item.product_discount == 0 }">
														<span style="font-size: 14px;"><fmt:formatNumber type="number" maxFractionDigits="3"
															value="${item.product_price}" /> VND</span>
													</c:if>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>

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
	<!--  
    JavaScripts
    =============================================
    -->
	<%@include file="/WEB-INF/views/user/js/js.jsp"%>
</body>
</html>