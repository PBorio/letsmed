<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Offices</title>
<link rel="stylesheet" href="<c:url value='/resources/canvas-theme/css/datepicker.css'/>" type="text/css" media="screen" />
</head>
<body>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Offices</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="70%">Office Name</th>
	   <th width="15%">Tax N.</th>
 	   <th>Actions</th>
	  </tr>
  </thead>   
  <tbody>
  	<c:forEach var="office" items="${officeList}">
  	  <tr>
	  		<td class="center"><a href="<c:url value='/offices/${office.id}'/>">${office.officeName}</a></td>
	  		<td class="center"><a href="<c:url value='/offices/${office.id}'/>">${office.taxNumber}</a></td>
  	  		<td class="center">
  	  			<a href="<c:url value='/offices/${office.id}'/>" class="btn btn-success"><i class="halflings-icon zoom-in halflings-icon"></i></a>
  	  			<a href="<c:url value='/offices/${office.id}'/>" class="btn btn-info"><i class="halflings-icon edit halflings-icon"></i></a>	        	 
				<a href="" onclick="remove(${office.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
	        </td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
<div class="form-actions">
<a href="<c:url value='/offices/office'/>" class="btn btn-primary">New Office</a>
</div>
</body>
</html>