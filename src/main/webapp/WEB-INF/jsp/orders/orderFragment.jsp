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
	<table class="table table-striped table-bordered">
	  <thead>
	  	  <tr><th colspan="4">Order Itens</th></tr>
		  <tr>
		   <th width="40%">Description</th>
		   <th width="10%">Quantity</th>
		   <th width="10%">Unit Price</th>
		   <th width="10%">Total Value</th>
		  </tr>
	  </thead>   
	  <tbody>
	  	<c:forEach var="item" items="${order.itens}" varStatus="idx">	
	  	  <tr>
		  		<td class="center">${item.product.description}</td>
		  		<td class="center"><fmt:formatNumber value='${item.quantity}' pattern='#,###'/></td>
		  		<td class="center"><fmt:formatNumber value='${item.unitPrice}' pattern='#,##0.00000'/></td>
		  		<td class="center"><fmt:formatNumber value='${item.totalProducts}' pattern='#,##0.00'/></td>
	  	  </tr>
	  	</c:forEach>
	  </tbody>
	 </table>   
