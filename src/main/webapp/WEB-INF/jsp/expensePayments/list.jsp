<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Expenses</title>
</head>
<body>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Expenses</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="15%">Date</th>
	   <th width="20%">Expense</th>
	   <th width="20%">Bank</th>
	   <th width="20%">Office</th>
	   <th width="15%">Value</th>
 	   <th>Actions</th>
	  </tr>
  </thead>   
  <tbody>
  	<c:forEach var="expensePayment" items="${expensePaymentList}">
  	  <tr>
	  		<td class="center"><a href="<c:url value='/expensePayments/${expensePayment.id}'/>"><fmt:formatDate value='${expensePayment.paymentDate}' pattern='MM/dd/yyyy'/></a></td>
	  		<td class="center"><a href="<c:url value='/expensePayments/${expensePayment.id}'/>">${expensePayment.expenseAccount.description}</a></td>
	  		<td class="center"><a href="<c:url value='/expensePayments/${expensePayment.id}'/>">${expensePayment.bankAccount.description}</a></td>
	  		<td class="center"><a href="<c:url value='/expensePayments/${expensePayment.id}'/>">${expensePayment.office.officeName}</a></td>
	  		<td class="center"><a href="<c:url value='/expensePayments/${expensePayment.id}'/>"><fmt:formatNumber value='${expensePayment.value}' pattern='#,##0.00'/></a></td>
  	  		<td class="center">
				<a href="" onclick="remove(${expensePayment.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
	        </td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
<div class="form-actions">
<a href="<c:url value='/expensePayments/payment'/>" class="btn btn-primary">New Payment</a>
</div>
</body>
</html>