<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Revenue Payment</title>
</head>
<body>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Revenue Payment</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="15%">Date</th>
	   <th width="35%">Revenue</th>
	   <th width="35%">Value</th>
 	   <th>Actions</th>
	  </tr>
  </thead>   
  <tbody>
  	<c:forEach var="revenue" items="${revenueList}">
  	  <tr>
	  		<td class="center"><a href="<c:url value='/revenues/payment/${revenue.id}'/>"><fmt:formatDate value='${revenue.revenueDate}' pattern='MM/dd/yyyy'/></a></td>
	  		<td class="center">
	  		   <a href="<c:url value='/revenues/payment/${revenue.id}'/>">${revenue.revenueAccount.description}
	  			  <c:if test="${revenue.order != null}"> - Order N. ${revenue.order.id} </c:if>
	  		   </a>
	  		</td>
	  		<td class="center"><a href="<c:url value='/revenues/payment/${revenue.id}'/>">${revenue.value}</a></td>
  	  		<td class="center">
				<a href="" onclick="remove(${revenue.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
	        </td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
</body>
</html>