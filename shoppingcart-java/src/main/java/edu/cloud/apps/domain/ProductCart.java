package edu.cloud.apps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;
    
    @OneToOne(fetch=FetchType.EAGER) 
    private Product product;
    
    public ProductCart() {
		super();
	}

	public ProductCart(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
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

    

}
