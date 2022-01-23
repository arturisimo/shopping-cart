package edu.cloud.apps.ports.web;

import java.util.List;

import edu.cloud.apps.dto.ShoppingCartDTO;

public interface CartUseCase {
	
    List<ShoppingCartDTO> findAll();

    ShoppingCartDTO get(final Long id);

    ShoppingCartDTO create(final ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO finalizeShoppingCart(Long id);

    ShoppingCartDTO delete(final Long id, Long productId);

    ShoppingCartDTO addProduct(Long id, Long productId, Integer quantity);
	
}
