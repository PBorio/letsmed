<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Account Details</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Account Details</h2>
</div>
<div class="box-content">
<form class="form-horizontal" action='<c:url value="/accountDetails/search"/>' method="post">
	<div class="control-group">
	   <label class="control-label col-xs2">Begin Date:</label>			 
       <div class="controls">
		  <input type="text" class="input-xlarge span2" data-behaviour="datepicker" name="begin" id="begin" value="<fmt:formatDate value='${begin}'  pattern='MM/dd/yyyy' />" />
	   </div>
	</div>
  	<div class="control-group">
	   <label class="control-label col-xs2">End Date:</label>			 
       <div class="controls">
		  <input type="text" class="input-xlarge span2" data-behaviour="datepicker" name="end" id="end" value="<fmt:formatDate value='${end}'  pattern='MM/dd/yyyy' />" />
	   </div>
	 </div>
	 <div class="control-group">
	  <label class="control-label col-xs2">Expense Account:</label>
	  <div class="controls">
	   <select name='idAccount' class='input-xlarge span12'>  
          <option value='-1'> Accounts...</option> 
           <c:forEach var="account" items="${expenseAccountList}">  
              <option value='${account.id}' <c:if test="${account.id == idAccount}"> selected='true' </c:if> > 
              	${account.description} 
              </option>  
      		</c:forEach>  
        </select> 
	  </div>
	</div>
	<hr class="hideInIE8"/>
	<div class="form-actions">
	  <button id="singlebutton" name="singlebutton" class="btn btn-primary">Search</button>
	</div>
</form>
</div>
<hr class="hideInIE8"/>
<c:if test="${accountDetailView != null}">
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Account Details</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="15%">Date</th>
	   <th>Account</th>
	   <th>Additional Info</th>
	   <th>Valor</th>
	  </tr>
  </thead>   
  <tbody>
     <tr>
	    <td colspan="3">Initial Balance</td>
	    <td><fmt:formatNumber value='${accountDetailView.initialBalance}' pattern='#,##0.00'/></td>
	  </tr>
  	<c:forEach var="line" items="${accountDetailView.lines}">
  	  <tr>
		<td class="center"><fmt:formatDate value='${line.date}' pattern='MM/dd/yyyy' /></td>
  		<td class="center">${line.description}</td>
  		<td class="center">${line.info}</td>
  		<td class="center" <c:if test="${line.value < 0.0}"> style="color: red;" </c:if> ><fmt:formatNumber value='${line.value}' pattern='#,##0.00'/></td>
  	  </tr>
  	</c:forEach>
  </tbody>
  <tfoot>
  	<tr>
  		<td colspan="3"><strong>Balance</strong></td>
  		<td<c:if test="${accountDetailView.balance < 0.0}"> style="color: red;" </c:if> ><fmt:formatNumber value='${accountDetailView.balance}' pattern='#,##0.00'/></td>
  	</tr>
  </tfoot>
 </table>            
</div>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Chart</h2>
</div>
<div class="box-content">
<div class="row-fluid">
	<div class="span6">
		<div id="expense_payments" class="row-fluid"></div>
	</div>
</div>
</div>
</c:if>
<content tag="local_script">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
});


showExpensesPayment();
	  
	  
function showExpensesPayment(){

	  // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Revenues');
        data.addColumn('number', 'Expenses');
        data.addColumn('number', 'Payments');
        data.addRows(1);
        
  	    data.setValue(0, 0 ,'${despesaPagamentoView.account}');
  	    data.setValue(0, 1 ,${despesaPagamentoView.expense});
  	    data.setValue(0, 2 ,${despesaPagamentoView.payment});
       

        // Set chart options
        var options = {'title':'Expenses vs Payments',
                       'height':480,
                       'is3D' : true,
                        vAxis: {minValue:0}};
                       
        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('expense_payments'));
        chart.draw(data, options);
      }
}

</script>
</content>
</body>
</html>