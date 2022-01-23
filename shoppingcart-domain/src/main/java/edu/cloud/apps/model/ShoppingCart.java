package edu.cloud.apps.model;

import java.util.Set;

import edu.cloud.apps.exception.StockException;

public class ShoppingCart {

    private Long id;

    private Boolean finalized;
    
    private Set<ProductCart> products;

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

	public Set<ProductCart> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductCart> products) {
		this.products = products;
	}
	
	public ShoppingCart validate() {
		products.forEach(productCart -> {
			Product product = productCart.getProduct();
			Integer newStock = product.getStock() - productCart.getQuantity();
			if (newStock < 0) {
				throw new StockException(product.getName());
			} else {
				product.setStock(newStock);
			}
		});
		return this;
	}
	
	
	
}
