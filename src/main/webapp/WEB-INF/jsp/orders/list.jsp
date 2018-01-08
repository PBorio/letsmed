<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Order</title>
<link rel="stylesheet" href="<c:url value='/resources/canvas-theme/css/datepicker.css'/>" type="text/css" media="screen" />
</head>
<body>
<div class="form-actions">
<a href="<c:url value='/orders/order'/>" class="btn btn-primary">New Order</a>
</div>
<br>
<br>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Orders</h2>
</div>
<div class="box-content">
<form class="form-horizontal" action='<c:url value="/orders/search"/>' method="post">
	<div class="row-fluid">
		<div class="span10">
			<div class="control-group">
			       <div class="controls">
					  <input id="customer-select" type="hidden" class="input-xlarge span12" name="customer.id" value="${customer.id}"/>
					  <input type="hidden" id="customer.name" name="customer.name" value="${customer.name}" />
				   </div>
				</div>
		</div>
		<div class="span2">
			<button type="submit" class="btn btn-small"><i class="halflings-icon search"></i>Search</button>
			<a href="<c:url value='/orders'/>" class="btn btn-small"><i class="halflings-icon refresh"></i>Clear Filters</a>
		</div>
	</div>

</form>
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
  	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.orderNumber}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>"><fmt:formatDate value='${order.orderDate}' pattern='MM/dd/yyyy'/></a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.customer.name}</a></td>
	  		<td class="center"><a href="<c:url value='/${url}/${order.id}'/>">${order.negotiationTerm.description}</a></td>
  	  		<td class="center"><a href="" onclick="remove(${order.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a></td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
<content tag="local_script">
<script src="<c:url value='/resources/js/plugins/select2/select2.js'/>"></script>
<script src="<c:url value='/resources/js/plugins/select2/select2_locale_pt-BR.js'/>"></script>
<script src="<c:url value='/resources/js/borioselect2.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	 $("#customer-select").borioSelect({
		selecao : {param1 :"name"},
		url: "<c:url value='/customers/searchByName.json'/>",
		editar:{id: '${customer.id}',texto:'${customer.name}'}
	}).on("change", function(e) { var data = $("#customer-select").select2("data"); setCostumer(data); });
	
});
	
function setCostumer(customer){
	$('#customer\\.name').val(customer.text);
}

</script>
</content>
</body>
</html>