<%@include file="/WEB-INF/views/tablib.jsp"%>
<!doctype html>
<html lang="en">
<title>Order Manager</title>
<style>
$grey: #F5F5F5;
$dark-grey: #323B40;

$light-blue: #E0F5FF;
$blue: #B9E5FE;
$dark-blue: #00A5FA;

$green: #B7E0DC;
$dark-green: #019888;

$lime: #C7E8C8;
$dark-lime: #42B045;

$yellow: #FFEEBA;
$dark-yellow: #FF9901;
  
$pink: #FABAD0;
$dark-pink: #EF075F;
 
$red: #FEC9C6;
$dark-red: #FD3D08;

@mixin color-div($color1, $color2){
    background-color: $color1;
    color: $color2;
}


h4 {
  margin: 2rem 0rem;
}

.panel {
  border-radius: 4px;
  margin-top: 0.2rem;
  
  @include color-div($grey, $dark-grey);
  
  &.panel-blue {
    @include color-div($light-blue, $dark-blue);
  }
  
  &.panel-big-height{
    min-height: 150px;
  }
}

.item {
  border-radius: 4px;
  padding: 0.5rem;
  margin: 0.2rem;
  
  &.item-blue {
    @include color-div($blue, $dark-blue);
  }
  
  &.item-green {
    @include color-div($green, $dark-green);
  }
  
  &.item-lime {
    @include color-div($lime, $dark-lime);
  }
  
  &.item-yellow {
    @include color-div($yellow, $dark-yellow);
  }
  
  &.item-pink {
    @include color-div($pink, $dark-pink);
  }
  
  &.item-red {
    @include color-div($red, $dark-red);
  }
  
  &.item-big-width{
    min-width: 380px;
  }
}

</style>
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
							<h5 class="card-header">Order Manager</h5>
							<div class="card-body">
								<div class="table-title">
									<div class="row">
										<div class="col-sm-6">
											<a href="admin/confirmedOrder.htm" class="btn btn-success"> <i
												class="material-icons"></i> <span>View confirmed order</span></a>
												<a href="admin/denyOrder.htm" class="btn btn-warning"> <i
												class="material-icons"></i> <span>View deny order</span></a>
												<p style="padding: 2px;"></p>
											<a style="font-size:30px; margin:10px;" href="admin/latest_order.htm"><i class="fas fa-sort-numeric-up"></i></a>
											<a style="padding: 20px; font-size:30px" href="admin/manager_order.htm"><i class="fas fa-sort-numeric-down"></i></a>
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

											</tr>
										</thead>
										<c:forEach var="item" items="${nullOrder}">
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
													<td> <fmt:formatNumber type="number" maxFractionDigits="3"
																value="${ item.total }" /> </td>
													<td><a title="accept" class="edit"
														href="admin/accept/${ item.order_id }.htm"
														data-toggle="tooltip"> <img
															src="${pageContext.request.contextPath}/assets/images/accept.png"
															height="30" style="max-width: 50px">
													</a></td>
													<%-- <td><a title="deny" class="delete"
														href="admin/deny/${ item.order_id }.htm"
														data-toggle="tooltip"> <img
															src="${pageContext.request.contextPath}/assets/images/xoa.png"
															height="30" style="max-width: 40px"
															onclick="return confirm('Are you sure you want to deny this order?');">
													</a></td> --%>
													<td>
													<div class="container">
													  <a href="admin/deny/${ item.order_id }.htm" type="button" data-toggle="modal" data-target="#form">
													  <img
															src="${pageContext.request.contextPath}/assets/images/xoa.png"
															height="30" style="max-width: 40px">
													  </a>  
													</div>
													
													<div class="modal fade" id="form" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
													  <div class="modal-dialog modal-dialog-centered" role="document">
													    <div class="modal-content">
													      <div class="modal-header border-bottom-0">
													        <h5 class="modal-title" id="exampleModalLabel">Deny reason</h5>
													        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
													          <span aria-hidden="true">&times;</span>
													        </button>
													      </div>
													      <form action="admin/deny/${ item.order_id }.htm" method="post">
													        <div class="modal-body">
													          <div class="form-group">
													            <input type="text" class="form-control" id="reason" name="reason"
													            aria-describedby="reason" placeholder="Enter reason">
													          </div>
													        </div>
													        <div class="modal-footer border-top-0 d-flex justify-content-center">
													          <button type="submit" class="btn btn-success">Submit</button>
													        </div>
													      </form>
													    </div>
													  </div>
													</div>	
													</td>
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
	<script type="text/javascript">
	
	</script>
</body>

</html>

