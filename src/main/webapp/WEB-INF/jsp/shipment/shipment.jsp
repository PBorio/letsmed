<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forward Details</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Forward Details</h2>
</div>
<form class="form-horizontal" action='<c:url value="/shipment/save"/>' method="post">
<div class="box-content">
	<%@include file="../orders/orderFragment.jsp" %>
	<hr class="hideInIE8"/>
	<div class="box-header" data-original-title>
        <h2><i class="halflings-icon edit"></i><span class="break"></span>Shipment</h2>
    </div>
    <div class="box-content">
    <fieldset>
		<div class="control-group">
		  <label class="control-label col-xs2">Ship Date:</label>
		  <div class="controls">
		    <input type="text" autocomplete="off" class="input-xlarge span2" data-behaviour="datepicker" name="order.shipDate" id="order.shipDate" value="<fmt:formatDate value='${order.shipDate}' pattern='MM/dd/yyyy'/>" />
		  </div>
		</div>
	</fieldset>
    </div>
    <hr class="hideInIE8"/>
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button>
	     <a href="<c:url value='/timeline'/>" class="btn btn-primary">Cancel</a>
	     <a href="<c:url value='/timeline'/>" class="btn btn-primary">Go To Timeline</a>   
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