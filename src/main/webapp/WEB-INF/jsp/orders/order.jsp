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
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Order N. ${order.id}</h2>
</div>
<form class="form-horizontal" action='<c:url value="/orders/save"/>' method="post">
<input type="hidden" name="order.id" value="${order.id}" />
<input type="hidden" name="order.confirmationDate" value="${order.confirmationDate}" />
<input type="hidden" name="order.proformaConfirmationDate" value="${order.proformaConfirmationDate}" />
<input type="hidden" name="order.artworkConfirmationDate" value="${order.artworkConfirmationDate}" />
<input type="hidden" name="order.productionStartDate" value="${order.productionStartDate}" />
<input type="hidden" name="order.shipDate" value="${order.shipDate}" />
<input type="hidden" name="order.copyDocumentDate" value="${order.copyDocumentDate}" />
<input type="hidden" name="order.originalDocumentDate" value="${order.originalDocumentDate}" />
<div class="box-content">
	  
	 <div class="box-header" data-original-title>
		<h2><i class="halflings-icon edit"></i><span class="break"></span>Order Data</h2>
	</div>
	<div class="box-content">
	  <fieldset>
		<div class="control-group">
		   <label class="control-label col-xs2">Order Date:</label>			 
	       <div class="controls">
			  <input type="text" autocomplete="off" class="input-xlarge span2" data-behaviour="datepicker" name="order.orderDate" id="order.orderDate" value="<fmt:formatDate value='${order.orderDate}' pattern='MM/dd/yyyy'/>" />
		   </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Order Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="order.orderNumber" id="order.orderNumber" value="${order.orderNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Customer:</label>
		  <div class="controls">
		    <select id="order.customer.id" name="order.customer.id" class="input-xlarge span6" >   
              <option value="-1"> Customers...</option>  
              <c:forEach var="customer" items="${customerList}">  
                  <option value="${customer.id}" <c:if test="${customer.id == order.customer.id}">selected="true"</c:if>> 
                  	${customer.name} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Supplier:</label>
		  <div class="controls">
		    <select id="order.supplier.id" name="order.supplier.id" class="input-xlarge span6" >   
              <option value="-1"> Suppliers...</option>  
              <c:forEach var="supplier" items="${supplierList}">  
                  <option value="${supplier.id}" <c:if test="${supplier.id == order.supplier.id}">selected="true"</c:if>> 
                  	${supplier.supplierName} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Transaction Term:</label>
		  <div class="controls">
		    <select id="order.transactionTerm.id" name="order.transactionTerm.id" class="input-xlarge span6" >   
              <option value="-1"> Transaction Terms...</option>  
              <c:forEach var="transactionTerm" items="${transactionTermList}">  
                  <option value="${transactionTerm.id}" <c:if test="${transactionTerm.id == order.transactionTerm.id}">selected="true"</c:if>> 
                  	${transactionTerm.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Negotiation Term:</label>
		  <div class="controls">
		    <select id="order.negotiationTerm.id" name="order.negotiationTerm.id" class="input-xlarge span6" >   
              <option value="-1"> Negotiation Terms...</option>  
              <c:forEach var="negotiationTerm" items="${negotiationTermList}">  
                  <option value="${negotiationTerm.id}" <c:if test="${negotiationTerm.id == order.negotiationTerm.id}">selected="true"</c:if>> 
                  	${negotiationTerm.description} 
                  </option>  
              </c:forEach> 
          </select>
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
	<div class="box-header" data-original-title>
		<h2><i class="halflings-icon edit"></i><span class="break"></span>Shipment Details</h2>
	</div>
	<div class="box-content">
	   <fieldset>
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
		<div class="control-group">
		  <label class="control-label col-xs2">Delivery Date:</label>
		  <div class="controls">
		     <input type="text" autocomplete="off" class="input-xlarge span2" data-behaviour="datepicker" name="order.deliveryDate" id="order.deliveryDate" value="<fmt:formatDate value='${order.deliveryDate}' pattern='MM/dd/yyyy'/>" />
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
    
    <hr class="hideInIE8"/>
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
			<td >Total</td>
			<td >Commision</td>
			<td ></td>
		  </tr>
		</thead>
		<tbody id="tb-itens">
		   <c:forEach var="item" items="${order.itens}" varStatus="idx">
		 	<tr id='item-${item.id}'>
		 	  <td><a data-toggle='modal' href='#myModal' id='a-product-${idx.index}' onclick='editItem(${idx.index});'>${item.product.description}</a>
		          <input type='hidden' id='product-${idx.index}' name='order.itens[${idx.index}].product.id' value='${item.product.id}' />
		 	  	  <input type='hidden' id='item-id-${idx.index}' name='order.itens[${idx.index}].id' value='${item.id}' />
		 	  	  <input type='hidden' id='order-id-${idx.index}' name='order.itens[${idx.index}].order.id' value='${order.id}' />
		 	  	  <input type='hidden' id='package-quantity-${idx.index}' name='order.itens[${idx.index}].packageQuantity' value='${item.packageQuantity}' />
		 	  	  <input type='hidden' id='gross-weight-${idx.index}' name='order.itens[${idx.index}].grossWeight' value='${item.grossWeight}' />
		 	  	  <input type='hidden' id='net-weight-${idx.index}' name='order.itens[${idx.index}].netWeight' value='${item.netWeight}' />
		 	  	  <input type='hidden' id='volume-${idx.index}' name='order.itens[${idx.index}].volume' value='${item.volume}' />
		 	  	  <input type='hidden' id='buy-price-${idx.index}' name='order.itens[${idx.index}].buyPrice' value='${item.buyPrice}' />
		 	  	  <input type='hidden' id='additional-description-${idx.index}' name='order.itens[${idx.index}].additionalDescription' value='${item.additionalDescription}' />
		 	      <input type='hidden' id='additional-info-${idx.index}' name='order.itens[${idx.index}].additionalInfo' value='${item.additionalInfo}' />
		 	    </td>
		 	  <td><a data-toggle='modal' href='#myModal' id='a-quantity-${idx.index}' onclick='editItem(${idx.index});'>${item.quantity}</a>
		 	     <input type='hidden' id='quantity-${idx.index}' name='order.itens[${idx.index}].quantity' value='${item.quantity}'  />
		 	  </td>
		 	  <td><a data-toggle='modal' href='#myModal' id='a-unit-${idx.index}' onclick='editItem(${idx.index});'>${item.unitOfMeasure.description}</a>
		 	  	 <input type='hidden' id='unit-${idx.index}' name='order.itens[${idx.index}].unitOfMeasure.id' value='${item.unitOfMeasure.id}'  />
		 	  </td>
		 	  <td><a data-toggle='modal' href='#myModal' id='a-unit-price-${idx.index}' onclick='editItem(${idx.index});'><fmt:formatNumber value='${item.unitPrice}' pattern='#,##0.00'/></a>
		 	      <input type='hidden' id='unit-price-${idx.index}' name='order.itens[${idx.index}].unitPrice' value='<fmt:formatNumber value='${item.unitPrice}' pattern='#,##0.00'/>'/>
		 	  </td>
		 	  <td><a data-toggle='modal' href='#myModal' id='a-total-value-${idx.index}' onclick='editItem(${idx.index});'><fmt:formatNumber value='${item.totalValue}' pattern='#,##0.00'/></a>
		 	  	<input type='hidden' id='total-value-${idx.index}' name='order.itens[${idx.index}].totalValue' value='<fmt:formatNumber value='${item.totalValue}' pattern='#,##0.00'/>'  />
		 	  </td>
		 	  <td><a data-toggle='modal' href='#myModal' id='a-commision-${idx.index}' onclick='editItem(${idx.index});'><fmt:formatNumber value='${item.commision}' pattern='#,##0.00'/></a>
		 	  	<input type='hidden' id='commision-${idx.index}' name='order.itens[${idx.index}].commision' value='<fmt:formatNumber value='${item.commision}' pattern='#,##0.00'/>'  />
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
	   <c:if test="${order.id == null || order.toBeFilledIn}">
		   <a data-toggle='modal' href='#myModal' id="new-item" onclick="newItem();" class="btn btn-primary">
		     New Item
		   </a>
	   </c:if>
	 </div>
	</div>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <c:choose>
	    <c:when test="${order.id == null || order.toBeFilledIn}">
		  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button>
	    </c:when>
	    <c:when test="${order.waitingSupplierProforma}">
	       <a href="<c:url value='/supplierProforma/confirm/${order.id}'/>" class="btn btn-primary">Supplier Proforma Arrived</a>
	    </c:when>
	    <c:when test="${order.waitingProformaConfirmation}">
	       <a href="<c:url value='/proformaConfirmation/confirm/${order.id}'/>" class="btn btn-primary">Proforma Confirmed</a>
	    </c:when>
	    <c:when test="${order.waitingProductionStart}">
	       <a href="<c:url value='/productionStartConfirmation/confirm/${order.id}'/>" class="btn btn-primary">Production Started</a>
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
      <a href="<c:url value='/orders'/>" class="btn btn-primary">Back to List</a>
      <a href="<c:url value='/timeline'/>" class="btn btn-primary">Back to Timeline</a>
      <c:if test="${order.toBeFilledIn}">
		 <a href="<c:url value='/orders/confirm/${order.id}'/>" class="btn btn-primary">Filled In Confirmation</a>
	  </c:if>
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

<content tag="local_modal">
<div class="modal hide modal-up" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Product</h3>
			</div>
			<div class="modal-body modal-body-item">
				<%@include file="item.jsp" %>
			</div>
			<div class="modal-footer">
			    <a href="#" class="btn btn-primary" data-dismiss="modal" onclick="addItem();">Add Item</a>
				<a href="#" class="btn" data-dismiss="modal">Close</a>
			</div>
		</div>
</content>

<content tag="local_script">
<script type="text/javascript">
$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
	
	<c:if test="${order.id == null}">
	    $('#order\\.orderDate').focus();
	</c:if>
	
	$('#order\\.orderNumber').setMask({mask: '99999999', autoTab: false});
	
	$('#myModal').on('shown', function (e) {
	    setTimeout(function(){
	    	$('#product-desc').focus();
	    }, 300);                        
	});
});
	
function newItem(){
	$("#product").focus();
}	

function addItem() {
	
	index = $('#item-index').val();
	
	if (!index){
		index = $('#table-item >tbody >tr').length;
		
		var	cHtml = " <tr id='row-"+ index + "'> "+
					"	<td><a data-toggle='modal' href='#myModal' id='a-product-"+index+"' onclick='editItem("+index+");'></a> "+
				    "    <input type='hidden' id='product-"+index+"' name='order.itens["+index+"].product.id' /> "+
					"  	  <input type='hidden' id='item-id-"+index+"' name='order.itens["+index+"].id' /> "+
					"  	  <input type='hidden' id='order-id-"+index+"' name='order.itens["+index+"].order.id'  /> "+
					"  	  <input type='hidden' id='package-quantity-"+index+"' name='order.itens["+index+"].packageQuantity' /> "+
					"  	  <input type='hidden' id='gross-weight-"+index+"' name='order.itens["+index+"].grossWeight' /> "+
					"  	  <input type='hidden' id='net-weight-"+index+"' name='order.itens["+index+"].netWeight' /> "+
					"  	  <input type='hidden' id='volume-"+index+"' name='order.itens["+index+"].volume' /> "+
					"  	  <input type='hidden' id='buy-price-"+index+"' name='order.itens["+index+"].buyPrice' /> "+
					"  	  <input type='hidden' id='additional-description-"+index+"' name='order.itens["+index+"].additionalDescription' /> "+
					"     <input type='hidden' id='additional-info-"+index+"' name='order.itens["+index+"].additionalInfo' /> "+
					"    </td> "+
					"  <td><a data-toggle='modal' href='#myModal' id='a-quantity-"+index+"' onclick='editItem("+index+");'></a> "+
					"     <input type='hidden' id='quantity-"+index+"' name='order.itens["+index+"].quantity' /> "+
					"  </td> "+
					"  <td><a data-toggle='modal' href='#myModal' id='a-unit-"+index+"' onclick='editItem("+index+");'></a> "+
					"  	 <input type='hidden' id='unit-"+index+"' name='order.itens["+index+"].unitOfMeasure.id' /> "+
					"  </td> "+
					"  <td><a data-toggle='modal' href='#myModal' id='a-unit-price-"+index+"' onclick='editItem("+index+");'></a> "+
					"      <input type='hidden' id='unit-price-"+index+"' name='order.itens["+index+"].unitPrice' /> "+
					"  </td> "+
					"  <td><a data-toggle='modal' href='#myModal' id='a-total-value-"+index+"' onclick='editItem("+index+");'></a> "+
					"  	<input type='hidden' id='total-value-"+index+"' name='order.itens["+index+"].totalValue' /> "+
					"  </td> "+
					"  <td><a data-toggle='modal' href='#myModal' id='a-commision-"+index+"' onclick='editItem("+index+");'></a> "+
			 	  	"  <input type='hidden' id='commision-"+index+"' name='order.itens["+index+"].commision'   /> "+
			 	    " </td> "+
				 	" <td> <a id='delete-"+index+"' href='javascript:' class='btn btn-danger'><i class='halflings-icon trash halflings-icon'></i></a></td>"+
				 	" </tr> ";
	
			$("#tb-itens").append(cHtml);
			$("#delete-"+index).on("click",{row : "row-" + index,item: "item-id-"+index},
					function (event){
				        //deleta o item da tela, ainda nao foi salvo no banco
						$("#"+event.data.row).fadeOut('slow');
						$("#"+event.data.row).remove();
										
						return false;
			});
	}
	
	    $("#a-product-"+index).html($("#product-desc").val());
	    $("#product-"+index).val($("#product-id").val());
	    $("#item-id-"+index).val($("#item-id").val());
	    $("#order-id-"+index).val($("#order-id").val());
	    $("#package-quantity-"+index).val($("#package-quantity").val());
	    $("#gross-weight-"+index).val($("#gw").val());
	    $("#net-weight-"+index).val($("#nw").val());
	    $("#volume-"+index).val($("#volume").val());
	    $("#buy-price-"+index).val($("#buy-price").val());
	    $("#additional-description-"+index).val($("#additional-description").val());
	    $("#additional-info-"+index).val($("#additional-info").val());
		
	    $("#a-quantity-"+index).html($("#quantity").val());
	    $("#quantity-"+index).val($("#quantity").val());
	    
	    $("#a-unit-"+index).html($("#unit option:selected").text());
	    $("#unit-"+index).val($("#unit").val());
	    
	    $("#a-unit-price-"+index).html($("#sell-price").val());
	    $("#unit-price-"+index).val($("#sell-price").val());
	    
	    $("#a-commision-"+index).html($("#commision").val());
	    $("#commision-"+index).val($("#commision").val());
		
	    clearInputs();
	    calculateTotalItem(index);
}

function clearInputs(){
	
	$("#item-index").val("");
	$("#item-id").val("");
    $("#order-id").val("");
    $("#package-quantity").val("");
    $("#gw").val("");
    $("#nw").val("");
    $("#volume").val("");
    $("#buy-price").val("");
    $("#additional-description").val("");
    $("#product-id").val("");
    $("#product-desc").val("");
    $("#additional-info").val("");
    $("#quantity").val("");
    $("#unit").val("-1");
    $("#sell-price").val("");
    $("#total-buy-price").val("");
    $("#total-sell-price").val("");
    $("#commision").val("");
}

function editItem(index){
    $("#item-index").val(index);
    $("#product-id").val($("#product-"+index).val());
    $("#product-desc").val($("#a-product-"+index).html());
	$("#item-id").val($("#item-id-"+index).val());
    $("#order-id").val($("#order-id-"+index).val());
    $("#package-quantity").val($("#package-quantity-"+index).val());
    $("#gw").val($("#gross-weight-"+index).val());
    $("#nw").val($("#net-weight-"+index).val());
    $("#volume").val($("#volume-"+index).val());
    $("#buy-price").val($("#buy-price-"+index).val());
    $("#additional-description").val($("#additional-description-"+index).val());
    $("#additional-info").val($("#additional-info-"+index).val());
    $("#quantity").val($("#quantity-"+index).val());
    $("#unit").val($("#unit-"+index).val());
    $("#sell-price").val($("#unit-price-"+index).val());
    calculateTotalBuy();
    calculateTotalSell();
    $("#additional-description").focus();
}
	
function calculateTotalItem(index) {
	var quantity  =  parseFloat($("#quantity-"+index).val());
	var price = parseFloat($("#unit-price-"+index).val());
	var total = price * quantity;
	$("#a-total-value-"+index).html(total);
	$("#total-value-"+index).val(total);
}

function calculateTotalBuy() {
	var quantity  =  parseFloat($("#quantity").val());
	var price = parseFloat($("#buy-price").val());
	var total = price * quantity;
	
	if(total){
		$("#total-buy-price").val(total);
	}
}

function calculateTotalSell() {
	var quantity  =  parseFloat($("#quantity").val());
	var price = parseFloat($("#sell-price").val());
	var total = price * quantity;
	
	if(total){
		$("#total-sell-price").val(total);
	}
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

function autoComplete(obj){
	var result = [];
	texto = $(obj).val();
	if (texto.length != 1)
		return false;
	$.getJSON('/letsmed/products/searchByDescription.json', {
		term : texto
		}, function(json_result) {
			for (i = 0; i < json_result.length; i++){
				result[i] = json_result[i].description;
			}
			var options = {updater: function(item){
				             setProduct(item);
				           	 return item;
				           }};
			$(obj).typeahead(options);
			var autocomplete = $(obj).typeahead();
			autocomplete.data('typeahead').source = result;
		}
	);
	
}

function setProduct(item){
	$.getJSON('/letsmed/products/find.json', {
		description : item
		}, function(json_result) {
			alert(json_result.description);
			$('#product-id').val(json_result.id);
			$('#product-desc').val(json_result.description);
			alert($('#product-desc').val());
		}
	);
}
</script>
</content>
</body>
</html>