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
<form class="form-horizontal" action='<c:url value="/forwardDetails/save"/>' method="post">
<div class="box-content">
	<%@include file="../orders/orderFragment.jsp" %>
	<hr class="hideInIE8"/>
	<table class="table table-striped table-bordered">
	  <thead>
	  	  <tr>
	  	    <th colspan="4">Details</th>
	  	  </tr>
		  <tr>
		   <th width="80%">Description</th>
		   <th>Actions</th>
		  </tr>
	  </thead>   
	  <tbody>
	  	<c:forEach var="detail" items="${order.forwardDetails}" varStatus="idx">	
	  	  <tr id="payment-${detail.id}">
	  		<td class="center">${detail.description}</td>
	  		<td class="center">
  	  			<a href="<c:url value='/forwardDetails/${detail.id}'/>" class="btn btn-success"><i class="halflings-icon zoom-in halflings-icon"></i></a>
				<a href="<c:url value='/forwardDetails/delete/${detail.id}'/>" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
	        </td>
	  	  </tr>
	  	</c:forEach>
	  </tbody>
	 </table>   
	<div class="box-header" data-original-title>
        <h2><i class="halflings-icon edit"></i><span class="break"></span>Revenue</h2>
    </div>
    <div class="box-content">
    <fieldset>
    	<input type="hidden" value="${forwardDetail.id}" name="forwardDetail.id" >
    	<input type="hidden" value="${forwardDetail.order.id}" name="forwardDetail.order.id" >
		<div class="control-group">
		  <label class="control-label col-xs2">Description:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span8" name="forwardDetail.description">${forwardDetail.description}</textarea>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Invoice Number:</label>
		  <div class="controls">
		    <input class="input-xlarge span8" name="forwardDetail.invoiceNumber" value="${forwardDetail.invoiceNumber}"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Terms:</label>
		  <div class="controls">
		    <input class="input-xlarge span8" name="forwardDetail.terms" value="${forwardDetail.terms}"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Landing Port:</label>
		  <div class="controls">
		    <input class="input-xlarge span8" name="forwardDetail.landingPort" value="${forwardDetail.landingPort}"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Destination Port:</label>
		  <div class="controls">
		    <input class="input-xlarge span8" name="forwardDetail.destinationPort" value="${forwardDetail.destinationPort}"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Payment Conditions:</label>
		  <div class="controls">
		    <input class="input-xlarge span8" name="forwardDetail.paymentConditions" value="${forwardDetail.paymentConditions}"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Insurance:</label>
		  <div class="controls">
		    <input class="input-xlarge span8" name="forwardDetail.insurance" value="${forwardDetail.insurance}"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Shipment:</label>
		  <div class="controls">
		    <input class="input-xlarge span8" name="forwardDetail.shipment" value="${forwardDetail.shipment}"/>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Delivery Date:</label>
		  <div class="controls">
		     <input type="text" autocomplete="off" class="input-xlarge span2" data-behaviour="datepicker" name="forwardDetail.deliveryDate" id="forwardDetail.deliveryDate" value="<fmt:formatDate value='${forwardDetail.deliveryDate}' pattern='MM/dd/yyyy'/>" />
		  </div>
		</div>
	</fieldset>
    </div>
    <hr class="hideInIE8"/>
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button>
	     <a href="<c:url value='/forwardDetails'/>" class="btn btn-primary">Cancel</a>
	     <a href="<c:url value='/timeline'/>" class="btn btn-primary">Go To Timeline</a>   
	</div>
</div>
</form>
</body>
</html>