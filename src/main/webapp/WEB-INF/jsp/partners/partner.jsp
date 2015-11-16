<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Partner</title>
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
<form class="form-horizontal" action='<c:url value="/partners/save"/>' method="post">
<input type="hidden" name="partner.id" value="${partner.id}" />
<div class="box-content">
    
	
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Partner Name:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="partner.partnerName" id="partner.partnerName" value="${partner.partnerName}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Address:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="partner.address" id="partner.address">${partner.address}</textarea> 
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="partner.phoneNumber" id="partner.phoneNumber" value="${partner.phoneNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="partner.phoneNumber2" id="partner.phoneNumber2" value="${partner.phoneNumber2}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Email:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="partner.email" id="partner.email" value="${partner.email}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Site:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="partner.site" id="partner.site" value="${partner.site}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Contact:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="partner.contact" id="partner.contact" value="${partner.contact}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Expense Account:</label>
		  <div class="controls">
		    <select id="partner.expenseAccount.id" name="partner.expenseAccount.id" class="input-xlarge span6" >   
              <option value="-1"> Expense Accounts...</option>  
              <c:forEach var="expenseAccount" items="${expenseAccountList}">  
                  <option value="${expenseAccount.id}" <c:if test="${expenseAccount.id == partner.expenseAccount.id}">selected="true"</c:if>> 
                  	${expenseAccount.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Commission Category:</label>
		  <div class="controls">
		    <select id="partner.expenseCategory.id" name="partner.expenseCategory.id" class="input-xlarge span6" >   
              <option value="-1"> Categories...</option>  
              <c:forEach var="expenseCategory" items="${expenseCategoryList}">  
                  <option value="${expenseCategory.id}" <c:if test="${expenseCategory.id == partner.expenseCategory.id}">selected="true"</c:if>> 
                  	${expenseCategory.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Percentual:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" name="partner.percentual" id="partner.percentual" value="${partner.percentual}" />
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
		$('#partner\\.partnerName').focus();
	});
</script>
</content>
</body>
</html>