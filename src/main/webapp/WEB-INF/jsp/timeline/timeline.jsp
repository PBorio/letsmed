<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time Line</title>
</head>
<body>
<div class="box-header" data-original-title>
		<h2><i class="halflings-icon user"></i><span class="break"></span>Orders Timeline</h2>
	</div>
	<div class="box-content">
	<form class="form-horizontal" action='<c:url value="/timeline/search"/>' method="post">
		<div class="row-fluid">
			<div class="span3">
				<div class="control-group">
						  <input type="text" placeholder="Order Number" class="input-xlarge span12" name="order.orderNumber" value="${order.orderNumber}"/>
					</div>
			</div>
			<div class="span3">
				       <div class="control-group">
						  <input type="text" placeholder="Customer" class="input-xlarge span12" name="order.customer.name" value="${order.customer.name}"/>
					   </div>
			</div>
			<div class="span2">
				       <div class="control-group">
						  <input type="text" placeholder="Supplier" class="input-xlarge span12" name="order.supplier.supplierName" value="${order.supplier.supplierName}"/>
					   </div>
			</div>
			<div class="span2">
				       <div class="control-group">
						  <input type="text" placeholder="User" class="input-xlarge span12" name="order.supplier.user.login" value="${order.supplier.user.login}"/>
					   </div>
			</div>
			<div class="span2">
				<button type="submit" class="btn btn-small"><i class="halflings-icon search"></i>Search</button>
				<a href="<c:url value='/timeline'/>" class="btn btn-small"><i class="halflings-icon refresh"></i>Clear Filters</a>
			</div>
		</div>
	</form>
	<table id="timeline" class="table table-striped">

	  <thead>
	  	  <tr><th colspan="${timelineViewer.columnNumber + 1}"><a href="<c:url value='/orders/order'/>" class="btn btn-primary">New Order</a></th></tr>	
		  <tr>
		   <th width="20%">Suppliers</th>	
		   <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <th>${coluna.supplier.supplierName }</th>
	 	   </c:forEach>
		  </tr>
	  </thead>   
	  <tbody>
	  	  <tr>
	  	  	<td width="20%">Waiting for Supplier Proforma</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingSupplierProforma}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Waiting for Proforma and Artwork Confirmation</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingArtworkConfirmation}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Waiting for Advanced Payment</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingAdvancedPayment}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Waiting for Forward Details</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingForwardDetails}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Waiting for Shipment</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingForShipment}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Waiting for Copies to Be Sent</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingCopiesToBeSent}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Waiting Order To Be Paid</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingPayment}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	   
	 	  <tr>
	  	  	<td width="20%">Waiting for Originals To Be Sent</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingOriginalsDocument}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Waiting for Commission Payment</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.ordersWaitingCommissionPayment}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	  
	 	  <tr>
	  	  	<td width="20%">Complains</td>	
		    <c:forEach var="coluna" items="${timelineViewer.columns}">
			   <td><c:forEach var="order" items="${coluna.complains}">
			   		<div class="circle jie">
		  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
		  	  			  ${order.orderNumber} <br/>
			  	  		  <fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>
		  	  			</a> 
	  	  			</div>
			   		</c:forEach>
			   </td>
	 	   </c:forEach>
	 	  </tr>
	 	   
	  </tbody>
	</table>
	
</div>
<content tag="local_script">
	<script src="<c:url value='/resources/js/plugins/stickytableheaders.min.js'/>"></script>
	<script type="text/javascript">
		
$(function(){
	
  $("#timeline").stickyTableHeaders();

});
</script>
</content>
</body>
</html>