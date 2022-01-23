package edu.cloud.apps.adapters.web;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cloud.apps.adapters.web.dto.ProductRequestDTO;
import edu.cloud.apps.adapters.web.dto.ProductResponseDTO;
import edu.cloud.apps.dto.ProductDTO;
import edu.cloud.apps.ports.web.ProductUseCase;

@Service
public class ProductAdapter {
	
	@Autowired
	private ProductUseCase productUseCase;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ProductResponseDTO> findAll() {
		return productUseCase.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	public ProductResponseDTO get(final Long id) {
		return toResponse(productUseCase.get(id));
	}

	public ProductResponseDTO create(final ProductRequestDTO productRequestDTO) {
		return toResponse(productUseCase.create(toDto(productRequestDTO)));
	}

	public ProductResponseDTO delete(final Long id) {
		return toResponse(productUseCase.delete(id));
	}

	public ProductResponseDTO updateStock(Long id, Integer quantity) {
		return toResponse(productUseCase.updateStock(id, quantity));
	}
	
	private ProductResponseDTO toResponse(ProductDTO product) {
		return modelMapper.map(product, ProductResponseDTO.class);
	}
	private ProductDTO toDto(ProductRequestDTO product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
}