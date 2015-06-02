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
						  <tr>
						   <th width="16%"></th>
					 	   <th width="7%">Waiting For Complete Fill In</th>
					 	   <th width="7%">Waiting for Supplier Proforma</th>
					 	   <th width="7%">Waiting for Proforma Confirmation</th>
					 	   <th width="7%">Waiting for Advanced Payment</th>
					 	   <th width="7%">Waiting for Production Start</th>
					 	   <th width="7%">Waiting for Forward Details</th>
					 	   <th width="7%">Waiting for Shipment</th>
					 	   <th width="7%">Waiting for Copies to Be Sent</th>
					 	   <th width="7%">Waiting Order To Be Paid</th>
					 	   <th width="7%">Waiting for Originals To Be Sent</th>
					 	   <th width="7%">Waiting for Commision Payment</th>
					 	   <th width="7%">Waiting for Commision Payment</th>
						  </tr>
					  </thead>   
					  <tbody>
					  	<c:forEach var="order" items="${orderList }">
					  	  <tr>
						  		<td><a href="<c:url value='/orders/${order.id}'/>"><strong>Order:</strong> ${order.id} - ${order.customer.name}</br> <strong>Terms:</strong> ${order.negotiationTerm.description}</a></td>
					  	  		<td>
					  	  		  <c:if test="${order.status.description == 'to_fill_in' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'supplier_proforma' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_proforma' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						         <td>
					  	  		  <c:if test="${order.status.description == 'to_advanced_paid' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_producing' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						         <td>
					  	  		  <c:if test="${order.status.description == 'to_forward_detail' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_ship' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_copy_doc_sent' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_be_paid' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						        
						        <td>
					  	  		  <c:if test="${order.status.description == 'to_original_doc_sent' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
						         <td>
					  	  		    <c:if test="${order.status.description == 'to_comisson_payment' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if> 
						        </td>
						        <td>
					  	  		  <c:if test="${order.status.description == 'complain' }">
					  	  			<a href="<c:url value='/${order.status.url}/${order.firstUnsolvedComplain.id}'/>">
					  	  			  <div class="circle"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></div>
					  	  			</a> 
					  	  		  </c:if>    	 
						        </td>
					  	  </tr>
					  	</c:forEach>
					  </tbody>
					</table>
					
				</div>

</body>
</html>