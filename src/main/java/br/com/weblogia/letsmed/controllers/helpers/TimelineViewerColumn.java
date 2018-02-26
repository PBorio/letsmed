package br.com.weblogia.letsmed.controllers.helpers;

import java.util.ArrayList;
import java.util.List;

import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.Supplier;

public class TimelineViewerColumn {
	
	private Supplier supplier;
	
	private List<Order> ordersWaitingSupplierProforma =  new ArrayList<Order>();
	private List<Order> ordersWaitingArtworkConfirmation = new ArrayList<Order>();
	private List<Order> ordersWaitingAdvancedPayment     = new ArrayList<Order>();
	private List<Order> ordersWaitingForwardDetails      = new ArrayList<Order>();
	private List<Order> ordersWaitingForShipment         = new ArrayList<Order>();
	private List<Order> ordersWaitingCopiesToBeSent      = new ArrayList<Order>();
	private List<Order> ordersWaitingPayment             = new ArrayList<Order>();
	private List<Order> ordersWaitingOriginalsDocument   = new ArrayList<Order>();
	private List<Order> ordersWaitingCommissionPayment   = new ArrayList<Order>();
	private List<Order> complains                        = new ArrayList<Order>();
	
	
	public TimelineViewerColumn(Supplier supplier) {
		this.supplier = supplier;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public List<Order> getOrdersWaitingSupplierProforma() {
		return ordersWaitingSupplierProforma;
	}
	public List<Order> getOrdersWaitingArtworkConfirmation() {
		return ordersWaitingArtworkConfirmation;
	}
	public List<Order> getOrdersWaitingAdvancedPayment() {
		return ordersWaitingAdvancedPayment;
	}
	public List<Order> getOrdersWaitingForwardDetails() {
		return ordersWaitingForwardDetails;
	}
	public List<Order> getOrdersWaitingForShipment() {
		return ordersWaitingForShipment;
	}
	public List<Order> getOrdersWaitingCopiesToBeSent() {
		return ordersWaitingCopiesToBeSent;
	}
	public List<Order> getOrdersWaitingPayment() {
		return ordersWaitingPayment;
	}
	public List<Order> getOrdersWaitingOriginalsDocument() {
		return ordersWaitingOriginalsDocument;
	}
	public List<Order> getOrdersWaitingCommissionPayment() {
		return ordersWaitingCommissionPayment;
	}
	public List<Order> getComplains() {
		return complains;
	}
	
	public void addOrder(Order order) {
		if ("complain".equals(order.getStatus().getDescription())) {
			this.complains.add(order);
		}else if ("supplier_proforma".equals(order.getStatus().getDescription())) {
			this.ordersWaitingSupplierProforma.add(order);
		}else if ("to_proforma".equals(order.getStatus().getDescription())) {
			this.getOrdersWaitingArtworkConfirmation().add(order);
		}else if ("to_advanced_paid".equals(order.getStatus().getDescription())) {
			this.ordersWaitingAdvancedPayment.add(order);
		}else if ("to_forward_detail".equals(order.getStatus().getDescription())) {
			this.ordersWaitingForwardDetails.add(order);
		}else if ("to_ship".equals(order.getStatus().getDescription())) {
			this.ordersWaitingForShipment.add(order);
		}else if ("to_copy_doc_sent".equals(order.getStatus().getDescription())) {
			this.ordersWaitingCopiesToBeSent.add(order);
		}else if ("to_be_paid".equals(order.getStatus().getDescription())) {
			this.ordersWaitingPayment.add(order);
		}else if ("to_original_doc_sent".equals(order.getStatus().getDescription())) {
			this.ordersWaitingOriginalsDocument.add(order);
		}else if ("to_comisson_payment".equals(order.getStatus().getDescription())) {
			this.ordersWaitingCommissionPayment.add(order);
		};
	}
	
	
	

}
