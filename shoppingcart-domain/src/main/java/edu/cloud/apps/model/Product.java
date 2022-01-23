package edu.cloud.apps.model;

import edu.cloud.apps.exception.StockException;

public class Product {

    private Long id;

    private String brand;

    private Integer stock;
    
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
    	if (stock < 0) throw new StockException(name);
        this.stock = stock;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}