<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Customers</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">�</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Customer</h2>
</div>
<form class="form-horizontal" action='<c:url value="/customers/save"/>' method="post">
<input type="hidden" name="partner.id" value="${partner.id}" />
<div class="box-content">
    
	
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Name:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="customer.name" id="customer.name" value="${customer.name}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Address:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="customer.address" id="partner.address">${customer.address}</textarea> 
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="customer.phoneNumber" id="customer.phoneNumber" value="${customer.phoneNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Phone Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" name="customer.phoneNumber2" id="customer.phoneNumber2" value="${customer.phoneNumber2}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Email:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="customer.email" id="customer.email" value="${customer.email}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Site:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="customer.site" id="customer.site" value="${customer.site}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Contact:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="customer.contact" id="customer.contact" value="${customer.contact}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Revenue Account:</label>
		  <div class="controls">
		    <select id="customer.revenueAccount.id" name="customer.revenueAccount.id" class="input-xlarge span6" >   
              <option value="-1"> Revenue Accounts...</option>  
              <c:forEach var="revenueAccount" items="${revenueAccountList}">  
                  <option value="${revenueAccount.id}" <c:if test="${revenueAccount.id == customer.revenueAccount.id}">selected="true"</c:if>> 
                  	${revenueAccount.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Office:</label>
		  <div class="controls">
		    <select id="customer.office.id" name="customer.office.id" class="input-xlarge span6" >   
              <option value="-1"> Offices...</option>  
              <c:forEach var="office" items="${officeList}">  
                  <option value="${office.id}" <c:if test="${office.id == customer.office.id}">selected="true"</c:if>> 
                  	${office.officeName} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Partner:</label>
		  <div class="controls">
		    <select id="customer.partner.id" name="customer.partner.id" class="input-xlarge span6" >   
              <option value="-1"> Partners...</option>  
              <c:forEach var="partner" items="${partnerList}">  
                  <option value="${partner.id}" <c:if test="${partner.id == customer.partner.id}">selected="true"</c:if>> 
                  	${partner.partnerName} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Comission:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" name="customer.commission" id="customer.commission" value="${customer.commission}" />
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/customers'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#customer\\.name').focus();
	});
</script>
</content>
</body>
</html>