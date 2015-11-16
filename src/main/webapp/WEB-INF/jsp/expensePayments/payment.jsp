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
<form class="form-horizontal" action='<c:url value="/expensePayments/save"/>' method="post">
<input type="hidden" name="expensePayment.id" value="${expensePayment.id}" />
<div class="box-content">
    
	
	  <fieldset>
	  	<div class="control-group">
		   <label class="control-label col-xs2">Payment Date:</label>			 
	       <div class="controls">
			  <input type="text" class="input-xlarge span2" data-behaviour="datepicker" name="expensePayment.paymentDate" id="expensePayment.paymentDate" value="<fmt:formatDate value='${expensePayment.paymentDate}'  pattern='MM/dd/yyyy' />" />
		   </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Expense Account:</label>
		  <div class="controls">
		    <select id="expensePayment.expenseAccount.id" name="expensePayment.expenseAccount.id" class="input-xlarge span6" >   
              <option value="-1"> Expense Accounts...</option>  
              <c:forEach var="expenseAccount" items="${expenseAccountList}">  
                  <option value="${expenseAccount.id}" <c:if test="${expenseAccount.id == expensePayment.expenseAccount.id}">selected="true"</c:if>> 
                  	${expenseAccount.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Office:</label>
		  <div class="controls">
		    <select id="expensePayment.office.id" name="expensePayment.office.id" class="input-xlarge span6" >   
              <option value="-1"> Offices...</option>  
              <c:forEach var="office" items="${officeList}">  
                  <option value="${office.id}" <c:if test="${office.id == expensePayment.office.id}">selected="true"</c:if>> 
                  	${office.officeName} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Bank:</label>
		  <div class="controls">
		    <select id="expensePayment.bankAccount.id" name="expensePayment.bankAccount.id" class="input-xlarge span6" >   
              <option value="-1"> Banks...</option>  
              <c:forEach var="bank" items="${bankAccountList}">  
                  <option value="${bank.id}" <c:if test="${bank.id == expensePayment.bankAccount.id}">selected="true"</c:if>> 
                  	${bank.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Value:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" name="expensePayment.value" data-behaviour="valor" 
		    	   id="expensePayment.value" value="<fmt:formatNumber value='${expensePayment.value}' pattern='#,##0.00'/>" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Additional Info:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="expensePayment.additionalInfo" id="expensePayment.additionalInfo">${expensePayment.additionalInfo}</textarea> 
		  </div>
		</div>
	  </fieldset>
	  
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/expensePayments'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
		$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
		$('[data-behaviour~=valor]').setMask('decimal-us');
	});
</script>
</content>
</body>
</html>