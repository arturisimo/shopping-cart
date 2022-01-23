package edu.cloud.apps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer stock;
    
    @Column(nullable = false)
    private String name;
    
    public Product() {
		super();
	}

	public Product(String brand, Integer stock, String name) {
		super();
		this.brand = brand;
		this.stock = stock;
		this.name = name;
	}

	public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(final Integer stock) {
        this.stock = stock;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
