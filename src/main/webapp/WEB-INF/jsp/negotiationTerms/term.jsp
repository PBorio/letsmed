<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Negotiation Terms</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">�</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Negotiation Terms</h2>
</div>
<form class="form-horizontal" action='<c:url value="/negotiationTerms/save"/>' method="post">
<input type="hidden" name="negotiationTerm.id" value="${negotiationTerm.id}" />
<div class="box-content">
	  <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Description:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span8" name="negotiationTerm.description" id="negotiationTerm.description" value="${negotiationTerm.description}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Negotiation Type:</label>
		  <div class="controls">
		    <select name='negotiationTerm.negotiationType' class='input-xlarge span8' id="type">    
		         <option value='-1'>Negotiation Type...</option>
		          <c:forEach var="type" items="${negotiationTypes}">
		         	<option value='${type}' 
	                <c:if test="${type == negotiationTerm.negotiationType}"> selected='true'</c:if>> 
	                	${type.description}
	                </option>   
		         </c:forEach>   
		    </select>
		  </div>
		</div>
	  </fieldset>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/negotiationTerms'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
	$(document).ready(function() {
		$('#negotiationTerm\\.description').focus();
	});
</script>
</content>
</body>
</html>