<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="box-header" data-original-title>
						<h2><i class="halflings-icon user"></i><span class="break"></span>Orders Timeline</h2>
					</div>
					<div class="box-content">
					<table id="timeline" class="table table-striped">

					  <thead>
					  	  <tr><th colspan="13"><a href="<c:url value='/orders/order'/>" class="btn btn-primary">New Order</a></th></tr>	
						  <tr>
						   <th width="20%">Suppliers</th>	
					 	   <th width="8%">Waiting for Supplier Proforma</th>
					 	   <th width="8%">Waiting for Proforma and Artwork Confirmation</th>
					 	   <th width="8%">Waiting for Advanced Payment</th>
					 	   <th width="8%">Waiting for Forward Details</th>
					 	   <th width="8%">Waiting for Shipment</th>
					 	   <th width="8%">Waiting for Copies to Be Sent</th>
					 	   <th width="8%">Waiting Order To Be Paid</th>
					 	   <th width="8%">Waiting for Originals To Be Sent</th>
					 	   <th width="8%">Waiting for Commision Payment</th>
					 	   <th width="8%">Complains</th>
						  </tr>
					  </thead>   
					  <tbody>
					  	<c:forEach var="order" items="${orderList }">
					  	  <tr>
					  	  		<td>${order.supplier.supplierName}</td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'supplier_proforma' }">
					  	  		  <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  			${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_proforma' }">
					  	  		    <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  		${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						         <td>
					  	  		  <c:if test="${order.status.description == 'to_advanced_paid' }">
					  	  			<div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			 ${order.orderNumber} <br/>
						  	  			${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						         <td>
					  	  		  <c:if test="${order.status.description == 'to_forward_detail' }">
					  	  		    <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  			 ${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_ship' }">
					  	  		  <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  		${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_copy_doc_sent' }">
					  	  		  <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  		${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_be_paid' }">
					  	  		  <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  		${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						        
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_original_doc_sent' }">
					  	  		  <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  		 ${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
						         <td>
					  	  		    <c:if test="${order.status.description == 'to_comisson_payment' }">
					  	  		    <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  		 ${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if> 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'complain' }">
					  	  		   <div class="circle">
					  	  			<a href="<c:url value='/${order.status.url}/${order.firstUnsolvedComplain.id}'/>">
					  	  			  ${order.orderNumber} <br/>
						  	  		${order.status.lastMovement}: <fmt:formatDate value='${order.status.statusDate}' pattern='MM/dd/yyyy'/>
					  	  			</a> 
					  	  			</div>
					  	  		  </c:if>    	 
						        </td>
					  	  </tr>
					  	</c:forEach>
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