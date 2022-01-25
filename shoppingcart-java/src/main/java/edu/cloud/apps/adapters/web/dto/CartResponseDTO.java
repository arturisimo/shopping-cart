package edu.cloud.apps.adapters.web.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

public class CartResponseDTO {
	
    private Long id;
	
    @JsonView({Basic.class})
	private Boolean finalized;
    
    @JsonView({Basic.class})
    private Set<ProductCartResponseDTO> products;
    
    public interface Basic {
   	}
    
    public Set<ProductCartResponseDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductCartResponseDTO> products) {
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
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
