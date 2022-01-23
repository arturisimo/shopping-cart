package edu.cloud.apps.adapters.web.dto;

public class ProductResponseDTO {

    private String brand;

    private Integer stock;

    private String name;    

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public ProductResponseDTO() {
		super();
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