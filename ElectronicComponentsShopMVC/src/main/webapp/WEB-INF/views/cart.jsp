<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">
<style type="text/css">
		input,
		textarea {
		  border: 1px solid #eeeeee;
		  box-sizing: border-box;
		  margin: 0;
		  outline: none;
		  padding: 10px;
		}
		
		input[type="button"] {
		  -webkit-appearance: button;
		  cursor: pointer;
		}
		
		input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button {
		  -webkit-appearance: none;
		}
		
		.input-group {
		  clear: both;
		  margin: 15px 0;
		  position: relative;
		}
		
		.input-group input[type='button'] {
		  background-color: #eeeeee;
		  min-width: 38px;
		  width: auto;
		  transition: all 300ms ease;
		}
		
		.input-group .button-minus,
		.input-group .button-plus {
		  font-weight: bold;
		  height: 38px;
		  padding: 0;
		  width: 38px;
		  position: relative;
		}
		
		.input-group .quantity-field {
		  position: relative;
		  height: 38px;
		  left: -6px;
		  text-align: center;
		  width: 62px;
		  display: inline-block;
		  font-size: 13px;
		  margin: 0 0 5px;
		  resize: vertical;
		}
		
		.button-plus {
		  left: -13px;
		}
		
		input[type="number"] {
		  -moz-appearance: textfield;
		  -webkit-appearance: none;
		}
	</style>
<%@include file="/WEB-INF/views/user/header/head.jsp"%>

<body data-spy="scroll" data-target=".onpage-navigation"
	data-offset="60">
	<main>
	<%@include file="/WEB-INF/views/user/header/header.jsp"%>

	
	<br>
	<p></p>
	<br>
	<p></p>
	<br>
	<div class="container">

        <!--Section: Block Content-->
        <section class="mt-5 mb-4">

            <!--Grid row-->
            <div class="row">

                <!--Grid column-->
                <div class="col-lg-8">

                    <!-- Card -->
                    <div class="card wish-list mb-4">
                        <div class="card-body">

                            <h2 class="mb-4">Cart (<span>${cartCount}</span> items)</h2>

                            <div class="row mb-4">
                            <c:forEach var="item" items="${gioHang.items}">
                                <div class="col-md-5 col-lg-3 col-xl-3">
                                    <div class="mb-3 mb-md-0">
                                        <img class="img-fluid w-100"
                                             
                                             src="assets/images/shop/${item.prod.product_images}" alt="Sample">

                                    </div>
                                </div>
                                <div class="col-md-7 col-lg-9 col-xl-9">
                                
                                    <div>
                                        <div class="d-flex justify-content-between">
                                        
                                            <div>
                                            	<br>
                                                <%-- <h3><strong>${item.prod.product_name}</strong> </h3>   --%>                                                                                  
                                                <h3><a  href="product/${ item.prod.product_id }.htm">${ item.prod.product_name }</a></h3>
                                                
		                                        <c:if test="${ item.prod.product_discount > 0 }">
		                                        
		                                        <p style="font-size:14px;" class="mb-0 small">Price: <del>
													<fmt:formatNumber type="number" maxFractionDigits="3"
														value="${item.prod.product_price}"/>
													VND
												</del>
												 <fmt:formatNumber type = "number" maxFractionDigits = "3"
		                                         value="${ item.prod.product_price - item.prod.product_discount}" /> VND</p>
												
												</c:if>
												
												<c:if test="${ item.prod.product_discount == 0 }">
												<p style="font-size:14px;" class="mb-0 small">Price: <fmt:formatNumber type = "number" maxFractionDigits = "3"
		                                         value="${ item.prod.product_price }" /> VND</p>
												</c:if>
                                                
                                                
                                                
                                                
                                                
												<p style="font-size:14px;" class="mb-0">Quantity:</p>
												<form style="float:left;" action="cart/update.htm" method="post">
													
															<input type="hidden" value="${item.prod.product_id}" name="product_id"/>
															<%-- <input type="number" step="1" max="" value="${item.quantity}" name="quantity">	 --%>
															<div style="position:relative; float:left; margin:-2px 0px;" class="input-group">
															  <input style="position:relative; max-height:34px; margin:0px 3.5px; " type="button" value="-" class="button-minus" data-field="quantity">
															  <%-- <c:forEach var="prod" items="${prod}">
															  	<c:if test="${item.prod.product_id == prod.product_id }"> --%>
																  	<input style="position:relative; max-height:34px; margin:0px 3.5px; "  type="number" 
																  		step="1" min="1"  value="${item.quantity}" 	name="quantity" class="quantity-field">
																<%-- </c:if>
															  </c:forEach> --%>
															  <input style="position:relative; max-height:34px; margin:0px 3.5px; "  type="button" value="+" class="button-plus" data-field="quantity">
															</div>					
													<!-- <button type="submit">Update</button> -->
													<button type="submit" class="btn btn-primary form-input">Update</button>
													
												</form>
												
												<form style="float:left" action="cart/delete.htm" method="post">
													<input type="hidden" value="${item.prod.product_id}" name="product_id" id="product_id"/>
													<button type="submit" class="btn btn-danger form-input" 
													onclick="return confirm('Are you sure you want to delete this product from cart?');">Delete</button>
												</form>	
												
												
												
												
												<p class="mb-0" style="display:inline-block; font-size:16px; position: relative; top:-70px; left:300px; text-align:center">
												Subtotal: <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${item.subTotal}" /> VND</p>

		                                        
		                                        <%-- <div style="display:inline; font-size:15px; position:relative; top:-60px; right:50px; left:30px">   
		                                            	<span ><i>Thành tiền: ${item.subTotal} VND</i></span>                             
		                                        </div> --%>
                                            </div>
												
													
												
	                                          
                                        </div>                                      
                                    </div>
                                   
                                </div>
                                </c:forEach>
                            </div>
                            <hr class="mb-4">

                            <p class="text-primary mb-0"><i class="fas fa-info-circle mr-1"></i> Do not delay the purchase, adding
                                items to your cart does not mean booking them.</p>

                        </div>
                    </div>




                </div>
                <!--Grid column-->

                <!--Grid column-->
                <div class="col-lg-4">

                    <!-- Card -->
                    <div class="card mb-4">
                        <div class="card-body">

                            <h3 class="mb-3" style="font-size:30px;">Bills</h3>

                            <ul class="list-group list-group-flush">
                                <li style="font-size:20px;" class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                    Total: 
                                    <span><span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${gioHang.total}" /></span> VND</span>
                                    
                                    
                                    
                                    
                                </li>
                                <li style="font-size:18px;" class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    Shipping: 
                                    <c:if test="${gioHang.total >= 500000 }">
                                    	<i>Free</i>
                                    </c:if>
                                    <c:if test="${gioHang.shipping != 0 }">
                                    	<i><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${gioHang.shipping}" /></span> VND</i>
                                    </c:if>
                                    <c:if test="${gioHang.total == 0 }">
                                    	<i>0 VND	</i>
                                    </c:if>
                                    
                                </li>
                                <li style="font-size:20px;" class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                    <div>
                                        <strong>The amount paid</strong>
                                        <strong>
                                            (including VAT)
                                        </strong>
                                    </div>
                                    <span><strong><span > <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${gioHang.tas}" /></span> VND</strong></span>
                                </li>
                            </ul>

                            <c:if test="${gioHang.total == 0}">
								<a href="product.htm"  class="btn btn-primary btn-block waves-effect waves-light">Shopping</a>
							</c:if>
							<c:if test="${gioHang.total != 0}">
								<a href="checkout.htm"  class="btn btn-primary btn-block waves-effect waves-light">Pay</a>
							</c:if>

                        </div>
                    </div>
                    <!-- Card -->

                    <!-- Card -->
                    <div class="card mb-4">
                        <div class="card-body">

                            <a class="dark-grey-text d-flex justify-content-between" data-toggle="collapse" href="#collapseExample"
                               aria-expanded="false" aria-controls="collapseExample">
                                Enter coupon (optional)
                                <span><i class="fas fa-chevron-down pt-1"></i></span>
                            </a>

                            <div class="collapse" id="collapseExample">
                                <div class="mt-3">
                                    <div class="md-form md-outline mb-0">
                                        <input type="text" id="discount-code" class="form-control font-weight-light"
                                               placeholder="Enter discount code">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Card -->

                </div>
                <!--Grid column-->

            </div>
            <!--Grid row-->

        </section>
        <!--Section: Block Content-->

    </div>
	
	
	
	</main>

	<%@include file="/WEB-INF/views/user/js/js.jsp"%>

<script type="text/javascript">
function incrementValue(e) {
	  e.preventDefault();
	  var fieldName = $(e.target).data('field');
	  var parent = $(e.target).closest('div');
	  var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

	  if (!isNaN(currentVal)) {
	    parent.find('input[name=' + fieldName + ']').val(currentVal + 1);
	  } else {
	    parent.find('input[name=' + fieldName + ']').val(0);
	  }
	}

	function decrementValue(e) {
	  e.preventDefault();
	  var fieldName = $(e.target).data('field');
	  var parent = $(e.target).closest('div');
	  var currentVal = parseInt(parent.find('input[name=' + fieldName + ']').val(), 10);

	  if (!isNaN(currentVal) && currentVal > 0) {
	    parent.find('input[name=' + fieldName + ']').val(currentVal - 1);
	  } else {
	    parent.find('input[name=' + fieldName + ']').val(0);
	  }
	}

	$('.input-group').on('click', '.button-plus', function(e) {
	  incrementValue(e);
	});

	$('.input-group').on('click', '.button-minus', function(e) {
	  decrementValue(e);
	});


</script>
	
</body>
</html>