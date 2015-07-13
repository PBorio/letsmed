<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Country</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Country</h2>
</div>
<form class="form-horizontal" action='<c:url value="/countries/save"/>' method="post">
<input type="hidden" name="supplier.id" value="${supplier.id}" />
<div class="box-content">
    
	
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Country Name:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="country.name" id="country.name" value="${country.name}" />
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/countries'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#supplier\\.supplierName').focus();
	});
</script>
</content>
</body>
</html>