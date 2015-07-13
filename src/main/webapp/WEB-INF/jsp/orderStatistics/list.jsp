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
<form class="form-horizontal" action='<c:url value="/orderStatistics/orders/search"/>' method="post">
	<div class="control-group">
	   <label class="control-label col-xs2">Begin Date:</label>			 
       <div class="controls">
		  <input type="text" class="input-xlarge span2" data-behaviour="datepicker" name="begin" id="begin" value="<fmt:formatDate value='${begin}'  pattern='MM/dd/yyyy' />" />
	   </div>
	</div>
  	<div class="control-group">
	   <label class="control-label col-xs2">End Date:</label>			 
       <div class="controls">
		  <input type="text" class="input-xlarge span2" data-behaviour="datepicker" name="end" id="end" value="<fmt:formatDate value='${end}'  pattern='MM/dd/yyyy' />" />
	   </div>
	 </div>
	<hr class="hideInIE8"/>
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Search</button>
	</div>
</form>
</div>
<hr class="hideInIE8"/>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="10%">Order Number</th>
	   <th width="10%">Order Date</th>
	   <th width="40%">Customer Name</th>
	   <th>Supplier Name</th>
	   <th>Value</th>
	   <th>Balance</th>
	  </tr>
  </thead>   
  <tbody>
  	<c:forEach var="order" items="${orders}">
  	  <tr>
  	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.orderNumber}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>"><fmt:formatDate value='${order.orderDate}' pattern='MM/dd/yyyy'/></a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.customer.name}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.supplier.supplierName}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>"><fmt:formatNumber value='${order.totalValue}' pattern='#,##0.00'/></a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>"><fmt:formatNumber value='${order.balance}' pattern='#,##0.00'/></a></td>
  	  </tr>
  	  		<c:set var="totalValue" value="${totalValue + order.totalValue}"/>
			<c:set var="totalBalance" value="${totalBalance + order.balance}"/>
  	</c:forEach>
  </tbody>
  <tfoot>
     <tr>
  		<td></td>
  		<td></td>
  		<td></td>
  		<td></td>
  		<td class='center'><strong><fmt:formatNumber value="${totalValue}" pattern="#,##0.00"/></strong></td>
  		<td class='center'><strong><fmt:formatNumber value="${totalBalance}" pattern="#,##0.00"/></strong></td>
  	</tr>
  </tfoot>
 </table>            
</div>
<content tag="local_script">
<script type="text/javascript">
$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
});
</script>
</content>
</body>
</html>