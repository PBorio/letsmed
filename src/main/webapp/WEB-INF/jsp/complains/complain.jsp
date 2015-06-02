<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forward Details</title>
</head>
<body>
	<c:forEach var="error" items="${errors}">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<strong>Erro:</strong> ${error.message}
		</div>
	</c:forEach>
	<div class="box-header" data-original-title>
		<h2>
			<i class="halflings-icon user"></i><span class="break"></span>Complains
		</h2>
	</div>
	<form class="form-horizontal"
		action='<c:url value="/forwardDetails/save"/>' method="post">
		<div class="box-content">
			<%@include file="../orders/orderFragment.jsp"%>
			<hr class="hideInIE8" />
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th colspan="4">Complains</th>
					</tr>
					<tr>
						<th width="80%">Description</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="complain" items="${order.complains}"
						varStatus="idx">
						<tr id="complain-${complain.id}">
							<td class="center">${complain.description}</td>
							<td class="center"><a
								href="<c:url value='/complains/${complain.id}'/>"
								class="btn btn-success"><i
									class="halflings-icon zoom-in halflings-icon"></i></a> <a
								href="<c:url value='/complains/delete/${complain.id}'/>"
								class="btn btn-danger"><i
									class="halflings-icon trash halflings-icon"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon edit"></i><span class="break"></span>Complain
				</h2>
			</div>
			<div class="box-content">
				<fieldset>
					<input type="hidden" value="${complain.id}" name="complain.id">
					<input type="hidden" value="${complain.order.id}" name="complain.order.id"> 
					<input type="hidden" value="<fmt:formatDate value='${complain.complainDate}' pattern='MM/dd/yyyy'/>" name="complain.complainDate">
					<input type="hidden" value="<fmt:formatDate value='${complain.solvedDate}' pattern='MM/dd/yyyy'/>" name="complain.solvedDate">
					<div class="control-group">
						<label class="control-label col-xs2">Description:</label>
						<div class="controls">
							<textarea class="input-xlarge span8" name="complain.description">${complain.description}</textarea>
						</div>
					</div>
				</fieldset>
			</div>
			<hr class="hideInIE8" />
			<div class="form-actions">
				<button id="singlebutton" name="singlebutton" class="btn btn-primary" onClick="this.form.action='<c:url value="/complains/save"/>';this.form.submit()">Create</button>
				<c:if test="${not empty complain.id}">
					<button id="singlebutton" name="singlebutton" class="btn btn-primary" onClick="this.form.action='<c:url value="/complains/solve"/>';this.form.submit()">Solved</button>
				</c:if>
				<a href="<c:url value='/timeline'/>" class="btn btn-primary">Go To Timeline</a>
			</div>
		</div>
	</form>
</body>
</html>