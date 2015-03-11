<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="box-header" data-original-title>
						<h2><i class="halflings-icon user"></i><span class="break"></span>Orders Timeline</h2>
					</div>
					<div class="box-content">
						<ul class="timeline">
               <c:forEach var="order" items="${orderList }">
                 <li class="timeline-${order.status.description}">
                 <a href="<c:url value='/${order.status.url}/${order.id}'/>">
                    <div class="timeline-time">
                      <span class="date">${order.status.lastMovement}</span>
                      <span class="time"><fmt:formatDate value="${order.status.statusDate}" pattern='MM/dd/yyyy'/></span>
                    </div>
                    
                   <div class="timeline-body">
                      <h2>${order.status.title} - Order N. ${order.id}</h2>
									    <div class="timeline-content">
                        <strong>Customer:</strong> ${order.customer.name} - <strong>Terms:</strong> ${order.negotiationTerm.description} <br>
                        <c:forEach var="item" items="${order.itens}">
                          <div class="row-fluid">
                                <div class="span2">
                                  ${item.product.articleNumber}
                                </div>
                                <div class="span4">
                                  ${item.product.description}
                                </div>
                                <div class="span2 align-right">
                                  ${item.quantity}
                               </div>
                               <div class="span2 align-right">
                                  ${item.unitPrice}
                               </div>
                               <div class="span2 align-right">
                                  ${item.totalValue}
                               </div>    
                          </div>
                        </c:forEach>
                      </div>
                   </div>
                   </a>
                 </li>
             </c:forEach>
            </ul>
					</div>

</body>
</html>