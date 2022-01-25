package edu.cloud.apps.ports.web;

import java.util.List;

import edu.cloud.apps.dto.CartDTO;

public interface CartUseCase {
	
    List<CartDTO> findAll();

    CartDTO get(final Long id);

    CartDTO create(final CartDTO shoppingCartDTO);
    
    CartDTO remove(Long id);
    
    CartDTO finalizeShoppingCart(Long id);

    CartDTO removeProduct(final Long id, Long productId);

    CartDTO addProduct(Long id, Long productId, Integer quantity);
	
}
