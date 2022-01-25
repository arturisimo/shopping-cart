package edu.cloud.apps.adapters.web.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class ProductResponseDTO {

    private Long id;
	
    @JsonView({Basic.class, CartResponseDTO.Basic.class})
	private String brand;

    @JsonView({Basic.class, CartResponseDTO.Basic.class})
    private Integer stock;

    @JsonView({Basic.class, CartResponseDTO.Basic.class})
    private String name;    
    
    public interface Basic {
	}
    
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}

}