<%@include file="/WEB-INF/views/tablib.jsp"%>
<!doctype html>
<html lang="en">
<title>Category Manager</title>

<%@include file="/WEB-INF/views/admin/header/head.jsp"%>
<base href="${pageContext.servletContext.contextPath}/">
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
							<h5 class="card-header">Category Manager</h5>
							<div class="card-body">
								<div class="table-title">
									<div class="row">
										<div class="col-sm-6">
											<a href="admin/insertCat.htm" class="btn btn-success"> <i
												class="material-icons"></i> <span>Add New Category</span></a>
												<p style="padding: 2px;"></p>
											<a style="font-size:30px; margin:10px;" href="admin/latest_category.htm"><i class="fas fa-sort-numeric-up"></i></a>
											<a style="padding: 20px; font-size:30px" href="admin/manager_category.htm"><i class="fas fa-sort-numeric-down"></i></a>
										</div>
									</div>
								</div>
								<br>
								<div class="table-responsive">
									<table class="table table-striped table-bordered first">
										<thead>
											<tr>
												<th>ID</th>
												<th>Name</th>

											</tr>
										</thead>
										<c:forEach var="item" items="${ view_category }" begin="0"
											end="${ view_category.size() }" varStatus="loop">
											<tbody>
												<tr>
													<td>${ item.category_id}</td>
													<td>${ item.category_name}</td>
													<td><a title="Edit" class="edit"
														href="admin/editCat/${ item.category_id }.htm"
														data-toggle="tooltip"> <img
															src="${pageContext.request.contextPath}/assets/images/chinhsua.jpg"
															height="30" style="max-width: 50px">
													</a></td>
													<td><a title="Delete" class="delete"
														href="admin/deleteCat/${ item.category_id }.htm"
														data-toggle="tooltip"> <img
															src="${pageContext.request.contextPath}/assets/images/xoa.png"
															height="30" style="max-width: 40px"
															onclick="return confirm('Are you sure you want to delete this category?');">
													</a></td>
													 <!-- <a href="url_to_delete" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a> -->
													
												</tr>
											</tbody>
										</c:forEach>
										<tfoot>
											<tr>
												<th>ID</th>
												<th>Name</th>
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