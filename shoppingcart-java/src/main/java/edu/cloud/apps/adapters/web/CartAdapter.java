package edu.cloud.apps.adapters.web;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cloud.apps.adapters.web.dto.CartRequestDTO;
import edu.cloud.apps.adapters.web.dto.CartResponseDTO;
import edu.cloud.apps.dto.CartDTO;
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

    public CartResponseDTO create(final CartRequestDTO cartRequestDTO) {
        return toResponse(cartUseCase.create(toModel(cartRequestDTO)));
    }

    public CartResponseDTO finalizeShoppingCart(Long id) {
       return toResponse(cartUseCase.finalizeShoppingCart(id));
	}

	public CartResponseDTO deleteProduct(final Long id, Long productId) {
    	return toResponse(cartUseCase.removeProduct(id, productId));
    }
	
	public CartResponseDTO delete(Long id) {
		return toResponse(cartUseCase.remove(id));
	}
	
	public CartResponseDTO addProduct(Long id, Long productId, Integer quantity) {
		return toResponse(cartUseCase.addProduct(id, productId, quantity));
	}	
	
	private CartResponseDTO toResponse(CartDTO cart) {
		return modelMapper.map(cart, CartResponseDTO.class);
	}
	
	private CartDTO toModel(CartRequestDTO cart) {
		return modelMapper.map(cart, CartDTO.class);
	}

}
