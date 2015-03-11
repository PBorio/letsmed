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
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Orders</h2>
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
		  <label class="control-label col-xs2">Payment Term:</label>
		  <div class="controls">
		    <select id="order.paymentTerm.id" name="order.paymentTerm.id" class="input-xlarge span6" >   
              <option value="-1"> Payment Terms...</option>  
              <c:forEach var="paymentTerm" items="${paymentTermList}">  
                  <option value="${paymentTerm.id}" <c:if test="${paymentTerm.id == order.paymentTerm.id}">selected="true"</c:if>> 
                  	${paymentTerm.description} 
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
	  <table class="table table-striped table-bordered" id="table-receita">
		<thead>
		  <tr>
			<td width="40%">Product</td>
			<td width="10%">Quantity</td>
			<td width="10%">Unit Price</td>
			<td width="30%">Additional Info</td>
			<td width="10%">Total</td>
		  </tr>
		</thead>
		<tbody id="tb-itens">
		   <c:forEach var="item" items="${order.itens}" varStatus="idx">
		 	<tr id="item-${item.id}">
		 	  <td>
		 	  	  <input type="hidden" name="order.itens[${idx.index}].id" value="${item.id}" />
		 	  	  <input type="hidden" name="order.itens[${idx.index}].order.id" value="${order.id}" />
		 	     <select id="order.itens[${idx.index}].product.id" name="order.itens[${idx.index}].product.id" class="input-xlarge span12" >   
		              <option value="-1"> Products...</option>  
		              <c:forEach var="product" items="${productList}">  
		                  <option value="${product.id}" <c:if test="${product.id == item.product.id}">selected="true"</c:if>> 
		                  	${product.description} 
		                  </option>  
		              </c:forEach>  
		          </select>
		 	    </td>
		 	  <td><input type="text" class="input-xlarge span12" name="order.itens[${idx.index}].quantity" value="${item.quantity}"  /></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" name="order.itens[${idx.index}].unitPrice" value="${item.unitPrice}"/></td>
		 	  <td><textarea class="input-xlarge span12" name="order.itens[${idx.index}].additionalInfo" id="order.itens[${idx.index}].additionalInfo">${item.additionalInfo}</textarea></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" readonly="readonly" name="order.itens[${idx.index}].totalValue" value="<fmt:formatNumber value='${item.totalValue}' pattern='#,##0.00'/>"  /></td>
		 	  <td><c:if test="${order == null || order.toBeFilledIn}">
		 	        <a href="" title="Delete" class="btn btn-danger" onclick="removeItem(${item.id}); return false;"><i class="halflings-icon trash halflings-icon"></i></a>
		 	       </c:if>
		 	   </td>
		 	</tr>
		 </c:forEach>
		</tbody>
		<tbody id="tb-movimentos">
		</tbody>
	 </table>
	 <div class="row-fluid">
	   <c:if test="${order == null || order.toBeFilledIn}">
		   <button id="new-item" type="button" class="btn btn-primary">
		     New Item
		   </button>
	   </c:if>
	 </div>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <c:choose>
	    <c:when test="${order == null || order.toBeFilledIn}">
		  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
	    </c:when>
	    <c:when test="${order.waitingProformaConfirmation}">
	       <a href="<c:url value='/proformaConfirmation/confirm/${order.id}'/>" class="btn btn-primary">Proforma Confirmed</a>
	    </c:when>
	    <c:when test="${order.waitingArtworkConfirmation}">
	       <a href="<c:url value='/artworkConfirmation/confirm/${order.id}'/>" class="btn btn-primary">Artwork Confirmed</a>
	    </c:when>
	    <c:when test="${order.waitingProductionStart}">
	       <a href="<c:url value='/productionStartConfirmation/confirm/${order.id}'/>" class="btn btn-primary">Production Started</a>
	    </c:when>
	    <c:when test="${order.waitingShipment}">
	       <a href="<c:url value='/shipment/confirm/${order.id}'/>" class="btn btn-primary">Shipped</a>
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
      <c:if test="${order.toBeFilledIn}">
		 <a href="<c:url value='/orders/confirm/${order.id}'/>" class="btn btn-primary">Filled In Confirmation</a>
	  </c:if>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
	
	var nContItems = ${order.itensNumber};
	$("#new-item").bind('click', function() {
		addItem(nContItems);
        nContItems++;
	});
	
});
	
function addItem(nCountItems) {
		
	var	cHtml = " <tr id='row-"+ nCountItems + "'> "+
			 	" <td> "+
			 	" <input type='hidden' name='order.itens["+nCountItems+"].id' /> "+
		 	  	" <input type='hidden' name='order.itens["+nCountItems+"].order.id' />"+
			 	" <select id='order.itens["+nCountItems+"].product.id' name='order.itens["+nCountItems+"].product.id' class='input-xlarge span12' >   "+
			    "   <option value='-1'> Products...</option> "+  
			    <c:forEach var="product" items="${productList}">  
			    "   <option value='${product.id}'> "+ 
			    "     ${product.description} "+ 
			    "   </option> "+  
			    </c:forEach>  
			    " </select> "+
			 	"</td> "+
			 	" <td><input type='text' class='input-xlarge span12' name='order.itens["+nCountItems+"].quantity' id='quantity-"+nCountItems+"' /></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' name='order.itens["+nCountItems+"].unitPrice' id='price-"+nCountItems+"' /></td> "+
			 	" <td><textarea class='input-xlarge span12' name='order.itens["+nCountItems+"].additionalInfo' id='order.itens["+nCountItems+"].additionalInfo'></textarea></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' readonly='readonly' name='order.itens["+nCountItems+"].totalValue' id='total-"+nCountItems+"' /></td> "+
			 	" <td> <a id='delete-"+nCountItems+"' href='javascript:' class='btn btn-danger'><i class='halflings-icon trash halflings-icon'></i></a></td>"+
			 	" </tr> ";

		$("#tb-itens").append(cHtml);
		
		$("#delete-"+nCountItems).on("click",{row : "row-" + nCountItems,item: "item-id-"+nCountItems},
				function (event){
			        //deleta o item da tela, ainda nao foi salvo no banco
					$("#"+event.data.row).fadeOut('slow');
					$("#"+event.data.row).remove();
									
					return false;
		});
		
		$("#quantity-" + nCountItems).on("blur", nCountItems,
				function(event) {
					calculateTotalItem(event.data);
				});
		
		$("#price-" + nCountItems).on("blur", nCountItems,
				function(event) {
					calculateTotalItem(event.data);
				});
		
		$("#total-" + nCountItems).setMask('decimal');
}
	
function calculateTotalItem(nLinhaDeItem) {
	var quantity  =  parseFloat($("#quantity-"+nLinhaDeItem ).val());
	var price = parseFloat($("#price-" + nLinhaDeItem).val());
	var total = price * quantity;
	$("#total-" + nLinhaDeItem).val(total);
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