package edu.cloud.apps.adapters.web.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import edu.cloud.apps.dto.ProductCartDTO;

public class CartResponseDTO {
	
	private Boolean finalized;
    
    private Set<ProductCartDTO> products;

    public Set<ProductCartDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductCartDTO> products) {
		this.products = products;
	}

	public CartResponseDTO() {
		super();
		this.finalized = Boolean.FALSE;
		this.products = new LinkedHashSet<>();
	}

	public Boolean getFinalized() {
        return finalized;
    }

    public void setFinalized(final Boolean finalized) {
        this.finalized = finalized;
    }
	
	
}
