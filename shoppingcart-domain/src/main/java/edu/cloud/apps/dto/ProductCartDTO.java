package edu.cloud.apps.dto;

public class ProductCartDTO {
	
	ProductDTO product;
	Integer quantity;
	
	public ProductCartDTO() {
		super();
	}
	public ProductCartDTO(ProductDTO product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}
