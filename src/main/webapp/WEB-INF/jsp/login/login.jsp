<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Login</h2>
</div>
<form class="form-horizontal" action='<c:url value="/login/logar"/>' method="post">
<div class="box-content">
    
	
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Login:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="login" id="login" value="${login}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">password:</label>
		  <div class="controls">
		    <input type="password" class="input-xlarge span6" name="senha" id="senha" value="${senha}" /> 
		  </div>
		</div>
		
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Login</button>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#login').focus();
	});
</script>
</content>
</body>
</html>