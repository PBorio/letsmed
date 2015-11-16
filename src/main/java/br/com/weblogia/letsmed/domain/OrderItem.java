package br.com.weblogia.letsmed.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;


@Entity
@Table(name="order_itens")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Product product;
	
	private Double quantity = 0.0;
	
	private Double unitPrice = 0.0;
	
	private String additionalInfo;
	
	private String additionalDescription;
	
	private Date deliveryDate;
	
	private Double buyPrice = 0.0;
	private Integer packageQuantity = 0;
	private Double grossWeight = 0.0;
	private Double netWeight = 0.0;
	private Double volume = 0.0;
	private Double commision = 0.0;
	
	@ManyToOne
	private UnitOfMeasure unitOfMeasure;
	
	public Double getTotalProducts(){
		if (this.quantity == null || this.unitPrice == null)
			return 0.0;
		return new Arredondamento().arredondar(this.quantity * this.unitPrice);
	}
	
	public Double getTotalBuyValue() {
		if (this.quantity == null || this.buyPrice == null)
			return 0.0;
		return new Arredondamento().arredondar(this.quantity * this.buyPrice);
	}
	
	public Double getTotalWithCommision(){
		if (this.quantity == null || this.unitPrice == null)
			return 0.0;
		
		if (this.commision != null){
			Double total = this.quantity * this.unitPrice;
			Double commisionValue = total * (commision/100);
			total += commisionValue;
			return new Arredondamento().arredondar(total);
		}
		
		return new Arredondamento().arredondar(this.quantity * this.unitPrice);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getPackageQuantity() {
		return packageQuantity;
	}

	public void setPackageQuantity(Integer packageQuantity) {
		this.packageQuantity = packageQuantity;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getAdditionalDescription() {
		return additionalDescription;
	}

	public void setAdditionalDescription(String additionalDescription) {
		this.additionalDescription = additionalDescription;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public Double getCommisionValue() {
		
		if (quantity == null || unitPrice == null || commision == null)
			return 0.0;
		
		return ((this.quantity * this.unitPrice) * (this.commision/100));
	}

	public String getCompleteDescription(){
		if (this.product == null)
			return this.additionalDescription;
		
		if (this.additionalDescription == null || "".equals(this.additionalDescription.trim()))
			return this.product.getDescription();
		
		return (this.product.getDescription()+" - "+this.additionalDescription);
	}
	
	

}
