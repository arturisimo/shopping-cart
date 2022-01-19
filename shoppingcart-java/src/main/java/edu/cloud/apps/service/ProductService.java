package edu.cloud.apps.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.cloud.apps.domain.Product;
import edu.cloud.apps.model.ProductDTO;
import edu.cloud.apps.repos.ProductRepository;
import edu.cloud.apps.repos.ShoppingCartRepository;


@Service
public class ProductService {

    private final ProductRepository productRepository;
   
    public ProductService(final ProductRepository productRepository,
            final ShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setBrand(product.getBrand());
        productDTO.setName(product.getName());
        productDTO.setStock(product.getStock());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setBrand(productDTO.getBrand());
        product.setName(productDTO.getName());
        product.setStock(productDTO.getStock());
        return product;
    }

	public void updateStock(Long id, Integer quantity) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		product.setStock(product.getStock() - quantity);
		productRepository.save(product);
	}

}

