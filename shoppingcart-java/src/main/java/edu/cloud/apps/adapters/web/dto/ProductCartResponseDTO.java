package edu.cloud.apps.adapters.web.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class ProductCartResponseDTO {
	
	@JsonView({CartResponseDTO.Basic.class})
	ProductResponseDTO product;
	
	@JsonView({CartResponseDTO.Basic.class})
	Integer quantity;
	
	public interface Basic {
   	}
	
	public ProductCartResponseDTO() {
		super();
	}
	public ProductCartResponseDTO(ProductResponseDTO product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public ProductResponseDTO getProduct() {
		return product;
	}
	public void setProduct(ProductResponseDTO product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
