<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Order</title>
<link rel="stylesheet" href="<c:url value='/resources/canvas-theme/css/datepicker.css'/>" type="text/css" media="screen" />
</head>
<body>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Orders</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="10%">Order Number</th>
	   <th width="10%">Order Date</th>
	   <th width="40%">Customer Name</th>
	   <th width="25%">Negotiation Term</th>
 	   <th>Actions</th>
	  </tr>
  </thead>   
  <tbody>
  	<c:forEach var="order" items="${orderList}">
  	  <tr>
  	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.id}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>"><fmt:formatDate value='${order.orderDate}' pattern='MM/dd/yyyy'/></a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.customer.name}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.negotiationTerm.description}</a></td>
  	  		<td class="center"><a href="" onclick="remove(${order.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a></td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
<div class="form-actions">
<a href="<c:url value='/orders/order'/>" class="btn btn-primary">New Order</a>
</div>
</body>
</html>