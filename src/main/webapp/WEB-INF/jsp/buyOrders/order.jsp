<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Buy Orders</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon edit"></i><span class="break"></span>Buy Orders</h2>
</div>
<form class="form-horizontal" action='<c:url value="/buyOrders/save"/>' method="post">
<input type="hidden" name="buyOrder.id" value="${buyOrder.id}" />
<input type="hidden" name="buyOrder.order.id" value="${buyOrder.order.id}" />
<div class="box-content">
	
	  <fieldset>
		<div class="control-group">
		   <label class="control-label col-xs2">Order Date:</label>			 
	       <div class="controls">
			  <input type="text" autocomplete="off" class="input-xlarge span2" data-behaviour="datepicker" name="buyOrder.orderDate" id="buyOrder.orderDate" value="<fmt:formatDate value='${buyOrder.orderDate}' pattern='MM/dd/yyyy'/>" />
		   </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Order Number:</label>
		  <div class="controls">
		    <input type="text" class="input-xlarge span6" name="buyOrder.orderNumber" id="buyOrder.orderNumber" value="${buyOrder.orderNumber}" />
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Supplier:</label>
		  <div class="controls">
		    <select id="buyOrder.supplier.id" name="buyOrder.supplier.id" class="input-xlarge span6" >   
              <option value="-1"> Suppliers...</option>  
              <c:forEach var="supplier" items="${supplierList}">  
                  <option value="${supplier.id}" <c:if test="${supplier.id == buyOrder.supplier.id}">selected="true"</c:if>> 
                  	${supplier.supplierName} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Payment Term:</label>
		  <div class="controls">
		    <select id="buyOrder.paymentTerm.id" name="buyOrder.paymentTerm.id" class="input-xlarge span6" >   
              <option value="-1"> Payment Terms...</option>  
              <c:forEach var="paymentTerm" items="${paymentTermList}">  
                  <option value="${paymentTerm.id}" <c:if test="${paymentTerm.id == buyOrder.paymentTerm.id}">selected="true"</c:if>> 
                  	${paymentTerm.description} 
                  </option>  
              </c:forEach> 
          </select>
		  </div>
		</div>
		<div class="control-group">
		  <label class="control-label col-xs2">Additional Info:</label>
		  <div class="controls">
		    <textarea class="input-xlarge span6" name="buyOrder.additionalInfo" id="buyOrder.additionalInfo">${buyOrder.additionalInfo}</textarea> 
		  </div>
		</div>
	  </fieldset>
	  <table class="table table-striped table-bordered" id="table-receita">
		<thead>
		  <tr>
			<td width="20%">Product</td>
			<td width="10%">Quantity</td>
			<td width="10%">Unit Price</td>
			<td width="10%">Total Value</td>
			<td width="10%">Quantity CTN</td>
			<td width="10%">G.W KGs</td>
			<td width="10%">N.W KGs</td>
			<td width="10%">Volume (CBM)</td>
		  </tr>
		</thead>
		<tbody id="tb-itens">
		   <c:forEach var="item" items="${buyOrder.itens}" varStatus="idx">
		 	<tr id="item-${item.id}">
		 	  <td>
		 	  	  <input type="hidden" name="buyOrder.itens[${idx.index}].id" value="${item.id}" />
		 	  	  <input type="hidden" name="buyOrder.itens[${idx.index}].buyOrder.id" value="${buyOrder.id}" />
		 	     <select id="buyOrder.itens[${idx.index}].product.id" name="buyOrder.itens[${idx.index}].product.id" class="input-xlarge span12" >   
		              <option value="-1"> Products...</option>  
		              <c:forEach var="product" items="${productList}">  
		                  <option value="${product.id}" <c:if test="${product.id == item.product.id}">selected="true"</c:if>> 
		                  	${product.description} 
		                  </option>  
		              </c:forEach>  
		          </select>
		 	    </td>
		 	  <td><input type="text" class="input-xlarge span12" name="buyOrder.itens[${idx.index}].quantity" value="${item.quantity}"  /></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" name="buyOrder.itens[${idx.index}].unitPrice" value="${item.unitPrice}"/></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" readonly="readonly" name="buyOrder.itens[${idx.index}].totalValue" value="<fmt:formatNumber value='${item.totalValue}' pattern='#,##0.00'/>"  /></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" name="buyOrder.itens[${idx.index}].packageQuantity" value="${item.packageQuantity}"/></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" name="buyOrder.itens[${idx.index}].grossWeight" value="${item.grossWeight}"/></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" name="buyOrder.itens[${idx.index}].netWeight" value="${item.netWeight}"/></td>
		 	  <td><input type="text" class="input-xlarge span12" data-behaviour="valor" name="buyOrder.itens[${idx.index}].volume" value="${item.volume}"/></td>
		 	  <td><a href="" title="Delete" class="btn btn-danger" onclick="removeItem(${item.id}); return false;"><i class="halflings-icon trash halflings-icon"></i></a></td>
		 	</tr>
		 </c:forEach>
		</tbody>
		<tbody id="tb-movimentos">
		</tbody>
	 </table>
	 <div class="row-fluid">
	   <button id="new-item" type="button" class="btn btn-primary">
	     New Item
	   </button>
	 </div>
    <hr class="hideInIE8"/>
   
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Confirm</button>
      <a href="<c:url value='/buyOrders'/>" class="btn btn-primary">Cancel</a>
	</div>
        
</div>
</form>
<content tag="local_script">
<script type="text/javascript">
$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
	
	var nContItems = 0;
	$("#new-item").bind('click', function() {
		addItem(nContItems);
        nContItems++;
	});
	
});
	
function addItem(nCountItems) {
		
	var	cHtml = " <tr id='row-"+ nCountItems + "'> "+
			 	" <td> "+
			 	" <select id='buyOrder.itens["+nCountItems+"].product.id' name='buyOrder.itens["+nCountItems+"].product.id' class='input-xlarge span12' >   "+
			    "   <option value='-1'> Products...</option> "+  
			    <c:forEach var="product" items="${productList}">  
			    "   <option value='${product.id}'> "+ 
			    "     ${product.description} "+ 
			    "   </option> "+  
			    </c:forEach>  
			    " </select> "+
			 	"</td> "+
			 	" <td><input type='text' class='input-xlarge span12' name='buyOrder.itens["+nCountItems+"].quantity' id='quantity-"+nCountItems+"' /></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' name='buyOrder.itens["+nCountItems+"].unitPrice' id='price-"+nCountItems+"' /></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' readonly='readonly' name='buyOrder.itens["+nCountItems+"].totalValue' id='total-"+nCountItems+"' /></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' name='buyOrder.itens["+nCountItems+"].packageQuantity' /></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' name='buyOrder.itens["+nCountItems+"].grossWeight' /></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' name='buyOrder.itens["+nCountItems+"].netWeight' /></td> "+
			 	" <td><input type='text' class='input-xlarge span12' data-behaviour='valor' name='buyOrder.itens["+nCountItems+"].volume' /></td> "+
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
		   url: '/letsmed/buyOrders/delete/item/'+id,
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