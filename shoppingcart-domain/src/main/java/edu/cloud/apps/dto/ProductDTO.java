package edu.cloud.apps.dto;

public class ProductDTO {

    public ProductDTO() {
		super();
	}

	private Long id;

    private String brand;

    private Integer stock;

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

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", brand=" + brand + ", stock=" + stock + ", name=" + name + "]";
	}
	
	
	
}
