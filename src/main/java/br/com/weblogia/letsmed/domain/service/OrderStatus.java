package br.com.weblogia.letsmed.domain.service;

import java.util.Date;

public class OrderStatus {

	private String description;
	private String title;
	private String lastMovement;
	private Date statusDate;
	private String url;

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLastMovement(String lastMovement) {
		this.lastMovement = lastMovement;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public String getLastMovement() {
		return lastMovement;
	}

	public void setStatusDate(Date lastMovementDate) {
		this.statusDate = lastMovementDate;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
