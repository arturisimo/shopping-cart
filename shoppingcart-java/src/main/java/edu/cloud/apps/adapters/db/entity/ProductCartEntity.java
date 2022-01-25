package edu.cloud.apps.adapters.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="product_cart")
public class ProductCartEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    
    @ManyToOne
    private CartEntity cart;
    
    public ProductCartEntity() {
		super();
	}

	public ProductCartEntity(ProductEntity product, Integer quantity) {
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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}
    

}
