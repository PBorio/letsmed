<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Offices</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Office</h2>
</div>
<form class="form-horizontal" action='<c:url value="/offices/save"/>' method="post">
<input type="hidden" name="office.id" value="${office.id}" />
<div class="box-content">
    
	
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Office Name:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="office.officeName" id="office.officeName" value="${office.officeName}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Tax Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" name="office.taxNumber" id="office.taxNumber" value="${office.taxNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Address:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="office.address" id="office.address">${office.address}</textarea> 
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="office.phoneNumber" id="office.phoneNumber" value="${office.phoneNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="office.phoneNumber2" id="office.phoneNumber2" value="${office.phoneNumber2}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="office.phoneNumber3" id="office.phoneNumber3" value="${office.phoneNumber3}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Email:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="office.email" id="office.email" value="${office.email}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Site:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="office.site" id="office.site" value="${office.site}" />
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/offices'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#office\\.officeName').focus();
	});
</script>
</content>
</body>
</html>