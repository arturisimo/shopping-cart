package edu.cloud.apps.dto;

import java.util.LinkedHashSet;
import java.util.Set;

public class CartDTO {

    private Long id;

    private Boolean finalized;
    
    private Set<ProductCartDTO> products;

    public Set<ProductCartDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductCartDTO> products) {
		this.products = products;
	}

	public CartDTO() {
		super();
		this.finalized = Boolean.FALSE;
		this.products = new LinkedHashSet<>();
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
