package edu.cloud.apps.model;

import edu.cloud.apps.exception.StockException;

public class ProductCart {

    private Long id;

    private Integer quantity;
    
    private Product product;
    
	public ProductCart() {
		super();
	}

	public ProductCart(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		this.validateStock();
	}

	public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void validateStock() {
		Integer newStock = product.getStock() - getQuantity();
		if (newStock < 0)
			throw new StockException(product.getName());
	}
    

}
