package edu.cloud.apps.dto;

public class ProductCartDTO {
	
	ProductDTO product;
	
	Integer quantity;
	
	Long cartId;
	
	public interface Basic {
   	}
	
	public ProductCartDTO() {
		super();
	}
	public ProductCartDTO(Long cartId, ProductDTO product, Integer quantity) {
		super();
		this.cartId = cartId;
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
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long carId) {
		this.cartId = carId;
	}
	
	@Override
	public String toString() {
		return "ProductCartDTO [product=" + product + ", quantity=" + quantity + ", cartId=" + cartId + "]";
	}
	
}
