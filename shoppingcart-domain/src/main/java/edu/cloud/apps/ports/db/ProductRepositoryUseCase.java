package edu.cloud.apps.ports.db;

import java.util.List;
import java.util.Optional;

import edu.cloud.apps.dto.ProductDTO;

public interface ProductRepositoryUseCase {

	List<ProductDTO> findAll();

	Optional<ProductDTO> findById(Long id);

	ProductDTO save(ProductDTO productDTO);

	Optional<ProductDTO> delete(Long id);

}
