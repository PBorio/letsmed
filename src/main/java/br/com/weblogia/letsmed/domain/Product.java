package br.com.weblogia.letsmed.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String articleNumber;
	
	private String description;
	
	private String differentOption1;
	
	private String differentOption2;
	
	private String differentOption3;
	
	private String differentOption4;

	@ManyToOne
	private ProductCategory productCategory;

	private Double buyPrice;

	private Double sellPrice;
	
	private Double commision;
	
	private String brandName;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne 
	private Supplier supplier;
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDifferentOption1() {
		return differentOption1;
	}

	public void setDifferentOption1(String differentOption1) {
		this.differentOption1 = differentOption1;
	}

	public String getDifferentOption2() {
		return differentOption2;
	}

	public void setDifferentOption2(String differentOption2) {
		this.differentOption2 = differentOption2;
	}

	public String getDifferentOption3() {
		return differentOption3;
	}

	public void setDifferentOption3(String differentOption3) {
		this.differentOption3 = differentOption3;
	}

	public String getDifferentOption4() {
		return differentOption4;
	}

	public void setDifferentOption4(String differentOption4) {
		this.differentOption4 = differentOption4;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public Double getCommision() {
		return commision;
	}

	@Override
	public int hashCode() {
		if (id == null) return super.hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) 
			return false;
		 if (!id.equals(other.id))
			return false;
		return true;
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
	

}
