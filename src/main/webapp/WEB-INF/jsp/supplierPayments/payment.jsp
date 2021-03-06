<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supplier Payment</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">�</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Order Payment</h2>
</div>
<form class="form-horizontal" action='<c:url value="/supplierPayments/save"/>' method="post">
<div class="box-content">
	<%@include file="../orders/orderFragment.jsp" %>
	<hr class="hideInIE8"/>
	
	<table class="table table-striped table-bordered">
	  <thead>
	  	  <tr>
	  	    <th colspan="4">Payments</th>
	  	  </tr>
		  <tr>
		   <th>Date</th>
		   <th>Value</th>
		   <th>Actions</th>
		  </tr>
	  </thead>   
	  <tbody>
	  	<c:forEach var="payment" items="${order.supplierPayments}" varStatus="idx">	
	  	  <tr id="payment-${payment.id}">
		  		<td class="center"><fmt:formatDate value='${payment.paymentDate}' pattern='MM/dd/yyyy'/></td>
		  		<td class="center"><fmt:formatNumber value='${payment.value}' pattern='#,##0.00'/></td>
		  		<td class="center">
  	  			<a href="<c:url value='/supplierPayments/${payment.id}'/>" class="btn btn-success"><i class="halflings-icon zoom-in halflings-icon"></i></a>
				<a href="<c:url value='/supplierPayments/delete/${payment.id}'/>" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
	        </td>
	  	  </tr>
	  	</c:forEach>
	  </tbody>
	 </table>   
	
	<div class="box-header" data-original-title>
        <h2><i class="halflings-icon edit"></i><span class="break"></span>Revenue</h2>
    </div>
    <div class="box-content">
    	<fieldset>
    	<input type="hidden" value="${supplierPayment.id}" name="supplierPayment.id" >
    	<input type="hidden" value="${supplierPayment.order.id}" name="supplierPayment.order.id" >
    	<div class="control-group">
		   <label class="control-label col-xs2">Payment Date:</label>			 
	       <div class="controls">
			  <input type="text" class="input-xlarge span2" data-behaviour="datepicker" name="supplierPayment.paymentDate" id="supplierPayment.paymentDate" value="<fmt:formatDate value='${supplierPayment.paymentDate}'  pattern='MM/dd/yyyy' />" />
		   </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Value:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" data-behaviour="valor" value="<fmt:formatNumber value='${supplierPayment.value}' pattern='#,##0.00'/>" name="supplierPayment.value" id="supplierPayment.value" value="${supplierPayment.value}" />
		  </div>
		</div>
		</fieldset>
    </div>
    <hr class="hideInIE8"/>
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button>
	  <a href="<c:url value='/supplierPayments'/>" class="btn btn-primary">Cancel</a>
	  <a href="<c:url value='/timeline'/>" class="btn btn-primary">Go To Timeline</a>   
	</div>
</div>
</form>
<content tag="local_script">
<script type="text/javascript">

$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
	$('[data-behaviour~=valor]').setMask('decimal-us');
});

function removePayment(id){
	   alert(id);
	   if (confirm('Are you sure?')){
		   $.ajax({
			   url: '/letsmed/supplierPayments/delete/item/'+id,
			   context: document.body
			 }).done(function() {
				 $('#payment-'+id).fadeOut('slow');
				 $('#payment-'+id).remove();
			 });   
	   }
	}

</script>
</content>
</body>
</html>