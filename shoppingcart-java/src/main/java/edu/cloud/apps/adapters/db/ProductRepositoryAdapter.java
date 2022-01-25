package edu.cloud.apps.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import edu.cloud.apps.adapters.db.entity.ProductEntity;
import edu.cloud.apps.dto.ProductDTO;
import edu.cloud.apps.ports.db.ProductRepositoryUseCase;
import edu.cloud.apps.repository.ProductRepository;

@Service
public class ProductRepositoryAdapter implements ProductRepositoryUseCase {
	
	ProductRepository productRepository;

	ModelMapper modelMapper;
	
	public ProductRepositoryAdapter(ProductRepository productRepository, ModelMapper modelMapper) {
		super();
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public Optional<ProductDTO> findById(final Long id) {
		return productRepository.findById(id).map(this::toDto);
	}

	public ProductDTO save(final ProductDTO productDTO) {
		return toDto(productRepository.save(toEntity(productDTO)));
	}

	public Optional<ProductDTO> delete(final Long id) {
		Optional<ProductDTO> product = findById(id);
		product.ifPresent(p -> productRepository.deleteById(id));
		return product;
	}

	private ProductDTO toDto(ProductEntity product) {
		return modelMapper.map(product, ProductDTO.class);
	}

	private ProductEntity toEntity(ProductDTO productDTO) {
		return modelMapper.map(productDTO, ProductEntity.class);
	}


	
}
