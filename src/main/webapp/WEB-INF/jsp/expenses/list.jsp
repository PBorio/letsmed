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
	   <th width="35%">Expense</th>
	   <th width="35%">Value</th>
 	   <th>Actions</th>
	  </tr>
  </thead>   
  <tbody>
  	<c:forEach var="expense" items="${expenseList}">
  	  <tr>
	  		<td class="center"><a href="<c:url value='/expenses/${expense.id}'/>"><fmt:formatDate value='${expense.expenseDate}' pattern='MM/dd/yyyy'/></a></td>
	  		<td class="center">
	  		   <a href="<c:url value='/expenses/${expense.id}'/>">
	  		   	  <c:if test="${expense.order != null}"> Order N. ${expense.order.id} - </c:if>
	  			  ${expense.expenseAccount.description}
	  		   </a>
	  		</td>
	  		<td class="center"><a href="<c:url value='/expenses/${expense.id}'/>">${expense.value}</a></td>
  	  		<td class="center">
				<a href="" onclick="remove(${expense.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
	        </td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
<div class="form-actions">
<a href="<c:url value='/expenses/expense'/>" class="btn btn-primary">New Expense</a>
</div>
</body>
</html>