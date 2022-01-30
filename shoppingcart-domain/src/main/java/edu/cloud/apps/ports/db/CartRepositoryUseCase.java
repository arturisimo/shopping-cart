package edu.cloud.apps.ports.db;

import java.util.List;
import java.util.Optional;

import edu.cloud.apps.dto.CartDTO;

public interface CartRepositoryUseCase {
	
	List<CartDTO> findAll();

	Optional<CartDTO> findById(Long id);
	
	CartDTO save(CartDTO CartDTO);

	Optional<CartDTO> deleteById(Long id);

}