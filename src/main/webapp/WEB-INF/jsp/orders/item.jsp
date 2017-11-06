<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<title>Order Item</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<c:if test="${order.itemDuplicated}">
	<div class="alert alert-warning">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Warning:</strong> Order with duplicated itens
	</div>
</c:if>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Order Items</h2>
</div>
<form class="form-horizontal" action='<c:url value="/orders/item/save"/>' method="post">
<div class="box-content">
	<div class="priority low"><span>Order</span></div>
	<div class="task low">
		<div class="desc">
			<input type="hidden" value="${order.id}" name="order.id" />
			<div class="title">Order: ${order.orderNumber} - ${order.customer.name}</div>
			<div>Value: <fmt:formatNumber value='${order.totalValue}' pattern='#,##0.00'/></div>
			<div>Commission: <fmt:formatNumber value='${order.commisionValue}' pattern='#,##0.00'/></div>
			<div>Balance: <fmt:formatNumber value='${order.balance}' pattern='#,##0.00'/></div>
			<div>Supplier Balance: <fmt:formatNumber value='${order.supplierBalance}' pattern='#,##0.00'/></div>
		</div>
		<div class="time">
			<div class="date"><fmt:formatDate value='${order.orderDate}'  pattern='MM/dd/yyyy' /></div>
		</div>
	</div>
	<hr class="hideInIE8"/>
	<div class="box-header" data-original-title>
        <h2><i class="halflings-icon edit"></i><span class="break"></span>Item</h2>
    </div>
    <div class="box-content">
    <fieldset>
    	<div class="row-fluid">
    	  <div class="span10">
	    	<div class="control-group">
			   <label class="control-label col-xs2">Product:</label>			 
		       <div class="controls">
		       		<input id="product-select" type="hidden" class="input-xlarge span12" name="item.product.id" value="${item.product.id}"/>
				    <input type="hidden" id="item.id" name="item.id" value="${item.id}" />
				    <input type="hidden" id="item.product.description" name="item.product.description" value="${item.product.description}" />
			 	  	<input type="hidden" id="item.order.id" name="item.order.id" value="${item.order.id}" />
			 	  	<input type="hidden" id="item.order.transactionTerm.id" name="item.order.transactionTerm.id" value="${item.order.transactionTerm.id}"/>
			   </div>
			</div>
		  </div>
		  <div class="span2">
				<a href="<c:url value='/products/product'/>" target="_blank" class="btn btn-small"><i class="halflings-icon file"></i>New Product</a>
		  </div>
		</div>
		<div class="control-group">
		   <label class="control-label col-xs2">Additional Description:</label>			 
	       <div class="controls">
			  <textarea rows="1" class="input-xlarge span12" id="item.additionalDescription" name="item.additionalDescription" value="${item.additionalDescription }"></textarea>
		   </div>
		</div>
		<div class="row-fluid">
		  <div class="span3">
			<div class="control-group">
			  <label class="control-label col-xs2">Quantity:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" data-behaviour="inteiro" id="item.quantity" name="item.quantity" 
			    		value="<fmt:formatNumber value='${item.quantity}' pattern='#,###'/>" onchange="calculateTotalSell(); calculateTotalBuy(); return false;" />
			  </div>
			</div>
			
		 </div>
		  <div class="span4">
		    <div class="control-group">
			  <label class="control-label col-xs2">Units:</label>
			  <div class="controls">
			    <select id="item.unitOfMeasure.id" name="item.unitOfMeasure.id" class="input-xlarge span12" >   
			              <option value="-1"> Units...</option>  
			              <c:forEach var="unit" items="${unitsList}">  
			                  <option value="${unit.id}" <c:if test="${unit.id == item.unitOfMeasure.id}">selected="true"</c:if>> 
			                  	${unit.description} 
			                  </option>  
			              </c:forEach>  
			          </select>
			  </div>
			</div>
		  </div>
		   <div class="span2">
		  		<a href="<c:url value='/unitsOfMeasures/unit'/>" target="_blank" class="btn btn-small"><i class="halflings-icon file"></i>New Unit</a>
		  	</div>
		</div>
		<div class="row-fluid">
		   <div class="span3">
			<div class="control-group">
			  <label class="control-label col-xs2">Buy Price:</label>
			  <div class="controls">
			    <input type="number" step="any" class="input-xlarge span12" data-behaviour="valordecimal" id="item.buyPrice" name="item.buyPrice"  value="${item.buyPrice}" onchange="calculateTotalBuy(); return false;" />
			  </div>
			</div>
		  </div>
		 <div class="span3">
			<div class="control-group">
			  <label class="control-label col-xs2">Sell Price:</label>
			  <div class="controls">
			    <input type="number" step="any" class="input-xlarge span12" data-behaviour="valordecimal" id="item.unitPrice" name="item.unitPrice" value="${item.unitPrice}"  onchange="calculateTotalSell(); return false;"/>
			  </div>
			</div>
		</div>
		 <div class="span3">
			<div class="control-group">
			  <label class="control-label col-xs2">Total Buy Price:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" data-behaviour="valor" value="<fmt:formatNumber value='${item.totalBuyValue }' pattern='#,##0.00'/>" tabindex="-1" readonly="readonly" id="total-buy-price" />
			  </div>
			</div>
		</div>
		 <div class="span3">
			<div class="control-group">
			  <label class="control-label col-xs2">Total Sell Price:</label>
			  <div class="controls">
			    <input type="text" class="input-xlarge span12" data-behaviour="valor" value="<fmt:formatNumber value='${item.totalProducts}' pattern='#,##0.00'/>" tabindex="-1" readonly="readonly" id="total-sell-price"  />
			  </div>
			</div>
		 </div>
		</div>
		<div class="row-fluid">
			<c:if test="${not item.order.profit}">
				 <div class="span3">
					<div class="control-group">
					  <label class="control-label col-xs2">Commission %:</label>
					  <div class="controls">
					    <input type="number" step="any" class="input-xlarge span12" data-behaviour="valordecimal" id="item.commision" name="item.commision" value="${item.commision }" onchange="calculateTotalSell(); return false;"  />
					  </div>
					</div>
				</div>
				<div class="span3">
					<div class="control-group">
					  <label class="control-label col-xs2">Commission Value:</label>
					  <div class="controls">
					    <input type="number" step="any" class="input-xlarge span12" data-behaviour="valor" id="item.commisionValue" name="item.commisionValue" value="<fmt:formatNumber value='${item.commisionValue}' pattern='#,##0.00'/>" onchange="calculateCommisionPercentual(); return false;"  />
					  </div>
					</div>
				</div>
			</c:if>
			 <div class="span3">
				<div class="control-group">
				  <label class="control-label col-xs2">Total Value:</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" value="<fmt:formatNumber value='${item.totalWithCommision}' pattern='#,##0.00'/>" tabindex="-1" readonly="readonly" id="total-price"  />
				  </div>
				</div>
		 </div>
		</div>
		<div class="row-fluid">
			<div class="span3">
				<div class="control-group">
				  <label class="control-label col-xs2">Quantity CTN:</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="inteiro" id="item.packageQuantity" name="item.packageQuantity" value="${item.packageQuantity }" />
				  </div>
				</div>
			</div>
			<div class="span3">
				<div class="control-group">
				  <label class="control-label col-xs2">G.W KGs:</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="item.grossWeight" name="item.grossWeight" value="${item.grossWeight }"/>
				  </div>
				</div>
			</div>
			<div class="span3">
				<div class="control-group">
				  <label class="control-label col-xs2">N.W KGs:</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="item.netWeight" name="item.netWeight" value="${item.netWeight }"/>
				  </div>
				</div>
			</div>
			<div class="span3">
				<div class="control-group">
				  <label class="control-label col-xs2">Volume (CBM):</label>
				  <div class="controls">
				    <input type="text" class="input-xlarge span12" data-behaviour="valor" id="item.volume" name="item.volume" value="${item.volume }" />
				  </div>
				</div>
			</div>
		</div>
	</fieldset>
    </div>
    <hr class="hideInIE8"/>
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Save</button>
	     <a href="<c:url value='/orders/${item.order.id}'/>" class="btn btn-primary">Back To Order</a>
	     <a href="<c:url value='/timeline'/>" class="btn btn-primary">Go To Timeline</a>   
	</div>
</div>
</form>
<content tag="local_script">
<script src="<c:url value='/resources/js/plugins/select2/select2.js'/>"></script>
<script src="<c:url value='/resources/js/plugins/select2/select2_locale_pt-BR.js'/>"></script>
<script src="<c:url value='/resources/js/borioselect2.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
// 	$('[data-behaviour~=valordecimal]').setMask('decimal-us-5');
	$('[data-behaviour~=valor]').setMask('decimal-us');
	$('[data-behaviour~=inteiro]').setMask('integer');
    
    $("#product-select").borioSelect({
		selecao : {param1 :"description"},
		url: "<c:url value='/products/searchByDescription.json'/>",
		extras :{buyPrice: "buyPrice", sellPrice: "sellPrice", commision: "commision"},
		editar:{id: '${item.product.id}',texto:'${item.product.description}'}
	}).on("change", function(e) { var data = $("#product-select").select2("data"); setProduct(data); });
    
    $('#product-select').select2('open');
    
});


function calculateTotalBuy() {
	var quantity  = $("#item\\.quantity").val();  
	quantity = quantity.replace(/,/g, '');
	quantity = parseFloat(quantity);
	
	var price = $("#item\\.buyPrice").val();
	price = price.replace(/,/g, '');
	price = parseFloat(price);
	
	var total = price * quantity;
	
	if(total){
		total = total.toFixed(2);
		$("#total-buy-price").val(total);
		$('[data-behaviour~=valor]').setMask('decimal-us');
// 		$('[data-behaviour~=valordecimal]').setMask('decimal-us-5');
	}
}

function calculateTotalSell() {
	var quantity  = $("#item\\.quantity").val();  
	quantity = quantity.replace(/,/g, '');
	quantity = parseFloat(quantity);
	
	var price = $("#item\\.unitPrice").val();
	price = price.replace(/,/g, '');
	price = parseFloat(price);
	
	var commision = $("#item\\.commision").val();
	if (commision){
		commision = commision.replace(/,/g, '');
		commision = parseFloat(commision);
	}else{
		commision = 0.0;
	}
	
	var totalSell = price * quantity;
	if(totalSell){
		$("#total-sell-price").val(totalSell);
		
		var commisionValue = (totalSell * (commision/100));
		var total = totalSell+commisionValue;
		
		$("#total-sell-price").val(totalSell.toFixed(2));
		$("#total-price").val(total.toFixed(2));
		$("#item\\.commisionValue").val(commisionValue.toFixed(2));
		$('[data-behaviour~=valor]').setMask('decimal-us');
// 		$('[data-behaviour~=valordecimal]').setMask('decimal-us-5');
	}
}

function calculateCommisionPercentual(){
	
	var quantity  = $("#item\\.quantity").val();  
	quantity = quantity.replace(/,/g, '');
	quantity = parseFloat(quantity);
	
	var price = $("#item\\.unitPrice").val();
	price = price.replace(/,/g, '');
	price = parseFloat(price);
	
	var commisionValue = $("#item\\.commisionValue").val();
	if (commisionValue){
		commisionValue = commisionValue.replace(/,/g, '');
		commisionValue = parseFloat(commisionValue);	
	}
	
	
	var totalSell = price * quantity;
	if (commisionValue && totalSell){
		var commision = ((commisionValue/totalSell) * 100);
		var total = totalSell+commisionValue;
		
		$("#total-sell-price").val(totalSell.toFixed(2));
		$("#total-price").val(total.toFixed(2));
		$("#item\\.commision").val(commision.toFixed(5));
		$('[data-behaviour~=valor]').setMask('decimal-us');
// 		$('[data-behaviour~=valordecimal]').setMask('decimal-us-5');
	}
	
}

function setProduct(item){
   	$('#item\\.product\\.id').val(item.id);
	$('#item\\.buyPrice').val(item.buyPrice);
	$('#item\\.unitPrice').val(item.sellPrice);
	$('#item\\.commision').val(item.commision);
	$('#item\\.product\\.description').val(item.text);
}

</script>
</content>
</body>
</html>