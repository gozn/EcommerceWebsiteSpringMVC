<%@page import="ElectronicComponentsShopMVC.Service.Algorithm.AES256"%>
<%@include file="/WEB-INF/views/tablib.jsp"%>
<!doctype html>
<html lang="en">
<title>Employee Manager</title>

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
							<h5 class="card-header">Employee Manager</h5>
							<div class="card-body">
								<div class="table-title">
									<div class="row">
										<div class="col-sm-6">
											<a href="admin/insertEml.htm" class="btn btn-success"> <i
												class="material-icons"></i> <span>Add New Employee</span></a>
												<p style="padding: 2px;"></p>
											<a style="font-size:30px; margin:10px;" href="admin/latest_emloyee.htm"><i class="fas fa-sort-numeric-up"></i></a>
											<a style="padding: 20px; font-size:30px" href="admin/manager_emloyee.htm"><i class="fas fa-sort-numeric-down"></i></a>
										</div>
									</div>
								</div>
								<br>
								<div class="table-responsive">
									<table class="table table-striped table-bordered first">
										<thead>
											<tr>
												<th>ID</th>
												<th>Username</th>
												<th>Password</th>
												<th>Name</th>
												<th>Phone</th>
												<th>Gender</th>
												<th>Salary</th>
												<th>Role name</th>
											</tr>
										</thead>
										<c:forEach var="item" items="${ view_userEml }" begin="0"
											end="${ view_userEml.size() }" varStatus="loop">
											<tbody>
												<tr>
													<c:if test="${ item.getRole().role_id == 1}">
														<td>${ item.users_id}</td>
														<td>${ item.users_username}</td>
														<td>${ item.users_password}</td>
														<td></td>
														<td></td>
														<td></td>
														<td><fmt:formatNumber type="number" maxFractionDigits="3"
																value="${ item.getEmloyee().nhanvien_luongd }" /></td>
														<td>${ item.getRole().role_name }</td>														
													</c:if>
													<c:if test="${item.getRole().role_id != 1 }">
														<td>${ item.users_id}</td>
														<td>${ item.users_username}</td>
														<td>${ item.users_password}</td>
														<td>${ item.getEmloyee().nhanvien_name }</td>
														<td>${ item.getEmloyee().nhanvien_sdt }</td>
														<c:if
															test="${ item.getEmloyee().nhanvien_gioitinh == true}">
															<td>Male</td>
														</c:if>
														<c:if
															test="${ item.getEmloyee().nhanvien_gioitinh == false}">
															<td>Female</td>
														</c:if>
														<c:choose>
															<c:when  test="${LoginInfo.role.role_id == 1 && item.users_id != LoginInfo.users_id}">
		
																<td><fmt:formatNumber type="number" maxFractionDigits="3"
																value="${ item.getEmloyee().nhanvien_luongd }" /></td>
																
															</c:when>
															<c:when  test="${LoginInfo.users_id == item.users_id }">
															
																<td><fmt:formatNumber type="number" maxFractionDigits="3"
																value="${ item.getEmloyee().nhanvien_luongd }" /></td>
																
															</c:when>
															<c:when test="${LoginInfo.users_id != item.users_id }">
																<td>Encrypted</td>
															</c:when>
														</c:choose>
														
														<td>${ item.getRole().role_name }</td>		
													</c:if>
													<c:choose>
														<c:when test="${LoginInfo.role.role_id == 1 && item.users_id != LoginInfo.users_id}">
															<td><a title="Edit" class="edit"
																href="admin/editEml/${ item.users_id }.htm" data-toggle="tooltip">
																	<img src="${pageContext.request.contextPath}/assets/images/chinhsua.jpg"
																	height="30" style="max-width: 50px">
															</a></td>
															<td><a title="Delete" class="delete"
																	href="admin/deleteEml/${ item.users_id }.htm"
																	data-toggle="tooltip"> <img src="${pageContext.request.contextPath}/assets/images/xoa.png"
																		height="30" style="max-width: 40px"
																		onclick="return confirm('Are you sure you want to delete this employee?');">
															</a></td>
														</c:when>
														<c:when test="${LoginInfo.role.role_id != 1 && item.users_id == LoginInfo.emloyee.nhanvien_id}">
															<td><a title="Edit" class="edit"
																href="admin/editEml/${ item.users_id }.htm" data-toggle="tooltip">
																	<img src="${pageContext.request.contextPath}/assets/images/chinhsua.jpg"
																	height="30" style="max-width: 50px">
															</a></td>
														</c:when>
													</c:choose>
														
												</tr>
											</tbody>
										</c:forEach>
										<tfoot>
											<tr>
												<th>ID</th>
												<th>Username</th>
												<th>Password</th>
												<th>Name</th>
												<th>Phone</th>
												<th>Gender</th>
												<th>Salary</th>
												<th>Role name</th>
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