package br.com.weblogia.letsmed.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.joda.time.DateTime;

import br.com.weblogia.domain.OrderCommisionPayment;
import br.com.weblogia.domain.OrderSupplierPayment;
import br.com.weblogia.letsmed.domain.helpers.Arredondamento;
import br.com.weblogia.letsmed.domain.service.OrderStatus;
import br.com.weblogia.letsmed.domain.service.OrderStatusFactory;
import br.com.weblogia.letsmed.domain.tipos.NegotiationType;
import br.com.weblogia.letsmed.domain.tipos.TransactionType;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date orderDate;
	
	
	private String orderNumber;
	
	private String additionalInfo;
	
	@ManyToOne
	private Customer customer;
	
	private Double commision;
	
	@ManyToOne
	private Office office;
	
	@ManyToOne
	private Supplier supplier;
	
	private Date confirmationDate;
	
	private Date supplierProformaDate;
	
	@ManyToOne
	private TransactionTerm transactionTerm;
	
	@ManyToOne
	private NegotiationTerm negotiationTerm;
	
	@ManyToOne
	private ShipmentTerm shipmentTerm;
	
	private Date proformaConfirmationDate;
	
	private Date artworkConfirmationDate;
	
	private Date productionStartDate;
	
	private Date shipDate;
	
	private Date copyDocumentDate;
	
	private Date conclusionDate;
	
	private String invoiceNumber;
	
	private Date invoiceDate;
	
	private String terms;
	
	private String landingPort;
	
	private String destinationPort;
	
	private String insurance;
	
	private String shipment;
	
	private Date deliveryDate;
	
	private String deliveryForecast;
	
	private String poNumber;
	
	private String thirdPartNumber;
	
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getThirdPartNumber() {
		return thirdPartNumber;
	}

	public void setThirdPartNumber(String thirdPartNumber) {
		this.thirdPartNumber = thirdPartNumber;
	}

	@OneToMany(mappedBy="order")
	@OrderBy("id")
	private List<OrderItem> itens = new ArrayList<OrderItem>();

	@OneToMany(mappedBy="order")
	private List<Revenue> comissionRevenues = new ArrayList<Revenue>();
	
	@OneToMany(mappedBy="order")
	private List<ForwardDetail> forwardDetails = new ArrayList<ForwardDetail>();
	
	@OneToMany(mappedBy="order")
	private List<OriginalDocumentShipment> originalDocumentShipment = new ArrayList<OriginalDocumentShipment>();

	@OneToMany(mappedBy="order")
	private List<OrderPayment> payments = new ArrayList<OrderPayment>();
	
	@OneToMany(mappedBy="order")
	private List<OrderSupplierPayment> supplierPayments = new ArrayList<OrderSupplierPayment>();
	
	@OneToMany(mappedBy="order")
	private List<Complain> complains = new ArrayList<Complain>();

	@OneToMany(mappedBy="order")
	private List<OrderCommisionPayment> comissionPayments = new ArrayList<OrderCommisionPayment>();
	
	@OneToMany(mappedBy="order")
	private List<OrderHistory> orderHistories = new ArrayList<OrderHistory>();

	public int getItensNumber(){
		return this.itens.size();
	}
	
	public Double getTotalValue(){
		
		if (this.itens == null)
			return 0.0;
		
		Double result = 0.0;
		for (OrderItem i :itens){
			result += i.getTotalProducts();
		}
		return new Arredondamento().arredondar(result);
		
	}
	
	public Double getTotalBuyValue() {
		if (this.itens == null)
			return 0.0;
		
		Double result = 0.0;
		for (OrderItem i :itens){
			result += i.getTotalBuyValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getBalance(){
		Double value = this.getTotalValue();
		Double paidValue = 0.0;
		
		paidValue = this.getPaidValue();
		
		return new Arredondamento().arredondar(value - paidValue);
	}
	
	public Double getSupplierBalance() {
		Double value = this.getTotalBuyValue();
		Double paidValue = 0.0;
		
		paidValue = this.getSupplierPaidValue();
		
		return new Arredondamento().arredondar(value - paidValue);
	}

	public Double getPaidValue() {
		Double result = 0.0;
		
		for (OrderPayment payment : this.payments){
			result += payment.getValue();
		}
		
		return new Arredondamento().arredondar(result);
	}
	
	private Double getSupplierPaidValue() {
		Double result = 0.0;
		
		for (OrderSupplierPayment payment : this.supplierPayments){
			result += payment.getValue();
		}
		
		return new Arredondamento().arredondar(result);
	}

	public boolean isUnsolvedComplains() {
		for (Complain complain : this.complains){
			if (complain.getSolvedDate() == null)
				return true;
		}
		return false;
	}
	
	public Complain getFirstUnsolvedComplain() {
		for (Complain complain : this.complains){
			if (complain.getSolvedDate() == null)
				return complain;
		}
		return null;
	}
	
	
	
	

	public boolean isPaid() {
		if (this.getTotalValue().doubleValue() == 0.0)
			return false;
		
		return (this.getBalance() <= 0.0);
	}
	
	private boolean isSupplierPaid() {
		if (this.getTotalBuyValue().doubleValue() == 0.0)
			return false;
		
		return (this.getSupplierBalance() <= 0.0);
	}

	public Date getLastPaymentDate() {
		if (this.payments == null || this.payments.size() == 0)
			return null;
		
		DateTime ultimaData = null;
		
		for (OrderPayment op : this.payments){
			if (ultimaData == null || ultimaData.isBefore(new DateTime(op.getPaymentDate().getTime())))
				ultimaData = new DateTime(op.getPaymentDate().getTime());
		}
		
		return ultimaData.toDate();
	}
	
	public Date getLastSupplierPaymentDate() {
		if (this.supplierPayments == null || this.supplierPayments.size() == 0)
			return null;
		
		DateTime ultimaData = null;
		
		for (OrderSupplierPayment op : this.supplierPayments){
			if (ultimaData == null || ultimaData.isBefore(new DateTime(op.getPaymentDate().getTime())))
				ultimaData = new DateTime(op.getPaymentDate().getTime());
		}
		
		return ultimaData.toDate();
	}
	
	public Date getAdvancedPaymentDate() {
		if (this.payments == null || this.payments.size() == 0)
			return null;
		
		if (this.isPaid() && this.payments.size() == 1)
			return null;
		
		DateTime firstDate = null;
		
		for (OrderPayment op : this.payments){
			if (firstDate == null || firstDate.isAfter(new DateTime(op.getPaymentDate().getTime())))
				firstDate = new DateTime(op.getPaymentDate().getTime());
		}
		
		return firstDate.toDate();
	}
	
	public Date getLastForwardDetailDate(){
		if (this.forwardDetails == null || this.forwardDetails.size() == 0)
			return null;
		
		DateTime lastDate = null;
		
		for (ForwardDetail f : this.forwardDetails){
			if (lastDate == null || lastDate.isBefore(new DateTime(f.getForwardDetailDate().getTime())))
				lastDate = new DateTime(f.getForwardDetailDate().getTime());
		}
		
		return lastDate.toDate();
	}

	public boolean isAdvancedPaid() {
		
		if (this.getTotalValue().doubleValue() == 0.0)
			return false;
		
		return (this.getPaidValue().doubleValue() > 0.0 && this.getBalance().doubleValue() > 0.0);
	}
	
	public boolean isSupplierAdvancedPaid() {
		if (this.getTotalBuyValue().doubleValue() == 0.0)
			return false;
		
		return (this.getSupplierPaidValue().doubleValue() > 0.0 && this.getSupplierBalance().doubleValue() > 0.0);
	}

	public OrderStatus getStatus(){
		return new OrderStatusFactory().getStatus(this);
	}

	public boolean isForwardDetailsSent() {
		return (this.forwardDetails != null && !this.forwardDetails.isEmpty());
	}
	
	public boolean isOriginalDocumentSent() {
		return (this.originalDocumentShipment != null && !this.originalDocumentShipment.isEmpty());
	}

	public boolean isToBeFilledIn() {
		return (this.orderDate != null && this.confirmationDate == null);
	}

	public boolean isWaitingSupplierProforma() {
		return (this.orderDate != null && this.supplierProformaDate == null);
	}

	public boolean isWaitingProformaConfirmation() {
		return (this.supplierProformaDate != null && this.proformaConfirmationDate == null);
	}

	public boolean isWaitingArtworkConfirmation() {
		return (this.proformaConfirmationDate != null && this.artworkConfirmationDate == null);
	}

	public boolean isWaitingProformaConfirmationAndArtwork(){
		if (supplierProformaDate == null)
			return false;
		
		return (this.proformaConfirmationDate == null && this.artworkConfirmationDate == null);
	}
	
	public boolean isWaitingAdvancedPayment() {
		
		if(negotiationTerm == null)
			return false;
		
		if (this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY)||
			this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT)){
			return ((this.proformaConfirmationDate != null && this.artworkConfirmationDate != null) && !this.isPaid() && !this.isAdvancedPaid());
		}
		return false;
	}
	
	public boolean isWaitingAdvancedSupplierPayment() {
		if(negotiationTerm == null)
			return false;

		if (isWaitingAdvancedPayment())
			return false;
		
		if (this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY)||
			this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT)){
			return ((this.proformaConfirmationDate != null && this.artworkConfirmationDate != null) && !this.isSupplierPaid() && !this.isSupplierAdvancedPaid());
		}
		
		return false;
	}

	public boolean isWaitingProductionStart() {
		
		if (negotiationTerm == null)
			return false;
		
		if (this.negotiationTerm.getNegotiationType().equals(NegotiationType.LC_AT_SIGHT)){
			return (this.isPaid() && this.productionStartDate == null);
		}
		
		if (this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY)||
			this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT)){
			return (this.isAdvancedPaid() && this.productionStartDate == null);
		}
		return ((this.proformaConfirmationDate != null && this.artworkConfirmationDate != null) && this.productionStartDate == null);
	}

	public boolean isWaitingForwardDetails() {
		
		if (negotiationTerm == null)
			return false;
		
		if (this.negotiationTerm.getNegotiationType().equals(NegotiationType.LC_AT_SIGHT)){
			return (this.isPaid() && !this.isForwardDetailsSent());
		}
		
		if (this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY)||
			this.negotiationTerm.getNegotiationType().equals(NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT)){
			return (this.isAdvancedPaid() && !this.isForwardDetailsSent());
		}
		return ((this.proformaConfirmationDate != null && this.artworkConfirmationDate != null) && !this.isForwardDetailsSent());
	}

	public boolean isWaitingShipment() {
		
		if (negotiationTerm == null)
			return false;
		
		if (!this.isForwardDetailsSent())
			return false;
		
		if (NegotiationType.TT_100_BEFORE_SHIPMENT.equals(this.negotiationTerm.getNegotiationType())||
			NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT.equals(this.negotiationTerm.getNegotiationType())){
			return (this.shipDate == null && this.isPaid());
		}
		return (this.shipDate == null);
	}

	public boolean isWaitingForDocumentCopy() {
		return (this.shipDate != null && this.copyDocumentDate == null);
	}

	public boolean isWaitingOrderToBePaid() {
		
		if (negotiationTerm == null)
			return false;
		
		if (this.isPaid())
			return false;
		
		if (NegotiationType.LC_AT_SIGHT.equals(this.negotiationTerm.getNegotiationType())){
			return ((this.proformaConfirmationDate != null && this.artworkConfirmationDate != null) && !this.isForwardDetailsSent());
		}
		
		if (NegotiationType.TT_100_BEFORE_SHIPMENT.equals(this.negotiationTerm.getNegotiationType())||
			NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT.equals(this.negotiationTerm.getNegotiationType())){
			return (this.isForwardDetailsSent() && this.shipDate == null);
		}
		
		if (NegotiationType.TT_100_AGAINST_COPY.equals(this.negotiationTerm.getNegotiationType())||
			NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY.equals(this.negotiationTerm.getNegotiationType())){
			return (this.copyDocumentDate != null && !this.isOriginalDocumentSent());
		}
		return false;
	}
	
//	public boolean isWaitingSupplierToBePaid() {
//		
//		if (negotiationTerm == null)
//			return false;
//		
//		if (this.isSupplierPaid())
//			return false;
//		
//		if (isWaitingOrderToBePaid())
//			return false;
//		
//		if (NegotiationType.LC_AT_SIGHT.equals(this.negotiationTerm.getNegotiationType())){
//			return ((this.proformaConfirmationDate != null && this.artworkConfirmationDate != null) && !this.isForwardDetailsSent());
//		}
//		
//		if (NegotiationType.TT_100_BEFORE_SHIPMENT.equals(this.negotiationTerm.getNegotiationType())||
//			NegotiationType.TT_ADVANCE_AND_BALANCE_BEFORE_SHIPMENT.equals(this.negotiationTerm.getNegotiationType())){
//			return (this.isForwardDetailsSent() && this.shipDate == null);
//		}
//		
//		if (NegotiationType.TT_100_AGAINST_COPY.equals(this.negotiationTerm.getNegotiationType())||
//			NegotiationType.TT_ADVANCE_AND_BALANCE_AGAINST_COPY.equals(this.negotiationTerm.getNegotiationType())){
//			return (this.copyDocumentDate != null && !this.isOriginalDocumentSent());
//		}
//		
//		return false;
//	}

	public boolean isWaitingForOriginalDocument() {
		return (!this.isOriginalDocumentSent() && this.isPaid());
	}

	public boolean isComissionPaid() {
		if (this.getComissionRevenue() == null)
			return false;
		
		return (this.getComissionRevenue().getBalance() <= 0.0);
	}

	public boolean isWaitingForComissionPayment() {
		return (this.isOriginalDocumentSent() && this.conclusionDate == null);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}


	public NegotiationTerm getNegotiationTerm() {
		return negotiationTerm;
	}

	public void setNegotiationTerm(NegotiationTerm negotiationTerm) {
		this.negotiationTerm = negotiationTerm;
	}

	public Date getProformaConfirmationDate() {
		return proformaConfirmationDate;
	}

	public void setProformaConfirmationDate(Date proformaConfirmationDate) {
		this.proformaConfirmationDate = proformaConfirmationDate;
	}

	public Date getArtworkConfirmationDate() {
		return artworkConfirmationDate;
	}

	public void setArtworkConfirmationDate(Date artworkConfirmationDate) {
		this.artworkConfirmationDate = artworkConfirmationDate;
	}

	public Date getCopyDocumentDate() {
		return copyDocumentDate;
	}

	public void setCopyDocumentDate(Date copyDocumentDate) {
		this.copyDocumentDate = copyDocumentDate;
	}

	public Date getOriginalDocumentDate() {
		Date originalDocumentShipmentDate = null;
		for (OriginalDocumentShipment ods : this.originalDocumentShipment) {
			originalDocumentShipmentDate = ods.getShipDate();
		}
		return originalDocumentShipmentDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public List<OrderItem> getItens() {
		return itens;
	}

	public void setItens(List<OrderItem> itens) {
		this.itens = itens;
	}

	public Double getRevenueValue() {
		if (this.transactionTerm == null || this.transactionTerm.getId() == null )
			return 0.0;
		
		if (this.transactionTerm.getId().longValue() == 3l){
			Double profit = this.getTotalValue() - this.getTotalBuyValue();
			return new Arredondamento().arredondar(profit);
		}else{
			Double commisionValue = 0.0; //(this.getTotalValue() * (this.commision/100));
			for (OrderItem item : this.itens){
				commisionValue += item.getCommisionValue();
			}
			return new Arredondamento().arredondar(commisionValue);
		}
	}
	
	public Double getPartnerComissionValue(){
		if (customer == null)
			return 0.0;
		
		if (customer.getPartner() == null)
			return 0.0;
		
		Double partnerComission = customer.getPartnerComissionTo(getRevenueValue());
		return new Arredondamento().arredondar(partnerComission);
	}

	public Revenue getComissionRevenue() {
		if (this.comissionRevenues  == null || this.comissionRevenues.size() == 0)
			return null;
		
		return this.comissionRevenues.get(0);
	}
	
	public List<OrderPayment> getPayments(){
		return this.payments;
	}

	public List<ForwardDetail> getForwardDetails() {
		return forwardDetails;
	}

	public void setForwardDetails(List<ForwardDetail> forwardDetails) {
		this.forwardDetails = forwardDetails;
	}

	public Date getProductionStartDate() {
		return productionStartDate;
	}

	public void setProductionStartDate(Date productionStartDate) {
		this.productionStartDate = productionStartDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	
	public Date getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(Date conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public ShipmentTerm getShipmentTerm() {
		return shipmentTerm;
	}

	public void setShipmentTerm(ShipmentTerm shipmentTerm) {
		this.shipmentTerm = shipmentTerm;
	}

	public TransactionTerm getTransactionTerm() {
		return transactionTerm;
	}

	public void setTransactionTerm(TransactionTerm transactionTerm) {
		this.transactionTerm = transactionTerm;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getLandingPort() {
		return landingPort;
	}

	public void setLandingPort(String landingPort) {
		this.landingPort = landingPort;
	}

	public String getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getShipment() {
		return shipment;
	}

	public void setShipment(String shipment) {
		this.shipment = shipment;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getSupplierProformaDate() {
		return supplierProformaDate;
	}

	public void setSupplierProformaDate(Date supplierProformaDate) {
		this.supplierProformaDate = supplierProformaDate;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public boolean isRevenuePaid() {
		if (this.getComissionRevenue() == null)
			return false;
		return this.getComissionRevenue().isPaid();
	}
	
	public Date getCommisionPaymentDate() {
		if (!isRevenuePaid())
			return null;
		
		return this.getComissionRevenue().getLastPaymentDate();
	}

	public List<Complain> getComplains() {
		return complains;
	}
	
	public Double getCommisionValue() {
		Double result = 0.0;
		for (OrderItem i : this.itens){
			result += i.getCommisionValue();
		}
		return result;
	}

	public boolean isItemDuplicated(){
		List<Product> products = new ArrayList<Product>();
		for (OrderItem i : this.itens){
			if (products.contains(i.getProduct()))
				return true;
			products.add(i.getProduct());
		}
		return false;
	}

	public List<OrderSupplierPayment> getSupplierPayments() {
		return this.supplierPayments;
	}

	public List<OrderCommisionPayment> getCommisionPayments() {
		return this.comissionPayments ;
	}

	public boolean isProfit(){
		if (this.transactionTerm == null)
			return false;
		
		return (TransactionType.PROFIT.equals(this.transactionTerm.getTransactionType()));
	}

	public List<Revenue> getComissionRevenues() {
		return comissionRevenues;
	}

	public List<OrderHistory> getOrderHistories() {
		return orderHistories;
	}

	public String getDeliveryForecast() {
		return deliveryForecast;
	}

	public void setDeliveryForecast(String deliveryForecast) {
		this.deliveryForecast = deliveryForecast;
	}

}
