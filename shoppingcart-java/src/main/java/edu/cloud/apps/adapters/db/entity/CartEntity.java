package edu.cloud.apps.adapters.db.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="shoppingcart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Boolean finalized;
    
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<ProductCartEntity> products;

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

	public Set<ProductCartEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductCartEntity> products) {
		this.products = products;
	}

}
