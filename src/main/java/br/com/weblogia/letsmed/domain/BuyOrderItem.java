package br.com.weblogia.letsmed.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="buy_order_itens")
public class BuyOrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private BuyOrder buyOrder;
	
	@ManyToOne
	private Product product;
	
	private Integer quantity = 0;
	
	private Double unitPrice = 0.0;
	
	private String additionalInfo;
	
	public Double getTotalValue(){
		return this.quantity * this.unitPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BuyOrder getBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(BuyOrder buyOrder) {
		this.buyOrder = buyOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
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


}
