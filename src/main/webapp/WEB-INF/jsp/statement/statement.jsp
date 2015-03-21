<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Statement</title>
</head>
<body>
<c:forEach var="error" items="${errors}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Erro:</strong> ${error.message}
	</div>
</c:forEach>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Statement</h2>
</div>
<div class="box-content">
<form class="form-horizontal" action='<c:url value="/statement/search"/>' method="post">
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
  <label class="control-label col-xs2">Bank:</label>
  <div class="controls">
   <select name='idAccount' class='input-xlarge span12'>  
          <option value='-1'> Banks...</option> 
           <c:forEach var="bank" items="${bankAccountList}">  
              <option value='${bank.id}' <c:if test="${bank.id == idAccount}"> selected='true' </c:if> > 
              	${bank.description} 
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
<c:if test="${statemenView != null}">
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Statement</h2>
</div>
<div class="box-content">
<table class="table table-striped table-bordered">
  <thead>
	  <tr>
	   <th width="15%">Date</th>
	   <th>Description</th>
	   <th>Value</th>
	   <th>Balance</th>
	  </tr>
  </thead>   
  <tbody>
     <tr>
     	<td></td>
	    <td colspan="2">Initial Balance</td>
	    <td><fmt:formatNumber value='${statemenView.initialBalance}' pattern='#,##0.00'/></td>
	  </tr>
  	<c:forEach var="line" items="${statemenView.lines}">
  	  <tr>
		<td class="center"><fmt:formatDate value='${line.date}' pattern='MM/dd/yyyy' /></td>
  		<td class="center">${line.account}</td>
  		<td class="center" <c:if test="${line.value < 0.0}"> style="color: red;" </c:if> ><fmt:formatNumber value='${line.value}' pattern='#,##0.00'/></td>
  		<td class="center"<c:if test="${line.balance < 0.0}"> style="color: red;" </c:if> ><fmt:formatNumber value='${line.balance}' pattern='#,##0.00'/></td>
  	  </tr>
  	</c:forEach>
  </tbody>
 </table>            
</div>
</c:if>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('[data-behaviour~=datepicker]').datepicker({dateFormat: "mm/dd/yy"});
	$('[data-behaviour~=datepicker]').setMask({mask: '19/39/9999', autoTab: false});
});
</script>
</content>
</body>
</html>