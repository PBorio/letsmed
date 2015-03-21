<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Bank Accounts</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Expense Categories</h2>
</div>
<form class="form-horizontal" action='<c:url value="/bankAccounts/save"/>' method="post">
<input type="hidden" name="bankAccount.id" value="${bankAccount.id}" />
<div class="box-content">
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Description:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="bankAccount.description" id="bankAccount.description" value="${bankAccount.description}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Account Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span4" name="bankAccount.accountNumber" id="bankAccount.accountNumber" value="${bankAccount.accountNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Agency Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span4" name="bankAccount.agencyNumber" id="bankAccount.agencyNumber" value="${bankAccount.agencyNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Balance:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" name="bankAccount.balance" id="bankAccount.balance" value="${bankAccount.balance}" />
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/bankAccounts'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#expenseCategory\\.description').focus();
	});
</script>
</content>
</body>
</html>