package edu.cloud.apps.adapters.web;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cloud.apps.adapters.web.dto.CartResponseDTO;
import edu.cloud.apps.dto.ShoppingCartDTO;
import edu.cloud.apps.ports.web.CartUseCase;

@Service
public class CartAdapter {
	
	@Autowired
	private CartUseCase cartUseCase;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CartResponseDTO> findAll() {
        return cartUseCase.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CartResponseDTO get(final Long id) {
        return toResponse(cartUseCase.get(id));
    }

    public CartResponseDTO create(final ShoppingCartDTO shoppingCartDTO) {
        return toResponse(cartUseCase.create(shoppingCartDTO));
    }

    public CartResponseDTO finalizeShoppingCart(Long id) {
       return toResponse(cartUseCase.finalizeShoppingCart(id));
	}

	public CartResponseDTO delete(final Long id, Long productId) {
    	return toResponse(cartUseCase.delete(id, productId));
    }

	public CartResponseDTO addProduct(Long id, Long productId, Integer quantity) {
		return toResponse(cartUseCase.addProduct(id, productId, quantity));
	}	
	
	private CartResponseDTO toResponse(ShoppingCartDTO cart) {
		return modelMapper.map(cart, CartResponseDTO.class);
	}
}
