package edu.cloud.apps.adapters.web.dto;

public class CartRequestDTO {

	private Long id;

	private Boolean finalized;

	public CartRequestDTO() {
		super();
		this.finalized = Boolean.FALSE;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Boolean getFinalized() {
		return finalized;
	}

	public void setFinalized(final Boolean finalized) {
		this.finalized = finalized;
	}

}
