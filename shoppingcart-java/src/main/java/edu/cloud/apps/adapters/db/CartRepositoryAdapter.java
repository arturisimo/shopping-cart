package edu.cloud.apps.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import edu.cloud.apps.adapters.db.entity.CartEntity;
import edu.cloud.apps.dto.CartDTO;
import edu.cloud.apps.ports.db.CartRepositoryUseCase;
import edu.cloud.apps.repository.CartRepository;

@Service
public class CartRepositoryAdapter implements CartRepositoryUseCase {
	
	CartRepository cartRepository;
	
	ModelMapper modelMapper;
	
	public CartRepositoryAdapter(CartRepository cartRepository, ModelMapper modelMapper) {
		super();
		this.cartRepository = cartRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<CartDTO> findAll() {
		return cartRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<CartDTO> findById(Long id) {
		return cartRepository.findById(id).map(this::toDto);
	}

	@Override
	public CartDTO save(CartDTO cartDTO) {
		return toDto(cartRepository.save(toEntity(cartDTO)));
	}

	@Override
	public Optional<CartDTO> deleteById(Long id) {
		Optional<CartDTO> cart = cartRepository.findById(id).map(this::toDto);
		cart.ifPresent(p -> cartRepository.deleteById(id));
		return cart;
	}
	
	private CartDTO toDto(CartEntity cart) {
		return modelMapper.map(cart, CartDTO.class);
	}

	private CartEntity toEntity(CartDTO cartDTO) {
		CartEntity CartEntity = modelMapper.map(cartDTO, CartEntity.class);
		CartEntity.getProducts().forEach(product-> product.setCart(CartEntity));
		return CartEntity;
	}
	
}
