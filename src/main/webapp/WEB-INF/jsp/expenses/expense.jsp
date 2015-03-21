<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Expense</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Expense</h2>
</div>
<form class="form-horizontal" action='<c:url value="/expenses/save"/>' method="post">
<input type="hidden" name="expense.id" value="${expense.id}" />
<div class="box-content">
    
	
	  <fieldset>
	  	<div class="control-group">
		   <label class="control-label col-xs2">Expense Date:</label>			 
	       <div class="controls">
			  <input type="text" class="input-xlarge span2" data-behaviour="datepicker" name="expense.expenseDate" id="expense.expenseDate" value="<fmt:formatDate value='${expense.expenseDate}'  pattern='MM/dd/yyyy' />" />
		   </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Expense Account:</label>
		  <div class="controls">
		    <select id="expense.expenseAccount.id" name="expense.expenseAccount.id" class="input-xlarge span6" >   
              <option value="-1"> Expense Accounts...</option>  
              <c:forEach var="expenseAccount" items="${expenseAccountList}">  
                  <option value="${expenseAccount.id}" <c:if test="${expenseAccount.id == expense.expenseAccount.id}">selected="true"</c:if>> 
                  	${expenseAccount.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Expense Category:</label>
		  <div class="controls">
		    <select id="expense.expenseCategory.id" name="expense.expenseCategory.id" class="input-xlarge span6" >   
              <option value="-1"> Categories...</option>  
              <c:forEach var="expenseCategory" items="${expenseCategoryList}">  
                  <option value="${expenseCategory.id}" <c:if test="${expenseCategory.id == expense.expenseCategory.id}">selected="true"</c:if>> 
                  	${expenseCategory.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Office:</label>
		  <div class="controls">
		    <select id="expense.office.id" name="expense.office.id" class="input-xlarge span6" >   
              <option value="-1"> Offices...</option>  
              <c:forEach var="office" items="${officeList}">  
                  <option value="${office.id}" <c:if test="${office.id == expense.office.id}">selected="true"</c:if>> 
                  	${office.officeName} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Value:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" name="expense.value" id="expense.value" value="${expense.value}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Additional Info:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="expense.additionalInfo" id="expense.additionalInfo">${expense.additionalInfo}</textarea> 
		  </div>
		</div>
	  </fieldset>
	  
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/expenses'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
		$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
	});
</script>
</content>
</body>
</html>