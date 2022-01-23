package edu.cloud.apps; 

import java.util.List;

import org.modelmapper.ModelMapper;

import edu.cloud.apps.dto.ProductDTO;
import edu.cloud.apps.model.Product;
import edu.cloud.apps.ports.db.ProductRepositoryUseCase;
import edu.cloud.apps.ports.web.ProductUseCase;

public class ProductUseCaseImpl implements ProductUseCase {
	
	private ProductRepositoryUseCase productRepository;
	private ModelMapper modelMapper;

	public ProductUseCaseImpl(ProductRepositoryUseCase productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<ProductDTO> findAll() {
		return productRepository.findAll();
	}

	@Override
	public ProductDTO get(Long id) {
		return productRepository.findById(id).orElseThrow();
	}

	@Override
	public ProductDTO create(ProductDTO productDTO) {
		return productRepository.save(productDTO);
	}

	@Override
	public ProductDTO delete(Long id) {
		return productRepository.delete(id).orElseThrow();
	}

	@Override
	public ProductDTO updateStock(Long id, Integer quantity) {
		ProductDTO productDTO = productRepository.findById(id).orElseThrow();
		Product product = toModel(productDTO);
		product.setStock(product.getStock() - quantity);
		return productRepository.save(toDto(product));
	}

	
	private ProductDTO toDto(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	private Product toModel(ProductDTO productDTO) {
		return modelMapper.map(productDTO, Product.class);
	}
	
}
