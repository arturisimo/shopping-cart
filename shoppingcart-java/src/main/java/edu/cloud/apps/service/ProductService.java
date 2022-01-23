package edu.cloud.apps.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.cloud.apps.domain.Product;
import edu.cloud.apps.model.ProductDTO;
import edu.cloud.apps.repos.ProductRepository;
import edu.cloud.apps.repos.ShoppingCartRepository;

@Service
public class ProductService {

	@Autowired
	private ModelMapper modelMapper;

	private final ProductRepository productRepository;

	public ProductService(final ProductRepository productRepository,
			final ShoppingCartRepository shoppingCartRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public ProductDTO get(final Long id) {
		return productRepository.findById(id).map(this::toDto)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public Product create(final ProductDTO productDTO) {
		return productRepository.save(toEntity(productDTO));
	}

	public void delete(final Long id) {
		productRepository.deleteById(id);
	}

	public void updateStock(Long id, Integer quantity) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		product.setStock(product.getStock() - quantity);
		productRepository.save(product);
	}

	private ProductDTO toDto(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}

	private Product toEntity(ProductDTO productDTO) {
		return modelMapper.map(productDTO, Product.class);
	}

}
