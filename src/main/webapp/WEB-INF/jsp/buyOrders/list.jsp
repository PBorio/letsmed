<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Buy Order</title>
</head>
<body>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Buy Orders</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="15%">Order Date</th>
	   <th width="35%">Supplier Name</th>
	   <th width="35%">Customer Name</th>
 	   <th>Actions</th>
	  </tr>
  </thead>   
  <tbody>
  	<c:forEach var="buyOrder" items="${buyOrderList}">
  	  <tr>
	  		<td class="center"><a href="<c:url value='/${url}/${buyOrder.id}'/>"><fmt:formatDate value='${buyOrder.orderDate}' pattern='dd/MM/yyyy'/></a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${buyOrder.id}'/>">${buyOrder.supplier.supplierName}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${buyOrder.id}'/>">${buyOrder.order.customer.name}</a></td>
  	  		<td class="center">
				<a href="" onclick="remove(${buyOrder.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
	        </td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
<div class="form-actions">
<a href="<c:url value='/buyOrders/order'/>" class="btn btn-primary">New Order</a>
</div>
</body>
</html>