	<div class="priority low"><span>Order</span></div>
	<div class="task low">
		<div class="desc">
			<input type="hidden" value="${buyOrder.order.id}" name="order.id" />
			<div class="title">Order N. ${order.id} - Supplier: ${buyOrder.supplier.supplierName - Customer: ${order.customer.name}</div>
			<div>Value: <fmt:formatNumber value='${buyOrder.totalValue}' pattern='#,##0.00'/></div>
		</div>
		<div class="time">
			<div class="date"><fmt:formatDate value='${buyOrder.order.orderDate}'  pattern='MM/dd/yyyy' /></div>
		</div>
	</div>
	<table class="table table-striped table-bordered">
	  <thead>
	  	  <tr>
	  	    <th colspan="4">Order Itens</th>
	  	  </tr>
		  <tr>
		   <th width="40%">Description</th>
		   <th width="10%">Quantity</th>
		   <th width="10%">Unit Price</th>
		   <th width="10%">Total Value</th>
		  </tr>
	  </thead>   
	  <tbody>
	  	<c:forEach var="item" items="${buyOrders.itens}" varStatus="idx">	
	  	  <tr>
		  		<td class="center">${item.product.description}</td>
		  		<td class="center">${item.quantity}</td>
		  		<td class="center">${item.unitPrice}</td>
		  		<td class="center">${item.totalValue}</td>
	  	  </tr>
	  	</c:forEach>
	  </tbody>
	 </table>   
