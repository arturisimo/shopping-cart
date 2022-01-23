package edu.cloud.apps.ports.db;

import java.util.List;
import java.util.Optional;

import edu.cloud.apps.dto.ShoppingCartDTO;

public interface CartRepositoryUseCase {
	
	List<ShoppingCartDTO> findAll();

	Optional<ShoppingCartDTO> findById(Long id);
	
	ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO);

	Optional<ShoppingCartDTO> delete(Long id, Long productId);
	
}
