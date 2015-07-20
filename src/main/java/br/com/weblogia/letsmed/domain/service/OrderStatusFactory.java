package br.com.weblogia.letsmed.domain.service;

import java.util.Date;

import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.tipos.NegotiationType;

public class OrderStatusFactory {
	
	public OrderStatus getStatus( Order order){
		OrderStatus status = new OrderStatus();
		if (order.isUnsolvedComplains()){
			status.setDescription("complain");
			status.setTitle("Order with Complains");
			status.setStatusDate(new Date());
			status.setLastMovement("Last Complain Date");
			status.setUrl("complains");
		}else if (order.isWaitingSupplierProforma()){
			status.setDescription("supplier_proforma");
			status.setTitle("Waiting for Supplier Proforma");
			status.setStatusDate(order.getOrderDate());
			status.setLastMovement("Order Date");
			status.setUrl("orders");
		}else if (order.isWaitingProformaConfirmation()||order.isWaitingArtworkConfirmation()){
			status.setDescription("to_proforma");
			status.setTitle("Waiting for Proforma and ArtWork Confirmation");
			
			if (order.isWaitingProformaConfirmation()){
				status.setStatusDate(order.getSupplierProformaDate());
				status.setLastMovement("Supplier Proforma Date");
			}
			else 
			if (order.isWaitingArtworkConfirmation()){
				status.setStatusDate(order.getProformaConfirmationDate());
				status.setLastMovement("Proforma Confirmation Date");
			}
			
			status.setUrl("orders");
		}else if (order.isWaitingAdvancedPayment()){
			status.setDescription("to_advanced_paid");
			status.setTitle("Waiting for Advanced Payment");
			status.setStatusDate(order.getProformaConfirmationDate());
			status.setLastMovement("Artwork Confirmation Date");
			status.setUrl("orderPayments/order");
//		}else if (order.isWaitingProductionStart()){
//			status = populateStatusToProductionSta1rt(order);
		}else if (order.isWaitingForwardDetails()){
			status = populateStatusToForwardDetail(order);
//			status.setDescription("to_forward_detail");
//			status.setTitle("Waiting for Forward Details");
//			status.setStatusDate(order.getProductionStartDate());
//			status.setLastMovement("Production Start Date");
//			status.setUrl("forwardDetails/order");
		}else if (order.isWaitingShipment()){
			status = populateStatusToShipment(order);
		}else if (order.isWaitingForDocumentCopy()){
			status.setDescription("to_copy_doc_sent");
			status.setTitle("Waiting for Documents Copy to Be Sent");
			status.setStatusDate(order.getShipDate());
			status.setLastMovement("Ship Date");
			status.setUrl("orders");
		}else if (order.isWaitingOrderToBePaid()){
			status = populateStatusToBePaid(order);
		}else if (order.isWaitingForOriginalDocument()){
			status.setDescription("to_original_doc_sent");
			status.setTitle("Waiting for Original Documents To Be Sent");
			status.setStatusDate(order.getLastPaymentDate());
			status.setLastMovement("Payment Date");
			status.setUrl("orders");
		}else if (order.isWaitingForComissionPayment()){
			status.setDescription("to_comisson_payment");
			status.setTitle("Waiting for Commision Payment");
			status.setStatusDate(order.getOriginalDocumentDate());
			status.setLastMovement("Original Documents Date");
			
			if (order.isRevenuePaid())
				status.setUrl("orders");
			else
				status.setUrl("revenues/order");
		}
		return status;
	}
	
	private OrderStatus populateStatusToShipment(Order order){
		OrderStatus status = new OrderStatus();
		status.setDescription("to_ship");
		status.setTitle("Waiting for Shipment");
		status.setUrl("shipment/order");
		
		if (NegotiationType.TT_100_BEFORE_SHIPMENT.equals(order.getNegotiationTerm().getNegotiationType())||
			NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT.equals(order.getNegotiationTerm().getNegotiationType())){
			status.setStatusDate(order.getLastPaymentDate());
			status.setLastMovement("Last Payment Date");
		}else{
			status.setStatusDate(order.getLastForwardDetailDate());
			status.setLastMovement("Forward Detail Date");
		}
		
		return status;
	}
	
	private OrderStatus populateStatusToForwardDetail(Order order) {
		OrderStatus status = new OrderStatus();
		status.setDescription("to_forward_detail");
		status.setTitle("Waiting for Forward Details");
		status.setUrl("forwardDetails/order");
		
		
		status.setStatusDate(order.getLastPaymentDate());
		status.setLastMovement("Last Payment Date");
		
		if (order.getNegotiationTerm().getNegotiationType().equals(NegotiationType.LC_AT_SIGHT)){
			status.setStatusDate(order.getLastPaymentDate());
			status.setLastMovement("Last Payment Date");
			return status;
		}
		
		if (order.getNegotiationTerm().getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY)||
			order.getNegotiationTerm().getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT)){
			status.setStatusDate(order.getLastPaymentDate());
			status.setLastMovement("Last Payment Date");
			return status;
		}
		status.setStatusDate(order.getArtworkConfirmationDate());
		status.setLastMovement("Artwork Confirmation Date");
		
		return status;
	}

	private OrderStatus populateStatusToBePaid(Order order) {
		OrderStatus status = new OrderStatus();
		status.setDescription("to_be_paid");
		status.setTitle("Waiting Order To Be Paid");
		status.setUrl("orderPayments/order");
		

		if (NegotiationType.LC_AT_SIGHT.equals(order.getNegotiationTerm().getNegotiationType())){
			status.setStatusDate(order.getProformaConfirmationDate());
			status.setLastMovement("Artwork confirmation Date");
		}
		
		if (NegotiationType.TT_100_BEFORE_SHIPMENT.equals(order.getNegotiationTerm().getNegotiationType())||
			NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT.equals(order.getNegotiationTerm().getNegotiationType())){
			status.setStatusDate(order.getLastForwardDetailDate());
			status.setLastMovement("Forward Detail Date");
		}
		
		if (NegotiationType.TT_100_AGAINST_COPY.equals(order.getNegotiationTerm().getNegotiationType())||
			NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY.equals(order.getNegotiationTerm().getNegotiationType())){
			status.setStatusDate(order.getCopyDocumentDate());
			status.setLastMovement("Copy of Documents Date");
		}
		return status;
	}
}
