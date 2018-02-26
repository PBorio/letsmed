<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>User</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Partner</h2>
</div>
<form class="form-horizontal" action='<c:url value="/users/save"/>' method="post">
<input type="hidden" name="user.id" value="${user.id}" />
<div class="box-content">
    
	
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">User Name:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="user.name" id="user.name" value="${user.name}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">login:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="user.login" id="user.login" value="${user.login}" /> 
		  </div>
		</div>
		
			<div class="control-group">
			  <label class="control-label col-xs2">Role:</label>
			  <div class="controls">
			    <select id="user.role.id" name="user.role.id" class="input-xlarge span6" >   
	              <option value="-1"> Roles...</option>  
	              <c:forEach var="role" items="${roles}">  
	                  <option value="${role.id}" <c:if test="${role.id == user.role.id}">selected="true"</c:if>> 
	                  	${role.name} 
	                  </option>  
	              </c:forEach> 
	          </select>
			  </div>
			</div>
			
		<div class="control-group">
		  <label class="control-label col-xs2">Password:</label>
		  <div class="controls">
		    <input type="password" class="input-xlarge span3" name="user.password" id="user.password"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Retype Password:</label>
		  <div class="controls">
		    <input type="password" class="input-xlarge span3" name="retype" id="retype" />
		  </div>
		</div>
		
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/partners'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#user\\.name').focus();
	});
</script>
</content>
</body>
</html>