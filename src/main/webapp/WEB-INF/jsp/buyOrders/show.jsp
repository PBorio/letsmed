<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order N. ${order.id}</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Order N. ${order.id}</h2>
</div>
<form class="form-horizontal" action='<c:url value="/recebimentos/receber"/>' method="post">
<div class="box-content">
	<div class="priority low"><span></span></div>
	<div class="task low">
		<div class="desc">
			<input type="hidden" value="${order.id}" name="order.id" />
			<div class="title">Order N. ${order.id} - ${order.customer.name}</div>
			<div>Valor: <fmt:formatNumber value='${order.totalValue}' pattern='#,##0.00'/></div>
		</div>
		<div class="time">
			<div class="date"><fmt:formatDate value='${order.orderDate}'  pattern='MM/dd/yyyy' /></div>
		</div>
	</div>
	<table class="table table-striped table-bordered">
	  <thead>
		  <tr>
		   <th width="40%">Description</th>
		   <th width="10%">Quantity</th>
		   <th width="10%">Unit Price</th>
		   <th width="10%">Total Value</th>
		   <th width="30%">Unit Price</th>
		  </tr>
	  </thead>   
	  <tbody>
	  	<c:forEach var="item" items="${order.itens}" varStatus="idx">	
	  	  <tr>
		  		<td class="center">${item.product.description}</td>
		  		<td class="center">${item.quantity}</td>
		  		<td class="center">${item.unitPrice}</td>
		  		<td class="center">${item.totalValue}</td>
		  		<td class="center">${item.additionalInfo}</td>
	  	  </tr>
	  	</c:forEach>
	  </tbody>
	 </table>   
</div>
	<div class="box-header" data-original-title>
        <h2><i class="halflings-icon edit"></i><span class="break"></span>Recebimento</h2>
    </div>
    <div class="box-content">
    	<fieldset>
    	
		</fieldset>
    </div>
    <hr class="hideInIE8"/>
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Salvar</button>
	     <a href="<c:url value='/recebimentos'/>" class="btn btn-primary">Cancelar</a>
	</div>
</form>
<content tag="local_script">
<script type="text/javascript">

$(document).ready(function() {
	$('#recebimento\\.valorRecebido').focus();
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "dd/mm/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '39/19/9999', autoTab: false});
	$('[data-behaviour~=valor]').setMask('decimal');
});

</script>
</content>
</body>
</html>