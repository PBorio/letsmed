<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Revenue Accounts</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Revenue Accounts</h2>
</div>
<form class="form-horizontal" action='<c:url value="/revenueAccounts/save"/>' method="post">
<input type="hidden" name="revenueAccount.id" value="${revenueAccount.id}" />
<div class="box-content">
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Description:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="revenueAccount.description" id="revenueAccount.description" value="${revenueAccount.description}" />
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/revenueAccounts'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#revenueAccount\\.description').focus();
	});
</script>
</content>
</body>
</html>