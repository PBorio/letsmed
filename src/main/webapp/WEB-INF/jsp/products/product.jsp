<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Products</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">�</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Product</h2>
</div>
<form class="form-horizontal" action='<c:url value="/products/save"/>' method="post">
<input type="hidden" name="product.id" value="${product.id}" />
<div class="box-content">
    
	
	  <fieldset>
	    <div class="control-group">
		  <label class="control-label col-xs2">Artible Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span2" name="product.articleNumber" id="product.articleNumber" value="${product.articleNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Description:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="product.description" id="product.description" value="${product.description}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Different Option 1:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="product.differentOption1" id="product.differentOption1" value="${product.differentOption1}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Different Option 2:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="product.differentOption2" id="product.differentOption2" value="${product.differentOption2}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Different Option 3:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="product.differentOption3" id="product.differentOption3" value="${product.differentOption3}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Different Option 4:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="product.differentOption4" id="product.differentOption4" value="${product.differentOption4}" />
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/products'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#product\\.articleNumber').focus();
	});
</script>
</content>
</body>
</html>