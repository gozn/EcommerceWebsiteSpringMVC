<%@include file="/WEB-INF/views/tablib.jsp"%>
<!doctype html>
<html lang="en">
<title>User Manager</title>

<%@include file="/WEB-INF/views/admin/header/head.jsp"%>

<body>
	<!-- ============================================================== -->
	<!-- main wrapper -->
	<!-- ============================================================== -->
	<div class="dashboard-main-wrapper">
		<!-- ============================================================== -->
		<!-- navbar -->
		<!-- ============================================================== -->
		<%@include file="/WEB-INF/views/admin/navbar/navbar.jsp"%>
		<!-- ============================================================== -->
		<!-- wrapper  -->
		<!-- ============================================================== -->
		<div class="dashboard-wrapper">
			<div class="container-fluid  dashboard-content">

				<div class="row">
					<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Deny order</h5>
							<div class="card-body">
								<div class="table-title">
									<div class="row">
										<div class="col-sm-6">
											<a href="admin/manager_order.htm" class="btn btn-primary"> <i
												class="material-icons"></i> <span>View waiting order</span></a>
												<a href="admin/confirmedOrder.htm" class="btn btn-success"> <i
												class="admin/material-icons"></i> <span>View confirmed order</span></a>
												<p style="padding: 2px;"></p>
											<a style="font-size:30px; margin:10px;" href="admin/latestDenyOrder.htm"><i class="fas fa-sort-numeric-up"></i></a>
											<a style="padding: 20px; font-size:30px" href="admin/denyOrder.htm"><i class="fas fa-sort-numeric-down"></i></a>
										</div>
									</div>
								</div>
								<br>
								<div class="table-responsive">
									<table class="table table-striped table-bordered first">
										<thead>
											<tr>
												<th>ID</th>
												<th>Account_ID</th>
												<th>Oder owner</th>
												<th>Address</th>
												<th>Phone number</th>
												<th>Email</th>
												<th>Item</th>
												<th>Note</th>
												<th>Total</th>
												<th>Reason</th>
											</tr>
										</thead>
										<c:forEach var="item" items="${ denyOrder }">
											<tbody>
												<tr>
													<td>${ item.order_id}</td>
													<td>${ item.order_owner }</td>
													<td>${ item.order_ownername}</td>
													<td>${ item.order_address}</td>
													<td>${ item.order_phone}</td>
													<td>${ item.order_email}</td>
													<td>
														<strong><a href="admin/orderDetails/${item.order_id}.htm">${ item.order_items}</a></strong>
													</td>
													<td>${ item.order_note}</td>
													<td><fmt:formatNumber type="number" maxFractionDigits="3"
																value="${ item.total }" /></td>	
													<td>${item.deny_reason }</td>								
												</tr>
											</tbody>
										</c:forEach>
										<tfoot>
											<tr>
												<th>ID</th>
												<th>Account_ID</th>
												<th>Oder owner</th>
												<th>Address</th>
												<th>Phone number</th>
												<th>Email</th>
												<th>Item</th>
												<th>Note</th>
												<th>Total</th>
												<th>Reason</th>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- </div> -->
				<!-- ============================================================== -->
				<!-- footer -->
				<!-- ============================================================== -->
				<%@include file="/WEB-INF/views/admin/footer/footer.jsp"%>
				<!-- ============================================================== -->
				<!-- end footer -->
				<!-- ============================================================== -->
			</div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- end main wrapper -->
	<!-- ============================================================== -->
	<!-- Optional JavaScript -->

	<script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
	<script src="assets/vendor/slimscroll/jquery.slimscroll.js"></script>
	<script src="assets/vendor/multi-select/js/jquery.multi-select.js"></script>
	<script src="assets/libs/js/main-js.js"></script>
	<script src="assets/vendor/datatables/js/buttons.bootstrap4.min.js"></script>
	<script src="assets/vendor/datatables/js/data-table.js"></script>
</body>

</html>
