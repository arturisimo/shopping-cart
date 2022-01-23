package edu.cloud.apps.ports.web;

import java.util.List;

import edu.cloud.apps.dto.ProductDTO;

public interface ProductUseCase {
	
	List<ProductDTO> findAll();

	ProductDTO get(final Long id);

	ProductDTO create(final ProductDTO productDTO);

	ProductDTO delete(final Long id);

	ProductDTO updateStock(Long id, Integer quantity);
	
}
