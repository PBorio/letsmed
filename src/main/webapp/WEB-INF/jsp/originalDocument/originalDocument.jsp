<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipment</title>
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
<form class="form-horizontal" action='<c:url value="/originalDocument/save"/>' method="post">
<div class="box-content">
	<%@include file="../orders/orderFragment.jsp" %>
	<hr class="hideInIE8"/>
	<div class="box-header" data-original-title>
        <h2><i class="halflings-icon edit"></i><span class="break"></span>Original Document</h2>
    </div>
    <div class="box-content">
    <fieldset>
    	<input type="hidden" value="${originalDocumentShipment.id}" name="originalDocumentShipment.id" >
    	<input type="hidden" value="${originalDocumentShipment.order.id}" name="originalDocumentShipment.order.id" >
		<div class="control-group">
		  <label class="control-label col-xs2">Send Date:</label>
		  <div class="controls">
		    <input type="text" autocomplete="off" class="input-xlarge span2" data-behaviour="datepicker" name="originalDocumentShipment.shipDate" id="originalDocumentShipment.shipDate" value="<fmt:formatDate value='${originalDocumentShipment.shipDate}' pattern='MM/dd/yyyy'/>" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Courier:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="originalDocumentShipment.courierName" id="originalDocumentShipment.courierName" value="${originalDocumentShipment.courierName}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Tracking Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="originalDocumentShipment.trackingNumber" id="originalDocumentShipment.trackingNumber" value="${originalDocumentShipment.trackingNumber}" />
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
	$('#originalDocumentShipment\\.courierName').focus();
});
</script>
</content>
</body>
</html>