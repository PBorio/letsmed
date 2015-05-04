<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Supplier</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Supplier</h2>
</div>
<form class="form-horizontal" action='<c:url value="/suppliers/save"/>' method="post">
<input type="hidden" name="supplier.id" value="${supplier.id}" />
<div class="box-content">
    
	
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Supplier Name:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="supplier.supplierName" id="supplier.supplierName" value="${supplier.supplierName}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Address:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="supplier.address" id="supplier.address">${supplier.address}</textarea> 
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Country:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="supplier.country" id="supplier.country" value="${supplier.country}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="supplier.phoneNumber" id="supplier.phoneNumber" value="${supplier.phoneNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="supplier.phoneNumber2" id="supplier.phoneNumber2" value="${supplier.phoneNumber2}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Email:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="supplier.email" id="supplier.email" value="${supplier.email}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Site:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="supplier.site" id="supplier.site" value="${supplier.site}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Contact:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="supplier.contact" id="supplier.contact" value="${supplier.contact}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Expense Account:</label>
		  <div class="controls">
		    <select id="supplier.expenseAccount.id" name="supplier.expenseAccount.id" class="input-xlarge span6" >   
              <option value=""> Expense Accounts...</option>  
              <c:forEach var="expenseAccount" items="${expenseAccountList}">  
                  <option value="${expenseAccount.id}" <c:if test="${expenseAccount.id == supplier.expenseAccount.id}">selected="true"</c:if>> 
                  	${expenseAccount.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/suppliers'/>" class="btn btn-primary">Cancel</a>
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