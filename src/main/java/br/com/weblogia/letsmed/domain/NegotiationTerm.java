package br.com.weblogia.letsmed.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.weblogia.letsmed.domain.tipos.NegotiationType;

@Entity
@Table(name="negotiation_terms")
public class NegotiationTerm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@Enumerated(EnumType.ORDINAL)
	private NegotiationType negotiationType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NegotiationType getNegotiationType() {
		return negotiationType;
	}

	public void setNegotiationType(NegotiationType negotiationType) {
		this.negotiationType = negotiationType;
	}

}
