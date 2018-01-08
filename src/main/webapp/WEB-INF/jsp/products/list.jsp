<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Products</title>
<link rel="stylesheet" href="<c:url value='/resources/canvas-theme/css/datepicker.css'/>" type="text/css" media="screen" />
</head>
<body>
<div class="box-header" data-original-title>
	<h2><i class="halflings-icon user"></i><span class="break"></span>Products</h2>
</div>
<div class="form-actions">
<a href="<c:url value='/products/product'/>" class="btn btn-primary">New Product</a>
</div>
<br>
<br>
<div class="box-content">

<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon user"></i><span class="break"></span></h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width="50%">Product</th>
								  <th width="30%">Category</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
							<c:forEach var="product" items="${productList}">
						  	  <tr>
									<td class="center"><a href="<c:url value='/products/${product.id}'/>">${product.description}</a></td>
							  		<td class="center"><a href="<c:url value='/products/${product.id}'/>">${product.productCategory.description}</a></td>
						  	  		<td class="center">
						  	  			<a href="<c:url value='/products/${product.id}'/>" class="btn btn-success"><i class="halflings-icon zoom-in halflings-icon"></i></a>
						  	  			<a href="<c:url value='/products/${product.id}'/>" class="btn btn-info"><i class="halflings-icon edit halflings-icon"></i></a>	        	 
										<a href="" onclick="remove(${product.id}); return false;" class="btn btn-danger"><i class="halflings-icon trash halflings-icon"></i></a>	        	 
							        </td>
						  	  </tr>
						  	</c:forEach>
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
</div>

</body>
</html>