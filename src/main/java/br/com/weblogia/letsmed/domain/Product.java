package br.com.weblogia.letsmed.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	

}
