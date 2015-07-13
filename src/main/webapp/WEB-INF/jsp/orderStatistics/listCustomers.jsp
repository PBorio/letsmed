<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Order</title>
<link rel="stylesheet" href="<c:url value='/resources/canvas-theme/css/datepicker.css'/>" type="text/css" media="screen" />
</head>
<body>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Orders</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	    <tr>
	      <th colspan="14">Orders - ${year}</th>
	    </tr>
		<tr>
			<th width='25%' >Customer</th>
			<th >January</th>
			<th >February</th>
			<th >March</th>
			<th >April</th>
			<th >May</th>
			<th >June</th>
			<th >July</th>
			<th >August</th>
			<th >September</th>
			<th >October</th>
			<th >November</th>
			<th >December</th>
			<th >Total</th>
		</tr>

	</thead>

	<tbody>
		<c:forEach var="line" items="${customerView.customerLines}">
			<tr>
				
				<td class="left" >${line.customer.name}</td>
				
				<td  ><fmt:formatNumber value='${line.januaryValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.februaryValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.marchValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.aprilValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.mayValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.juneValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.julyValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.augustValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.septemberValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.octoberValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.novemberValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.decemberValue}' pattern='#,##0.00'/></td>
				<td  ><fmt:formatNumber value='${line.totalValue}' pattern='#,##0.00'/></td>
			</tr>
			
		</c:forEach>
	</tbody>
	  <tfooter>
	    <tr>
	    <th>Totais</th>
		<td ><fmt:formatNumber value='${customerView.totalJanuary}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalFebrary}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalMarch}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalApril}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalMay}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalJune}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalJuly}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalAugust}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalSeptember}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalOctober}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalNovember}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.totalDecember}' pattern='#,##0.00'/></td>
		<td ><fmt:formatNumber value='${customerView.total}' pattern='#,##0.00'/></td>
		</tr>
	  </tfooter>
 </table>            
</div>
</body>
</html>