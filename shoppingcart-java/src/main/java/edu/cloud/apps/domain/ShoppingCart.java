package edu.cloud.apps.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Boolean finalized;
    
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
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

}
