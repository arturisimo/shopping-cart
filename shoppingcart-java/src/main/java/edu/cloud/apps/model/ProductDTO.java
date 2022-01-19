package edu.cloud.apps.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String brand;

    @NotNull
    private Integer stock;

    @NotNull
    private String name;    

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
