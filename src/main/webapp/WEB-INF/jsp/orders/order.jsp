<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<c:if test="${order.itemDuplicated}">
	<div class="alert alert-block">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Warning:</strong> Order with duplicated itens
	</div>
</c:if>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Order N. ${order.id}</h2>
</div>
<form class="form-horizontal" action='<c:url value="/orders/save"/>' method="post">
<input type="hidden" name="order.id" value="${order.id}" />
<input type="hidden" name="order.confirmationDate" value="<fmt:formatDate value='${order.confirmationDate}' pattern='MM/dd/yyyy'/>"  />
<input type="hidden" name="order.supplierProformaDate" value="<fmt:formatDate value='${order.supplierProformaDate}' pattern='MM/dd/yyyy'/>" />
<input type="hidden" name="order.proformaConfirmationDate" value="<fmt:formatDate value='${order.proformaConfirmationDate}' pattern='MM/dd/yyyy'/>" />
<input type="hidden" name="order.artworkConfirmationDate" value="<fmt:formatDate value='${order.artworkConfirmationDate}' pattern='MM/dd/yyyy'/>" />
<input type="hidden" name="order.productionStartDate" value="<fmt:formatDate value='${order.productionStartDate}' pattern='MM/dd/yyyy'/>" />
<input type="hidden" name="order.shipDate" value="<fmt:formatDate value='${order.shipDate}' pattern='MM/dd/yyyy'/>" />
<input type="hidden" name="order.copyDocumentDate" value="<fmt:formatDate value='${order.copyDocumentDate}' pattern='MM/dd/yyyy'/>" />
<input type="hidden" name="order.originalDocumentDate" value="<fmt:formatDate value='${order.originalDocumentDate}' pattern='MM/dd/yyyy'/>" />
<div class="box-content">
	  
	 <div class="box-header" data-original-title>
		<h2><i class="halflings-icon edit"></i><span class="break"></span>Order Data</h2>
	</div>
	<div class="box-content">
	  <fieldset>
	    <div class="row-fluid">
	    	<div class="span3">
				<div class="control-group">
				   <label class="control-label col-xs2">Order Date:</label>			 
			       <div class="controls">
					  <input type="text" autocomplete="off" class="input-xlarge span12" data-behaviour="datepicker" name="order.orderDate" id="order.orderDate" value="<fmt:formatDate value='${order.orderDate}' pattern='MM/dd/yyyy'/>" />
				   </div>
				</div>
			</div>
			<div class="span3">
				<div class="control-group">
				  <label class="control-label col-xs2">Delivery Date:</label>
				  <div class="controls">
				     <input type="text" autocomplete="off" class="input-xlarge span12" data-behaviour="datepicker" name="order.deliveryDate" id="order.deliveryDate" value="<fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>" />
				  </div>
				</div>
			</div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Order Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span4" tabindex="-1" name="order.orderNumber" id="order.orderNumber" value="${order.orderNumber}" />
		  </div>
		</div>
		<div class="row-fluid">
    	  <div class="span7">
				<div class="control-group">
				  <label class="control-label col-xs2">Customer:</label>
				  <div class="controls">
				  	<input id="customer-select" type="hidden" class="input-xlarge span12" name="order.customer.id" value="${order.customer.id}"/>
				    <input type="hidden" id="order.customer.name" name="order.customer.name" value="${order.customer.name}" />
				    <input type="hidden" name="order.customer.code" name="order.customer.code" value="${order.customer.code}" />
				  </div>
				</div>
			</div>
			<div class="span2">
				<a href="<c:url value='/customers/customer'/>" tabindex="-1" target="_blank" class="btn btn-small"><i class="halflings-icon file"></i>New Customer</a>
			</div>
			
		</div>
		<div class="row-fluid">
    	  <div class="span7">
			<div class="control-group">
			  <label class="control-label col-xs2">Supplier:</label>
			  <div class="controls">
			    	<input id="supplier-select" type="hidden" class="input-xlarge span12" name="order.supplier.id" value="${order.supplier.id}"/>
				    <input type="hidden" id="order.supplier.supplierName" name="order.supplier.supplierName" value="${order.supplier.supplierName}" />
			  </div>
			</div>
		  </div>
		  <div class="span2">
				<a href="<c:url value='/suppliers/supplier'/>" target="_blank" tabindex="-1" class="btn btn-small"><i class="halflings-icon file"></i>New Supplier</a>
			</div>
		</div>
		<div class="row-fluid">
    	  <div class="span5">
			<div class="control-group">
			  <label class="control-label col-xs2">Transaction Term:</label>
			  <div class="controls">
			    <select id="order.transactionTerm.id" name="order.transactionTerm.id" class="input-xlarge span12" >   
	              <option value="-1"> Transaction Terms...</option>  
	              <c:forEach var="transactionTerm" items="${transactionTermList}">  
	                  <option value="${transactionTerm.id}" <c:if test="${transactionTerm.id == order.transactionTerm.id}">selected="true"</c:if>> 
	                  	${transactionTerm.description} 
	                  </option>  
	              </c:forEach> 
	          </select>
			  </div>
			</div>
		   </div>
		    <div class="span2">
		    </div>
		</div>
		<div class="row-fluid">
    	  <div class="span5">
				<div class="control-group">
				  <label class="control-label col-xs2">Payment Term:</label>
				  <div class="controls">
				    <select id="order.negotiationTerm.id" name="order.negotiationTerm.id" class="input-xlarge span12" >   
		              <option value="-1"> Negotiation Terms...</option>  
		              <c:forEach var="negotiationTerm" items="${negotiationTermList}">  
		                  <option value="${negotiationTerm.id}" <c:if test="${negotiationTerm.id == order.negotiationTerm.id}">selected="true"</c:if>> 
		                  	${negotiationTerm.description} 
		                  </option>  
		              </c:forEach> 
		          </select>
				  </div>
				</div>		
		  </div>
		  <div class="span2">
		 	 <a href="<c:url value='/negotiationTerms/term'/>" target="_blank" tabindex="-1" class="btn btn-small"><i class="halflings-icon file"></i>New Payment Term</a>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Total Value:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span3" data-behaviour="valor" value="<fmt:formatNumber value='${order.totalValue}' pattern='#,##0.00'/>" tabindex="-1" readonly="readonly" id="total-sell-price"  />
		  </div>
		</div>
		
		<div class="control-group">
		  <label class="control-label col-xs2">Additional Info:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="order.additionalInfo" id="order.additionalInfo">${order.additionalInfo}</textarea> 
		  </div>
		</div>
	  </fieldset>
    </div>
    
    <hr class="hideInIE8"/>
    
    
    <div class="tabbable"> <!-- Only required for left/right tabs -->
	  <ul class="nav nav-tabs">
	    <li class="active"><a href="#tab1" data-toggle="tab">Itens</a></li>
	    <li><a href="#tab2" data-toggle="tab">Shipment Details</a></li>
	    <li><a href="#tab3" data-toggle="tab">Status Details</a></li>
	  </ul>
	  <div class="tab-content">
	    <div class="tab-pane active" id="tab1">
	      <div class="box-header" data-original-title>
				<h2><i class="halflings-icon edit"></i><span class="break"></span>Products</h2>
			</div>
			<div class="box-content">
			  <table class="table table-striped table-bordered" id="table-item">
				<thead>
				  <tr>
					<td width="20%" >Product</td>
					<td >Quantity</td>
					<td width="15%" >Units</td>
					<td >Price</td>
					<td >Total Products</td>
					<td >Commision</td>
					<td >Total</td>
					<td ></td>
				  </tr>
				</thead>
				<tbody id="tb-itens">
				   <c:forEach var="item" items="${order.itens}" varStatus="idx">
				 	<tr id='item-${item.id}'>
				 	  <td><a  href='<c:url value='/orders/edit/item/${item.id}'/>' id='a-product-${idx.index}' >${item.completeDescription} </a>
				 	    </td>
				 	  <td><a  href='<c:url value='/orders/edit/item/${item.id}'/>'  id='a-quantity-${idx.index}' >${item.quantity}</a>
				 	  </td>
				 	  <td><a  href='<c:url value='/orders/edit/item/${item.id}'/>' id='a-unit-${idx.index}' >${item.unitOfMeasure.description}</a>
				 	  </td>
				 	  <td><a  href='<c:url value='/orders/edit/item/${item.id}'/>' id='a-unit-price-${idx.index}' ><fmt:formatNumber value='${item.unitPrice}' pattern='#,##0.00'/></a>
				 	  </td>
				 	  <td><a  href='<c:url value='/orders/edit/item/${item.id}'/>' id='a-total-value-${idx.index}' ><fmt:formatNumber value='${item.totalProducts}' pattern='#,##0.00'/></a>
				 	  </td>
				 	  <td><a  href='<c:url value='/orders/edit/item/${item.id}'/>' id='a-commision-${idx.index}' ><fmt:formatNumber value='${item.commision}' pattern='#,##0.00'/></a>
				 	  </td>
				 	  <td><a  href='<c:url value='/orders/edit/item/${item.id}'/>' id='a-commision-${idx.index}' ><fmt:formatNumber value='${item.totalWithCommision}' pattern='#,##0.00'/></a>
				 	  </td>
				 	  <td><c:if test='${order == null || order.toBeFilledIn}'>
				 	        <a href='' title='Delete' class='btn btn-danger' onclick='removeItem(${item.id}); return false;'><i class='halflings-icon trash halflings-icon'></i></a>
				 	       </c:if>
				 	   </td>
				 	</tr>
				 </c:forEach>
				</tbody>
			 </table>
			 <div class="row-fluid">
			   <c:if test="${order.toBeFilledIn}">
				   <a href="<c:url value='/orders/item/${order.id}'/>" id="new-item" class="btn btn-primary">
				     New Item
				   </a>
			   </c:if>
			 </div>
			</div>
	    </div>
	    <div class="tab-pane" id="tab2">
	      <div class="box-header" data-original-title>
				<h2><i class="halflings-icon edit"></i><span class="break"></span>Shipment Details</h2>
			</div>
			<div class="box-content">
			   <fieldset>
			    <div class="row-fluid">
    	  			<div class="span5">
					    <div class="control-group">
						  <label class="control-label col-xs2">Shipment Term:</label>
						  <div class="controls">
						    <select id="order.shipmentTerm.id" name="order.shipmentTerm.id" class="input-xlarge span6" >   
				              <option value="-1"> Shipment Terms...</option>  
				              <c:forEach var="shipmentTerm" items="${shipmentTermList}">  
				                  <option value="${shipmentTerm.id}" <c:if test="${shipmentTerm.id == order.shipmentTerm.id}">selected="true"</c:if>> 
				                  	${shipmentTerm.description} 
				                  </option>  
				              </c:forEach> 
				          </select>
						  </div>
						  
						</div>
					 </div>
					 <div class="span2">
					 	<a href="<c:url value='/shipmentterms/term'/>" target="_blank" tabindex="-1" class="btn btn-small"><i class="halflings-icon file"></i>New Shipment Term</a>
					 </div>
				</div>
				<div class="control-group">
				  <label class="control-label col-xs2">Invoice Number:</label>
				  <div class="controls">
				    <input class="input-xlarge span6" name="order.invoiceNumber" value="${order.invoiceNumber}"/>
				  </div>
				</div>
				<div class="control-group">
				  <label class="control-label col-xs2">Terms:</label>
				  <div class="controls">
				    <input class="input-xlarge span6" name="order.terms" value="${order.terms}"/>
				  </div>
				</div>
				<div class="control-group">
				  <label class="control-label col-xs2">Landing Port:</label>
				  <div class="controls">
				    <input class="input-xlarge span6" name="order.landingPort" value="${order.landingPort}"/>
				  </div>
				</div>
				<div class="control-group">
				  <label class="control-label col-xs2">Destination Port:</label>
				  <div class="controls">
				    <input class="input-xlarge span6" name="order.destinationPort" value="${order.destinationPort}"/>
				  </div>
				</div>
				<div class="control-group">
				  <label class="control-label col-xs2">Insurance:</label>
				  <div class="controls">
				    <input class="input-xlarge span6" name="order.insurance" value="${order.insurance}"/>
				  </div>
				</div>
				<div class="control-group">
				  <label class="control-label col-xs2">Shipment:</label>
				  <div class="controls">
				    <input class="input-xlarge span6" name="order.shipment" value="${order.shipment}"/>
				  </div>
				</div>
				
			  </fieldset>
			</div>
	    </div>
	    <div class="tab-pane" id="tab3">
	      <div class="box-header" data-original-title>
				<h2><i class="halflings-icon edit"></i><span class="break"></span>Products</h2>
			</div>
			<div class="box-content">
			  <table class="table table-striped table-bordered" id="table-item">
				<thead>
				  <tr>
					<td width="20%" >Status</td>
					<td >Date</td>
				  </tr>
				</thead>
				<tbody id="tb-itens">
				 	<tr>
				 	  <td>Order Date</td>
				 	  <td><fmt:formatDate value='${order.orderDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	
				 	<tr>
				 	  <td>Supplier Proforma</td>
				 	  <td><fmt:formatDate value='${order.supplierProformaDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	
				 	<tr>
				 	  <td>Proforma Confirmation</td>
				 	  <td><fmt:formatDate value='${order.proformaConfirmationDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	
				 	<tr>
				 	  <td>Artwork Confirmation</td>
				 	  <td><fmt:formatDate value='${order.artworkConfirmationDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	
				 	<tr>
				 	  <td>Advanced Payment</td>
				 	  <td><fmt:formatDate value='${order.advancedPaymentDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	<tr>
				 	  <td>Forward Details</td>
				 	  <td><fmt:formatDate value='${order.lastForwardDetailDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	<tr>
				 	  <td>Shipment Date</td>
				 	  <td><fmt:formatDate value='${order.shipDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	<tr>
				 	  <td>Copy of Documents</td>
				 	  <td><fmt:formatDate value='${order.copyDocumentDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	<tr>
				 	  <td>Balance Payment Date</td>
				 	  <td><c:if test="${order.paid}"><fmt:formatDate value='${order.lastPaymentDate}' pattern='MM/dd/yyyy'/></c:if></td>
				 	</tr>
				 	<tr>
				 	  <td>Original Document</td>
				 	  <td><fmt:formatDate value='${order.originalDocumentDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				 	<tr>
				 	  <td>Commision Payment</td>
				 	  <td><fmt:formatDate value='${order.commisionPaymentDate}' pattern='MM/dd/yyyy'/></td>
				 	</tr>
				</tbody>
			 </table>
			 <div class="row-fluid">
			   <c:if test="${order.toBeFilledIn}">
				   <a href="<c:url value='/orders/item/${order.id}'/>" id="new-item" class="btn btn-primary">
				     New Item
				   </a>
			   </c:if>
			 </div>
			</div>
	    </div>
	  </div>
	</div>
    
	
    
    <hr class="hideInIE8"/>
	
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
		<button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button>
	  <c:choose>
	    <c:when test="${order.waitingSupplierProforma}">
	       <a href="<c:url value='/supplierProforma/confirm/${order.id}'/>" class="btn btn-primary">Supplier Proforma Arrived</a>
	    </c:when>
	    <c:when test="${order.waitingProformaConfirmation}">
	       <a href="<c:url value='/proformaConfirmation/confirm/${order.id}'/>" class="btn btn-primary">Proforma Confirmed</a>
	    </c:when>
	     <c:when test="${order.waitingArtworkConfirmation}">
	       <a href="<c:url value='/artworkConfirmation/confirm/${order.id}'/>" class="btn btn-primary">Artwork Confirmed</a>
	    </c:when>
	    <c:when test="${order.waitingShipment}">
	       <a href="<c:url value='/shipment/${order.id}'/>" class="btn btn-primary">Shipping Information</a>
	    </c:when>
	    <c:when test="${order.waitingForDocumentCopy}">
	       <a href="<c:url value='/sendDocumentCopy/confirm/${order.id}'/>" class="btn btn-primary">Document Copy Sent</a>
	    </c:when>
	    <c:when test="${order.waitingForOriginalDocument}">
	       <a href="<c:url value='/sendOriginalDocument/confirm/${order.id}'/>" class="btn btn-primary">Original Document Sent</a>
	    </c:when>
	    <c:when test="${order.waitingForComissionPayment}">
	       <a href="<c:url value='/conclusion/confirm/${order.id}'/>" class="btn btn-primary">Finished</a>
	    </c:when>
      </c:choose>
      <a href="<c:url value='/timeline'/>" class="btn btn-primary">Back to Timeline</a>
	  <c:if test="${order.id != null}">
		  <a href="<c:url value='/orders/print/purchase/${order.id}'/>" class="btn btn-success">Print Purchase Order</a>
		  <a href="<c:url value='/orders/print/proforma/${order.id}'/>" class="btn btn-success">Print Proforma Invoice</a>
		  <a href="<c:url value='/orders/print/comercial/${order.id}'/>" class="btn btn-success">Print Comercial Invoice</a>
  	  	  <a href="<c:url value='/orders/print/packing/${order.id}'/>" class="btn btn-success">Print Packing List</a>
		  <a href="<c:url value='/complains/order/${order.id}'/>" class="btn btn-danger">Register Complain</a>
	  </c:if>
	</div>
        
</div>
</form>

<content tag="local_script">
<script src="<c:url value='/resources/js/plugins/select2/select2.js'/>"></script>
<script src="<c:url value='/resources/js/plugins/select2/select2_locale_pt-BR.js'/>"></script>
<script src="<c:url value='/resources/js/borioselect2.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
	
	<c:if test="${order.id == null}">
	    $('#order\\.orderDate').focus();
	</c:if>
	
	 $("#customer-select").borioSelect({
		selecao : {param1 :"name"},
		url: "<c:url value='/customers/searchByName.json'/>",
		extras :{code: "code"},
		editar:{id: '${order.customer.id}',texto:'${order.customer.name}'}
	}).on("change", function(e) { var data = $("#customer-select").select2("data"); setCostumer(data); });
	 
	 $("#supplier-select").borioSelect({
			selecao : {param1 :"supplierName"},
			url: "<c:url value='/suppliers/searchByName.json'/>",
			editar:{id: '${order.supplier.id}',texto:'${order.supplier.supplierName}'}
		}).on("change", function(e) { var data = $("#supplier-select").select2("data"); setSupplier(data); });
	    
	
});
	

function setCostumer(customer){
	$('#order\\.customer\\.name').val(customer.text);
	$('#order\\.customer\\.code').val(customer.code);
}

function setSupplier(supplier){
	$('#order\\.supplier\\.supplierName').val(supplier.text);
}


function removeItem(id){
	
   if (confirm('Are you sure?')){
	   $.ajax({
		   url: '/letsmed/orders/delete/item/'+id,
		   context: document.body
		 }).done(function() {
			 $('#item-'+id).fadeOut('slow');
			 $('#item-'+id).remove();
		 });   
   }
}
</script>
</content>
</body>
</html>